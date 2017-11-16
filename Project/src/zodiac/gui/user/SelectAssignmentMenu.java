package zodiac.gui.user;

import zodiac.dao.ClassDao;
import zodiac.dao.StudentDao;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.Class;
import zodiac.definition.coursework.Assignment;
import zodiac.util.ActiveUser;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectAssignmentMenu extends Component {
    public static final String ASS_ID = "ID";
    public static final String ASS_NAME = "Name";
    public static final String ASS_CLASS = "Class";
    public static final String ASS_HIGH_SCORE = "High Score";

    public static final String DEBUG = "wandavi2";

    private DefaultTableModel tblmodel;
    public JPanel setUpMenu()
    {
        JPanel panel = new JPanel(new FlowLayout());

        String colNames[] = {ASS_ID, ASS_NAME, ASS_CLASS, ASS_HIGH_SCORE};
        this.tblmodel = new DefaultTableModel(0, colNames.length) {
            @Override
            public boolean isCellEditable(int i, int i1)
            {
                return false;
            }
        };

        this.tblmodel.addRow(colNames);

        // Currently holds debug value
        List<String> enrolled = StudentDao.getEnrolledClasses(DEBUG);

        if (enrolled != null)
        {
            for (String c : enrolled)
            {
                List<Assignment> assignments = new AssignmentDao().getAssignments(c);

                for (Assignment a : assignments)
                {
                    Object row[] = {a.getId(), a.getName(), c, a.getHighScore()};
                    this.tblmodel.addRow(row);
                }
            }
        }

        // Create the table and disable multiple selection and column reordering
        JTable table = new JTable(this.tblmodel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane pane = new JScrollPane(table);
        pane.setColumnHeaderView(table.getTableHeader());

        panel.add(pane);
        return panel;
    }


}
