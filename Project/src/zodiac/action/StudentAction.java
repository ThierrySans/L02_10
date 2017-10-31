package zodiac.action;

import zodiac.dao.StudentDao;
import zodiac.definition.Student;
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
   * @param questionId the question id this answer belong
   * @param answer  the answer in text
   * @return succefully string or fail
   */
  public static String addAnswerToQuestion(Student student, String assignmentId, String questionId, String answer ) {
	  return StudentDao.addAnswerToQuestion(student,assignmentId,questionId,answer);
  }
  /**
   * API to add answer for specific assignment and answer 
   * @param student the answer is added under this student
   * @param assignmentId the assignment id this answer belong
   * @param QA  the question to answers map
   * @return true if added succ, false otherwise 
   */
  public static boolean addAnswerToAssignment(Student student, String assignmentId,TreeMap<String,String> QA) {
	  
	  for(String question:QA.keySet()) {
		   StudentAction.addAnswerToQuestion(student, assignmentId, question, QA.get(question));   
	  }
	  return true;
  }
}

