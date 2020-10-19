package com.web.crawler.scrapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Chaklader at 15/10/20
 */
public class HtmlScrappingJob implements ScrappingJob {
    
    public HtmlScrapper htmlScrapper;
    
    public HtmlScrappingJob(HtmlScrapper htmlScrapper) {
        this.htmlScrapper = htmlScrapper;
    }
    
    @Override
    public void scrap(String url) throws IOException, URISyntaxException {
        scrap(url, new HashSet<>());
    }
    
    private void scrap(String url, Set<String> l) throws IOException, URISyntaxException {
        htmlScrapper.scrapLinksByURLFromHTML(url, l, getDomainName(url));
    }
    
    private static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
