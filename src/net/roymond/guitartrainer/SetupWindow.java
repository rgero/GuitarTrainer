package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
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
    private Image iconImg;

    private int numberOfChords;
    private float timeBetweenChords;
    private List<String> chordList;
    private HashMap<String, ImageIcon> chordMap;

    private List<String> customChordList;
    private String inputDir;
    private String fileExt;

    /**
     * This function updates all of the appropriate labels on the window
     */
    private void updateLabels(){
        numberOfChordsLabel.setText(String.valueOf(numberOfChords));
        timeBetweenChordsLabel.setText(String.format("%2.2fs",timeBetweenChords));
        numberOfChordsSelected.setText(String.valueOf(chordList.size()));
    }


    /**
     * This function creates the default hashmap that contains all the chords
     * supported out of the box. This can be overwritten if the user specifies
     * a "Custom Chord" directory.
     * @return Hashmap of default chords.
     */
    private HashMap<String, ImageIcon> initializeDefaultChordMap(){
        HashMap<String, ImageIcon> chordMap = new HashMap<>();

        chordMap.put("A", new ImageIcon(this.getClass().getResource("/net/roymond/chords/A.png")));
        chordMap.put("B", new ImageIcon(this.getClass().getResource("/net/roymond/chords/B.png")));
        chordMap.put("C", new ImageIcon(this.getClass().getResource("/net/roymond/chords/C.png")));
        chordMap.put("D", new ImageIcon(this.getClass().getResource("/net/roymond/chords/D.png")));
        chordMap.put("E", new ImageIcon(this.getClass().getResource("/net/roymond/chords/E.png")));
        chordMap.put("F", new ImageIcon(this.getClass().getResource("/net/roymond/chords/F.png")));
        chordMap.put("G", new ImageIcon(this.getClass().getResource("/net/roymond/chords/G.png")));
        chordMap.put("Am", new ImageIcon(this.getClass().getResource("/net/roymond/chords/Am.png")));
        chordMap.put("Bm", new ImageIcon(this.getClass().getResource("/net/roymond/chords/Bm.png")));
        chordMap.put("Cm", new ImageIcon(this.getClass().getResource("/net/roymond/chords/C.png")));
        chordMap.put("Dm", new ImageIcon(this.getClass().getResource("/net/roymond/chords/Dm.png")));
        chordMap.put("Em", new ImageIcon(this.getClass().getResource("/net/roymond/chords/Em.png")));
        chordMap.put("Fm", new ImageIcon(this.getClass().getResource("/net/roymond/chords/Fm.png")));
        chordMap.put("Gm", new ImageIcon(this.getClass().getResource("/net/roymond/chords/Gm.png")));

        return chordMap;
    }

    SetupWindow(){

        //Adding the icon
        Toolkit kit = Toolkit.getDefaultToolkit();
        iconImg = kit.createImage(ClassLoader.getSystemResource("net/roymond/Resources/Icon.png"));

        //Initial values.
        numberOfChords = 10;
        timeBetweenChords = 10.f;
        chordList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"
                , "Am", "Bm", "Cm", "Dm", "Em", "Fm", "Gm"));
        chordMap = initializeDefaultChordMap();
        updateLabels();

        customChordList = new ArrayList<>();
        inputDir = "";
        fileExt = "";

        //Action Listeners for the buttons.
        decreaseCNB.addActionListener(e -> {
            if (numberOfChords > 1){
                numberOfChords--;
                updateLabels();
            } else {

                //Present a popup window to the user when they try to enter invalid ranges.
                JOptionPane.showMessageDialog(null, "You must have at least one chord.");
            }
        });


        increaseCNB.addActionListener(e -> {
            if (numberOfChords < 25){
                numberOfChords++;
                updateLabels();
            } else {
                //Present a popup window to the user when they try to enter invalid ranges.
                JOptionPane.showMessageDialog(null, "The current limit is 25 chords.");
            }
        });


        increaseTBCB.addActionListener(e -> {
            if (timeBetweenChords < 25.0f){
                timeBetweenChords += 0.25f;
                updateLabels();
            } else {
                //Present a popup window to the user when they try to enter invalid ranges.
                JOptionPane.showMessageDialog(null, "25 seconds is a long time.");
            }
        });

        decreaseTBCB.addActionListener(e -> {
            if (timeBetweenChords > .5f){
                timeBetweenChords -= 0.25f;
                updateLabels();
            } else {
                JOptionPane.showMessageDialog(null, "What are you superhuman? Time between chords must at least be half a second.");
            }
        });

        //This action listener will initialize the chord map and then launch the "Training Window"
        startButton.addActionListener(e -> {
            Trainer training = new Trainer(frame, numberOfChords, timeBetweenChords, chordList, chordMap);
            training.launchWindow(iconImg);
            frame.setVisible(false);

        });

        // This action listener will launch the chord selection window.
        launchChordSelect.addActionListener(e -> {
            ChordSelector dialog = new ChordSelector(customChordList, chordList);
            dialog.pack();
            dialog.setIconImage(iconImg);
            dialog.setVisible(true);


            List<String> updatedSelection = dialog.getResult();
            if (!updatedSelection.isEmpty())
            {
                chordList = updatedSelection;
            }

            updateLabels();
        });

        /* Current idea for strategy:
                Input - Directory and file type
                Execution - Parse through the folder looking for the file types.
                            Get the names from the files
                            Create a chord map of those names and files
                            Create a chord list (potentially)
                Returns - Chord Map and Chord List.
                    - We'll need the chord map so the trainer can load the images
                    - Ideally if we have the chord list then we can let the user
                      limit their selection
         */
        loadCustomChords.addActionListener(e -> {
            CustomChordLoader dialog = new CustomChordLoader( inputDir, fileExt);
            dialog.setIconImage(iconImg);
            dialog.pack();
            dialog.setVisible(true);

            List<Chord> data;
            data = dialog.getResults();

            if (data != null && data.size()>0 ){
                for (Chord customChord : data){
                    chordMap.put(customChord.getName(), customChord.getImage());
                    customChordList.add(customChord.getName());
                }
            }


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

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The About lives here");

        Action aboutAction = new AbstractAction("About the Chord Drawer") {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutDisplay = new JFrame("AboutDisplay");
                aboutDisplay.setTitle("About Roy's Chord Drawer");
                aboutDisplay.setContentPane(new AboutDisplay().About);
                aboutDisplay.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                aboutDisplay.pack();
                aboutDisplay.setResizable(false);
                aboutDisplay.setVisible(true);
            }
        };

        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(aboutAction);
        menu.add(menuItem);
        menuBar.add(menu);

        frame.setIconImage(iconImg);
        frame.setJMenuBar(menuBar);

    }

}





