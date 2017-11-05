package zodiac.action;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;
import zodiac.dao.coursework.QuestionDao;
import zodiac.definition.coursework.Question;

public class QuestionAction {

    public String createQuestion(int assignmentid, String question) {
        int newQuestion = new QuestionDao().addQuestion(question).getQid();
        return new QuestionDao().addQuestionToAssignment(newQuestion, assignmentid);
    }
    public String addAnswer(int QuestionID, String answer, boolean correct) {
        return new QuestionDao().addAnswerToQuestion(QuestionID, answer, correct);
    }
    public List<Question> getQid(int AssignmentId){
        return new QuestionDao().getQuestions(AssignmentId);
    }
    public List<Question> getQuestionsWithAnswer(int AssignmentId){
        return new QuestionDao().getQuestions(AssignmentId,true);
    }
}