package zodiac.gui.user;

import zodiac.action.StudentAction;
import zodiac.definition.Student;
import zodiac.definition.coursework.Assignment;
import zodiac.definition.coursework.Question;
import zodiac.util.PDFGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.JScrollPane;

public class AssignmentUI extends JFrame implements ActionListener {

    ArrayList<Integer> answers;
    ArrayList<JRadioButton> rdbts;
    JButton btnSaveNext;
    JButton btnPrev;
    JButton btnPDF;
    JLabel lblNewLabel;
    ButtonGroup group;
    JScrollPane scrollPane;
    private JPanel contentPane;
    private Assignment assignment;
    private List<Question> questions;
    private TreeMap<Question, List<String>> qas;
    private Student student;
    private AssignmentUI ref = this;
    private int currentAt = 0;

    public AssignmentUI(Assignment ass, Student stud) {
        // -1 id marks a new assignment not in the database
        super("Assignment " + ass.getId());
        this.student = stud;
        this.assignment = ass;
        this.questions = ass.getQuestionList();
        this.createContents();
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);


        //  buttons
        btnSaveNext = new JButton();
        btnSaveNext.setBounds(346, 291, 94, 28);
        btnSaveNext.setText("Save & Next");
        btnSaveNext.setEnabled(false);
        contentPane.add(btnSaveNext);

        btnPrev = new JButton();
        btnPrev.setBounds(248, 291, 94, 28);
        btnPrev.setText("Back");
        btnPrev.setEnabled(false);
        contentPane.add(btnPrev);

        btnPDF = new JButton();
        btnPDF.setBounds(20, 291, 94, 28);
        btnPDF.setText("PDF");
        contentPane.add(btnPDF);
        // question label
        lblNewLabel = new JLabel("1. " + this.questions.get(0).getQuestion());
        lblNewLabel.setBounds(40, 34, 350, 40);
        contentPane.add(lblNewLabel);
        // note label
        JLabel newLable = new JLabel("Note: your progress will be saved.");
        newLable.setBounds(130, 261, 350, 40);
        contentPane.add(newLable);
        // setting answer options
        group = new ButtonGroup();


        answers = new ArrayList<Integer>();
        // initialize qa set
        qas = new TreeMap<Question, List<String>>();

        for (Question question : this.questions) {
            qas.put(question, question.getAnswerList());
        }
        // init temporal answer

        TreeMap<Question, String> temporalAnswer = new StudentAction().fetchTempAnswerFromAssignment(student, assignment);
        for (Question question : questions) {
            if (temporalAnswer.get(question) != null) {
                Integer rs = null;
                for (int p = 0; p < question.getAnswerList().size(); p++) {
                    if (qas.get(question).get(p).equals(temporalAnswer.get(question))) {

                        rs = p;
                        break;
                    }

                }
                if (rs != null) {
                    answers.add(rs);
                }

            }


        }

        rdbts = new ArrayList<JRadioButton>();
        // adding answer option
        int i;
        for (i = 0; i < 5; i++) {
            JRadioButton rb = new JRadioButton();
            rdbts.add(rb);
            group.add(rb);
            rb.setBounds(50, 80 + i * 50, 350, 37);
            rb.addActionListener(this);
            contentPane.add(rb);
        }

        i = 0;
        // only set avaliable option visible
        for (String answer : this.qas.get(this.questions.get(0))) {
            JRadioButton rb = rdbts.get(i);
            rb.setText(answer);
            i += 1;
        }
        try {
            rdbts.get(this.answers.get(0)).setSelected(true);
            btnSaveNext.setEnabled(true);
        } catch (IndexOutOfBoundsException ex) {

        }

        // set the rest of options invisible
        for (int j = i; j < 5; j++) {
            JRadioButton rb = rdbts.get(j);
            rb.setVisible(false);
        }
        // change to submit immediately

        btnSaveNext.addActionListener(this);
        if (this.questions.size() == 1) {
            btnSaveNext.setText("Submit");
        }

        btnPrev.addActionListener(this);

