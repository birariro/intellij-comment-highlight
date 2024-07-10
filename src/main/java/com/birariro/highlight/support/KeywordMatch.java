package com.birariro.highlight.support;

import org.apache.commons.lang.StringUtils;

public class KeywordMatch {
    private final String SINGLE_COMMENT_PREFIX = "//";
    private final String MULTI_COMMENT_PREFIX = "/***";
    public boolean matches(String text, String keyword){

        String connectedText = StringUtils.deleteWhitespace(text);

        if(connectedText.startsWith(keyword)){
            return true;
        }
        if(connectedText.startsWith(SINGLE_COMMENT_PREFIX+keyword)){
            return true;
        }
        if(connectedText.startsWith(MULTI_COMMENT_PREFIX+keyword)){
            return true;
        }
        return false;
    }

}
