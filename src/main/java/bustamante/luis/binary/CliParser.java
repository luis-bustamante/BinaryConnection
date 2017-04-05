package bustamante.luis.binary;

import org.apache.commons.cli.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by luisbustamante on 05/04/2017.
 */
public class CliParser {

    private static final Logger log = Logger.getLogger(CliParser.class.getName());
    private String[] args = null;
    private Options options = new Options();
    private CommandLine cmd = null;
    private String username = "admin";
    private String keyword = "admin";
    private String hostname = "localhost";

    public CliParser(String[] args){
        this.args = args;

        options.addOption("?", "help", false, "Show this help.");
        options.addOption("t", "transport", true, "Required. Binary Transport: -t TCP|SSL");
        options.addOption("h", "hostname", true, "Optional. Server Hostname. ex: -h 127.0.0.1 ");
        options.addOption("p", "port", true, "Optional. Port number. ex: -p 9711");
        options.addOption("u", "username", true, "Optional. Username. ex: -u admin");
        options.addOption("k", "keyword", true, "Optional. User password. ex: -k admin");
    }

    public void parse() {
        CommandLineParser parser = new DefaultParser();
        try {

            cmd = parser.parse(options, args);

            if (cmd.hasOption("?"))
                help();

            if (cmd.hasOption("h"))
                this.hostname = cmd.getOptionValue("h");

            if (cmd.hasOption("u"))
                this.username = cmd.getOptionValue("u");

            if (cmd.hasOption("k"))
                this.keyword = cmd.getOptionValue("k");

            if (cmd.hasOption("t")) {
                if(cmd.getOptionValue("t").equalsIgnoreCase("TCP")){
                    int port = Integer.parseInt(cmd.getOptionValue("p", "9611"));
                    new TCPConnector().testConnection(this.hostname, port, this.username, this.keyword);
                }else if(cmd.getOptionValue("t").equalsIgnoreCase("SSL")){
                    int port = Integer.parseInt(cmd.getOptionValue("p", "9711"));
                    new SSLConnector().testConnection(this.hostname, port, this.username, this.keyword);
                }else {
                    log.log(Level.SEVERE, "Unsupported transport (-t) option");
                    help();
                }
            } else {
                log.log(Level.SEVERE, "Missing transport (-t) option");
                help();
            }

        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse comand line properties");
            help();
        }
    }

    private void help() {
        // This prints out some help
        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp("Main", options);
        System.exit(0);
    }
}
