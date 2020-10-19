package com.web.crawler.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chaklader at 15/10/20
 */
public class TextFileReader implements iFileReader {
    
    @Override
    public List<String> read(File file) {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
