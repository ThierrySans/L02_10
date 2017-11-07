package zodiac.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBList;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.coursework.Assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GetAssignmentsMenu {
    private JPanel panel;
    private JBList assignList;
    private DefaultListModel model;

    /**
     * Generate the contents of the Assignment manager menu.
     */
    public JPanel generateContents()
    {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel prompt = new JLabel("Enter a Course Code: ");
        JTextField textCourseCode = new JTextField();
        textCourseCode.setPreferredSize(new Dimension(300, 25));
        textCourseCode.setMinimumSize(new Dimension(300, 25));
        JButton submit = new JButton("Search");

        submit.addActionListener(new getAssignmentsListener(textCourseCode));

        searchPanel.add(prompt);
        searchPanel.add(textCourseCode);
        searchPanel.add(submit);

        this.model = new DefaultListModel();

        assignList = new JBList(model);
        JBScrollPane scrollPane = new JBScrollPane(assignList);


        this.panel.add(searchPanel);
        this.panel.add(scrollPane);

        return this.panel;
    }

    private class getAssignmentsListener implements ActionListener
    {
        private JTextField courseCode;
        getAssignmentsListener(JTextField code)
        {
            this.courseCode = code;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println(this.courseCode);
            List<Assignment> results = new AssignmentDao().getAssignments(this.courseCode.getText());
            model.clear();
            for (Assignment a : results)
            {
//                System.out.println(a.getName());
                model.addElement(a.getName());
            }

            assignList = new JBList(model);
            panel.revalidate();
        }
    }
}
