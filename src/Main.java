
public class Main {
    public static void main(String[] args) {
        Chbspass test = new Chbspass(30, 2, 3);
        String potato = test.generatePassword();
        System.out.println(potato);
        System.out.println(potato.length());
    }
}
