package zodiac.gui;

import zodiac.action.ClassAction;
import zodiac.action.StudentAction;
import zodiac.dao.ClassDao;
import zodiac.definition.Class;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.List;

/**
 * Created by radiantwings on 11/1/17.
 * The MainMenu class is the GUI of the
 * Mainmenu for the app. This main menu is for admins.
 */
public class MainMenu implements ItemListener {
    //Constants
    public static String WELCOME_MESSAGE = "Welcome to the STAT_TRAKER admin app!";
    public static String CREATE_CLASS = "Create a class";
    public static String GET_CLASSES = "Get a list of classes";

    private JPanel mainPanel;
    private JLabel labelIntro;
    private JPanel cbPanel;
    private JComboBox cbOptions;
    private JPanel panelAddClass;
    private JPanel panelGetClass;
    private JTable classesTable;
    private JPanel cards;

    public MainMenu()
    {
        // Set up the main Panel in a Grid Layout
        mainPanel = new JPanel(new GridLayout());
    }

    private void setupMainMenu(Container pane)
    {
        pane.add(new JLabel(WELCOME_MESSAGE), BorderLayout.NORTH);

        JPanel cbPanel = new JPanel(new FlowLayout());
        String cbOptionList[] = {CREATE_CLASS, GET_CLASSES};
        cbOptions = new JComboBox(cbOptionList);
        cbOptions.setEditable(false);
        cbOptions.addItemListener(this);
        cbPanel.add(cbOptions);

        createAddClassPanel(pane);
        createGetClassPanel();

        cards = new JPanel(new CardLayout());
        cards.add(panelAddClass, CREATE_CLASS);
        cards.add(panelGetClass, GET_CLASSES);

        pane.add(cbPanel, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    private void createAddClassPanel(Container pane)
    {
        // Create the AddClass panel
        panelAddClass = new JPanel();
        panelAddClass.setLayout(new BoxLayout(panelAddClass, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Enter Class Code: ");
        label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label1.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel label2 = new JLabel("Enter Class Name: ");
        label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label2.setBorder(new EmptyBorder(20, 0, 10, 0));

        JTextField textCourseCode = new JTextField();
        textCourseCode.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textCourseCode.setMaximumSize(new Dimension(600, 70));

        JTextField textCourseName = new JTextField();
        textCourseName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textCourseName.setMaximumSize(new Dimension(600, 70));

        JButton button = new JButton("Submit");
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);

        button.addActionListener(new AddClassListener(textCourseCode, textCourseName));

        // Add contents to the panel
        panelAddClass.add(label1);
        panelAddClass.add(textCourseCode);
        panelAddClass.add(label2);
        panelAddClass.add(textCourseName);
        panelAddClass.add(button);
    }

    private void createGetClassPanel()
    {
        panelGetClass = new JPanel(new GridLayout());

        // Create the Table Model for the class table
        List<Class> classes = new ClassDao().getClasses();
        String colNames[] = {"Class Code", "Class Name"};
        DefaultTableModel tableMod = new DefaultTableModel(0, colNames.length);

        for (Class c : classes)
        {
            Object[] data = {c.getCourseCode(), c.getClassName()};
            tableMod.addRow(data);
        }

        classesTable = new JTable(tableMod);

        panelGetClass.add(classesTable);

    }

    public void itemStateChanged(ItemEvent evt)
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    /**
     * Class that is a listener for the Submit button in the
     * Add class panel
     */
    public class AddClassListener implements ActionListener
    {
        private JTextField classCode;
        private JTextField className;

        AddClassListener(JTextField code, JTextField name)
        {
            classCode = code;
            className = name;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new ClassAction().addClass(classCode.getText(), className.getText());
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("App");
        //Create and set up the content pane.

        MainMenu menu = new MainMenu();
        menu.setupMainMenu(frame);

        frame.setVisible(true);
        frame.setSize(1280, 720);

        // Uncomment if you want the window to size to the contents on the screen
//        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
