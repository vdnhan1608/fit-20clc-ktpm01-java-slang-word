package SlangWord;

import java.io.*;
import java.util.*;

public class SlangWord {
     /*
      * Desc: 3 of these hashmap will change during user interaction
      * */
     static private HashMap<String, String> dictionary = new HashMap<String, String>();
     static private HashMap<String, HashMap<String, Boolean>> keywordDictionary = new HashMap<String, HashMap<String, Boolean>>();
     static private HashMap<String, HashMap<String, Boolean>> definitionDictionary = new HashMap<String, HashMap<String, Boolean>>();

     public static ArrayList<String> searchedSlangWords ;
     /*
      * These 3 hashmap will be the origin to reset
      * */
     static private HashMap<String, String> originDictionary;
     static private HashMap<String, HashMap<String, Boolean>> originKeyWordDictionary;
     static private HashMap<String, HashMap<String, Boolean>> originDefinitionDictionary;


     public static String searchDefinition(String slangWord)
     {
         return dictionary.get(slangWord);
     }
     /*
      * THIS FUNCTION RETURN HASHMAP CONTAIN <KEYWORD , HASHMAP<SLANGWORD, BOOLEAN>>
      * KEYWORD IS THE KEYWORD USER INPUT
      * SLANGWORD CONTAINS KEYWORDS
      *
      * */
     public static void loadDataByKeyWord() throws IOException {
// Read data from slang.txt fil
         HashMap<String, Boolean> slangWord;
         BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
         String line = ""; // SKIP 1ST LINE: SLANG`MEANING
         line = br.readLine();

         while (line != null) {
             String[] parts = line.split("`");
             if (parts.length == 2) {
                 String keyword = "";
                 for (int i = 0; i < parts[0].length(); i++) {
                     keyword = keyword + parts[0].charAt(i);
                     if (keywordDictionary.get(keyword) == null) {
                         slangWord = new HashMap<String, Boolean>();
                         slangWord.put(parts[0], true);
                         keywordDictionary.put(keyword, slangWord);
                     } else {
                         slangWord = keywordDictionary.get(keyword);
                         slangWord.put(parts[0], true);
                     }
                 }

             }
             line = br.readLine();

         }
         originKeyWordDictionary = keywordDictionary;
     }

     /*
      * THIS FUNCTION RETURN HASHMAP CONTAIN <KEYWORD , HASHMAP<SLANGWORD, BOOLEAN>>
      * KEYWORD IS THE KEYWORD USER INPUT
      * SLANGWORD 'S DEFINITION CONTAINS THE KEYWORDS
      * */
     public static void loadDataByDefinition() throws IOException {

         HashMap<String, Boolean> slangWord;
         BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
         String line = br.readLine(); // SKIP 1ST LINE: SLANG`MEANING
         line = br.readLine();

         while (line != null) {
             String[] parts = line.split("`");
             if (parts.length == 2) {
                 String keyword = "";
                 for (int i = 0; i < parts[1].length(); i++) {

                     for (int j = i; j < parts[1].length(); j++) {
                         keyword = "";
                         for (int k = i; k <= j; k++) {
                             keyword = keyword + parts[1].charAt(k);
                         }
                         if (definitionDictionary.get(keyword) == null) {
                             slangWord = new HashMap<String, Boolean>();
                             slangWord.put(parts[0], true);
                             definitionDictionary.put(keyword, slangWord);
                         } else {
                             slangWord = definitionDictionary.get(keyword);
                             slangWord.put(parts[0], true);
                         }
                     }

                 }
             }
             line = br.readLine();

         }

         originDefinitionDictionary = definitionDictionary;

     }

     public static void loadData() throws IOException {
         BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
         String line = br.readLine(); // SKIP 1ST LINE: SLANG`MEANING
         line = br.readLine();

         while (line != null) {
             String[] parts = line.split("`");
             if (parts.length == 2) {
                 dictionary.put(parts[0], parts[1]);
             }
             line = br.readLine();

         }

         originDictionary = dictionary;
     }

     /*
      * Params: String keyword: User input
      * HashMap<String, HashMap<String, Boolean>> dictionary: Load from slang.txt include
      *
      * Return: List of searched slang words
      * */
     public static HashMap<String, Boolean> searchSlangWordByKeyWord(String keyword) {
         HashMap<String, Boolean> slangWords = keywordDictionary.get(keyword);
         return slangWords;
     }

     /*
      * Search slangword by definition
      * */
     public static HashMap<String, Boolean> searchSlangWordByDefinition(String keyword) {
         HashMap<String, Boolean> slangWords = definitionDictionary.get(keyword);

         return slangWords;
     }

     /*
      * Params: slangWord, definition, HashMap<String, String> dictionary
      * Return true/false
      * Desc: add a slang word to dictionary
      * */
     public static Boolean addSlangWord(String slangWord, String definition) {
         // CHECK DIEU KIEN ADD VO
         if (dictionary.get(slangWord) == null) {
             HashMap<String, Boolean> slang;
             dictionary.put(slangWord, definition);

             // ADD TO KEYWORD HASHMAP
             String keyWord = "";
             for (int i = 0; i < slangWord.length(); i++) {
                 keyWord += slangWord.charAt(i);

                 if (keywordDictionary.get(keyWord) == null) {
                     slang = new HashMap<String, Boolean>();
                     slang.put(slangWord, true);
                     keywordDictionary.put(keyWord, slang);
                 } else {
                     slang = keywordDictionary.get(keyWord);
                     slang.put(slangWord, true);
                 }
             }

             // ADD TO DEFINITION HASHMAP,
             for (int i = 0; i < definition.length(); i++) {
                 for (int j = 0; j < definition.length(); j++) {
                     keyWord = "";
                     for (int k = i; k <= j; k++)
                         keyWord += definition.charAt(k);

                     if (definitionDictionary.get(keyWord) == null) {
                         slang = new HashMap<String, Boolean>();
                         slang.put(slangWord, true);
                         definitionDictionary.put(keyWord, slang);
                     } else {
                         slang = definitionDictionary.get(keyWord);
                         slang.put(slangWord, true);
                     }
                 }
             }
         } else
             return false;

         return true;

     }

