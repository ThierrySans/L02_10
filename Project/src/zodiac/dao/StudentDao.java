package zodiac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zodiac.definition.Student;
import zodiac.util.PostgreSqlJdbc;

public class StudentDao {

  /**
   * Gets all the students in the given class.
   *
   * @param courseCode the course code of the class
   * @return list of students in course code
   */
  public List<Student> getStudentsInClass(String courseCode) {
    List<Student> students = new ArrayList<>();

    Connection c;
    PreparedStatement stmt;

    String sql = "SELECT u.UTOR_Id, u.Last_Name, u.First_Name"
        + " FROM Users u"
        + " INNER JOIN UserClassMap m"
        + " ON u.UTOR_Id=m.UTOR_Id"
        + " WHERE m.Course_Code = ?"
        + " ORDER BY UTOR_Id desc";

    try {
      c = new PostgreSqlJdbc().getConnection();
      stmt = c.prepareStatement(sql);
      stmt.setString(1, courseCode);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        String utorId = rs.getString("UTOR_Id");
        String lastName = rs.getString("Last_Name");
        String firstName = rs.getString("First_Name");
        Student student = new Student(utorId, lastName, firstName);
        students.add(student);
      }

      rs.close();
      stmt.close();
      c.close();
    } catch (Exception e) {
      // TODO Error Handling
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return students;
  }

  /**
   * Add a student to the system. Students must be registered to a course in the system Can also add
   * an existing student to another course
   *
   * @param student the student being added
   * @param courseCode the course that the student belongs to
   * @return message that explains the outcome of the function
   */
  public String addStudent(Student student, String courseCode) {
    String message = "";

    Connection c;
    PreparedStatement stmt;

    String sql = "SELECT Add_Student(?, ?, ?, ?)";

    try {
      c = new PostgreSqlJdbc().getConnection();
      stmt = c.prepareStatement(sql);
      stmt.setString(1, student.getUtorId());
      stmt.setString(2, courseCode);
      stmt.setString(3, student.getLastName());
      stmt.setString(4, student.getFirstName());

      ResultSet rs = stmt.executeQuery();

      rs.next();
      message = rs.getString(1);

      rs.close();
      stmt.close();
      c.close();

    } catch (Exception e) {
      // TODO Error Handling
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

    return message;
  }

  /**
   * Edits an existing student. Can change their first and last name
   *
   * @param student student being edited
   * @param lastName new last name of the student
   * @param firstName new first name of the student
   * @return message that explains the outcome of the function
   */
  public String editStudent(Student student, String lastName, String firstName) {
    String message = "";

    Connection c;
    PreparedStatement stmt;

    String sql = "SELECT Edit_Student(?, ?, ?)";

    try {
      c = new PostgreSqlJdbc().getConnection();
      stmt = c.prepareStatement(sql);
      stmt.setString(1, student.getUtorId());
      stmt.setString(2, lastName);
      stmt.setString(3, firstName);

      ResultSet rs = stmt.executeQuery();

      rs.next();
      message = rs.getString(1);

      rs.close();
      stmt.close();
      c.close();

    } catch (Exception e) {
      // TODO Error Handling
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

    return message;
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
	  String message = "";

	    Connection c;
	    PreparedStatement stmt;

	    String sql = "SELECT Add_Answer(?, ?, ?, ?)";

	    try {
	      c = new PostgreSqlJdbc().getConnection();
	      stmt = c.prepareStatement(sql);
	      stmt.setString(1, student.getUtorId());
	      stmt.setString(2, assignmentId);
	      stmt.setString(3, questionId);
	      stmt.setString(4, answer);

	      ResultSet rs = stmt.executeQuery();

	      rs.next();
	      message = rs.getString(1);

	      rs.close();
	      stmt.close();
	      c.close();

	    } catch (Exception e) {
	      // TODO Error Handling
	      System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    }

	    return message;
  }
  
}
