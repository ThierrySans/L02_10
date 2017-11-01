package zodiac.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private JPanel cards;

    public MainMenu()
    {
        // Set up the main Panel in a Grid Layout
        mainPanel = new JPanel(new GridLayout());

    }

    private void addBasicElements(Container pane)
    {
        pane.add(new JLabel(WELCOME_MESSAGE), BorderLayout.NORTH);

        JPanel cbPanel = new JPanel(new FlowLayout());
        String cbOptionList[] = {CREATE_CLASS, GET_CLASSES};
        cbOptions = new JComboBox(cbOptionList);
        cbOptions.setEditable(false);
        cbOptions.addItemListener(this);
        cbPanel.add(cbOptions);

        createAddClassPanel();
        createGetClassPanel();

        cards = new JPanel(new CardLayout());
        cards.add(panelAddClass, CREATE_CLASS);
        cards.add(panelGetClass, GET_CLASSES);

        pane.add(cbPanel, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);

    }

    private void createAddClassPanel()
    {
        panelAddClass = new JPanel();
        panelAddClass.add(new JButton("xdddddd"));
    }

    public void createGetClassPanel()
    {
        panelGetClass = new JPanel();
        panelGetClass.add(new JLabel("FUCK"));
    }

    public void itemStateChanged(ItemEvent evt)
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("App");
        //Create and set up the content pane.
        MainMenu demo = new MainMenu();
        demo.addBasicElements(frame);

        frame.setVisible(true);
        frame.setSize(1280, 720);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

//    JPanel cards; //a panel that uses CardLayout
//    final static String BUTTONPANEL = "Card with JButtons";
//    final static String TEXTPANEL = "Card with JTextField";
//
//    public void addComponentToPane(Container pane) {
//        //Put the JComboBox in a JPanel to get a nicer look.
//        JPanel comboBoxPane = new JPanel(); //use FlowLayout
//        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
//        JComboBox cb = new JComboBox(comboBoxItems);
//        cb.setEditable(false);
//        cb.addItemListener(this);
//        comboBoxPane.add(cb);
//
//        //Create the "cards".
//        JPanel card1 = new JPanel();
//        card1.add(new JButton("Button 1"));
//        card1.add(new JButton("Button 2"));
//        card1.add(new JButton("Button 3"));
//
//        JPanel card2 = new JPanel();
//        card2.add(new JTextField("TextField", 20));
//
//        //Create the panel that contains the "cards".
//        cards = new JPanel(new CardLayout());
//        cards.add(card1, BUTTONPANEL);
//        cards.add(card2, TEXTPANEL);
//
//        pane.add(comboBoxPane, BorderLayout.PAGE_START);
//        pane.add(cards, BorderLayout.CENTER);
//    }
//
//    public void itemStateChanged(ItemEvent evt) {
//        CardLayout cl = (CardLayout)(cards.getLayout());
//        cl.show(cards, (String)evt.getItem());
//    }
//
//    /**
//     * Create the GUI and show it.  For thread safety,
//     * this method should be invoked from the
//     * event dispatch thread.
//     */
//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("CardLayoutDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        MainMenu demo = new MainMenu();
//        demo.addComponentToPane(frame.getContentPane());
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        /* Use an appropriate Look and Feel */
//        try {
//            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        } catch (UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
//        } catch (InstantiationException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        /* Turn off metal's use of bold fonts */
//        UIManager.put("swing.boldMetal", Boolean.FALSE);
//
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
