package net.roymond.guitartrainer;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChordSelector extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList selectedMajorChords;
    private JList selectedMinorChords;
    private JButton selectAll;
    private JButton clearAll;
    private JPanel customChordPanel;
    private JPanel majorChordPanel;
    private JPanel okPanel;
    private JPanel selectAllPanel;
    private JPanel minorChordPanel;
    private JList customChordList;
    private List<String> selectedChords;

    ChordSelector(boolean customChords) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        customChordPanel.setVisible(customChords);




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

        clearAll.addActionListener(e -> {
            selectedMajorChords.clearSelection();
            selectedMinorChords.clearSelection();
        });

        selectAll.addActionListener(e -> {
            selectedMajorChords.addSelectionInterval(0, selectedMajorChords.getLastVisibleIndex());
            selectedMinorChords.addSelectionInterval(0, selectedMinorChords.getLastVisibleIndex());
        });

    }

    private void onOK() {
        List<String> selectedChords = new Vector<>();
        if (!selectedMajorChords.isSelectionEmpty()){
            selectedChords.addAll(selectedMajorChords.getSelectedValuesList());
        }
        if (!selectedMinorChords.isSelectionEmpty()){
            selectedChords.addAll(selectedMinorChords.getSelectedValuesList());
        }

        this.selectedChords = selectedChords;

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.selectedChords = new Vector<>();
        dispose();
    }

    List<String> getResult(){
        return this.selectedChords;
    }
}
