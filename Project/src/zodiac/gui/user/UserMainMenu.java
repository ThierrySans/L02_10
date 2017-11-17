package zodiac.gui.user;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static zodiac.util.Constants.*;

public class UserMainMenu {


    private JPanel cards;

    public void setupMainMenu(JFrame frame)
    {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel banner = new JLabel(WELCOME_BANNER, SwingConstants.CENTER);

        banner.setFont(new Font("Bitstream Vera Sans", 0, 48));
        panel.add(banner, BorderLayout.PAGE_START);

        this.cards = new JPanel(new CardLayout());
        this.cards.add(this.generateMainMenu(), MAIN_MENU);
        this.cards.add(new SelectAssignmentMenu().setUpMenu(), START_ASS);

        panel.add(this.cards);

        frame.add(panel);
    }

    private JPanel generateMainMenu()
    {
        // initiate panel and panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JButton button1 = new JButton(START_ASS);
        button1.addActionListener(new StartAssignmentListener());

        JButton button2 = new JButton(VIEW_MARKS);

        JButton button3 = new JButton(LOGOFF);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        return panel;
    }

    private class StartAssignmentListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, START_ASS);
        }
    }

    /**
     * main function for UserMainMenu. Run this to launch the
     * standalone User version of the app.
     * @param args
     */
    public static void main(String args[])
    {
        JFrame frame = new JFrame("App");
        //Create and set up the content pane.

        UserMainMenu menu = new UserMainMenu();
        menu.setupMainMenu(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        frame.setVisible(true);

    }
}
