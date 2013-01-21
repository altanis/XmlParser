package com.sl.xmlparser.config;

import java.io.OutputStream;
import java.io.PrintWriter;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;

public class ConfirurationParser {

    private Configuration commandLineConfigurationBean;
    CmdLineParser parser;

    public ConfirurationParser(Configuration commandLineConfigurationBean) {
        this.commandLineConfigurationBean = commandLineConfigurationBean;
        this.parser = new CmdLineParser(this.commandLineConfigurationBean);
    }

    public Configuration getConfiguration() {
        return commandLineConfigurationBean;
    }

    public void parseConfiguration(String[] cmdLineArgs) throws CmdLineException {
        parser.parseArgument(cmdLineArgs);
    }

    public void printUsage(OutputStream stream) {
        parser.printUsage(stream);
    }

    public void printExample(OutputStream stream) {
        new PrintWriter(stream).write(parser.printExample(ExampleMode.ALL));
    }
}
