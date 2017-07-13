package com.converter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

import com.converter.Site;
import com.converter.SiteDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test case for {@code SiteDeserializer}
 * @author seungchan
 *
 */
public class SiteDeserializerTest {
	
	@Test
	public void deserialize() throws JsonParseException, IOException {
		String jsonString = "{\"site_id\": \"13000\", \"name\": \"example.com/json1\", \"mobile\": 1, \"score\": 21}";
		ObjectMapper mapper = new ObjectMapper();
        JsonParser parser = mapper.getFactory().createParser(jsonString);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        SiteDeserializer deserializer = new SiteDeserializer();
        Site site = deserializer.deserialize(parser, ctxt);
        
        assertEquals(site.getId(), "13000");
        assertEquals(site.getName(), "example.com/json1");
        assertEquals(site.getMobile(), 1);
        assertEquals(site.getScore(), "21"); 
	}
	
}
