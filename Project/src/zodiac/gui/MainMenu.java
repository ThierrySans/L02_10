package zodiac.gui;

import zodiac.action.ClassAction;
import zodiac.action.StudentAction;
import zodiac.dao.ClassDao;
import zodiac.dao.StudentDao;
import zodiac.definition.Class;
import zodiac.definition.Student;

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
    public static String ADD_STUDENT = "Add a student";
    public static String GET_STUDENT = "Get a list of students";

    private JPanel cbPanel;
    private JComboBox cbOptions;
    private JPanel panelAddClass;
    private JPanel panelGetClass;
    private JPanel panelGetStudents;
    private JTable classesTable;
    private DefaultTableModel classesTableModel;
    private JTable studentsTable;
    private DefaultTableModel studentsTableModel;
    private JPanel cards;

    private void setupMainMenu(Container pane)
    {
        JPanel cbPanel = new JPanel(new FlowLayout());
        String cbOptionList[] = {CREATE_CLASS, GET_CLASSES, ADD_STUDENT, GET_STUDENT};
        cbOptions = new JComboBox(cbOptionList);
        cbOptions.setEditable(false);
        cbOptions.addItemListener(this);
        cbPanel.add(cbOptions);

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel msg = new JLabel(WELCOME_MESSAGE);
        msg.setHorizontalAlignment(JLabel.CENTER);
        msg.setVerticalAlignment(JLabel.CENTER);
        msg.setFont(new Font("Bitstream Vera Sans", 0, 48));

        topPanel.add(msg, BorderLayout.NORTH);
        topPanel.add(cbPanel, BorderLayout.PAGE_END);

        createAddClassPanel(pane);
        createGetClassPanel();
        createGetStudentsPanel();

        cards = new JPanel(new CardLayout());
        cards.add(panelAddClass, CREATE_CLASS);
        cards.add(panelGetClass, GET_CLASSES);
        cards.add(createAddStudentPanel(), ADD_STUDENT);
        cards.add(panelGetStudents, GET_STUDENT);


        pane.add(topPanel, BorderLayout.PAGE_START);
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
        classesTable = new JTable();
        panelGetClass.add(classesTable);
    }

    private JPanel createAddStudentPanel()
    {
        JPanel panelAddStudent = new JPanel();
        panelAddStudent.setLayout(new BoxLayout(panelAddStudent, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Enter UTOR ID: ");
        label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label1.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel label2 = new JLabel("Enter Last name: ");
        label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label2.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel label3 = new JLabel("Enter First name: ");
        label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label2.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel label4 = new JLabel("Enter Course Code: ");
        label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label2.setBorder(new EmptyBorder(20, 0, 10, 0));

        JTextField textCourseCode = new JTextField();
        textCourseCode.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textCourseCode.setMaximumSize(new Dimension(600, 70));

        JTextField textLastName = new JTextField();
        textLastName .setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textLastName .setMaximumSize(new Dimension(600, 70));

        JTextField textFirstName = new JTextField();
        textFirstName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textFirstName.setMaximumSize(new Dimension(600, 70));

        JTextField textUtorId = new JTextField();
        textUtorId.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        textUtorId.setMaximumSize(new Dimension(600, 70));

        JButton button = new JButton("Submit");
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);

        button.addActionListener(new AddStudentListener(textUtorId, textLastName, textFirstName, textCourseCode));

        // Add contents to the panel
        panelAddStudent.add(label1);
        panelAddStudent.add(textUtorId);
        panelAddStudent.add(label2);
        panelAddStudent.add(textLastName);
        panelAddStudent.add(label3);
        panelAddStudent.add(textFirstName);
        panelAddStudent.add(label4);
        panelAddStudent.add(textCourseCode);
        panelAddStudent.add(button);

        return panelAddStudent;
    }

    private void createGetStudentsPanel()
    {
        panelGetStudents = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel prompt = new JLabel("Enter a Course Code: ");
        JTextField textCourseCode = new JTextField();
        textCourseCode.setPreferredSize(new Dimension(300, 25));
        textCourseCode.setMinimumSize(new Dimension(300, 25));
        JButton submit = new JButton("Search");

        submit.addActionListener(new GetStudentListener(textCourseCode));

        searchPanel.add(prompt);
        searchPanel.add(textCourseCode);
        searchPanel.add(submit);

        panelGetStudents.add(searchPanel, BorderLayout.NORTH);

    }

    private void updateClassesTable()
    {
        panelGetClass.remove(classesTable);

        List<Class> classes = new ClassDao().getClasses();
        String colNames[] = {"Class Code", "Class Name"};

        classesTableModel= new DefaultTableModel(0, colNames.length);

        Object[] headers = {colNames[0], colNames[1]};
        classesTableModel.addRow(headers);

        for (Class c : classes)
        {
            Object[] data = {c.getCourseCode(), c.getClassName()};
            classesTableModel.addRow(data);
        }

        classesTable = new JTable(classesTableModel);

        panelGetClass.add(classesTable);
    }

    public void itemStateChanged(ItemEvent evt)
    {
        CardLayout cl = (CardLayout)(cards.getLayout());

        String choice = (String)evt.getItem();
        if (choice.equals(GET_CLASSES))
        {
            updateClassesTable();
        }
        cl.show(cards, (String)evt.getItem());
    }

    /**
     * Class that is a listener for the Submit button in the
     * Add class panel
     */
    private class AddClassListener implements ActionListener
    {
        private JTextField classCode;
        private JTextField className;

        AddClassListener(JTextField code, JTextField name)
        {
            this.classCode = code;
            this.className = name;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new ClassAction().addClass(classCode.getText(), className.getText());
        }
    }

    /**
     * Class that is a listener for the Submit button in the
     * Add student panel
     */
    private class AddStudentListener implements ActionListener
    {
        private JTextField utorID;
        private JTextField lastName;
        private JTextField firstName;
        private JTextField courseCode;

        public AddStudentListener(JTextField utorID, JTextField lastName, JTextField firstName, JTextField courseCode) {
            this.utorID = utorID;
            this.lastName = lastName;
            this.firstName = firstName;
            this.courseCode = courseCode;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Student student = new Student(utorID.getText(), lastName.getText(), firstName.getText());
            new StudentAction().addStudentToClass(student, courseCode.getText());
        }
    }

    /**
     * Class that is a listener for the Search button in the
     * Get student panel
     */
    private class GetStudentListener implements ActionListener
    {
        private JTextField courseCode;

        public GetStudentListener(JTextField courseCode) {
            this.courseCode = courseCode;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<Student> results = new StudentDao().getStudentsInClass(this.courseCode.getText());

            String colNames[] = {"UTOR ID", "First Name", "Last Name"};

            studentsTableModel= new DefaultTableModel(0, colNames.length);

            Object[] headers = {colNames[0], colNames[1], colNames[2]};
            studentsTableModel.addRow(headers);

            for (Student s : results) {
                Object[] data = {s.getUtorId(), s.getFirstName(), s.getLastName()};
                studentsTableModel.addRow(data);
            }

            if (studentsTable.isValid())
            {
                panelGetStudents.remove(studentsTable);
            }

            panelGetStudents.add(new JTable(studentsTableModel), BorderLayout.CENTER);
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
