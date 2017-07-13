package com.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * A {@code SiteDeserializer} that deserialize the json data to Java object.
 * @author seungchan
 *
 */
public class SiteDeserializer extends StdDeserializer<Site> {
	
	private static final long serialVersionUID = 1L;
	
	private final static KeywordService<Site> keywordService = new KeywordService<Site>();
	
	public SiteDeserializer() { 
        this(null); 
    } 
 
    public SiteDeserializer(Class<Site> vc) { 
        super(vc); 
    }

	@Override
	public Site deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
        Site site = new Site();
		site.setId(node.get(SiteConstants.SITE_ID).asText());
        site.setName(node.get(SiteConstants.NAME).asText());
		site.setMobile(node.get(SiteConstants.MOBILE).asInt());
        site.setScore(node.get(SiteConstants.SCORE).asText());
        site.setKeywords(keywordService.resolveKeywords(site));
              
        return site;
	}
    
    
}
