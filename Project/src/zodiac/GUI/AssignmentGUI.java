package zodiac.GUI;

import zodiac.action.AssignmentAction;
import zodiac.action.ClassAction;
import zodiac.definition.Class;
import zodiac.definition.coursework.Assignment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class AssignmentGUI {
    private JComboBox comboBox1;
    private JPanel mypanel;
    private JLabel lablel;
    private JPanel panel1;
    private JList assignments_Jlist;
    private JButton changeVisibilityButton;

    private ClassAction classAction=new ClassAction();
    private AssignmentAction  assignmentAction=new AssignmentAction();
    private  List<Assignment> assignments=new ArrayList<Assignment>();
    /**
     * Constructor
     */
    public AssignmentGUI() {
        JFrame frame = new JFrame("AssignmentGUI");
        frame.setSize(400,300);
        frame.setContentPane(this.mypanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        init();
        frame.setVisible(true);

    }

    /**
     * Get all the classes and put them into the JComboBox
     */
    private void init()
    {
        List<Class> classes=classAction.getClasses();
        for (int i=0;i<classes.size();i++)
        {
            this.comboBox1.addItem(classes.get(i).getCourseCode());
        }

        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                show_assignments_Jlist(comboBox1.getSelectedItem().toString());
                System.out.println("Selected index=" + comboBox1.getSelectedIndex());
            }
        });

        changeVisibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select_index=assignments_Jlist.getSelectedIndex();
                assignmentAction.changeAssignmentVisibility(assignments.get(select_index));
                show_assignments_Jlist(comboBox1.getSelectedItem().toString());

            }
        });
     }

    /**
     * Show assignments in the Jlist when JComboBox choose a  course
     * @param course_code
     */
     private void show_assignments_Jlist(String course_code)
     {
         assignments= assignmentAction.checkAssignments(course_code);

         DefaultListModel listModel = new DefaultListModel();
            for(int i=0;i<assignments.size();i++)
            {
                listModel.addElement(assignments.get(i).getName()+"       "+assignments.get(i).getVisibility());
            }
         assignments_Jlist.setModel(listModel);
     }


}
