package com.sl.xmlparser.xml;

import com.sl.xmlparser.config.Configuration;
import com.sun.security.auth.login.ConfigFile;
import java.net.URL;
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
    public void testParser() throws Exception {
        assertNotNull(mtmXml);
        
        xmlParser.parse(mtmXml);
    }
    
}
