import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
/*
* Author
*
* */

// 1. Doc tu file vao hashmap
// 2. Tao menu console tam thoi
// 3. Lam cac chuc nang
public class Main {
    public static HashMap<String, HashMap<String, Boolean>> LoadData () throws IOException
    {
// Read data from slang.txt file
        HashMap<String, HashMap<String, Boolean>> keywordSlangWordDictionary = new HashMap<String, HashMap<String, Boolean>>();
        HashMap<String, Boolean> slangWord;
        BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
        String line = br.readLine(); // SKIP 1ST LINE: SLANG`MEANING
        line = br.readLine();

        while (line != null)
        {
            String[] parts = line.split("`");
            if (parts.length == 2)
            {
                String keyword = "";
                for (int i = 0 ; i < parts[0].length(); i ++) {
                    keyword = keyword + parts[0].charAt(i);
                    if (keywordSlangWordDictionary.get(keyword) == null) {
                        slangWord = new HashMap<String, Boolean>();
                        slangWord.put(parts[0], true);
                        keywordSlangWordDictionary.put(keyword,slangWord);
                    }
                    else {
                        slangWord = keywordSlangWordDictionary.get(keyword);
                        slangWord.put(parts[0], true);
                    }
                }

            }
            line = br.readLine();

        }

        return keywordSlangWordDictionary;
    }
    public static void  main(String[] args) throws IOException {

    HashMap<String , HashMap<String, Boolean>> keywordSlangWordDictionary = LoadData();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter keyword: ");
    String keyword = scanner.nextLine();

    HashMap <String, Boolean> slang = keywordSlangWordDictionary.get(keyword);
    System.out.println(slang);
    }
}