        btnPDF.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {


        if (e.getActionCommand() == btnSaveNext.getActionCommand()) {
            // get the answer
            Integer answerInt = 0;
            for (JRadioButton rdb : rdbts) {
                if (rdb.isSelected()) {
                    group.clearSelection();
                    break;
                }
                answerInt += 1;
            }

            // save the answer or change the existing answer
            try {
                answers.get(currentAt);
                answers.set(currentAt, answerInt);
            } catch (IndexOutOfBoundsException ex) {
                answers.add(answerInt);
            }

            if (currentAt == questions.size() - 1) {
                // close uI and submit the answer
                TreeMap<Question, String> qa = new TreeMap<Question, String>();
                for (int i = 0; i < questions.size(); i++) {
                    Question q = questions.get(i);
                    String a = String.valueOf(rdbts.get(answers.get(i)).getText());
                    qa.put(q, a);

                }
                new StudentAction().addAnswerToAssignment(student, assignment, qa);
                Integer result = new StudentAction().validateAnswer(qa);
                new StudentAction().saveMark(assignment.getId(), student.getUtorId(),result);
                lblNewLabel.setText("Your result: " + result);
                Font f = lblNewLabel.getFont();
                lblNewLabel.setFont(new Font(f.getName(), Font.BOLD, f.getSize() + 4));
                for (int j = 0; j < 5; j++) {
                    JRadioButton rb = rdbts.get(j);
                    rb.setVisible(false);
                    contentPane.remove(rb);
                }
                btnPrev.setEnabled(false);
                btnSaveNext.setEnabled(false);
                // display all the answers
                // setting all answers
                GridLayout grid = new GridLayout(0, 1);
                grid.setVgap(8);
                JPanel panel = new JPanel(grid);
                int k;
                for (k = 0; k < questions.size(); k++) {
                    Question q = this.questions.get(k);
                    lblNewLabel = new JLabel(String.valueOf(k + 1) + ": " + q.getQuestion());
                    panel.add(lblNewLabel);
                    lblNewLabel = new JLabel("    Answer: " + q.getCorrectAnswer());
                    panel.add(lblNewLabel);
                    lblNewLabel = new JLabel("    Your Answer: " + rdbts.get((answers.get(k))).getText());
                    panel.add(lblNewLabel);

                }
                panel.revalidate();

                scrollPane = new JScrollPane(panel);

                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
                scrollPane.setBounds(35, 80, 380, 200);
                scrollPane.getViewport().setMinimumSize(new Dimension(380, 200));
                scrollPane.getViewport().setPreferredSize(new Dimension(380, 200));

                contentPane.add(scrollPane);
            } else {
                // save current answer into database
                new StudentAction().addTempAnswerToQuestion(student, assignment, questions.get(currentAt), rdbts.get(answers.get(currentAt)).getText());


                currentAt += 1;
                // trying to see if we have next answer
                try {
                    answers.get(currentAt);
                } catch (IndexOutOfBoundsException ex) {
                    btnSaveNext.setEnabled(false);
                }
                // rendering the next question and answers
                lblNewLabel.setText(String.valueOf(currentAt + 1) + ". " + questions.get(currentAt).getQuestion());
                int i = 0;
                for (String answer : this.qas.get(this.questions.get(currentAt))) {
                    JRadioButton rb = rdbts.get(i);
                    rb.setText(answer);
                    rb.setVisible(true);
                    i += 1;
                }
                for (int j = i; j < 5; j++) {
                    JRadioButton rb = rdbts.get(j);
                    rb.setVisible(false);
                }
                // setting anwser for rendered question if we have
                try {
                    int answer = answers.get(currentAt);
                    rdbts.get(answer).setSelected(true);
                } catch (IndexOutOfBoundsException ex) {

                }
                // setting rendering rang
                btnPrev.setEnabled(true);
                // see if we have reach the end
                if (currentAt == questions.size() - 1) {
                    btnSaveNext.setText("Submit");
                }
            }


        } else if (e.getActionCommand() == btnPrev.getActionCommand()) {
            if (currentAt == questions.size() - 1) {
                btnSaveNext.setText("Save & Next");
            }
            currentAt -= 1;


            lblNewLabel.setText(String.valueOf(currentAt + 1) + ". " + questions.get(currentAt).getQuestion());
            int i = 0;
            for (String answer : this.qas.get(this.questions.get(currentAt))) {
                JRadioButton rb = rdbts.get(i);
                rb.setText(answer);
                i += 1;
            }
            for (int j = i; j < 5; j++) {
                JRadioButton rb = rdbts.get(j);
                rb.setVisible(false);
            }
            // trying to see if we have prev answer, and set it
            try {
                int answer = answers.get(currentAt);
                rdbts.get(answer).setSelected(true);
                btnSaveNext.setEnabled(true);
            } catch (IndexOutOfBoundsException ex) {
            }

            if (currentAt == 0) {
                btnPrev.setEnabled(false);

            }
        }  else if(e.getActionCommand()==btnPDF.getActionCommand()){
            String path = (new PDFGenerator(assignment)).generate();

            try{
                if(path !=null){
                    Desktop.getDesktop().open(new File(path));
                }   else{
                    JOptionPane.showMessageDialog(null, "Error occur, PDF File writing failed.");
                }

            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occur, PDF File writing failed.");
            }

        }else {
            btnSaveNext.setEnabled(true);
        }
    }

}

