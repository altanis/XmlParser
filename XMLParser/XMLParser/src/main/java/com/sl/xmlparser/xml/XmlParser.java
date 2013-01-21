package com.sl.xmlparser.xml;

import com.sl.xmlparser.config.Configuration;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlParser {

    private static final Logger logger = Logger.getLogger(XmlParser.class.getCanonicalName());
    private Configuration configuration;

    public XmlParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public Document parse(URL url) throws DocumentException {
        logger.log(Level.INFO, "Building Document from URL: {0}", url);
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        List<Node> nodes = document.selectNodes(this.configuration.getMainXmlElementXPath());
        for (Node projectNode : nodes) {
            System.out.println("Project Id: " + getTextFromNode(configuration.getProjectIdXPath(), projectNode));
            System.out.println("Project Name: " + getTextFromNode(configuration.getProjectNameXPath(), projectNode));            
        }

        return document;
    }

    private String getTextFromNode(String xPath, Node node) {
        if (xPath == null || xPath.isEmpty()) {
            throw new IllegalArgumentException("XPath must not be null");
        }

        if (node == null) {
            throw new IllegalArgumentException("Node must not be null");
        }

        Node n = node.selectSingleNode(xPath);
        if (n != null) {
            return n.getText();
        }
        
        logger.log(Level.WARNING, "Could not find matching XML Node for XPath: {0}", xPath);
        return null;
    }
}
