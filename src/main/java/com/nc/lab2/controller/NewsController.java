package com.nc.lab2.controller;

import com.nc.lab2.handlerJSON.handlerJSON;
import com.nc.lab2.services.News;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static com.nc.lab2.Lab2Application.log;


/**
 * Class for Controller for News.
 * @author  Pavel Koval
 */
@RestController
public class NewsController {

    /** field for determination of Quantity of selected news category*/
    public static String quantityOfNews = "0";

    /** Massive of selected news country*/
    private String[] country1 = new String[]{"def"};

    /** Massive of selected news category*/
    private String[] category1 = new String[]{"def"};


    /**
     * Method that get list with news and passes them to resultNews.jsp page
     * @return ModelAndView object with list news.
     */
    @GetMapping(value = "/news")
    public ModelAndView news() {
        ModelAndView modelAndView = new ModelAndView("newsView/resultNews");
        country1[0] = country1[0].equals("def") ? "ua": country1[0];
        String result = getJSONFromSource();
        News currentNews = new News();
        handlerJSON.parseCurrentNewsJsonMultiple(result, currentNews);
        modelAndView.addObject("currentNews", currentNews);
        News newsForCheck = new News();
        modelAndView.addObject("command", newsForCheck);
        return modelAndView;
    }

    /**
     * Method that realize possibility of choosing required country and category for check news
     * @param news new object of News filled with param's from resultNews.jsp
     * @return ModelAndView redirection to news()
     */
    @RequestMapping(value = "/checkNews", method = RequestMethod.POST)
    public ModelAndView checkNews (@ModelAttribute News news) {
        if (country1.length != Integer.parseInt(quantityOfNews) ) {

            int qOfNews = Integer.parseInt(quantityOfNews) == 0 ? 1 : Integer.parseInt(quantityOfNews);
            country1 = new String[qOfNews];
            category1 = new String[qOfNews];
            for (int i = 0; i < country1.length; i++) {
                country1[i] = "def";
                System.out.println("country = " + country1[i] + ";");
            }
            for (int i = 0; i < category1.length; i++) {
                category1[i] = "def";
                System.out.println("category = " + category1[i] + ";");
            }
        }
        for (int i = 0; i < country1.length; i++) {
            country1[i] = news.getCountry()[i].equals("")  ? "def": news.getCountry()[i];
            category1[i] = news.getCategory()[i].equals("") ? "def": news.getCategory()[i];
            System.out.println("from check cao=" + country1[i]);
            System.out.println("from check cat=" + category1[i]);
        }
        log.info("Country for news chosen:" + country1.toString());
        log.info("Category for news chosen:" + category1.toString());
        return new ModelAndView("redirect:/news");
    }

    /**
     * Method that get JSON from web service (lab3)
     * @return JSON in String type.
     */
    public String getJSONFromSource () {
//        String uri = "http://localhost:8787/lab3_dev_war_exploded/newsjson?"; // for local
        String uri = "http://app3:8787/newsjson?"; // for docker

        for (int i = 0; i < country1.length; i++) {
            uri = uri + "country" + "=" + country1[i] + "&category" + "=" +category1[i];
            if (i != (country1.length - 1)) { uri = uri + "&";}
        }
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(uri, String.class);
        } catch (HttpServerErrorException e) {
            return null;
        }
    }
}
