package com.web.crawler.validator;

import com.web.crawler.reader.TextFileReader;
import com.web.crawler.reader.iFileReader;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


/**
 * @author Chaklader at 15/10/20
 */
public class TextDataValidatorService extends DataValidatorService {
    
    private final UrlValidator urlValidator;
    
    public TextDataValidatorService(UrlValidator urlValidator) {
        this.urlValidator = urlValidator;
    }
    
    @Override
    protected iFileReader getFileReaderInstance() {
        return new TextFileReader();
    }
    
    @Override
    public List<String> validateAndRetrieveAllURLs(File file) throws FileNotFoundException {
        iFileReader iFileReader = getFileReaderInstance();
        
        List<String> urls = iFileReader.read(file);
        urls.removeIf(url -> !urlValidator.isValid(url));
        
        return urls;
    }
}
