    
    
    
    Simple web crawler
    ------------------
    
    Write a program that accepts a filename as an argument, and does the following:
    
        Read the file - each line should be a URL
        
        Visit each URL, and scan it for links (anchors, <a> tags)
        
        Prints all the links it finds to stdout (each URL in a separate line)
        
        No need to visit the extracted links
    
   
    RUN THE APP 
    -----------
    
        Please, use the commands to run the JAR file from the project root:
        
        mvn clean install && mvn clean package
        java -Xmx700m -jar target/WebCrawler-1.0-SNAPSHOT-jar-with-dependencies.jar 
        
        
