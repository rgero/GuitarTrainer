package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

/**
 * Created by Roymond on 1/1/2017.
 */
public class Trainer {
    static JFrame trainerFrame;
    private JButton backToSetupButton;
    private JPanel Trainer;
    private JLabel currentChordLabel;
    private JLabel numberOfChordsRemaining;
    private JLabel timeRemaining;
    private JPanel ChordRemaining;
    private JPanel timeRemainingPanel;
    private JPanel currentChordPanel;

    private int numChords;
    private float timeBetween;
    private float time;
    private List<String> chordList;
    private String currentChord;
    private Random rand;
    private Timer timer;


    private String processChordList(){
        StringBuilder sb = new StringBuilder();
        for(String s : chordList){
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

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
    }

    private void runTimer(){
        time = timeBetween;
        int delay = 10;
        numberOfChordsRemaining.setText(String.valueOf(numChords));
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (time <= .011) {
                    numChords--;
                    chooseNewChord();
                    currentChordLabel.setText(currentChord);
                    numberOfChordsRemaining.setText(String.valueOf(numChords));
                    time = timeBetween;
                    if (numChords < 1){
                        timer.stop();
                    }
                } else {
                    time -= 0.01;
                }
                timeRemaining.setText(String.format("%.2f", time));
            }
        });

        timer.start();
    }


    public Trainer(int numChords, float timeBetween, List<String> chordList){

        this.numChords = numChords;
        this.timeBetween = timeBetween;
        this.chordList = chordList;
        this.currentChord = "";
        this.rand = new Random();

        backToSetupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupWindow setupWindow = new SetupWindow();
                setupWindow.launchSetupWindow();
                trainerFrame.dispose();
            }
        });

        chooseNewChord();
        currentChordLabel.setText(currentChord);

        runTimer();
    }

    public void launchWindow() {
        trainerFrame = new JFrame("Trainer");
        trainerFrame.setTitle("Roy's Guitar Trainer");
        trainerFrame.setContentPane(this.Trainer);
        trainerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        trainerFrame.pack();
        trainerFrame.setVisible(true);
        trainerFrame.setResizable(false);
    }


}
