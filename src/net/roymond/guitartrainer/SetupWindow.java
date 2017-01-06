package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roymond on 1/1/2017.
 * This is the setup for the program (TODO write this better)
 */
public class SetupWindow {

    private static JFrame frame;
    private JPanel GuitarTrainer;
    private JLabel title;
    private JButton decreaseCNB;
    private JButton increaseCNB;
    private JButton decreaseTBCB;
    private JButton increaseTBCB;
    private JLabel numberOfChordsLabel;
    private JLabel timeBetweenChordsLabel;
    private JButton startButton;
    private JButton launchChordSelect;
    private JPanel selectedChordPanel;
    private JLabel numberOfChordsSelected;
    private JButton loadCustomChords;
    private JPanel chords;
    private JPanel time;

    private int numberOfChords;
    private float timeBetweenChords;
    private List<String> chordList;

    private void updateLabels(){
        numberOfChordsLabel.setText(String.valueOf(numberOfChords));
        timeBetweenChordsLabel.setText(String.format("%2.2f",timeBetweenChords));
        numberOfChordsSelected.setText(String.valueOf(chordList.size()));
    }

    private void checkChords(){
        if (chordList.isEmpty()){
            chordList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"
                    , "Am", "Bm", "Cm", "Dm", "Em", "Fm", "Gm"));
        }
    }


    SetupWindow(){

        //Initial values.
        numberOfChords = 10;
        timeBetweenChords = 1.f;
        chordList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"
                , "Am", "Bm", "Cm", "Dm", "Em", "Fm", "Gm"));
        updateLabels();

        decreaseCNB.addActionListener(e -> {
            if (numberOfChords > 1){
                numberOfChords--;
                updateLabels();
            } else {
                JOptionPane.showMessageDialog(null, "You must have at least one chord.");
            }
        });

        increaseCNB.addActionListener(e -> {
            if (numberOfChords < 25){
                numberOfChords++;
                updateLabels();
            }
        });

        increaseTBCB.addActionListener(e -> {
            if (timeBetweenChords < 25.0f){
                timeBetweenChords += 0.25f;
                updateLabels();
            }
        });

        decreaseTBCB.addActionListener(e -> {
            if (timeBetweenChords > .5f){
                timeBetweenChords -= 0.25f;
                updateLabels();
            } else {
                JOptionPane.showMessageDialog(null, "What are you superhuman? Time between switches must at least be half a second.");
            }
        });

        startButton.addActionListener(e -> {
            Trainer training = new Trainer(numberOfChords, timeBetweenChords, chordList);
            training.launchWindow();
            frame.setVisible(false);

        });

        launchChordSelect.addActionListener(e -> {
            ChordSelector dialog = new ChordSelector();
            dialog.pack();
            dialog.setVisible(true);

            chordList = dialog.getResult();
            checkChords();
            updateLabels();
        });

        loadCustomChords.addActionListener(e -> {
            CustomChordLoader dialog = new CustomChordLoader();
            dialog.pack();
            dialog.setVisible(true);
        });

    }

    void launchSetupWindow() {
        frame = new JFrame("SetupWindow");
        frame.setTitle("Roy's Guitar Trainer");
        frame.setContentPane(new SetupWindow().GuitarTrainer);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

}





