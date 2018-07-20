import java.util.ArrayList;

public class Main {
    private final static String filepath = "C:\\Users\\NHol01\\IdeaProjects\\chbspass\\src\\top-1000-words-uc.txt";

    public static void main(String[] args) {
        Chbspass test = new Chbspass.Password(30).Numbers(2).Symbols(2).generate();
        test.readWordFile(filepath);
        ArrayList<String> pwds = test.Next(10);
        for (String pwd : pwds) {
            System.out.println(pwd);
        }
    }
}
