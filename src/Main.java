import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import SlangWord.SlangWord;
/*
* Author: Vo Duy Nhan
* MSSV: 20127056
* */

/*
* Làm giao diện
* */

public class Main {

    public static JButton searchBtnByKeyWord = new JButton("Search by keyword");
    public static JButton searchBtnByDefinition = new JButton("Search by definition");
    public static JButton addBtn = new JButton("Add new slang word");
    public static JButton deleteBtn = new JButton("Delete slang word");
    public static JButton editBtn = new JButton("Edit slang word");
    public static JButton historyBtn = new JButton("History of searched word");
    public static JButton resetBtn = new JButton("Reset slang word");
    public static JButton randomBtn = new JButton("Random slang word");
    public static JButton guessDefinitionBtn = new JButton("Guess definition of slang word");
    public static JButton guessSlangWordBtn = new JButton("Guess slang words");

    public static SlangWord SlangWord = new SlangWord();
    /*
     * These JFrame are all screen of application
     * */
    public static JFrame mainScreen = new JFrame("Slang word");

    public static JFrame searchByKeywordScreen = new JFrame("Search by keyword");

    public static JFrame searchByDefinitionScreen = new JFrame("Search by definition");

    public static JFrame addSlangWordScreen = new JFrame("Add new slang words");

    public static JFrame editSlangWordScreen = new JFrame("Edit slang words");

    public static JFrame deleteSlangWordScreen = new JFrame("Delete slang words");

    public static JFrame resetSlangWordScreen = new JFrame("Reset slang words");

    public static JFrame randomSlangWord = new JFrame("Random slang word");

    public static JFrame guessDefiniton = new JFrame("Guess definiton");

