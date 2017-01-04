package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    public String decrementTime(){
        if (time <= .002) {
            timer.cancel();
            return "0.00";
        }
        time-=0.001;
        return String.valueOf(time).subSequence(0,4).toString();
    }

    private void runTimer(){
        time = timeBetween;
        int delay = 1;
        int period = 1;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeRemaining.setText(decrementTime());
            }
        }, delay, period);

    }


    public Trainer(int numChords, float timeBetween, List<String> chordList){

        this.numChords = numChords;
        this.timeBetween = timeBetween;
        this.chordList = chordList;
        timer = new Timer();

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
