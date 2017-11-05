package zodiac.definition.coursework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radiantwings on 10/24/17. This class represents a Question which would be included in
 * >= 0 Assignments
 */
public class Question {

  private int qid;
  private List<String> answerList;
  private String question;
  private String correctAnswer;

  // TODO: Add Javadoc
  public Question(int qid) {
    this.qid = qid;
    answerList = new ArrayList<String>();
    answerList.add("mostly right");
    answerList.add("not right");
    answerList.add("tricky");
    answerList.add("right");
    this.correctAnswer = "";

  }

  public int getQid() {
    return qid;
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

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
}
