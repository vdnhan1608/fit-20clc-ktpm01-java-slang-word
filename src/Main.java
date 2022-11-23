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
    static private HashMap<String, String> dictionary = new HashMap<String, String>();
    static private HashMap<String, HashMap<String, Boolean>> keywordDictionary = new HashMap<String, HashMap<String, Boolean>>();
    static private HashMap<String, HashMap<String, Boolean>> definitionDictionary= new HashMap<String, HashMap<String, Boolean>>();
    /*
    * THIS FUNCTION RETURN HASHMAP CONTAIN <KEYWORD , HASHMAP<SLANGWORD, BOOLEAN>>
    * KEYWORD IS THE KEYWORD USER INPUT
    * SLANGWORD CONTAINS KEYWORDS
    *
    * */
    public static void loadDataByKeyWord () throws IOException
    {
// Read data from slang.txt fil
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
                    if (keywordDictionary.get(keyword) == null) {
                        slangWord = new HashMap<String, Boolean>();
                        slangWord.put(parts[0], true);
                        keywordDictionary.put(keyword,slangWord);
                    }
                    else {
                        slangWord = keywordDictionary.get(keyword);
                        slangWord.put(parts[0], true);
                    }
                }

            }
            line = br.readLine();

        }
    }

    /*
    * Params: String keyword: User input
    * HashMap<String, HashMap<String, Boolean>> dictionary: Load from slang.txt include
    *
    * Return: List of searched slang words
    * */
    public static HashMap<String, Boolean> searchSlangWordByKeyWord (String keyword)
    {
        HashMap<String, Boolean> slangWords =  keywordDictionary.get(keyword);
        return slangWords;
    }

    /*
     * THIS FUNCTION RETURN HASHMAP CONTAIN <KEYWORD , HASHMAP<SLANGWORD, BOOLEAN>>
     * KEYWORD IS THE KEYWORD USER INPUT
     * SLANGWORD 'S DEFINITION CONTAINS THE KEYWORDS
     * */
    public static void loadDataByDefinition () throws IOException
    {

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
                        if (definitionDictionary.get(keyword) == null) {
                            slangWord = new HashMap<String, Boolean>();
                            slangWord.put(parts[0], true);
                            definitionDictionary.put(keyword,slangWord);
                        }
                        else {
                            slangWord = definitionDictionary.get(keyword);
                            slangWord.put(parts[0], true);
                        }
                    }

                }
            }
            line = br.readLine();

        }

    }

    public static void loadData() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
        String line = br.readLine(); // SKIP 1ST LINE: SLANG`MEANING
        line = br.readLine();

        while (line != null)
        {
            String[] parts = line.split("`");
            if (parts.length == 2)
            {
                dictionary.put(parts[0], parts[1]);
            }
            line = br.readLine();

        }
    }

    /*
    * Params: slangWord, definition, HashMap<String, String> dictionary
    * Return true/false
    * Desc: add a slang word to dictionary
    * */
    public static Boolean addSlangWord (String slangWord, String definition){
        // CHECK DIEU KIEN ADD VO
        if (dictionary.get(slangWord)==null) {
            HashMap<String, Boolean> slang;
            dictionary.put(slangWord, definition);

            // ADD TO KEYWORD HASHMAP
            String keyWord = "";
            for (int i = 0; i < slangWord.length(); i++)
            {
                keyWord += slangWord.charAt(i);

                if (keywordDictionary.get(keyWord) == null)
                {
                    slang = new HashMap<String, Boolean>();
                    slang.put(slangWord,true);
                    keywordDictionary.put(keyWord,slang);
                }
                else {
                    slang = keywordDictionary.get(keyWord);
                    slang.put(slangWord,true);
                }
            }

            // ADD TO DEFINITION HASHMAP,
            for (int i = 0; i < definition.length();i++)
            {
                for (int j = 0 ; j <definition.length(); j ++)
                {
                    keyWord = "";
                    for (int k = i ; k <= j ; k ++)
                        keyWord += definition.charAt(k);

                    if (definitionDictionary.get(keyWord)== null)
                    {
                        slang = new HashMap<String, Boolean>();
                        slang.put(slangWord, true);
                        definitionDictionary.put(keyWord,slang);
                    }
                    else
                    {
                        slang = definitionDictionary.get(keyWord);
                        slang.put(slangWord, true);
                    }
                }
            }
        }
        else
            return false;

        return true;

    }


    public static Boolean deleteSlangWord (String slangWord) {
        HashMap<String, Boolean> slang = new HashMap<String, Boolean>();
        String definition = dictionary.get(slangWord);
        if (definition == null) {
            System.out.println("Cannot delelte");
            return false;}

        dictionary.remove(slangWord);

        // Remove in keyword dictionary
        String keyWord = "";
        for (int i = 0; i < slangWord.length(); i++)
        {
            keyWord += slangWord.charAt(i);
            if (keywordDictionary.get(keyWord) != null) {
                slang = keywordDictionary.get(keyWord);
                slang.remove(slangWord);
            }
        }

        // Delete in definition dictionary

        for (int i = 0 ; i < definition.length(); i ++)
        {
            for (int j = i; j < definition.length(); j ++)
            {
                keyWord = "";
                for (int k = i ; k <= j ; k ++)
                    keyWord += definition.charAt(k);

                if (definitionDictionary.get(keyWord) != null)
                {
                    slang = definitionDictionary.get(keyWord);
                    slang.remove(slangWord);
                }
            }
        }

        System.out.println("Deleted words");
        return true;
    }
    public static void  main(String[] args) throws IOException {
        loadDataByDefinition();
        loadDataByKeyWord();
        loadData();


        System.out.print("Enter any key");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String definition = dictionary.get("V");
        if (deleteSlangWord("V")) System.out.println("Delete words");
        else System.out.println("Cannot delete");

    }
}