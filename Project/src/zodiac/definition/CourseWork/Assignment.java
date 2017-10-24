package CourseWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radiantwings on 10/24/17.
 * This class represents an Assignment that Students will
 * complete. An assignment will typically hold >= 1 Questions
 */
public class Assignment {
    private List<Question> questionList;
    private int currScore;
    private int highScore;

    /**
     * Constructor for Assignment
     */
    public Assignment ()
    {
        this.questionList = new ArrayList<Question>();
        this.currScore = 0;
        this.highScore = 0;
    }

    public boolean addQuestion(Question q)
    {
        return this.questionList.add(q);
    }

    public boolean removeQuestion(String id)
    {
        for (int i = 0; i < this.questionList.size(); i++)
        {
            if (this.questionList.get(i).getQID().equals(id))
            {
                this.questionList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public int getCurrScore() {
        return currScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public static void main(String[] args)
    {
        new Assignment();
    }
}