     /*
      * Desc: delete a slang word
      * Params: String slangWord
      * Return: Boolean
      * */
     public static Boolean deleteSlangWord(String slangWord) {
         HashMap<String, Boolean> slang = new HashMap<String, Boolean>();
         String definition = dictionary.get(slangWord);
         if (definition == null) {
             System.out.println("Cannot delelte");
             return false;
         }

         dictionary.remove(slangWord);

         // Remove in keyword dictionary
         String keyWord = "";
         for (int i = 0; i < slangWord.length(); i++) {
             keyWord += slangWord.charAt(i);
             if (keywordDictionary.get(keyWord) != null) {
                 slang = keywordDictionary.get(keyWord);
                 slang.remove(slangWord);
             }
         }

         // Delete in definition dictionary

         for (int i = 0; i < definition.length(); i++) {
             for (int j = i; j < definition.length(); j++) {
                 keyWord = "";
                 for (int k = i; k <= j; k++)
                     keyWord += definition.charAt(k);

                 if (definitionDictionary.get(keyWord) != null) {
                     slang = definitionDictionary.get(keyWord);
                     slang.remove(slangWord);
                 }
             }
         }

         System.out.println("Deleted words");
         return true;
     }

     public static Boolean editSlangWord(String oldSlangWord, String oldDefinition, String newSlangWord, String newDefinition) {
         if (oldSlangWord.equals(newSlangWord) && oldDefinition.equals(newDefinition)) return true;
         // Delete the old slang word, definition
         // Add new
         if (deleteSlangWord(oldSlangWord) == false) return false;
         if (addSlangWord(newSlangWord, newDefinition) == false) return false;
         return true;
     }

     /*
      * Desc: Show history of searched slang words
      * Params: String Filename
      * Return ArrayList<String> slang words
      * */

     public static ArrayList<String> showHistory(String fileName) throws IOException {
         try {
             ArrayList<String> slangWords = new ArrayList<String>();
             BufferedReader br = new BufferedReader(new FileReader(fileName));
             String line = br.readLine();
             while (line != null) {
                 slangWords.add(line);
                 line = br.readLine();
             }
             return slangWords;
         }
         catch (IOException ie)
         {
             System.out.println(ie);
         }

         return null;
     }

     /*
      * Write searched slang word to file history.txt
      * Params: String slangword
      * Return: Boolean
      * */
     public static Boolean saveHistory(String slangWord, String fileName) throws IOException {
         BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
         bw.write(slangWord);
         bw.newLine();
         bw.close();

         return true;
     }

     /*
      * Desc: Assign dictionary, keyWordDictionary, definitionDictionary to the origin
      * Params:
      * Return: Boolean
      * */
     public static Boolean resetSlangWord() {
         dictionary = new HashMap<String, String>(originDictionary);
         keywordDictionary = new HashMap<String, HashMap<String, Boolean>>(originKeyWordDictionary);
         definitionDictionary = new HashMap<String, HashMap<String, Boolean>>(originDefinitionDictionary);

         return true;

     }

     /*
      * Desc: Random a slangword
      * Params:
      * Return: String slangWord
      * */
     public static HashMap<String, String> randomSlangWord() {
         HashMap<String, String> slangWord = new HashMap<String, String>();
         String[] slangWords = dictionary.keySet().toArray(new String[0]);
         Random rand = new Random();
         int upperBound = 7000;
         int int_random = rand.nextInt(upperBound);
         slangWord.put(slangWords[int_random], dictionary.get(slangWords[int_random]));
         return slangWord;

     }

     /*
      * Desc: Generate list of 4 slang words
      * */
     public static ArrayList<String> generateRandomSlangWordList() {
         ArrayList<String> slangWords = new ArrayList<String>();
         HashMap<String, String> slangWord ;
         for (int i = 0 ; i < 4 ;i ++)
         {
             slangWord = SlangWord.randomSlangWord();
             String word = slangWord.keySet().toString().substring(1,slangWord.keySet().toString().length()-1);
             slangWords.add(word);
         }

         return slangWords;
     }

     /*
      * Desc: Generate list of 4 definition corressponding to list of 4 slang words
      * */

     public static ArrayList<String> generateRandomDefinitionList(ArrayList<String> slangWords) {
        ArrayList<String> definitions = new ArrayList<String>();
        for (int i = 0 ; i < 4; i ++)
        {
            definitions.add(dictionary.get(slangWords.get(i)));
        }
        return definitions;

     }

     /*
     * Randome index of [0,1,2,3]
     * */

    public static int randomIndex()
    {
        Random rand = new Random();
        int upper_bound = 4;
        int index = rand.nextInt(upper_bound);

        return index;
    }
 }