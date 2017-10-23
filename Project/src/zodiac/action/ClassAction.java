package zodiac.action;

import zodiac.dao.ClassDao;

public class ClassAction {

  /**
   * Add API to create a Class object.
   */
  public String addClass(String courseCode, String className) {
    return new ClassDao().addEditClass(courseCode, className);
  }


}
