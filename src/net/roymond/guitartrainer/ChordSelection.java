package net.roymond.guitartrainer;

import javax.swing.*;

/**
 * Created by roymond on 1/2/17.
 */
public class ChordSelection {
    private JPanel ChordSelection;
    private JList selectedChords;
    private JFrame chordSelectionFrame;

    public ChordSelection(){

    }

    public void launchWindow() {
        chordSelectionFrame = new JFrame("ChordSelection");
        chordSelectionFrame.setTitle("Roy's Guitar Trainer");
        chordSelectionFrame.setContentPane(this.ChordSelection);
        //chordSelectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chordSelectionFrame.pack();
        chordSelectionFrame.setVisible(true);
        chordSelectionFrame.setResizable(false);
    }


}
