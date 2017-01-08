package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Roymond on 1/1/2017.
 * This is where the actual training session will be handled.
 */
public class Trainer {
    private static JFrame trainerFrame;
    private JButton backToSetupButton;
    private JPanel Trainer;
    private JLabel currentChordLabel;
    private JLabel numberOfChordsRemaining;
    private JLabel timeRemaining;
    private JPanel ChordRemaining;
    private JPanel timeRemainingPanel;
    private JPanel currentChordPanel;
    private JLabel imageLabel;
    private ImageIcon imageIcon;

    private int numChords;
    private float timeBetween;
    private float time;
    private List<String> chordList;
    private String currentChord;
    private Random rand;
    private Timer timer;
    private HashMap<String, ImageIcon> chordMap;

    private void chooseNewChord(){
        String tempSelected = currentChord;
        if (chordList.size() > 1) {
            while (tempSelected.equals(currentChord)) {
                tempSelected = chordList.get(rand.nextInt(chordList.size()));
            }
        } else {
            tempSelected = chordList.get(0);
        }
        currentChord = tempSelected;
        currentChordLabel.setText(currentChord);
        if ( !chordMap.get(currentChord).equals(null) ){
            imageLabel.setIcon(chordMap.get(currentChord));
        }
    }

    private void runTimer(){
        time = timeBetween;
        int delay = 10;
        numberOfChordsRemaining.setText(String.valueOf(numChords));
        timer = new Timer(delay, e -> {
            if (time <= .011) {
                numChords--;
                chooseNewChord();

                numberOfChordsRemaining.setText(String.valueOf(numChords));

                time = timeBetween;
                if (numChords < 1){
                    timer.stop();
                }
            } else {
                time -= 0.01;
            }
            timeRemaining.setText(String.format("%.2f", time));
        });

        timer.start();
    }


    public Trainer(int numChords, float timeBetween, List<String> chordList, HashMap<String, ImageIcon> map){

        this.numChords = numChords;
        this.timeBetween = timeBetween;
        this.chordList = chordList;
        this.currentChord = "";
        this.chordMap = map;
        this.rand = new Random();

        backToSetupButton.addActionListener(e -> {
            SetupWindow setupWindow = new SetupWindow();
            setupWindow.launchSetupWindow();
            trainerFrame.dispose();
        });

        chooseNewChord();

        runTimer();
    }

    void launchWindow() {
        trainerFrame = new JFrame("Trainer");
        trainerFrame.setTitle("Roy's Guitar Trainer");
        trainerFrame.setContentPane(this.Trainer);
        trainerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        trainerFrame.pack();
        trainerFrame.setVisible(true);
        trainerFrame.setResizable(false);
    }


}
