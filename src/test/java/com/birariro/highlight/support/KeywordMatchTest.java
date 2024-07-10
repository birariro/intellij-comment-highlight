package com.birariro.highlight.support;


import org.junit.Assert;
import org.junit.Test;

public class KeywordMatchTest {

    private KeywordMatch sut = new KeywordMatch();

    @Test
    public void should_true_then_single_comment(){

        String keyword = "given";
        String comment = "// given";

        boolean matches = sut.matches(comment, keyword);
        Assert.assertTrue(matches);
    }

    @Test
    public void should_true_then_multi_comment(){

        String keyword = "given";
        String comment = "/**" +
                            "* given" +
                            "**/";

        boolean matches = sut.matches(comment, keyword);
        Assert.assertTrue(matches);
    }

    @Test
    public void should_false_then_not_keyword(){

        String keyword = "when";
        String singleComment = "// given";
        String multiComment = "/**" +
                "* given" +
                "**/";

        Assert.assertFalse(sut.matches(singleComment, keyword));
        Assert.assertFalse(sut.matches(multiComment, keyword));
    }

    @Test
    public void should_false_then_keyword_but_notStartsWith(){

        String keyword = "given";
        String doubleComment = "// // given";
        String notStringKeywordComment = "// test given";

        Assert.assertFalse(sut.matches(doubleComment, keyword));
        Assert.assertFalse(sut.matches(notStringKeywordComment, keyword));
    }

}