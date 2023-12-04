package project.kakaochatanalyzer.upload.Controller;

import java.util.List;

public class Keyword {
    private String totalMessage;
    private List<String> keywords;
    public Keyword() {
    }

    // 생성자 추가
    public Keyword(List<String> keywords) {
        this.keywords = keywords;
    }
    public String getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(String totalMessage) {
        this.totalMessage = totalMessage;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
