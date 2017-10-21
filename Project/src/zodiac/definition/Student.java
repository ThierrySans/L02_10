package zodiac.definition;

public class Student {

  private String utorId;
  private String lastName;
  private String firstName;
  private String classCode;
  private String tutCode;

  /**
   * Initialize the student with given information.
   */
  public Student(String utorId, String lastName, String firstName, String classCode, String tutCode) {
    this.utorId = utorId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.classCode = classCode;
    this.tutCode = tutCode;
  }

  /**
   * set the class code.
   */
  public void setClassCode(String classCode) { this.classCode = classCode; }

  /**
   * get the class code.
   *
   * @return the code of class
   */
  public String getClassCode() { return classCode; }

  /**
   * set the code of tut
   */
  public void setTutCode(String tutCode) { this.tutCode = tutCode; }

  /**
   * get the code of tut
   *
   * @return the code of tut
   */
  public String getTutCode() { return tutCode; }

  /**
   * set the student UTOR ID.
   */
  public void setUtorId(String utorId) {
    this.utorId = utorId;
  }

  /**
   * get the UTOR ID.
   *
   * @return the UTOR ID of student
   */
  public String getUtorId() {
    return this.utorId;
  }

  /**
   * set the last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * get the last name.
   *
   * @return the last name of student
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * set the first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * get the first name.
   *
   * @return the last name of student
   */
  public String getFirstName() {
    return this.firstName;
  }

}
















