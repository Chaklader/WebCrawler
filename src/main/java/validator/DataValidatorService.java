package validator;

import reader.iFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Chaklader at 15/10/20
 */
public abstract class DataValidatorService {
    
    public abstract List<String> validateAndRetrieveAllURLs(File file) throws FileNotFoundException;
    protected abstract iFileReader getFileReaderInstance();
}
