    
    
    
    Home assignment - simple web crawler
    
    Goal:
    
    Write a program that accepts a filename as an argument, and does the following:
    
    Read the file - each line should be a URL
    
    Visit each URL, and scan it for links (anchors, <a> tags)
    
    Prints all the links it finds to stdout (each URL in a separate line)
    
    No need to visit the extracted links
    
    
    Notes:
    
    The assignment should be implemented in Java or Kotlin.
    
    The required deliverable is a zip file containing the source code and a README file with instructions for building and running the crawler.
    
    You may use any 3rd party library you see fit, provided that:
    
    It is not a complete web crawler library/framework.
    
    You include the libraries using Maven or Gradle
    
    The solutions will be evaluated based on the following criteria:
    
    A correct working implementation of the requirements
    
    Code quality (clean, elegant, readable, and maintainable code)
    
    Handling of invalid input and other edge-cases
    
    
    Bonus 1:
    
    Scan the URLs in parallel, to increase speed
    
    Bonus 2:
    
    Also, scan resulting URLs - only one level depth. Only scan URLs that point to the same site (host) which you are currently scanning.
    
    For instance -
    
    If the input file contains the URL www.cats.org/projects, and this page contains the following links :
    
    www.cats.org/mew
    
    www.chaos.com
    
    Then you should also scan www.cats.org/mew, but not www.chaos.com
    
