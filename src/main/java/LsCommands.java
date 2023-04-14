import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class LsCommands {
    @Option(name = "-l")
    boolean longFormat = false;

    @Option(name = "-h")
    boolean humanReadable;

    @Option(name = "-r")
    boolean reverse = false;

    @Option(name = "-o")
    String outputFile = "";

    @Argument(metaVar = "InPath")
    String inputFile;


    public static void main(String[] args) throws IOException {
        new LsCommands().launchMain(args);

    }

    public void launchMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }
        try {
            LS is = new LS(longFormat, humanReadable, reverse);
            is.OutputInfo(inputFile, outputFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

