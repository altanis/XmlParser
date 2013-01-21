package com.sl.xmlparser.config;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class Configuration {

    @Option(name = "--main-element")
    private String mainXmlElementXPath = "/projekty/projekt";
    @Option(name = "--project-id")
    private String projectIdXPath = "id";
    @Option(name = "--project-name")
    private String projectNameXPath = "nazwa";
    @Option(name = "--project-price")
    private String priceXPath = "cena";
    @Option(name = "--full-description")
    private String fullDescriptionXPath = "opis_pelny";
    @Option(name = "--short-description")
    private String shortDescriptionXPath = "opis_krotki";
    @Argument
    private List<String> arguments = new ArrayList<String>();

    public String getMainXmlElementXPath() {
        return mainXmlElementXPath;
    }

    public void setMainXmlElementXPath(String mainXmlElementXPath) {
        this.mainXmlElementXPath = mainXmlElementXPath;
    }

    public String getProjectIdXPath() {
        return projectIdXPath;
    }

    public void setProjectIdXPath(String projectIdXPath) {
        this.projectIdXPath = projectIdXPath;
    }

    public String getProjectNameXPath() {
        return projectNameXPath;
    }

    public void setProjectNameXPath(String projectNameXPath) {
        this.projectNameXPath = projectNameXPath;
    }

    public String getPriceXPath() {
        return priceXPath;
    }

    public void setPriceXPath(String priceXPath) {
        this.priceXPath = priceXPath;
    }

    public String getFullDescriptionXPath() {
        return fullDescriptionXPath;
    }

    public void setFullDescriptionXPath(String fullDescriptionXPath) {
        this.fullDescriptionXPath = fullDescriptionXPath;
    }

    public String getShortDescriptionXPath() {
        return shortDescriptionXPath;
    }

    public void setShortDescriptionXPath(String shortDescriptionXPath) {
        this.shortDescriptionXPath = shortDescriptionXPath;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Configuration:\n");

        sb.append("--main-element").append(mainXmlElementXPath).append("\n");
        sb.append("--project-id ").append(projectIdXPath).append("\n");
        sb.append("--project-name ").append(projectNameXPath).append("\n");
        sb.append("--project-price ").append(priceXPath).append("\n");
        sb.append("--full-description ").append(fullDescriptionXPath).append("\n");
        sb.append("--short-description ").append(shortDescriptionXPath).append("\n");
        sb.append("arguments ").append(arguments);

        return sb.toString();
    }
}
