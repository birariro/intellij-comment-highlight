package com.birariro.highlight.support;


import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;

public class ColorsTest  {
    private Colors colors = new Colors();

    @Test
    public void stringToColorTest(){
        String blueStr = colors.colorToString(new Color(35, 45, 241));
        Assert.assertEquals("#232df1", blueStr);

        String redStr = colors.colorToString(new Color(245, 37, 100));
        Assert.assertEquals("#f52564", redStr);

        String greenStr = colors.colorToString(new Color(192, 252, 200));
        Assert.assertEquals("#c0fcc8", greenStr);
    }
}