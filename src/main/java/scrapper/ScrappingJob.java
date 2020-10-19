package scrapper;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * @author Chaklader at 15/10/20
 */
public interface ScrappingJob {
    void scrap(String url) throws IOException, URISyntaxException;
}
