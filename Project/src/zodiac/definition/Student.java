package zodiac.definition;

public class Student {

  private String utorId;
  private String lastName;
  private String firstName;

  /**
   * Initialize the student with given information.
   */
  public Student(String utorId, String lastName, String firstName) {
    this.utorId = utorId;
    this.lastName = lastName;
    this.firstName = firstName;
  }

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
















