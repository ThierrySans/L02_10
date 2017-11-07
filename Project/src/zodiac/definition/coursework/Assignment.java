package zodiac.definition.coursework;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by radiantwings on 10/24/17. This class represents an Assignment that Students will
 * complete. An assignment will typically hold >= 1 Questions
 */
public class Assignment {

  private int id;
  private List<Question> questionList;
  private int currScore;
  private int highScore;
  private String name;



  //field added
  private String visibility;

  /**
   * Constructor for Assignment.
   */
  public Assignment(String name) {
    // -1 id marks a new assignment not in the database
    this.id = -1;
    this.questionList = new ArrayList<Question>();
    this.currScore = 0;
    this.highScore = 0;
    this.name = name;
    this.visibility="on";
  }

  public Assignment(String name, int id) {
    this(name);
    this.id = id;
  }

  public boolean addQuestion(Question q) {
    return this.questionList.add(q);
  }

  // TODO: Add Javadoc
  public boolean removeQuestion(int id) {
    for (int i = 0; i < this.questionList.size(); i++) {
      if (this.questionList.get(i).getQid() == id) {
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    // Id can not be changed after it is set
    this.id = this.id < 0 ? id : this.id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  @Override
  public String toString() {
    Integer myInt = new Integer(this.id);
    return StringUtils.join(myInt, " " ,this.name);
  }
}
