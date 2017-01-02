package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Roymond on 1/1/2017.
 */
public class Trainer {
    static JFrame trainerFrame;
    private JButton backToSetupButton;
    private JPanel Trainer;

    private int numChords;
    private float timeBetween;

    private void establishFunctionality(){

        backToSetupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupWindow setupWindow = new SetupWindow();
                setupWindow.launchSetupWindow();
                 trainerFrame.dispose();
            }
        });

    }


    public Trainer(int numChords, float timeBetween){
        this.numChords = numChords;
        this.timeBetween = timeBetween;

        establishFunctionality(); //This is totally a hack because of the two constructors.
    }

    public Trainer(){

        establishFunctionality(); //This is totally a hack because of the two constructors.
    }

    public void launchWindow() {
        trainerFrame = new JFrame("Trainer");
        trainerFrame.setTitle("Roy's Guitar Trainer");
        trainerFrame.setContentPane(new Trainer().Trainer);
        trainerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        trainerFrame.pack();
        trainerFrame.setVisible(true);
        trainerFrame.setResizable(false);
    }


}
