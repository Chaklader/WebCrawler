package com.web.crawler.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Chaklader at 15/10/20
 */
public interface iFileReader {
    public List<String> read(File file) throws FileNotFoundException;
}
