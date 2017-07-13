package com.converter;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.converter.Site;
import com.converter.SiteProcessor;
import com.converter.Sites;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Test case for {@code SiteProcessor}
 * @author seungchan
 *
 */
public class SiteProcessorTest {
	
	private File dataDirectory = new File("src/test/data");
	private File outDirectory = new File("src/test/data/out");
	private File csvFile;
	private File jsonFile;
	private String outFileName = "output.json";
	private SiteProcessor sp = new SiteProcessor(dataDirectory.getAbsolutePath(), "");
	
	@Before
	public void init() {
		sp = new SiteProcessor(dataDirectory.getAbsolutePath(), outDirectory.getAbsolutePath() + File.separator + outFileName);
		File[] files = sp.getFiles();
		csvFile = getCSV(files);
		jsonFile = getJSON(files);
	}
	
	@After
	public void finalize() {
		File[] files = outDirectory.listFiles();
		for (File file : files) {
			file.delete();
		}
	}
	
	@Test
	public void getFiles() {
		File[] files = sp.getFiles();
		assertEquals(2, files.length);
	}
	
	@Test
	public void processCSV() throws JsonGenerationException, JsonMappingException, IOException {
		Sites sites = sp.processCSV(csvFile.getAbsolutePath());
		assertEquals(csvFile.getName(), sites.getCollectionId());
		assertEquals(3, sites.getSites().size());
		assertCSVSite(sites);
	}
	
	@Test
	public void processJSON() throws JsonGenerationException, JsonMappingException, IOException {
		Sites sites = sp.processJSON(jsonFile.getAbsolutePath());
		assertEquals(jsonFile.getName(), sites.getCollectionId());
		assertEquals(3, sites.getSites().size());
		assertJSONSite(sites);
	}
	
	@Test
	public void writeJSON() throws IOException {
		Sites sites = sp.processJSON(jsonFile.getAbsolutePath());
		sp.writeJSON(sites);
		
		File[] files = outDirectory.listFiles();
		assertEquals(1, files.length);
		// TODO assert output file
	}
	
	private File getCSV(File[] files) {
		for (File file : files) {
			if (file.getAbsolutePath().endsWith("csv"))
				return file;
		}
		return null;
	}
	
	private File getJSON(File[] files) {
		for (File file : files) {
			if (file.getAbsolutePath().endsWith("json"))
				return file;
		}
		return null;
	}
	
	private void assertCSVSite(Sites sites) {
		List<Site> list = sites.getSites();
		Site site = list.get(0);
		assertEquals("12000", site.getId());
		assertEquals("example.com/csv1", site.getName());
		assertEquals(1, site.getMobile());
		assertEquals("454", site.getScore());
		
	}
	
	private void assertJSONSite(Sites sites) {
		List<Site> list = sites.getSites();
		Site site = list.get(0);
		assertEquals("13000", site.getId());
		assertEquals("example.com/json1", site.getName());
		assertEquals(1, site.getMobile());
		assertEquals("21", site.getScore());
	}
}
