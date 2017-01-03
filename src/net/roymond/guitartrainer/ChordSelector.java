package net.roymond.guitartrainer;

import javax.swing.*;
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
    private List<String> selectedChords;

    public ChordSelector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMajorChords.clearSelection();
                selectedMinorChords.clearSelection();
            }
        });

        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMajorChords.addSelectionInterval(0, selectedMajorChords.getLastVisibleIndex());
                selectedMinorChords.addSelectionInterval(0, selectedMinorChords.getLastVisibleIndex());
            }
        });

    }

    private void onOK() {
        List<String> selectedChords = new Vector<String>();
        if (!selectedMajorChords.isSelectionEmpty()){
            System.out.println("Major is not empty");
            selectedChords.addAll(selectedMajorChords.getSelectedValuesList());
        }
        if (!selectedMinorChords.isSelectionEmpty()){
            System.out.println("Minor is not empty");
            selectedChords.addAll(selectedMinorChords.getSelectedValuesList());
        }

        this.selectedChords = selectedChords;

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public List<String> getResult(){
        return this.selectedChords;
    }
}
