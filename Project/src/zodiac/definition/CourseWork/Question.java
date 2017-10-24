package CourseWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radiantwings on 10/24/17.
 * This class represents a Question which
 * would be included in >= 0 Assignments
 */
public class Question {
    private String QID;
    private List<String> answerList;
    private String correctAnswer;

    public Question(String qid)
    {
        this.QID = qid;
        answerList = new ArrayList<String>();
        this.correctAnswer = "";

    }

    public String getQID() {
        return QID;
    }

    public void setQID(String QID) {
        this.QID = QID;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
