import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;

public class Chbspass {
    private final static String[] words = new String[1000];
    private int passwordLength;
    private int numNumbers;
    private int numSpecial;
    private int constraintLength;
    private final static String filepath = "C:\\Users\\NHol01\\IdeaProjects\\chbspass\\src\\top-1000-words-uc.txt";
    private final static String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final static  String[] special_chars = {"!", "@", "#", "$", "^", "%", "&", "*"};
    private SecureRandom PwdGen = new SecureRandom();
    static {
        Chbspass.readWordFile(filepath);
    }

    public Chbspass(int passwordLength, int numNumbers, int numSpecial) {
        this.passwordLength = passwordLength;
        this.numNumbers = numNumbers;
        this.numSpecial = numSpecial;
        this.constraintLength = this.passwordLength - this.numNumbers - this.numSpecial;
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

    public String generatePassword() {
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
        for (int y = 0; y < this.numSpecial; y++) {
            String special = special_chars[PwdGen.nextInt(8)];
            pw.append(special);
        }
        return pw.toString();
    }

}
