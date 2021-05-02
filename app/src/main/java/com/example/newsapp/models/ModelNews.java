package com.example.newsapp.models;

public class ModelNews {
    private String urlToImage;
    private String title;
    private String publishedAt;
    private String description;
    private String url;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDescription() {
        return description;
    }
}
