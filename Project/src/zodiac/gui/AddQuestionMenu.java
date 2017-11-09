package zodiac.gui;


import zodiac.dao.coursework.QuestionDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddQuestionMenu {
    public JPanel generateContents() {
        // Create the AddClass panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Generate JLabels for each text field
        JLabel label1 = new JLabel("Enter your Question: ");
        label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label1.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Create the text fields
        JTextField textQuestion = new JTextField();
        textQuestion.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textQuestion.setMaximumSize(new Dimension(600, 70));

        // Create the submit button and link an action listener to it
        JButton button = new JButton("Submit");
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);

        button.addActionListener(new AddQuestionListener(textQuestion));

        // Add contents to the panel
        panel.add(label1);
        panel.add(textQuestion);
        panel.add(button);

        return panel;
    }

    private class AddQuestionListener implements ActionListener {
        private JTextField question;

        public AddQuestionListener(JTextField question) {
            this.question = question;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new QuestionDao().addQuestion(question.getText());
        }
    }
}
