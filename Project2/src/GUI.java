/*
 * File: GUI.java
 *
 * Description: This file contains the class implementation for the GUI class. This class is the one responsible for
 * displaying a user interface and make the user to be able to input a filename or look for a file on disk.
 *
 * Authors: Carlos Alvarenga, Xiaochen Zhang
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <h1>GUI</h1>
 * <h2>Purpose</h2>
 * The GUI class provides all methods to initialize the graphical user interface and track and received all user input
 */
public class GUI extends JPanel {

    private JTextField fldText;
    private JTextField fldFileName;
    private JButton browseFileButton;
    private JRadioButton rbText;
    private File selectedFile;
    private String user_input;

    /**
     * Initializes an instance of the "Voting System" graphical user interface (GUI).
     */
    public GUI() {
        user_input = "";
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        rbText = new JRadioButton("Enter filename: ");
        JRadioButton rbFile = new JRadioButton("Search For File: ");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbText);
        bg.add(rbFile);

        fldText = new JTextField(10);
        fldFileName = new JTextField(10);
        fldFileName.setEditable(false);
        browseFileButton = new JButton("...");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(rbText, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(fldText, gbc);

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        add(rbFile, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(fldFileName, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.NONE;
        add(browseFileButton, gbc);

        fldText.setEnabled(false);
        fldFileName.setEnabled(false);
        browseFileButton.setEnabled(true);

        ActionListener listener = e -> {
            fldText.setEnabled(rbText.isSelected());
            fldFileName.setEnabled(!rbText.isSelected());
            browseFileButton.setEnabled(!rbText.isSelected());

            if (rbText.isSelected()) {
                fldText.requestFocusInWindow();
            }
        };

        rbFile.addActionListener(listener);
        rbText.addActionListener(listener);

        browseFileButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                fldFileName.setText(selectedFile.getName());
            }
        });
    }

    /**
     * Method responsible for obtaining the string input (filename) that the user has provided and that the GUI captured
     * either through the text field or search-for-file window
     * @return A {@code String} denoting the filename of the ballot file that is to be processed.
     */
    public String getUserInput() {
        if (rbText.isSelected()) {
            user_input = fldText.getText().replaceAll("[[,]]","");
        } else if (selectedFile != null) {
            user_input = selectedFile.getName();
        }
        return user_input;
    }

    /**
     * Mutator method for the user_input variable of the {@code GUI} instance.
     * This variable denotes what filename the user entered or the name of the file that the user selected in the GUI.
     * @param user_input A {@code String} denoting the input filename provided by the user.
     */
    public void setUserInput(String user_input) {
        this.user_input = user_input;
    }
}