/*
 * File: GUI.java
 *
 * Description: This file contains the class implementation for the GUI class. This class is the one responsible for displaying a user interface and make the user to be able to input a filename or look for a file on disk.
 *
 * Authors: Xiaochen Zhang
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            UserInputPane userInputPane = new UserInputPane();
            int result = JOptionPane.showConfirmDialog(null, userInputPane, "Ballot File", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    List<String> text = userInputPane.getText();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public class UserInputPane extends JPanel {

        private JTextField fldText;
        private JTextField fldFileName;
        private JButton browseFileButton;

        private File selectedFile;

        private JRadioButton rbText;
        private JRadioButton rbFile;

        public UserInputPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            rbText = new JRadioButton("Text: ");
            rbFile = new JRadioButton("File: ");
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
            browseFileButton.setEnabled(false);

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

                int returnValue = chooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    fldFileName.setText(selectedFile.getName());
                }
            });

        }

        public List<String> getText() throws IOException {
            List<String> text = new ArrayList<>(25);
            if (rbText.isSelected()) {
                text.add(fldText.getText());
            } else if (selectedFile != null) {

                try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                    String value;
                    while ((value = br.readLine()) != null) {
                        text.add(value);
                    }
                }

            }
            return text;
        }

    }

}
