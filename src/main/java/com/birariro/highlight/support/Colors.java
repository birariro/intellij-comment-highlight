package com.birariro.highlight.support;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colors {
    public Color stringToColor(String str){
        String regex = "#([0-9a-fA-F]{2})([0-9a-fA-F]{2})([0-9a-fA-F]{2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            int red = Integer.parseInt(matcher.group(1), 16);
            int green = Integer.parseInt(matcher.group(2), 16);
            int blue = Integer.parseInt(matcher.group(3), 16);
            return new Color(red, green, blue);
        } else {
            throw new IllegalArgumentException("Invalid color format");
        }
    }
    public String colorToString(Color color){
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
