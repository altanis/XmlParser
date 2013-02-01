package com.sl.xmlparser;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.config.ConfirurationParser;
import com.sl.xmlparser.csv.CsvWriter;
import com.sl.xmlparser.model.ProjectModel;
import com.sl.xmlparser.xml.XmlParser;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kohsuke.args4j.CmdLineException;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());
    private static final ConfirurationParser parser = new ConfirurationParser(new Configuration());
    private static final XmlParser xmlParser = new XmlParser(parser.getConfiguration());

    public static void main(String[] args) throws Exception {
        try {

            args = new String[] {"projekty_offline_mod.xml"};
            logger.info("Starting application, parsing configuration");

            parser.parseConfiguration(args);

            logger.log(Level.INFO, "XML File for parsing {0}", parser.getConfiguration().getInputXmlFile());

            URL xmlFile = Thread.currentThread().getContextClassLoader().getResource(parser.getConfiguration().getInputXmlFile());
            
            List<ProjectModel> parsedModels = xmlParser.parse(xmlFile);

            CsvWriter csvWriter = new CsvWriter(parser.getConfiguration());
            csvWriter.write(parsedModels);
            





//            

//            Node cosignerNode = document.selectSingleNode("/Account/Cosigner");
//            System.out.println(cosignerNode.selectSingleNode("ContactInfo/Name").getText());



//            new ImageResizer().resizeAndSave(new URI("https://www.google.pl/logos/2013/grandparents_day_2013-1014005-hp.png"), new File("./test.jpg"), 640, 480);



        } catch (CmdLineException e) {
            parser.printUsage(System.out);
            parser.printExample(System.out);
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
