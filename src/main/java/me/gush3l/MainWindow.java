package me.gush3l;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;
import java.util.List;

public class MainWindow {

    public static void createJFrame() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Found Words"));

        JTextArea foundWordsTextArea = new JTextArea(30, 65);
        foundWordsTextArea.setEditable(false);
        foundWordsTextArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(foundWordsTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scroll);

        JFrame frame = new JFrame("Word Puzzle Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel wordPlaceholderLabel = new JLabel("Word Placeholder");
        JTextField wordPlaceholderTextField = new JTextField(10);
        wordPlaceholderTextField.setEditable(true);
        JLabel wordLettersLabel = new JLabel("Letters List");
        JTextField wordLetters = new JTextField(10);
        wordLetters.setEditable(true);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            foundWordsTextArea.setText("");
            List<String> foundWords = WordHandler.getMatchingWordsList(wordPlaceholderTextField.getText(),wordLetters.getText());
            for (String word : foundWords) {
                foundWordsTextArea.setText(foundWordsTextArea.getText()+System.lineSeparator()+word);
            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener( e -> {
            foundWordsTextArea.setText("");
            wordPlaceholderTextField.setText("");
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openFileItem = new JMenuItem("Open Word Dictionary");
        fileMenu.add(openFileItem);
        openFileItem.addActionListener(e -> {
            openFileChooser(frame);
        });

        panel.add(wordPlaceholderLabel);
        panel.add(wordPlaceholderTextField);
        panel.add(wordLettersLabel);
        panel.add(wordLetters);
        panel.add(sendButton);
        panel.add(resetButton);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.setSize(780,625);
    }

    public static void openFileChooser(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the words dictionary file...");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Main.setWordDictionaryFile(selectedFile);
            WordHandler.createWordsList();
            WordHandler.getLongestWordLenght();
        }
    }

}
