package com.sl.xmlparser.config;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class Configuration {
    
    @Option(name = "--xml-main-element")
    private String mainXmlElementXPath = "/projekty/projekt";
    
    @Option(name = "--xml-project-name")
    private String projectNameXPath = "nazwa";
    
    @Option(name = "--xml-full-description")
    private String fullDescriptionXPath = "opis";
    
    @Option(name = "--xml-short-description")
    private String shortDescriptionXPath = "skrot";
    
    @Option(name = "--xml-category")
    private String categoryXPath = "kategoria";
    
    @Option(name = "--xml-price-with-vat")
    private String priceWithVatXPath = "ceny/@brutto";
    
    @Option(name = "--xml-img-main")
    private String imgMainXPath = "zdjecia//zdjecie[@nazwa='zdjecie']/@plik";
    
    @Option(name = "--xml-img-projection")
    private String imgProjectionXPath = "kondygnacje//kondygnacja/@rzut";
    
    @Option(name = "--xml-img-elevation")
    private String imgElevationXPath = "elewacje//elewacja/@zdjecie";
    
    @Option(name = "--xml-img-location")
    private String imgLocationXPath = "zdjecia/zdjecie[@nazwa='teren']/@plik";
    
    @Option(name = "--xml-usable-space")
    private String usableSpaceXPath = "powierzchnie/@uzytkowa";
    
    @Option(name = "--xml-floors")
    private String floorsXPath = "concat(kondygnacje//kondygnacja[1]/@nazwa, ',', kondygnacje//kondygnacja[2]/@nazwa, ',', kondygnacje//kondygnacja[3]/@nazwa)";
    
    @Option(name = "--xml-garage")
    private String garageXPath = "ilosc_stanowisk_garazowych";
    
    @Option(name = "--xml-technology")
    private String technologyXPath = "rodzaj";
    
    @Option(name = "--xml-roof-type")
    private String roofTypeXPath = "rodzaj_dachu";
    
    @Option(name = "--xml-minimum-plot")
    private String minimumPlotXPath = "concat(/wymiary/wymiar[@min-szerokosc-dzialki], ' x ', /wymiary/wymiar[@min-dlugosc-dzialki])";
    
    @Option(name = "--xml-built-in-area")
    private String builtInAreaXPath = "powierzchnie/powierzchnia[@zabudowy]";
    
    @Option(name = "--xml-volume")
    private String volumeXPath = "kubatura[@calkowita]";
    
    @Option(name = "--xml-building-height")
    private String buildingHeightXPath = "wymiary/wmiar[@nazwa=wysokosc]";
    
    @Option(name = "--xml-slope-of-the-roof")
    private String slopeOfTheRoofXPath = "wymiary/wmiar[@nazwa=kat-dachu]";
    
    @Option(name = "--xml-basement")
    private String basementXPath;
    
    @Option(name = "--constant-amount")
    private Integer amountConstant = 99;
    
    @Option(name = "--constant-active")
    private Integer active = 1;
    
    @Option(name = "--constant-img-uri-prefix")
    private String uriPrefixConstant = "http://localhost/";
    
    @Argument
    private List<String> arguments = new ArrayList<String>();

    public String getMainXmlElementXPath() {
        return mainXmlElementXPath;
    }

    public void setMainXmlElementXPath(String mainXmlElementXPath) {
        this.mainXmlElementXPath = mainXmlElementXPath;
    }

    public String getProjectNameXPath() {
        return projectNameXPath;
    }

    public void setProjectNameXPath(String projectNameXPath) {
        this.projectNameXPath = projectNameXPath;
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

    public String getCategoryXPath() {
        return categoryXPath;
    }

    public void setCategoryXPath(String categoryXPath) {
        this.categoryXPath = categoryXPath;
    }

    public String getPriceWithVatXPath() {
        return priceWithVatXPath;
    }

    public void setPriceWithVatXPath(String priceWithVatXPath) {
        this.priceWithVatXPath = priceWithVatXPath;
    }

    public String getImgMainXPath() {
        return imgMainXPath;
    }

    public void setImgMainXPath(String imgMainXPath) {
        this.imgMainXPath = imgMainXPath;
    }

    public String getImgProjectionXPath() {
        return imgProjectionXPath;
    }

    public void setImgProjectionXPath(String imgProjectionXPath) {
        this.imgProjectionXPath = imgProjectionXPath;
    }

    public String getImgElevationXPath() {
        return imgElevationXPath;
    }

    public void setImgElevationXPath(String imgElevationXPath) {
        this.imgElevationXPath = imgElevationXPath;
    }

    public String getImgLocationXPath() {
        return imgLocationXPath;
    }

    public void setImgLocationXPath(String imgLocationXPath) {
        this.imgLocationXPath = imgLocationXPath;
    }

    public String getUsableSpaceXPath() {
        return usableSpaceXPath;
    }

    public void setUsableSpaceXPath(String usableSpaceXPath) {
        this.usableSpaceXPath = usableSpaceXPath;
    }

    public String getFloorsXPath() {
        return floorsXPath;
    }

    public void setFloorsXPath(String floorsXPath) {
        this.floorsXPath = floorsXPath;
    }

    public String getGarageXPath() {
        return garageXPath;
    }

    public void setGarageXPath(String garageXPath) {
        this.garageXPath = garageXPath;
    }

    public String getTechnologyXPath() {
        return technologyXPath;
    }

    public void setTechnologyXPath(String technologyXPath) {
        this.technologyXPath = technologyXPath;
    }

    public String getRoofTypeXPath() {
        return roofTypeXPath;
    }

    public void setRoofTypeXPath(String roofTypeXPath) {
        this.roofTypeXPath = roofTypeXPath;
    }

    public String getMinimumPlotXPath() {
        return minimumPlotXPath;
    }

    public void setMinimumPlotXPath(String minimumPlotXPath) {
        this.minimumPlotXPath = minimumPlotXPath;
    }

    public String getBuiltInAreaXPath() {
        return builtInAreaXPath;
    }

    public void setBuiltInAreaXPath(String builtInAreaXPath) {
        this.builtInAreaXPath = builtInAreaXPath;
    }

    public String getVolumeXPath() {
        return volumeXPath;
    }

    public void setVolumeXPath(String volumeXPath) {
        this.volumeXPath = volumeXPath;
    }

    public String getBuildingHeightXPath() {
        return buildingHeightXPath;
    }

    public void setBuildingHeightXPath(String buildingHeightXPath) {
        this.buildingHeightXPath = buildingHeightXPath;
    }

    public String getSlopeOfTheRoofXPath() {
        return slopeOfTheRoofXPath;
    }

    public void setSlopeOfTheRoofXPath(String slopeOfTheRoofXPath) {
        this.slopeOfTheRoofXPath = slopeOfTheRoofXPath;
    }

    public String getBasementXPath() {
        return basementXPath;
    }

    public void setBasementXPath(String basementXPath) {
        this.basementXPath = basementXPath;
    }

    public Integer getAmountConstant() {
        return amountConstant;
    }

    public void setAmountConstant(Integer amountConstant) {
        this.amountConstant = amountConstant;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer aktywny) {
        this.active = aktywny;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
    
    public String getUriPrefixConstant() {
        return uriPrefixConstant;
    }

    public void setUriPrefixConstant(String uriPrefixConstant) {
        this.uriPrefixConstant = uriPrefixConstant;
    }
}
