package com.example.helloworld;

public class MatchData {

    private String matchName;
    private int matchImage;

    public MatchData(String matchName, int matchImage) {
        this.matchName = matchName;
        this.matchImage = matchImage;
    }

    public String getMatchName() {
        return matchName;
    }

    public int getMatchImage() {
        return matchImage;
    }
}
