import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Chbspass test = new Chbspass.Password(30).numNumbers(2).numSymbols(2).generate();
        ArrayList<String> pwds = test.Next(10);
        for (String pwd : pwds) {
            System.out.println(pwd);
        }
    }
}
