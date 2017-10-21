package zodiac.action;

import zodiac.dao.ClassDao;

public class ClassAction {

  private ClassDao classDao;

  /**
   * Add API to create a Class object.
   */
  public String addClass(String courseCode, String className) {
    return classDao.addEditClass(courseCode, className);
  }


}
