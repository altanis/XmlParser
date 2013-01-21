package com.sl.xmlparser.xml;

import java.net.URL;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlParser {
    
    private static final Logger logger = Logger.getLogger(XmlParser.class.getCanonicalName());
    
    public Document parse(URL url) throws DocumentException {
        logger.info("Building Document from URL: " + url);
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }
    
}
