package com.converter;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.converter.SiteProcessor;

/**
 * A {@code SiteCollector} that is main class the whole application.
 * @author seungchan
 *
 */
public class SiteCollector {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		if (!validateArgs(args)) {
			System.out.println("Usage: Two Arguments are required, [PathToDirectory] [outputFile]");
			return;
		}
		
		String pathToDir = args[0];
		String outputFile = args[1];
		System.out.println("Starting site collection...");
		SiteProcessor processor = new SiteProcessor(pathToDir, outputFile);
		processor.run();
		System.out.println("Finishing site collection...");
	}
	
	public static boolean validateArgs(String args[]) {
		return (args.length >= 2);
	}
}
