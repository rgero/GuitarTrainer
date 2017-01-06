package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Roymond on 1/1/2017.
 */
public class Trainer {
    static JFrame trainerFrame;
    private JButton backToSetupButton;
    private JPanel Trainer;
    private JLabel chordListLabel;
    private JLabel numberOfChordsRemaining;
    private JLabel timeRemaining;

    private int numChords;
    private float timeBetween;
    private float time;
    private List<String> chordList;
    Timer timer;


    private String processChordList(){
        StringBuilder sb = new StringBuilder();
        for(String s : chordList){
            sb.append(s);
            sb.append(" ");
        }
        System.out.println("1: " + sb.toString());
        return sb.toString();
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
                    numberOfChordsRemaining.setText(String.valueOf(numChords));
                    time = timeBetween;
                    //THIS IS WHERE WE CHOOSE NEW CHORD
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

        backToSetupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupWindow setupWindow = new SetupWindow();
                setupWindow.launchSetupWindow();
                trainerFrame.dispose();
            }
        });

        this.chordListLabel.setText(processChordList());

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