    public static JFrame guessSlangWord = new JFrame("Guess slang word");
    /*
     * Desc: Screen search by definition
     * */
    public static void searchByDefinitionScreen()
    {

        searchByDefinitionScreen.setSize(200,200);
        Container pane = searchByDefinitionScreen.getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel label = new JLabel("Search");
        JTextField textField = new JTextField(10);
        textField.setText("");
        pane1.add(label);
        pane1.add (textField);

        JPanel pane2 = new JPanel();
        JTextArea textArea = new JTextArea(20,50);
        textArea.setText("");
        JScrollPane scrollPane = new JScrollPane(textArea);
        pane2.add(scrollPane);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JLabel meaningLabel = new JLabel("Meaning");
        JTextField meaningTextField = new JTextField(15);
        pane3.add(meaningLabel);
        pane3.add(meaningTextField);



        pane.add(pane1, BorderLayout.PAGE_START);
        pane.add(pane2, BorderLayout.CENTER);
        pane.add(pane3, BorderLayout.PAGE_END);
        searchByDefinitionScreen.pack();

        /*
        * Event handle
        * */
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                String keyword = textField.getText();
                String[] slangWords = SlangWord.searchSlangWordByDefinition(keyword).keySet().toArray(new String[0]);

                for (int i = 0 ; i < slangWords.length ; i ++) textArea.append(slangWords[i]  +"\n");
            }
        });
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)   {
                super.mouseClicked(e);
                try{
                    int line = textArea.getLineOfOffset( textArea.getCaretPosition() );
                    int start = textArea.getLineStartOffset( line );
                    int end = textArea.getLineEndOffset( line );
                    String text = textArea.getDocument().getText(start, end - start);
                    meaningTextField.setText("");
                    String slangWord = text.substring(0,text.length()-1);
                    String definition = SlangWord.searchDefinition(slangWord);
                    meaningTextField.setText(slangWord+ " : " + definition);
                }
                catch (BadLocationException badLocationException)
                {
                    System.out.println(badLocationException);
                }

            }
        });

        searchByDefinitionScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                searchByDefinitionScreen.setVisible(false);
                /*
                * Delete history of search word
                * */
                textField.setText("");
                textArea.setText("");
                meaningTextField.setText("");
                mainScreen.setVisible(true);
            }
        });
    }


    /*
    * Desc: Create search by keyword screen
    * */
    public static void searchByKeywordScreen()
    {

        searchByKeywordScreen.setSize(200, 200);

        Container contentPane = searchByKeywordScreen.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Search");
        pane1.add(label);

        JTextField textField = new JTextField(10);
        pane1.add(textField);

        JPanel pane2 = new JPanel();
        JTextArea textArea = new JTextArea(20,50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        pane2.add(scrollPane);


        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JLabel meaningLabel = new JLabel("Meaning");
        JTextField meaningTextField = new JTextField(15);
        pane3.add(meaningLabel);
        pane3.add(meaningTextField);

        contentPane.add(pane1,BorderLayout.PAGE_START);
        contentPane.add(pane2, BorderLayout.CENTER);
        contentPane.add(pane3, BorderLayout.PAGE_END);


        /*
        * Xu ly su kien
        * */
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                String keyword = textField.getText();

                String[] slangWords = SlangWord.searchSlangWordByKeyWord(keyword).keySet().toArray(new String[0]);
                for (int i = 0; i < slangWords.length; i ++)
                textArea.append(slangWords[i]+'\n');
            }
        });

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)   {
                super.mouseClicked(e);
                try{
                    int line = textArea.getLineOfOffset( textArea.getCaretPosition() );
                    int start = textArea.getLineStartOffset( line );
                    int end = textArea.getLineEndOffset( line );
                    String text = textArea.getDocument().getText(start, end - start);
                    meaningTextField.setText("");
                    String slangWord = text.substring(0,text.length()-1);
                    String definition = SlangWord.searchDefinition(slangWord);
                    meaningTextField.setText(slangWord+ " : " + definition);
                }
                catch (BadLocationException badLocationException)
                {
                    System.out.println(badLocationException);
                }

            }
        });

        searchByKeywordScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                searchByDefinitionScreen.setVisible(false);
                /*
                * Delete history of searching
                * */
                textField.setText("");
                textArea.setText("");
                meaningTextField.setText("");

                mainScreen.setVisible(true);
            }
        });
        searchByKeywordScreen.pack();
    }


    /*
    * Desc: Add a new slang word screen
    * */
    public static void addSlangWordScreen()
    {

        addSlangWordScreen.setPreferredSize(new Dimension(300, 200));
        Container pane = addSlangWordScreen.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        // Test search by definition xem co ra tu moi them vao khong
        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel label1 = new JLabel("Slang Word");
        JTextField textField1 = new JTextField(15);
        pane1.add(label1);
        pane1.add(textField1);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new FlowLayout());
        JLabel label2 = new JLabel("Definition");
        JTextField textField2 = new JTextField(15);
        pane2.add(label2);
        pane2.add(textField2);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JLabel statusLabel = new JLabel("Status");
        pane3.add(statusLabel);
        JTextField statusTextField = new JTextField(15);
        statusTextField.setEditable(false);
        statusTextField.setHorizontalAlignment(JTextField.CENTER);
        pane3.add(statusTextField);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new FlowLayout());
        JButton addBtn = new JButton("Add");
        pane4.add(addBtn);

        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane3);
        pane.add(pane4);



        /*
        * Event handle
        * */

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slangWord = textField1.getText();
                String definition = textField2.getText();

                Boolean check = SlangWord.addSlangWord(slangWord,definition);
                if (check == true) statusTextField.setText("Added!");
                else statusTextField.setText("Duplicate!");
            }
        });

        addSlangWordScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                addSlangWordScreen.setVisible(false);
                /*
                * Clear textfield before navigate to main screen
                * */

                textField1.setText("");
                textField2.setText("");
                statusTextField.setText("");

                mainScreen.setVisible(true);
            }
        });

        addSlangWordScreen.pack();
    }


    /*
    * Desc: Edit slang word screen
    * */
    public static void editSlangWordScreen()
    {

        editSlangWordScreen.setPreferredSize(new Dimension(800, 200));
        Container pane = editSlangWordScreen.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        // Test search by definition xem co ra tu moi them vao khong
        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel oldSlangWordLabel = new JLabel("Slang Word");
        JTextField oldSlangWordTextField = new JTextField(15);
        pane1.add(oldSlangWordLabel);
        pane1.add(oldSlangWordTextField);


        JPanel pane2 = new JPanel();
        pane2.setLayout(new FlowLayout());
        JLabel newSlangWordLabel = new JLabel("New slang word");
        JTextField newSlangWordTextField = new JTextField(15);
        JLabel newDefinitionLabel = new JLabel("New definition");
        JTextField newDefinitionTextField = new JTextField(15);

        pane2.add(newSlangWordLabel);
        pane2.add(newSlangWordTextField);
        pane2.add(newDefinitionLabel);
        pane2.add(newDefinitionTextField);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JLabel statusLabel = new JLabel("Status");
        pane3.add(statusLabel);
        JTextField statusTextField = new JTextField(15);
        statusTextField.setEditable(false);
        statusTextField.setHorizontalAlignment(JTextField.CENTER);
        pane3.add(statusTextField);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new FlowLayout());
        JButton addBtn = new JButton("Edit");
        pane4.add(addBtn);

        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane3);
        pane.add(pane4);

        editSlangWordScreen.pack();

        /*
         * Event handle
         * */

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slangWord = oldSlangWordTextField.getText();
                String definition = SlangWord.searchDefinition(slangWord);

                String newSlangWord = newSlangWordTextField.getText();
                String newDefinition = newDefinitionTextField.getText();
                Boolean check = SlangWord.editSlangWord(slangWord,definition,newSlangWord,newDefinition);
                if (check == true) statusTextField.setText("Success!");
                else statusTextField.setText("Fail!");
            }
        });

        editSlangWordScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                editSlangWordScreen.setVisible(false);
                // Clear history before navigate to main
                oldSlangWordTextField.setText("");
                newSlangWordTextField.setText("");
                newDefinitionTextField.setText("");
                statusTextField.setText("");

                mainScreen.setVisible(true);

            }
        });
    }


    /*
    * Desc: Delete slang word screen
    * */

    public static void deleteSlangWordScreen()
    {

        deleteSlangWordScreen.setPreferredSize(new Dimension(300, 200));
        Container pane = deleteSlangWordScreen.getContentPane();

        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        // Test search by definition xem co ra tu moi them vao khong
        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel label1 = new JLabel("Slang Word");
        JTextField textField1 = new JTextField(15);
        pane1.add(label1);
        pane1.add(textField1);


        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JLabel statusLabel = new JLabel("Status");
        pane3.add(statusLabel);
        JTextField statusTextField = new JTextField(15);
        statusTextField.setEditable(false);
        statusTextField.setText("Confirm before deleting");
        statusTextField.setHorizontalAlignment(JTextField.CENTER);
        pane3.add(statusTextField);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new FlowLayout());
        JButton confirmBtn = new JButton("Confirm");
        pane4.add(confirmBtn);
        JButton deleteBtn = new JButton("Delete");
        pane4.add(deleteBtn);

        pane.add(pane1);
        pane.add(pane3);
        pane.add(pane4);

        deleteSlangWordScreen.pack();



        /*
        * Xu ly su kien
        * */


        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        statusTextField.setText("");
                        if (SlangWord.deleteSlangWord(textField1.getText()) == true) statusTextField.setText("Success");
                        else statusTextField.setText("Fail");
                    }
                });
            }
        });

        deleteSlangWordScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                deleteSlangWordScreen.setVisible(false);
                // Clear history
                textField1.setText("");
                statusTextField.setText("Confirm before deleting");

                mainScreen.setVisible(true);
            }
        });

    }

    /*
    * Desc: Reset slang word (alert actually)
    *
    * */

    public static void resetSlangWordScreen()
    {
        SlangWord.resetSlangWord();


        resetSlangWordScreen.setPreferredSize(new Dimension(200, 200));
        resetSlangWordScreen.setLocationRelativeTo(null);
        Container pane = resetSlangWordScreen.getContentPane();
        pane.setLayout(new BorderLayout());



        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel label = new JLabel("Successfully reset!");
        pane1.add(label);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new FlowLayout());
        JButton btn = new JButton("Return");
        pane2.add(btn);

        pane.add(pane1,BorderLayout.CENTER);
        pane.add(pane2,BorderLayout.PAGE_END);


        resetSlangWordScreen.pack();
        /*
        * Handle event
        * */
        resetSlangWordScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                resetSlangWordScreen.setVisible(false);
                mainScreen.setVisible(true);
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSlangWordScreen.setVisible(false);
                mainScreen.setVisible(true);
            }
        });
    }

    /*
    * Desc: Random slang word
    * */
    public static void randomSlangWord()
    {
        HashMap<String, String> slangWord = SlangWord.randomSlangWord();
        String word = slangWord.keySet().toString().substring(1,  slangWord.keySet().toString().length()-1);
        String definition = slangWord.get(word);

        randomSlangWord.setPreferredSize(new Dimension(400, 200));
        Container pane = randomSlangWord.getContentPane();
        pane.setLayout(new GridLayout(3,1));

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel slangWordLabel = new JLabel("Slang Word");
        JTextField slangWordTextField = new JTextField(15);
        slangWordTextField.setHorizontalAlignment(JTextField.CENTER);
        slangWordTextField.setText(word);
        pane1.add(slangWordLabel);
        pane1.add(slangWordTextField);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new FlowLayout());
        JLabel definitionLabel = new JLabel("Definition");
        JTextField definitionTextField = new JTextField(15);
        definitionTextField.setHorizontalAlignment(JTextField.CENTER);
        definitionTextField.setText(definition);
        pane2.add(definitionLabel);
        pane2.add(definitionTextField);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JButton btn = new JButton("Return");
        pane3.add(btn);

        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane3);
        randomSlangWord.pack();

        /*
        * Handle event
        * */
        randomSlangWord.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                randomSlangWord.setVisible(false);
                // Clear history
                HashMap<String, String> slangWord = SlangWord.randomSlangWord();
                String word = slangWord.keySet().toString().substring(1,  slangWord.keySet().toString().length()-1);
                String definition = slangWord.get(word);
                slangWordTextField.setText(word);
                definitionTextField.setText(definition);
                mainScreen.setVisible(true);
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomSlangWord.setVisible(false);
                // Clear history
                HashMap<String, String> slangWord = SlangWord.randomSlangWord();
                String word = slangWord.keySet().toString().substring(1,  slangWord.keySet().toString().length()-1);
                String definition = slangWord.get(word);
                slangWordTextField.setText(word);
                definitionTextField.setText(definition);
                mainScreen.setVisible(true);
            }
        });
    }

    /*
    * Desc: Game guess the definition of a slang word
    * */
    public static void guessDefiniton()
    {
        /*
        * Handle to get random slang word
        * Generate 4 random slang word, pick one randomly for question
        * */
        ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
        ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
        int randomIndex = SlangWord.randomIndex();





        /**/
        guessDefiniton.setPreferredSize(new Dimension(500, 300));
        Container pane = guessDefiniton.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel questionLabel = new JLabel("Guess the definition ");
        JTextField slangWordTextField = new JTextField(15);
        slangWordTextField.setHorizontalAlignment(JTextField.CENTER);
        slangWordTextField.setEditable(false);
        slangWordTextField.setText(slangWords.get(randomIndex));
        pane1.add(questionLabel, BorderLayout.NORTH);
        pane1.add(slangWordTextField, BorderLayout.SOUTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new GridLayout(2,2));
        JTextField[] answers = new JTextField[4];


        for (int i = 0 ; i < 4; i++)
        {
            answers[i] = new JTextField(20);
            answers[i].setHorizontalAlignment(JTextField.CENTER);
            answers[i].setText(definitions.get(i));
            answers[i].setEditable(false);
            pane2.add(answers[i]);
        }


        JPanel pane4 = new JPanel();
        pane4.setLayout(new FlowLayout());
        JLabel statusLabel = new JLabel("Status");
        JTextField statusTextField = new JTextField(20);
        statusTextField.setHorizontalAlignment(JTextField.CENTER);
        statusTextField.setEditable(false);
        pane4.add(statusLabel);
        pane4.add(statusTextField);

        JPanel pane5 = new JPanel();
        pane5.setLayout(new FlowLayout());
        JButton playAgainBtn = new JButton("Play Again");
        JButton returnBtn = new JButton("Return");
        pane5.add(playAgainBtn);
        pane5.add(returnBtn);

        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane4);
        pane.add(pane5);
        guessDefiniton.pack();

        /*
        * Handle event
        * */
        for (int i = 0 ; i < 4; i++)
        {
            final int index = i;
            answers[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (index == randomIndex) statusTextField.setText("Correct");
                    else statusTextField.setText("False");
                }
            });
        }
        guessDefiniton.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                guessDefiniton.setVisible(false);

                ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
                ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
                int randomIndex = SlangWord.randomIndex();

                slangWordTextField.setText(slangWords.get(randomIndex));

                for (int i = 0 ; i < 4; i++)
                {
                    answers[i].setText(definitions.get(i));
                }

                for (int i = 0 ; i < 4; i++)
                {
                    final int index = i;
                    answers[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (index == randomIndex) statusTextField.setText("Correct");
                            else statusTextField.setText("False");
                        }
                    });
                }
                statusTextField.setText("");
                mainScreen.setVisible(true);
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessDefiniton.setVisible(false);

                ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
                ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
                int randomIndex = SlangWord.randomIndex();

                slangWordTextField.setText(slangWords.get(randomIndex));

                for (int i = 0 ; i < 4; i++)
                {
                    answers[i].setText(definitions.get(i));
                }

                for (int i = 0 ; i < 4; i++)
                {
                    final int index = i;
                    answers[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (index == randomIndex) statusTextField.setText("Correct");
                            else statusTextField.setText("False");
                        }
                    });
                }
                statusTextField.setText("");
                mainScreen.setVisible(true);
            }
        });

        playAgainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
                ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
                int randomIndex = SlangWord.randomIndex();

                slangWordTextField.setText(slangWords.get(randomIndex));

                for (int i = 0 ; i < 4; i++)
                {
                    answers[i].setText(definitions.get(i));
                }

                for (int i = 0 ; i < 4; i++)
                {
                    final int index = i;
                    answers[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (index == randomIndex) statusTextField.setText("Correct");
                            else statusTextField.setText("False");
                        }
                    });
                }
                statusTextField.setText("");
            }
        });

    }

    /*
    * Desc: Game guess the slang word
    * */
    public static void guessSlangWord()
    {
        /*
         * Handle to get random slang word
         * Generate 4 random slang word, pick one randomly for question
         * */
        ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
        ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
        int randomIndex = SlangWord.randomIndex();



        /**/
        guessSlangWord.setPreferredSize(new Dimension(500, 300));
        Container pane = guessSlangWord.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel questionLabel = new JLabel("Guess the slang word ?");
        JTextField slangWordTextField = new JTextField(15);
        slangWordTextField.setHorizontalAlignment(JTextField.CENTER);
        slangWordTextField.setEditable(false);
        slangWordTextField.setText(definitions.get(randomIndex));
        pane1.add(questionLabel, BorderLayout.NORTH);
        pane1.add(slangWordTextField, BorderLayout.SOUTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new GridLayout(2,2));

        JTextField[] answers = new JTextField[4];
        for (int i = 0 ; i < 4; i ++)
        {
            answers[i] = new JTextField(20);
            answers[i].setHorizontalAlignment(JTextField.CENTER);
            answers[i].setEditable(false);
            answers[i].setText(slangWords.get(i));
            pane2.add(answers[i]);
        }



        JPanel pane4 = new JPanel();
        pane4.setLayout(new FlowLayout());
        JLabel statusLabel = new JLabel("Status");
        JTextField statusTextField = new JTextField(20);
        statusTextField.setHorizontalAlignment(JTextField.CENTER);
        statusTextField.setEditable(false);
        pane4.add(statusLabel);
        pane4.add(statusTextField);

        JPanel pane5 = new JPanel();
        pane5.setLayout(new FlowLayout());
        JButton playAgainBtn = new JButton("Play Again");
        JButton returnBtn = new JButton("Return");
        pane5.add(playAgainBtn);
        pane5.add(returnBtn);

        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane4);
        pane.add(pane5);
        guessSlangWord.pack();

        /*
        * Handle event
        * */
        for (int i = 0 ; i < 4; i ++)
        {
            final int index = i;
            answers[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (index == randomIndex){
                        statusTextField.setText("Correct");
                    }
                    else statusTextField.setText("False");
                }
            });
        }


        guessSlangWord.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                guessSlangWord.setVisible(false);

                ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
                ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
                int randomIndex = SlangWord.randomIndex();

                slangWordTextField.setText(definitions.get(randomIndex));

                for (int i = 0 ; i < 4; i++)
                {
                    answers[i].setText(slangWords.get(i));
                }

                for (int i = 0 ; i < 4; i++)
                {
                    final int index = i;
                    answers[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (index == randomIndex) statusTextField.setText("Correct");
                            else statusTextField.setText("False");
                        }
                    });
                }
                statusTextField.setText("");
                mainScreen.setVisible(true);
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessSlangWord.setVisible(false);

                ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
                ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
                int randomIndex = SlangWord.randomIndex();

                slangWordTextField.setText(definitions.get(randomIndex));

                for (int i = 0 ; i < 4; i++)
                {
                    answers[i].setText(slangWords.get(i));
                }

                for (int i = 0 ; i < 4; i++)
                {
                    final int index = i;
                    answers[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (index == randomIndex) statusTextField.setText("Correct");
                            else statusTextField.setText("False");
                        }
                    });
                }
                statusTextField.setText("");
                mainScreen.setVisible(true);
            }
        });

        playAgainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> slangWords = SlangWord.generateRandomSlangWordList();
                ArrayList<String> definitions = SlangWord.generateRandomDefinitionList(slangWords);
                int randomIndex = SlangWord.randomIndex();

                slangWordTextField.setText(definitions.get(randomIndex));

                for (int i = 0 ; i < 4; i++)
                {
                    answers[i].setText(slangWords.get(i));
                }

                for (int i = 0 ; i < 4; i++)
                {
                    final int index = i;
                    answers[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (index == randomIndex) statusTextField.setText("Correct");
                            else statusTextField.setText("False");
                        }
                    });
                }
                statusTextField.setText("");
            }
        });

    }

    /*
    * Desc: Create main screen
    * */
    public static void mainScreen()
    {
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setPreferredSize(new Dimension(500, 600));


        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(5,2));
        pane.setBorder(new EmptyBorder(10,10,10,10));


        pane.add(searchBtnByKeyWord);
        pane.add(searchBtnByDefinition);

        pane.add(historyBtn);
        pane.add(addBtn);

        pane.add(editBtn);
        pane.add(deleteBtn);

        pane.add(resetBtn);
        pane.add(randomBtn);

        pane.add(guessSlangWordBtn);
        pane.add(guessDefinitionBtn);

        JLabel label = new JLabel("SLANG WORD APPLICATION");
        mainScreen.getContentPane().setLayout(new BoxLayout(mainScreen.getContentPane(), BoxLayout.Y_AXIS));
        mainScreen.getContentPane().add(label);
        mainScreen.getContentPane().add(pane);

        mainScreen.pack();
        mainScreen.setVisible(true);
        /*
        * Handle screen navigation
        * Thieu screen load history
        * */
        searchBtnByKeyWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                searchByKeywordScreen.setVisible(true);
            }
        });

        searchBtnByDefinition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                searchByDefinitionScreen.setVisible(true);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                addSlangWordScreen.setVisible(true);
            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                editSlangWordScreen.setVisible(true);
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                deleteSlangWordScreen.setVisible(true);
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                resetSlangWordScreen.setVisible(true);
            }
        });

        randomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                randomSlangWord.setVisible(true);
            }
        });

        guessDefinitionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                guessDefiniton.setVisible(true);
            }
        });

        guessSlangWordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.setVisible(false);
                guessSlangWord.setVisible(true);
            }
        });


    }



    public static void  main(String[] args) throws IOException {

        SlangWord.loadDataByDefinition();
        SlangWord.loadDataByKeyWord();
        SlangWord.loadData();

        mainScreen();
        addSlangWordScreen();
        searchByKeywordScreen();
        searchByDefinitionScreen();
        editSlangWordScreen();
        deleteSlangWordScreen();
        resetSlangWordScreen();
        randomSlangWord();
        guessDefiniton();
        guessSlangWord();


//        System.out.println(SlangWord.searchDefinition("HOLS"));





    }
}