package zodiac.action;

import zodiac.dao.StudentDao;
import zodiac.definition.Student;

public class StudentAction {

  /**
   * API to add Student objects to a Class object.
   */
  public String addStudentToClass(Student student, String courseCode) {
    return new StudentDao().addStudent(student, courseCode);

  }

}
