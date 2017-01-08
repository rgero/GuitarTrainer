package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private boolean customChords;

    private void updateLabels(){
        numberOfChordsLabel.setText(String.valueOf(numberOfChords));
        timeBetweenChordsLabel.setText(String.format("%2.2f",timeBetweenChords));
        numberOfChordsSelected.setText(String.valueOf(chordList.size()));
    }

    private HashMap<String, ImageIcon> initializeDefaultChordMap(){
        HashMap<String, ImageIcon> chordMap = new HashMap<>();
        chordMap.put("A", new ImageIcon(".\\Chords\\A.png"));
        chordMap.put("B", new ImageIcon(".\\Chords\\B.png"));
        chordMap.put("C", new ImageIcon(".\\Chords\\C.png"));
        chordMap.put("D", new ImageIcon(".\\Chords\\D.png"));
        chordMap.put("E", new ImageIcon(".\\Chords\\E.png"));
        chordMap.put("F", new ImageIcon(".\\Chords\\F.png"));
        chordMap.put("G", new ImageIcon(".\\Chords\\G.png"));
        chordMap.put("Am", new ImageIcon(".\\Chords\\Am.png"));
        chordMap.put("Bm", new ImageIcon(".\\Chords\\Bm.png"));
        chordMap.put("Cm", new ImageIcon(".\\Chords\\Cm.png"));
        chordMap.put("Dm", new ImageIcon(".\\Chords\\Dm.png"));
        chordMap.put("Em", new ImageIcon(".\\Chords\\Em.png"));
        chordMap.put("Fm", new ImageIcon(".\\Chords\\Fm.png"));
        chordMap.put("Gm", new ImageIcon(".\\Chords\\Gm.png"));

        return chordMap;
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
        customChords = false;

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
            HashMap<String, ImageIcon> chordMap = null;
            if (customChords) {
                //TODO Custom chord
            } else {
                chordMap = initializeDefaultChordMap();
            }


            Trainer training = new Trainer(numberOfChords, timeBetweenChords, chordList, chordMap);
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

            //TODO: Return customChords True when data is returned

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





