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

    private int numChords;
    private float timeBetween;
    private List<String> chordList;


    private String processChordList(){
        StringBuilder sb = new StringBuilder();
        for(String s : chordList){
            sb.append(s);
            sb.append(" ");
        }
        System.out.println("1: " + sb.toString());
        return sb.toString();
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
