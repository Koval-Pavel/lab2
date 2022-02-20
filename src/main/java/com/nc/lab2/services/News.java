package com.nc.lab2.services;

import java.util.ArrayList;
import java.util.List;

import static com.nc.lab2.controller.NewsController.quantityOfNews;

/**
 * Class with model of result News
 */
public class News {

    /** list of country for news */
    private String[] country = new String[Integer.parseInt(quantityOfNews) + (Integer.parseInt(quantityOfNews) == 0? 2: 0)];

    /** list of category for news */
    private String[] category = new String[Integer.parseInt(quantityOfNews) + (Integer.parseInt(quantityOfNews) == 0? 2: 0)];

    /** info message */
    private String infoMessage;

    /** list of article for news */
    private List<Article> articleList = new ArrayList<>();


    /** constructor without param's */
    public News() {
        super();
    }

    /** constructor with param */
    public News(String infoMessage, List<Article> articleList) {

        this.infoMessage = infoMessage;
        this.articleList = articleList;

    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
