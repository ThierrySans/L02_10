package zodiac.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBList;
import org.bouncycastle.util.Pack;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.coursework.Assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;


public class AssignmentManagerMenu {
    public static String TITLE_LABEL = "ASSIGNMENT MANAGER";
    public static String GET_ASSIGNMENT = "Get Assignments";
    public static String ADD_ASSIGNMENT = "Add Assignments";
    public static String ADD_QUESTION = "Add Question";
    public static String ADD_QUESTION_TO_ASS = "Add Question to Assignment";

    private JPanel panel;
    private JPanel cards;
    private JPanel getAssignmentsPanel;

    public void generateContents(Container window)
    {
        // Create the combo box JPanel and add all the options into it
        JPanel cbPanel = new JPanel(new FlowLayout());
        String cbOptionList[] = {GET_ASSIGNMENT, ADD_ASSIGNMENT, ADD_QUESTION, ADD_QUESTION_TO_ASS};
        JComboBox cbOptions = new JComboBox(cbOptionList);
        cbOptions.setEditable(false);
        cbOptions.addItemListener(new OptionsItemListener());
        cbPanel.add(cbOptions);

        // Create JPanel to hold the Combobox and a title card
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel msg = new JLabel(TITLE_LABEL);
        msg.setHorizontalAlignment(JLabel.CENTER);
        msg.setVerticalAlignment(JLabel.CENTER);
        msg.setFont(new Font("Bitstream Vera Sans", 0, 48));

        topPanel.add(msg, BorderLayout.NORTH);
        topPanel.add(cbPanel, BorderLayout.PAGE_END);

        // create the various panels for each function
        getAssignmentsPanel = new GetAssignmentsMenu().generateContents();

//        createGetClassPanel();
//        createGetStudentsPanel();

        // create the JPanel to hold all the cards and add each card
        cards = new JPanel(new CardLayout());
        cards.add(getAssignmentsPanel, GET_ASSIGNMENT);
        cards.add(new AddAssignmentMenu().generateContents(), ADD_ASSIGNMENT);
//        cards.add(createAddStudentPanel(), ADD_STUDENT);
//        cards.add(panelGetStudents, GET_STUDENT);

        // Add everything to the pane
        window.add(topPanel, BorderLayout.PAGE_START);
        window.add(cards, BorderLayout.CENTER);
    }

    private class OptionsItemListener implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)itemEvent.getItem());
        }
    }
}
