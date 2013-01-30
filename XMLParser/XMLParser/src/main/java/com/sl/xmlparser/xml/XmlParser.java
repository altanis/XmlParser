package com.sl.xmlparser.xml;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.model.ProjectModel;
import com.sl.xmlparser.model.decorator.MtmModelDecorator;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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

    public List<ProjectModel> parse(URL url) throws DocumentException {
        logger.log(Level.INFO, "Building Document from URL: {0}", url);
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        List<ProjectModel> parsedModels = new ArrayList<ProjectModel>();


        List<Node> nodes = document.selectNodes(this.configuration.getMainXmlElementXPath());
        for (Node projectNode : nodes) {
            ProjectModel model = MtmModelDecorator.decorateModel(new ProjectModel(), configuration);
            model.setProjectName(projectNode.valueOf(configuration.getProjectNameXPath()));
            model.setShortDescription(projectNode.valueOf(configuration.getShortDescriptionXPath()));
            model.setFullDescription(projectNode.valueOf(configuration.getFullDescriptionXPath()));
            model.setCategory(projectNode.valueOf(configuration.getCategoryXPath()));
            model.setPriceWithVat(projectNode.valueOf(configuration.getPriceWithVatXPath()));
            model.setImgMain(getUri(projectNode, configuration.getImgMainXPath(), configuration.getUriPrefixConstant()).toArray(new URI[0]));
            model.setImgElevation(getUri(projectNode, configuration.getImgElevationXPath(), configuration.getUriPrefixConstant()).toArray(new URI[0]));
            model.setImgProjection(getUri(projectNode, configuration.getImgProjectionXPath(), configuration.getUriPrefixConstant()).toArray(new URI[0]));
            model.setImgLocation(getUri(projectNode, configuration.getImgLocationXPath(), configuration.getUriPrefixConstant()).toArray(new URI[0]));
            model.setUsableSpace(projectNode.valueOf(configuration.getUsableSpaceXPath()));
            model.setFloors(projectNode.valueOf(configuration.getFloorsXPath()));
            model.setGarage(projectNode.valueOf(configuration.getGarageXPath()));
            model.setTechnology(projectNode.valueOf(configuration.getTechnologyXPath()));

            parsedModels.add(model);
            System.out.println("Project = " + model);
        }

        return parsedModels;
    }

    private List<URI> getUri(Node parentNode, String xPath, String uriPrefix) {
        List<URI> ret = new ArrayList<URI>();

        for (Object n : parentNode.selectNodes(xPath)) {
            if (n instanceof Node) {
                try {
                    Node node = (Node) n;
                    StringBuilder uriBuilder = new StringBuilder();
                    uriBuilder.append(uriPrefix);
                    uriBuilder.append(node.getText());
                    logger.log(Level.INFO, "Parsed URI: {0}", uriBuilder.toString());
                    ret.add(new URI(uriBuilder.toString()));
                } catch (URISyntaxException ex) {
                    logger.log(Level.SEVERE, "Error during parsing URI occured", ex);
                }
            }
        }

        return ret;
    }
}
