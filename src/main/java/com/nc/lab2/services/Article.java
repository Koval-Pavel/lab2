package com.nc.lab2.services;

/**
 * Class with model of Article.
 */
public class Article {

    /** field with name of country for News*/
    private String country;

    /** field with name of category for News*/
    private String category;

    /** field with publisher for News*/
    private String publishedAt;

    /** field with name of author for News*/
    private String author;

    /** field with url with image for News*/
    private String urlToImage;

    /** field with description for News*/
    private String description;

    /** field with name of source for News*/
    private String sourceName;

    /** field with title for News*/
    private String title;

    /** field with url for News*/
    private String url;

    /**
     * constructor without param's
     */
    public Article() {
        super();
    }

    /** conctructor with params */
    public Article(String country, String category, String publishedAt, String author, String urlToImage, String description, String sourceName, String title, String url) {
        this.country = country;
        this.category = category;
        this.publishedAt = publishedAt;
        this.author = author;
        this.urlToImage = urlToImage;
        this.description = description;
        this.sourceName = sourceName;
        this.title = title;
        this.url = url;

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", author='" + author + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", description='" + description + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
