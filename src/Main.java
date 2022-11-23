import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
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

    /*
    * Set up listener for the button
    * */
    public static void setUpListener(){}

    public static void createAndShowUI()
    {
        JFrame frame = new JFrame("Slang Word");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 600));

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
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(label);
        frame.getContentPane().add(pane);

        frame.pack();
        frame.setVisible(true);
    }
    public static void  main(String[] args) throws IOException {
        SlangWord SlangWord = new SlangWord();
        SlangWord.loadDataByDefinition();
        SlangWord.loadDataByKeyWord();
        SlangWord.loadData();

        createAndShowUI();



    }
}