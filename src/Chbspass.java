import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Chbspass {
    private final static int Words = 1000;
    private final static String[] words = new String[Words];
    private final static String filepath = "C:\\Users\\NHol01\\IdeaProjects\\chbspass\\src\\top-1000-words-uc.txt";
    private final static String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final static  String[] syms = {"!", "@", "#", "$", "^", "%", "&", "*"};
    private final int passwordLength;
    private final int Numbers;
    private final int Symbols;
    private final int constraintLength;
    private SecureRandom PwdGen = new SecureRandom();
    static {
        Chbspass.readWordFile(filepath);
    }

    public Chbspass(Password password) {
        this.passwordLength = password.passwordLength;
        this.Numbers = password.Numbers;
        this.Symbols = password.Symbols;
        this.constraintLength = this.passwordLength - this.Numbers - this.Symbols;
    }

    public static class Password {
        private int passwordLength;
        private int Numbers = 2;
        private int Symbols = 2;

        public Password(int passwordLength) {
            this.passwordLength = passwordLength;
        }

        public Password Numbers(int val) {
            Numbers = val;
            return this;
        }

        public Password Symbols(int val) {
            Symbols = val;
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

        for (int x = 0; x < this.Numbers; x++) {
            String num = nums[PwdGen.nextInt(9)];
            pw.append(num);
        }

        for (int y = 0; y < this.Symbols; y++) {
            String sym = syms[PwdGen.nextInt(8)];
            pw.append(sym);
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
