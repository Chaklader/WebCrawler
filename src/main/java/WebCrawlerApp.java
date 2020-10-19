import org.apache.commons.validator.routines.UrlValidator;
import scrapper.HtmlScrapper;
import scrapper.HtmlScrappingJob;
import validator.DataValidatorService;
import validator.TextDataValidatorService;

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
    
    public static void main(String[] args) throws IOException {
        
        UrlValidator urlValidator = new UrlValidator();
        DataValidatorService service = new TextDataValidatorService(urlValidator);
        List<String> urls = service.validateAndRetrieveAllURLs(new File(FILE_LOCATION));
        
        HtmlScrapper htmlScrapper = new HtmlScrapper(urlValidator);
        HtmlScrappingJob job = new HtmlScrappingJob(htmlScrapper);
        
        LOGGER.info("Starting the link scrapping job from the provided URL");
        scrapLinksAndLogParallel(urls, job);
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
