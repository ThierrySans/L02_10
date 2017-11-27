package zodiac.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import zodiac.action.security.UserAction;
import zodiac.dao.security.UserDao;


public class register {
    private JTextField usernameField;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private String username;
    private String password;
    private JDialog result;

    public register() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                char[] workingPassword = passwordField1.getPassword();
                password = new String(workingPassword);
                UserAction potentialUser = new UserAction();
                result = new JDialog();
                result.setSize(new Dimension(400, 400));
                JLabel prompt = new JLabel(potentialUser.register(username,password));
                result.add(prompt);
                result.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                result.setVisible(true);

            }
        });
        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usernameField.setText("");
            }
        });
        passwordField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField1.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("register");
        frame.setContentPane(new register().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Registration");
        frame.setSize(800, 400);
    }
}
