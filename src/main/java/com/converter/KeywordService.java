package com.converter;

/**
 * A {@code KeywordService} that always return the keywords "rubicon,project".
 * <p>
 * Copyright @ 2016 Rubicon Project, All rights reserved.
 */
public class KeywordService<T extends Object> {

    public String resolveKeywords(T site) {
        return "rubicon,project";
    }
}
