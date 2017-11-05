package zodiac.action;

import java.util.List;

import zodiac.dao.ClassDao;
import zodiac.definition.Class;

public class ClassAction {

  /**
   * Add API to create a Class object.
   */
  public String addClass(String courseCode, String className) {
    return new ClassDao().addEditClass(courseCode, className);
  }

  /**
   * Gets a list of all classes.
   *
   * @return a string the contains
   */
  public List<Class> getClasses() {
    return new ClassDao().getClasses();
  }

}
