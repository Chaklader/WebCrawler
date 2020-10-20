package com.web.crawler;

import com.web.crawler.scrapper.HtmlScrapper;
import com.web.crawler.scrapper.HtmlScrappingJob;
import com.web.crawler.validator.DataValidatorService;
import com.web.crawler.validator.TextDataValidatorService;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Chaklader at 15/10/20
 */
public class WebCrawlerApp {
    
    private static final Logger LOGGER = Logger.getLogger(WebCrawlerApp.class.getName());
    private static final String FILE_LOCATION = "src/main/resources/urls.txt";
    
    private static DataValidatorService service;
    private static HtmlScrapper htmlScrapper;
    
    
    public static void main(String[] args) throws IOException {
        init();
        
        List<String> urls = service.validateAndRetrieveAllURLs(new File(FILE_LOCATION));
        HtmlScrappingJob job = new HtmlScrappingJob(htmlScrapper);
        
        LOGGER.info("Starting the link scrapping job from the provided URL");
        scrapLinksAndLogParallel(urls, job);
    }
    
    private static void init() {
        final UrlValidator urlValidator = new UrlValidator();
        
        service = new TextDataValidatorService(urlValidator);
        htmlScrapper = new HtmlScrapper(urlValidator);
        
        LOGGER.info("Initiated the required object instances for the web crawling");
    }
    
    private static void scrapLinksAndLogParallel(List<String> urls, HtmlScrappingJob job) {
        urls.parallelStream().forEach(url ->
                {
                    try {
                        job.scrap(url);
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    } finally {
                        LOGGER.info("Accomplished the link scrapping job from the provided URL");
                    }
                }
        );
    }
}
