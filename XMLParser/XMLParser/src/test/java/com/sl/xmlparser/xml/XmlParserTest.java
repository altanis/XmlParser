package com.sl.xmlparser.xml;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.model.ProjectModel;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

public class XmlParserTest {

    private static final URL mtmXml = Thread.currentThread().getContextClassLoader().getResource("projekty_offline_mod.xml");
    
    private Configuration testedConfiguration;
    private XmlParser xmlParser;
    
    @Before
    public void setup() {
        testedConfiguration = new Configuration();
        xmlParser = new XmlParser(testedConfiguration);
    }
    
    @Test
    public void testMtMParser() throws Exception {
        assertNotNull(mtmXml);
        
        List<ProjectModel> projectModelList  = xmlParser.parse(mtmXml);
        assertEquals(1, projectModelList.size());
        
        ProjectModel testedModel = projectModelList.get(0);
        
        assertNotNull(testedModel);
        
        assertEquals("Sopran 3 (Y)", testedModel.getProjectName());
        assertEquals("Wolno stojący dom jednorodzinny, niepodpiwniczony. Powiększona o dodatkowe stanowisko garażowe wersja domu –SOPRAN 2–. Dom ekonomiczny w budowie i utrzymaniu. Prosty, czytelny, funkcjonalny układ. Budynek można podpiwniczyć.", testedModel.getFullDescription());
        assertEquals("Wolno stojący dom jednorodzinny, niepodpiwniczony. Powiększona o dodatkowe stanowisko garażowe wersja domu –SOPRAN 2–. Dom ekonomiczny w budowie i utrzymaniu. Prosty, czytelny, funkcjonalny układ. Bud", testedModel.getShortDescription());
        assertEquals("Projekty domów z poddaszem użytkowym", testedModel.getCategory());
        assertEquals("Projekty domów z poddaszem użytkowym", testedModel.getCategoryForSearcher());
        assertEquals(new BigDecimal("2520.00"), testedModel.getPriceWithVat());
        assertEquals(Integer.valueOf(99), testedModel.getAmount());
        assertEquals(Integer.valueOf(1), testedModel.getActive());
        assertEquals(new BigDecimal("145.7"), testedModel.getUsableSpace());
        assertEquals("od 130m2 - 160m2", testedModel.getUsableSpace2());
        assertEquals("Parterowe z poddaszem", testedModel.getFloors());
        assertEquals("Parterowe z poddaszem", testedModel.getFloors2());
        assertEquals("Dwustanowiskowy", testedModel.getGarage());
        assertEquals("Dwustanowiskowy", testedModel.getGarage2());
        assertEquals("Tradycyjna", testedModel.getTechnology());
        assertEquals("Tradycyjna", testedModel.getTechnology2());
        assertEquals("Wielospadowy", testedModel.getRoofType());
        assertEquals("Wielospadowy", testedModel.getRoofType2());
        assertEquals("20.39 x 21.34 m", testedModel.getMinimumPlot());
        assertEquals("do 30m", testedModel.getMinimumPlot2());
        assertEquals("124 m2", testedModel.getBuildInArea());
        assertEquals("690 m3", testedModel.getVolume());
        assertEquals("8.5 m", testedModel.getBuildingHeight());
        assertEquals("45°", testedModel.getSlopeOfTheRoof());
        assertNull(testedModel.getBasement());
        
        assertTrue(testedModel.getImgMain().contains("aga_duzy_0.jpg"));
        assertTrue(testedModel.getImgMain().contains("aga_duzy_1.jpg"));

        assertTrue(testedModel.getImgProjection().contains("aga_rz1.jpg"));
        assertTrue(testedModel.getImgProjection().contains("aga_rz2.jpg"));
        
        assertTrue(testedModel.getImgElevation().contains("aga_elewacja_1.jpg"));
        assertTrue(testedModel.getImgElevation().contains("aga_elewacja_2.jpg"));
        assertTrue(testedModel.getImgElevation().contains("aga_elewacja_3.jpg"));
        
        assertTrue(testedModel.getImgLocation().contains("aga_sytuacja.jpg"));
    }
    
}
