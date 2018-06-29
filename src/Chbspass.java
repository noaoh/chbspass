import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Chbspass {
    private final static int numWords = 1000;
    private final static String[] words = new String[numWords];
    private final static String filepath = "C:\\Users\\NHol01\\IdeaProjects\\chbspass\\src\\top-1000-words-uc.txt";
    private final static String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final static  String[] symbols = {"!", "@", "#", "$", "^", "%", "&", "*"};
    private final int passwordLength;
    private final int numNumbers;
    private final int numSymbols;
    private final int constraintLength;
    private SecureRandom PwdGen = new SecureRandom();
    static {
        Chbspass.readWordFile(filepath);
    }

    public Chbspass(Password password) {
        this.passwordLength = password.passwordLength;
        this.numNumbers = password.numNumbers;
        this.numSymbols = password.numSymbols;
        this.constraintLength = this.passwordLength - this.numNumbers - this.numSymbols;
    }

    public static class Password {
        private int passwordLength;
        private int numNumbers = 2;
        private int numSymbols = 2;

        public Password(int passwordLength) {
            this.passwordLength = passwordLength;
        }

        public Password numNumbers(int val) {
            numNumbers = val;
            return this;
        }

        public Password numSymbols(int val) {
            numSymbols = val;
            return this;
        }

        public Chbspass generate() {
            return new Chbspass(this);
        }
    }

    private static void readWordFile(String filepath) {
        int i = 0;
        String w;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while ((w = br.readLine()) != null) {
                Chbspass.words[i] = w;
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String Next() {
        StringBuilder pw = new StringBuilder();
        String word = "";
        while (pw.length() < this.constraintLength) {
            do {
                word = words[PwdGen.nextInt(1000)];
            }
            while (word.length() + pw.length() > this.constraintLength);
            pw.append(word);
        }

        for (int x = 0; x < this.numNumbers; x++) {
            String number = numbers[PwdGen.nextInt(9)];
            pw.append(number);
        }

        for (int y = 0; y < this.numSymbols; y++) {
            String symbol = symbols[PwdGen.nextInt(8)];
            pw.append(symbol);
        }

        return pw.toString();
    }

    public ArrayList<String> Next(int n) {
        ArrayList<String> pws = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            pws.add(this.Next());
        }
        return pws;
    }
}
