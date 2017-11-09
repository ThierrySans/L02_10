package zodiac.action;

import java.util.TreeMap;
import zodiac.dao.StudentDao;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.Student;
import zodiac.definition.coursework.Assignment;
import zodiac.definition.coursework.Question;

public class StudentAction {

  /**
   * API to get a Student obejct
   */
  public static Student getStudent(String userID, String courseId) {
    return StudentDao.getStudent(userID, courseId);
  }

  /**
   * API to add answer for specific assignment and answer
   *
   * @param student the answer is added under this student
   * @param assignmentId the assignment id this answer belong
   * @param QA the question to answers map
   * @return true if added succ, false otherwise
   */
  public static boolean addAnswerToAssignment(Student student, Assignment assignment,
      TreeMap<Question, String> QA) {

    for (Question question : QA.keySet()) {
      StudentDao
          .addAnswerToQuestion(student, assignment.getId(), question.getQid(), QA.get(question));
    }
    return true;
  }

  /**
   * API validating answers for questions
   *
   * @param QA a map from Question object to answer string
   * @return a map from Question object to true or false, depends on if the answer is correct for
   * that Question
   */

  public static Integer validateAnswer(TreeMap<Question, String> QA) {
    Integer res = 0;
    for (Question question : QA.keySet()) {
      if (question.getCorrectAnswer() == QA.get(question)) {
        res += 1;
      }
    }
    return res;
  }

  /**
   * API for saving mark into database
   */
  public static void saveMark(Student student, Assignment ass, Integer mark) {
    if (usedMaxAttempts(student, ass)) {
      // If students finds a way to complete an assignment even after using all attempts,
      // do not save mark
      StudentDao.saveMark(student, ass, mark);
    }
  }

  /**
   * Return whether the student has used all their attempts of an assignment or not.
   *
   * @param student the student doing the assignment
   * @param assignment the assignment being done
   * @return true if the student has used all their attempts, false otherwise
   */
  public static boolean usedMaxAttempts(Student student, Assignment assignment) {
    if (assignment.getMaxAttempt() > 0) {
      return (new AssignmentDao().getStudentUsedAttempts(assignment, student)
          >= assignment.getMaxAttempt());
    } else {
      return false;
    }
  }

  /**
   * API to add Student objects to a Class object.
   */
  public String addStudentToClass(Student student, String courseCode) {
    return new StudentDao().addStudent(student, courseCode);

  }
}

