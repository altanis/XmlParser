package com.sl.xmlparser;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.config.ConfirurationParser;
import com.sl.xmlparser.csv.CsvWriter;
import com.sl.xmlparser.image.ImageResizer;
import com.sl.xmlparser.model.ProjectModel;
import com.sl.xmlparser.xml.XmlParser;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kohsuke.args4j.CmdLineException;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());

    public static void main(String[] args) throws Exception {
        logger.info("Starting application, parsing configuration");

        ConfirurationParser parser = new ConfirurationParser(new Configuration());
        XmlParser xmlParser = new XmlParser(parser.getConfiguration());

        Long time = System.currentTimeMillis();

        try {
            parser.parseConfiguration(args);

            logger.log(Level.INFO, "XML File for parsing {0}", parser.getConfiguration().getInputXmlFile());

            URL xmlFile = Thread.currentThread().getContextClassLoader().getResource(parser.getConfiguration().getInputXmlFile());

            List<ProjectModel> parsedModels = xmlParser.parse(xmlFile);

            CsvWriter csvWriter = new CsvWriter(parser.getConfiguration());
            csvWriter.write(parsedModels);

            ImageResizer imgResizer = new ImageResizer(parser.getConfiguration());
            imgResizer.resizeImages(parsedModels);

            time = (System.currentTimeMillis() - time) / 1000;

            logger.log(Level.INFO, "XML File for parsing {0} finnished in {1}", new Object[]{parser.getConfiguration().getInputXmlFile(), time});

        } catch (CmdLineException e) {
            parser.printUsage(System.out);
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
