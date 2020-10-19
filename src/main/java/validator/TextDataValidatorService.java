package validator;

import org.apache.commons.validator.routines.UrlValidator;
import reader.TextFileReader;
import reader.iFileReader;

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
        
        urls.removeIf(s -> !urlValidator.isValid(s));
        return urls;
    }
}
