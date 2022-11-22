import javax.swing.plaf.synth.SynthTextAreaUI;
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
    /*
    * THIS FUNCTION RETURN HASHMAP CONTAIN <KEYWORD , HASHMAP<SLANGWORD, BOOLEAN>>
    * KEYWORD IS THE KEYWORD USER INPUT
    * SLANGWORD CONTAINS KEYWORDS
    *
    * */
    public static HashMap<String, HashMap<String, Boolean>> loadData () throws IOException
    {
// Read data from slang.txt file
        HashMap<String, HashMap<String, Boolean>> keywordSlangWordDictionary = new HashMap<String, HashMap<String, Boolean>>();
        HashMap<String, Boolean> slangWord;
        BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
        String line = ""; // SKIP 1ST LINE: SLANG`MEANING
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

    /*
    * Params: String keyword: User input
    * HashMap<String, HashMap<String, Boolean>> dictionary: Load from slang.txt include
    *
    * Return: List of searched slang words
    * */
    public static HashMap<String, Boolean> searchSlangWord (String keyword, HashMap<String, HashMap<String, Boolean>> dictionary)
    {
        HashMap<String, Boolean> slangWords =  dictionary.get(keyword);
        return slangWords;
    }

    /**
     * THIS FUNCTION DOES NOT WORK
     * */
    public static HashMap<String, HashMap<String, Boolean>> loadDataByDefinition () throws IOException
    {
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
                for (int i = 0 ; i < parts[1].length(); i ++) {

                    for (int j = i ; j < parts[1].length();j ++) {
                        keyword = "";
                    for (int k = i ; k <= j ;  k ++)
                        {
                            keyword = keyword + parts[1].charAt(k);
                        }
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
            }
            line = br.readLine();

        }

        return keywordSlangWordDictionary;
    }

    private static void generateKeyWords (String definition)
    {
        String keyword = "";
        for (int i = 0 ; i < definition.length(); i++)
        {

            for (int j = i ; j < definition.length(); j ++)
            {
                keyword = "";
                for (int k = i ; k <= j ; k++)
                    keyword += definition.charAt(k);
                System.out.println(keyword);
            }

        }


    }
    public static void  main(String[] args) throws IOException {
        HashMap<String, HashMap<String, Boolean>> dictionary = loadDataByDefinition();
        HashMap<String, HashMap<String, Boolean>> anotherDictionary = loadData();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter keyword: ");
        String keyword = scanner.nextLine();

        HashMap<String, Boolean> slangWords = dictionary.get(keyword);
        System.out.println(slangWords);

    }
}