import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Main  {

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

        contentPane.add(pane1,BorderLayout.PAGE_START);
        contentPane.add(pane2, BorderLayout.PAGE_END);

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

        searchByKeywordScreen.pack();
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

        //mainScreen();
        searchByKeywordScreen();
        setUpListener();

        searchByKeywordScreen.setVisible(true);





    }
}