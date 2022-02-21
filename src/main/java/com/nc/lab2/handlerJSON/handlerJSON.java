package com.nc.lab2.handlerJSON;

import com.nc.lab2.services.Article;
import com.nc.lab2.services.News;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.nc.lab2.Lab2Application.log;
import static com.nc.lab2.controller.NewsController.quantityOfNews;

/**
 * Class that create model based on JSON format
 */
public class handlerJSON {

    /** Info Message */
    private final static String JSONEXC = "INFO MESSAGE: JSONException, problem with parsing";

    /**
     * Method that parse JSON and formed Object on them.
     * @param resultJson - incoming JSON file
     * @param news - incoming model for fiiling with params.
     * @return Object of News
     */
    public static News parseCurrentNewsJsonMultiple(String resultJson, News news) {
        try {
            JSONObject newsJsonObject = new JSONObject(resultJson);
            JSONArray articlesArray = (JSONArray) newsJsonObject.get("articles");
            for (Object items : articlesArray) {
                JSONArray arrayJSON1 = (JSONArray) items;
                for (Object items1 : arrayJSON1) {
                    JSONObject firstArtical = (JSONObject) items1;
                    JSONObject articlesSource = (JSONObject) firstArtical.get("source");
                    Article article = new Article();
                    article.setSourceName((articlesSource.get("name")).toString());
                    article.setPublishedAt(firstArtical.get("publishedAt").toString());
                    article.setAuthor(firstArtical.get("author").toString());
                    article.setUrlToImage(firstArtical.get("urlToImage").toString());
                    article.setDescription(firstArtical.get("description").toString());
                    article.setTitle(firstArtical.get("title").toString());
                    article.setUrl(firstArtical.get("url").toString());
                    news.getArticleList().add(article);
                }
            }
            news.setInfoMessage(newsJsonObject.get("infoMessage").toString());
            quantityOfNews = (newsJsonObject.get("quantityOfNews").toString());
            System.out.println("quantity of news=" + quantityOfNews);
        } catch (JSONException | NullPointerException e) {
            log.warn(JSONEXC);
            news.setInfoMessage((JSONEXC));
        }
        return news;
    }
}
