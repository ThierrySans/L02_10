package zodiac.definition.coursework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radiantwings on 10/24/17. This class represents a Question which would be included in
 * >= 0 Assignments
 */
public class Question {

  private String qid;
  private List<String> answerList;
  private String correctAnswer;

  // TODO: Add Javadoc
  public Question(String qid) {
    this.qid = qid;
    answerList = new ArrayList<String>();
    this.correctAnswer = "";

  }

  public String getQid() {
    return qid;
  }

  public void setQid(String qid) {
    this.qid = qid;
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
