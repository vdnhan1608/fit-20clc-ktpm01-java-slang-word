import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    /*
     * Desc: Screen search by definition
     * */
    public static void searchByDefinitionScreen()
    {
        searchByDefinitionScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByDefinitionScreen.setSize(200,200);
        Container pane = searchByDefinitionScreen.getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel label = new JLabel("Search");
        JTextField textField = new JTextField(10);
        pane1.add(label);
        pane1.add (textField);

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


    }


    /*
    * Desc: Create search by keyword screen
    * */
    public static void searchByKeywordScreen()
    {
        searchByKeywordScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        searchByKeywordScreen.pack();
    }


    /*
    * Desc: Add a new slang word screen
    * */
    public static void addSlangWordScreen()
    {
        addSlangWordScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        addSlangWordScreen.pack();

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
    }


    /*
    * Desc: Edit slang word screen
    * */
    public static void editSlangWordScreen()
    {
        editSlangWordScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }


    /*
    * Desc: Delete slang word screen
    * */

    public static void deleteSlangWordScreen()
    {
        deleteSlangWordScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


    }

    /*
    * Desc: Reset slang word (alert actually)
    *
    * */

    public static void resetSlangWordScreen()
    {
        SlangWord.resetSlangWord();

        resetSlangWordScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    /*
    * Desc: Random slang word
    * */
    public static void randomSlangWord()
    {
        HashMap<String, String> slangWord = SlangWord.randomSlangWord();
        String word = slangWord.keySet().toString().substring(1,  slangWord.keySet().toString().length()-1);
        String definition = slangWord.get(word);
        randomSlangWord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
//        frame.setVisible(true);
    }
    /*
    * Set up listener for the button
    * */
    public static void setUpListener()
    {
        searchBtnByDefinition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("search by definition");
            }
        });
    }


    public static void  main(String[] args) throws IOException {

        SlangWord.loadDataByDefinition();
        SlangWord.loadDataByKeyWord();
        SlangWord.loadData();

        mainScreen();
        searchByKeywordScreen();
        searchByDefinitionScreen();
        setUpListener();
        editSlangWordScreen();
        deleteSlangWordScreen();
        resetSlangWordScreen();
        randomSlangWord();

        randomSlangWord.setVisible(true);

//        System.out.println(SlangWord.searchDefinition("HOLS"));





    }
}