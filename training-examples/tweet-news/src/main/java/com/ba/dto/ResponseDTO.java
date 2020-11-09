package com.ba.dto;

public class ResponseDTO {

    private String status;
    private int totalResults;
    private ArticleDTO[] articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArticleDTO[] getArticles() {
        return articles;
    }

    public void setArticles(ArticleDTO[] articles) {
        this.articles = articles;
    }
}
