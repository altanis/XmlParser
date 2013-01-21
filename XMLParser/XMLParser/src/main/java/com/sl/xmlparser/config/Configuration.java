package com.sl.xmlparser.config;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class Configuration {

    @Option(name = "-h", usage = "host of the external system", required = true)
    private String host;
    @Option(name = "-p", usage = "port of the external system")
    private int port = 80;
    @Argument
    private List<String> arguments = new ArrayList<String>();

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Configuration{" + "host=" + host + ", port=" + port + '}';
    }
}
