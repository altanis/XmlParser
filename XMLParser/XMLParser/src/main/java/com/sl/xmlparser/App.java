package com.sl.xmlparser;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.config.ConfirurationParser;
import org.kohsuke.args4j.CmdLineException;

public class App {

    private static final ConfirurationParser parser = new ConfirurationParser(new Configuration());

    public static void main(String[] args) throws Exception {
        try {
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
