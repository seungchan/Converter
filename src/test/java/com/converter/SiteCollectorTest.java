package com.converter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.converter.SiteCollector;

/**
 * Test case for {@code SiteCollector}
 * @author seungchan
 *
 */
public class SiteCollectorTest {
	
	@Test
	public void validateArgs() {
		String[] args = {"C:\\Temp", "C:\\Input"};		
		assertTrue(SiteCollector.validateArgs(args));
	}
}
