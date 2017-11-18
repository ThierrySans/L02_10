package zodiac.gui.user;

import zodiac.action.AssignmentAction;
import zodiac.definition.coursework.Assignment;

import javax.swing.*;

import static zodiac.util.UserViewMarksConstants.*;

import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class UserViewMarks extends UserSubMenu {
    private UneditableTableModel tblmdl;

    public JPanel setUpMenu()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel contentsPanel = new JPanel(new BorderLayout());

        this.tblmdl = new UneditableTableModel(0, USER_VIEW_MARKS_COLUMNS.length);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

        JPanel searchBar = new JPanel(new FlowLayout());
        searchBar.add(new JLabel(USER_VIEW_MARKS_ASS_SEARCH_TEXT));
        JTextField text = new JTextField();
        searchBar.add(text);
        JButton searchButton = new JButton();
        searchButton.addActionListener(new SearchActionListener(text));
        searchBar.add(searchButton);

        panel.add(contentsPanel);

        return panel;
    }

    private class UneditableTableModel extends DefaultTableModel
    {
        public UneditableTableModel(int i, int i1) {
            super(i, i1);
        }

        @Override
        public boolean isCellEditable(int i, int i1)
        {
            return false;
        }
    }

    private class SearchActionListener implements ActionListener
    {
        private JTextField textField;

        public SearchActionListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String code = this.textField.getText();
            List<Assignment> res = new AssignmentAction().getAssignments(code);
        }
    }
}
