package zodiac.gui;

import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.coursework.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//import com.intellij.ui.components.JBScrollPane;
//import com.intellij.ui.components.JBList;


public class GetAssignmentsMenu {
    private JPanel panel;
    private JList assignList;
    private DefaultListModel model;

    /**
     * Generate the contents of the Assignment manager menu.
     */
    public JPanel generateContents() {
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

        assignList = new JList(model);
        JScrollPane scrollPane = new JScrollPane(assignList);


        this.panel.add(searchPanel);
        this.panel.add(scrollPane);

        addMouseListener(assignList);

        return this.panel;
    }

    // Open assignment editor when on double click assignment
    private void addMouseListener(JList list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    Assignment assignment = (Assignment)model.get(index);
                    new EditAssignmentMenu(assignment).setVisible(true);
                }
            }
        });
    }

    private class getAssignmentsListener implements ActionListener {
        private JTextField courseCode;

        getAssignmentsListener(JTextField code) {
            this.courseCode = code;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println(this.courseCode);
            List<Assignment> results = new AssignmentDao().getAssignments(this.courseCode.getText());
            model.clear();
            for (Assignment a : results) {
//                System.out.println(a.getName());
                // What the model displays is defined in Assignment.ToString
                model.addElement(a);
            }

            assignList = new JList(model);
            panel.revalidate();
        }
    }
}
