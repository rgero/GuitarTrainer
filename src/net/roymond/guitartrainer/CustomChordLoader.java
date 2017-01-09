package net.roymond.guitartrainer;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

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
    private boolean customChords;
    private String fileExt;
    private String[] results;

    CustomChordLoader(boolean customChords, String inputDir, String fileExt) {

        this.customChords = customChords;
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

        customChords = false;

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

        clearButton.addActionListener(e -> {
            fileTypeButtonGroup.clearSelection();
            inputDirectory.setText("");
            this.customChords = false;
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

    private void onOK() {
        // validation needed.
        String testDir = inputDirectory.getText();
        if (new File(testDir).isDirectory()) {
            if (fileTypeButtonGroup.getSelection() != null) {
                inputDir = testDir;
                customChords = true;
                if (pngButton.isSelected()) {
                    fileExt = "png";
                } else if (jpgButton.isSelected()) {
                    fileExt = "jpg";
                }
                results = new String[]{String.valueOf(customChords),inputDir,fileExt};
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Select a file extension.");
            }
        } else if (!pngButton.isSelected() || !jpgButton.isSelected()) {
            results = new String[]{"false","",""};
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No Import Directory selected");
        }

    }

    private void onCancel() {
        results = null;
        dispose();
    }

    String[] getResults(){
        return results;
    }
}
