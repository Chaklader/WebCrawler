package com.web.crawler.scrapper;

import org.apache.commons.validator.routines.UrlValidator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.web.crawler.parameter.Parameters.*;

/**
 * @author Chaklader at 15/10/20
 */
public class HtmlScrapper {
    
    private static final Logger LOGGER = Logger.getLogger(HtmlScrapper.class.getName());
    
    private final UrlValidator urlValidator;
    
    public HtmlScrapper(UrlValidator urlValidator) {
        this.urlValidator = urlValidator;
    }
    
    public Set<String> scrapLinksByURLFromHTML(String url, Set<String> links, String domain) throws IOException {
        if (links.size() > LINK_COLLECTION_LINK) {
            return links;
        }
        
        String HTMLPage = getStringFromUrl(url);
        Pattern linkPattern = Pattern.compile(LINK_FINDER_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher pageMatcher = linkPattern.matcher(HTMLPage);
        
        while (pageMatcher.find()) {
            
            String linkFromURL = pageMatcher.group(1);
            if (links.contains(linkFromURL) || !urlValidator.isValid(linkFromURL)) {
                continue;
            }
            
            LOGGER.info(linkFromURL);
            links.add(linkFromURL);
            
            if (linkFromURL.contains(domain)) {
                return scrapLinksByURLFromHTML(linkFromURL, links, domain);
            }
        }
        return links;
    }
    
    public String getStringFromUrl(String url) throws IOException {
        URL siteURL = new URL(url);
        return inputStreamToString(urlToInputStream(siteURL, null));
    }
    
    public String inputStreamToString(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
    
            return result.toString();
        }
    }
    
    private InputStream urlToInputStream(URL url, Map<String, String> args) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        
        try {
    
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            if (args != null) {
                for (Map.Entry<String, String> e : args.entrySet()) {
                    httpURLConnection.setRequestProperty(e.getKey(), e.getValue());
                }
            }
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 400 && responseCode > 299) {
                String redirectUrl = httpURLConnection.getHeaderField("Location");
                try {
                    URL newUrl = new URL(redirectUrl);
                    return urlToInputStream(newUrl, args);
                } catch (MalformedURLException e) {
                    URL newUrl = new URL(url.getProtocol() + "://" + url.getHost() + redirectUrl);
                    return urlToInputStream(newUrl, args);
                }
            }
            
            inputStream = httpURLConnection.getInputStream();
            return inputStream;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
