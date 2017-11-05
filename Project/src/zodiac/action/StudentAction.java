package zodiac.action;

import zodiac.dao.StudentDao;
import zodiac.definition.Student;
import zodiac.definition.coursework.Assignment;
import zodiac.definition.coursework.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class StudentAction {

  /**
   * API to add Student objects to a Class object.
   */
  public String addStudentToClass(Student student, String courseCode) {
    return new StudentDao().addStudent(student, courseCode);

  }
  
  /**
   * API to add answer for specific assignment and answer 
   * @param student the answer is added under this student
   * @param assignmentId the assignment id this answer belong
   * @param QA  the question to answers map
   * @return true if added succ, false otherwise 
   */
  public static boolean addAnswerToAssignment(Student student, Assignment assignment,TreeMap<Question,String> QA) {
	  
	  for(Question question:QA.keySet()) {
		  StudentDao.addAnswerToQuestion(student, assignment.getId(), question.getQid(), QA.get(question));   
	  }
	  return true;
  }
  /**
   * API validating answers for questions
   * @param QA a map from Question object to answer string
   * @return a map from Question object to true or false, depends on if the answer is correct for that Question
   */
  
  public static TreeMap<Question,Boolean> validateAnswerToaAssignment(TreeMap<Question,String> QA) {
	  TreeMap<Question,Boolean> res = new TreeMap<Question,Boolean>();
	  for(Question question:QA.keySet()) {
		  if(question.getCorrectAnswer() == QA.get(question)) {
			  res.put(question, new Boolean(true));
		  }else {
			  res.put(question, new Boolean(false));
		  }
	  }
	  return res;
  }
  
  
}

