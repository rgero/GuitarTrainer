package net.roymond.guitartrainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomChordLoader extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField inputDirectory;
    private JButton browseButton;
    private JPanel directoryPanel;
    private JPanel FileTypePanel;
    private JRadioButton pngButton;
    private JRadioButton jpgButton;
    private JButton clearButton;
    private ButtonGroup fileTypeButtonGroup;

    private String inputDir;
    private String fileExt;
    private List<Chord> results;

    CustomChordLoader(String inputDir, String fileExt) {
        this.results = new ArrayList<>();
        this.inputDir = inputDir;
        this.fileExt = fileExt;

        setTitle("Loading Custom Chords");
        setResizable(false);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        fileTypeButtonGroup = new ButtonGroup();
        fileTypeButtonGroup.add(pngButton);
        fileTypeButtonGroup.add(jpgButton);


        if (inputDir != null && !inputDir.isEmpty()){
            inputDirectory.setText(inputDir);
        }
        if (fileExt.equals("png")){
            pngButton.setSelected(true);
        }
        if (fileExt.equals("jpg")){
            jpgButton.setSelected(true);
        }

        browseButton.addActionListener(e -> {
            JFileChooser exportDirChooser = new JFileChooser();
            exportDirChooser.setCurrentDirectory(new File("."));
            exportDirChooser.setDialogTitle("Select an export directory");
            exportDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            exportDirChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = exportDirChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String exportPath = exportDirChooser.getSelectedFile().getAbsolutePath();
                inputDirectory.setText(exportPath);
            }
        });
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("net/roymond/Resources/FolderIcon.png")).getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
        browseButton.setIcon(imgIcon);

        clearButton.addActionListener(e -> {
            fileTypeButtonGroup.clearSelection();
            inputDirectory.setText("");
            this.results.clear();
            this.inputDir = "";
            this.fileExt = "";
        });

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /***
     *  This looks into the directory. It looks for all files with an extension
     *  It'll create a custom chord object and add it to a list.
     *
     *  This list will be stored and available to the setup window as a result.
     */
    private void loadChords(){
        File dir = new File(inputDir);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith(fileExt));
        if (files != null) {
            for(File f : files){
                int lastPeriod = f.getName().lastIndexOf('.');
                String name = f.getName().substring(0,lastPeriod);

                BufferedImage loadedImage = null;
                try {
                    loadedImage = ImageIO.read(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if(loadedImage != null) {
                    results.add(new Chord(name, new ImageIcon(loadedImage.getScaledInstance(340, 416, Image.SCALE_DEFAULT))));
                }
            }
        }

    }

    private void onOK() {

        /*
            Validation works like this:
                - Checks to see if the directory is real
                    - If it is empty or invalid, a message is presented
                - Checks to see if the user has selected a file type
                    - If neither is selected, a message is presented
                - Proceeds

                Edge Case: If nothing is selected, nothing should be updated.
         */
        String testDir = inputDirectory.getText();
        if (new File(testDir).isDirectory()) {
            if (fileTypeButtonGroup.getSelection() != null) {
                inputDir = testDir;
                if (pngButton.isSelected()) {
                    fileExt = ".png";
                } else if (jpgButton.isSelected()) {
                    fileExt = ".jpg";
                }
                loadChords();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Select a file extension.");
            }
        } else if (!pngButton.isSelected() || !jpgButton.isSelected()) {
            results = new ArrayList<>();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No Valid Import Directory selected");
        }

    }

    private void onCancel() {
        results = null;
        dispose();
    }

    List<Chord> getResults(){
        return results;
    }
}
