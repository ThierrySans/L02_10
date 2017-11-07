package zodiac.gui;

import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.coursework.Assignment;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class AddQuestionToAssignmentMenu {
    private Assignment currAssignment;
    private JBList questionList;
    private DefaultListModel model;

    public JPanel generateContents()
    {
        JPanel panel = new JPanel(new FlowLayout());

        // Create combo box with all assignments
        List<Assignment> assignments = new AssignmentDao().getAllAssignments();
        JComboBox questions = new JComboBox(assignments.toArray());

        // Create button to add questions to assignments
        // TODO: create action listener
        JButton addButton = new JButton("Add Questions");
        
        this.model = new DefaultListModel();
        this.questionList = new JBList(this.model);
        JScrollPane pane = new JBScrollPane(this.questionList);

        panel.add(questions);
        panel.add(pane);

        return panel;
    }
}
