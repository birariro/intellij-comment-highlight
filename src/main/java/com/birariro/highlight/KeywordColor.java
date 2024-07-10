package com.birariro.highlight;

public class KeywordColor {

    private final String keyword;
    private final String color;

    public KeywordColor(String keyword, String color) {
        this.keyword = keyword;
        this.color = color;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KeywordColor that = (KeywordColor) o;

        if (!keyword.equals(that.keyword)) {
            return false;
        }
        return color.equals(that.color);
    }
}
