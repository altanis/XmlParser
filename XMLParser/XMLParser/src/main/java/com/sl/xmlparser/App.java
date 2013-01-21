package com.sl.xmlparser;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.config.ConfirurationParser;
import com.sl.xmlparser.image.ImageResizer;
import com.sl.xmlparser.xml.XmlParser;
import java.io.File;
import java.net.URI;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.Node;
import org.kohsuke.args4j.CmdLineException;

public class App {

    private static final ConfirurationParser parser = new ConfirurationParser(new Configuration());

    private static final XmlParser xmlParser = new XmlParser();
    
    public static void main(String[] args) throws Exception {
        try {
            URL testXml = Thread.currentThread().getContextClassLoader().getResource("test.xml");
            
            Document document = xmlParser.parse(testXml);
            
            Node cosignerNode = document.selectSingleNode("/Account/Cosigner");
            System.out.println(cosignerNode.selectSingleNode("ContactInfo/Name").getText());
            
            
            
            new ImageResizer().resizeAndSave(new URI("https://www.google.pl/logos/2013/grandparents_day_2013-1014005-hp.png"), new File("./test.jpg"), 640, 480);
            
            parser.parseConfiguration(args);
            
        } catch (CmdLineException e) {
            parser.printUsage(System.out);
            parser.printExample(System.out);
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
