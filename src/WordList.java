import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordList {
    private static HashMap<String, ArrayList<String>> cachedData;
    String filepath = "C:\\Users\\NHol01\\IdeaProjects\\chbspass\\src\\top-1000-words-uc.txt";
    ArrayList<String> words;
    {
        if (cachedData.containsKey(filepath )) {
            this.words = cachedData.get(filepath);
        } else {
            this.readWordFile(this.filepath);
        }
    }

    private void readWordList(ArrayList<String> words) {
        this.words = words;
    }

    private static void readWordFile(String filepath) {
        String w;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while ((w = br.readLine()) != null) {
                this.words.add(w);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
