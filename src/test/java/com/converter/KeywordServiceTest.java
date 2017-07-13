package com.converter;

import org.junit.Test;

import com.converter.KeywordService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test Case for {@code KeywordService}
 * <p>
 * Copyright @ 2016 Rubicon Project, All rights reserved.
 */
public class KeywordServiceTest {

    @Test
    public void resolvesKeywords() {
        KeywordService<Object> constantKeywordService = new KeywordService<>();
        assertThat(constantKeywordService.resolveKeywords("any"), is("rubicon,project"));
    }

}