package zodiac.gui.admin;

import zodiac.action.ClassAction;
import zodiac.action.MarkAction;
import zodiac.definition.Student;
import zodiac.gui.GuiSubMenu;
import static zodiac.util.MarkSummaryConstants.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class MarkSummaryMenu extends GuiSubMenu{
    DefaultTableModel tblmdl;
    @Override
    public JPanel setUpMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel searchBar = new JPanel(new FlowLayout());
        JTextField textField = new JTextField();
        JButton button = new JButton(SEARCH_BUTTON_TEXT);
        button.addActionListener(new GetMarksActionListener(textField));

        searchBar.add(new JLabel(SEARCH_BAR_TEXT));
        searchBar.add(textField);
        searchBar.add(button);

        this.tblmdl = new UneditableTableModel(0, MARKS_TABLE_COLUMNS.length);

        panel.add(searchBar);

        return panel;
    }

    private class GetMarksActionListener implements ActionListener
    {
        JTextField textField;

        public GetMarksActionListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new ClassAction().getMarksInClass(this.textField.getText());
        }
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
}
