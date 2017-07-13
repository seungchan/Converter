package com.converter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A {@code SiteProcessor} that does actual work for the application.
 * Supported format is csv or json format. It parse the file and write to the output file.
 * @author seungchan
 *
 */
public class SiteProcessor {
	private static final KeywordService<Site> keywordService = new KeywordService<Site>();
	
	private String pathToDir;
	private String outputFile;
	
	public SiteProcessor() {
	}
	
	public SiteProcessor(String path, String output) {
		this.pathToDir = path;
		this.outputFile = output;
	}
	
	public void run() {
		File[] files = getFiles();
		if (getFiles() == null) {
			System.out.println("There is no file to process.");
			return;
		}
		 
        for (File f : files) {
            System.out.println("Processing a file " + f.getAbsolutePath());
            String filename = f.getAbsolutePath();
            if (filename.endsWith(".csv")) {
            	try {
            		writeJSON(processCSV(filename));
				} catch (IOException e) {
					System.out.println("There is an error while processing csv file " + filename);
				}
            } else {
            	try {
            		writeJSON(processJSON(filename));
				} catch (IOException e) {
					System.out.println("There is an error while processing json file " + filename);
				}
            }
        }
	}
	
	protected File[] getFiles() {
		FileFilter filefilter = (file) -> {return (file.getName().endsWith(".csv") || file.getName().endsWith(".json"));};

		File sourceDir = new File(pathToDir);
		if(!sourceDir.isDirectory()){
			System.out.println("Directory does not exists : " + pathToDir);
			return null;
		}
		
		return sourceDir.listFiles(filefilter);
	}
	
	protected Sites processCSV(String filename) throws JsonGenerationException, JsonMappingException, IOException {
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',').withEscape('/').withIgnoreEmptyLines();
		CSVParser parser = null;
		
		List<Site> sites = new ArrayList<Site>();
		try {
			parser = new CSVParser(new FileReader(filename), format);
			//parser = CSVParser.parse(new File(filename), Charset.forName("UTF-8"), format);
			
			for(CSVRecord record : parser){
				Site site = new Site();
				site.setId(record.get(SiteConstants.ID));
				site.setName(record.get(SiteConstants.NAME));
				String isMobile = record.get(SiteConstants.IS_MOBILE);
				if (isMobile != null && isMobile.equals("true")) {
					site.setMobile(1);
				} else {
					site.setMobile(0);
				}
				site.setScore(record.get(SiteConstants.SCORE));
				site.setKeywords(keywordService.resolveKeywords(site));
				sites.add(site);
			}	
		} catch (IOException e) {
			System.out.println("There is an error when parsing CSV file " + filename + ".");
		} finally {
			if (parser != null) {
				try {
					parser.close();
				} catch (IOException e) {
					System.out.println("CSV parser did not closed properly.");
				}
			}
		}
		
		Sites colSites = new Sites();
		Path p = Paths.get(filename);
		colSites.setCollectionId(p.getFileName().toString());
		colSites.setSites(sites);
		
		return colSites;
	}
	
	protected Sites processJSON(String filename) throws IOException {
		byte[] jsonData = Files.readAllBytes(Paths.get(filename));
        ObjectMapper objectMapper = new ObjectMapper();
        
		List<Site> sites2 = objectMapper.readValue(jsonData, new TypeReference<List<Site>>(){});

		Sites colSites = new Sites();
		Path p = Paths.get(filename);
		colSites.setCollectionId(p.getFileName().toString());
		colSites.setSites(sites2);
		
		return colSites;
	}
	
	protected void writeJSON(Sites sites) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        // Write to output file
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outputFile, true);
			objectMapper.writeValue(fos, sites);
			fos.write(System.getProperty("line.separator").getBytes());
		} catch (IOException e) {
			System.out.println("There is an error when writing to output file " + outputFile + ".");
		} finally {
			try {
				if (fos != null) {
					fos.flush();
					fos.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
	}
}
