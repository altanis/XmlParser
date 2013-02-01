package com.sl.xmlparser.csv;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.model.ProjectModel;
import java.io.IOException;
import java.util.List;
import org.bouncycastle.asn1.x509.sigi.PersonalData;

public class CsvWriter {

    private Configuration configuration;
    
    public CsvWriter(Configuration configuration) {
        this.configuration = configuration;
    }
    
    public void write(List<ProjectModel> modelsToBeWritten) throws IOException {
        
    }
    
}
