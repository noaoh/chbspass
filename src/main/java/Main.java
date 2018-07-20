import java.util.ArrayList;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class Main {
    private final static String filepath = "C:\\Users\\NHol01\\IdeaProjects\\chbspass\\src\\main\\java\\top-1000-words-uc.txt";

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("Main").build()
                .defaultHelp(true)
                .description("Generates an xkcd-style password.");
        parser.addArgument("-l", "--length")
                .setDefault(30)
                .help("Exact length of the password to be generated.");
        parser.addArgument("-f", "--file")
                .setDefault(filepath)
                .help("File to get the words from.");
        parser.addArgument("-s", "--symbols")
                .setDefault(3)
                .help("Number of special characters (\"!\", \"@\", \"#\", \"$\", \"^\", \"%\", \"&\", \"*\") in the password");
        parser.addArgument("-n", "--numbers")
                .setDefault(3)
                .help("Number of numbers (1, 2, 3, 4, 5, 6, 7, 8, 9) in the password.");
        parser.addArgument("-p", "--passwords")
                .setDefault(1)
                .help("Number of passwords to generate.");

        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        Chbspass test = new Chbspass.Password(ns.getInt("length"))
                .Numbers(ns.getInt("numbers"))
                .Symbols(ns.getInt("symbols")).generate();
        test.readWordFile(ns.getString("file"));

        ArrayList<String> pwds = test.Next(ns.getInt("passwords"));

        for (String pwd : pwds) {
            System.out.println(pwd);
        }
    }
}
