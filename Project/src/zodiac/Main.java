package zodiac;

import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import zodiac.action.ClassAction;
import zodiac.action.StudentAction;
import zodiac.dao.ClassDao;
import zodiac.dao.StudentDao;
import zodiac.definition.Class;
import zodiac.definition.Student;

public class Main {

  /**
   * Console app to showcase first functions.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    String input;
    while (!exit) {
      System.out.print("Enter command (help for commands): ");
      input = scanner.nextLine();
      switch (input) {
        case "help":
          System.out.println("Add student: addStudent");
          System.out.println("Add class: addClass");
          System.out.println("Get classes: getClasses");
          System.out.println("Get students: getStudents");
          System.out.println("Exit: exit");
          break;
        case "addStudent": {
          System.out.print("Enter UTOR ID: ");
          String utorId = StringUtils.trimToEmpty(scanner.nextLine());
          System.out.print("Enter last name: ");
          String lastName = StringUtils.trimToNull(scanner.nextLine());
          System.out.print("Enter first name: ");
          String firstName = StringUtils.trimToNull(scanner.nextLine());
          System.out.print("Enter course code: ");
          String courseCode = StringUtils.trimToEmpty(scanner.nextLine());
          Student student = new Student(utorId, lastName, firstName);
          System.out.println(new StudentAction().addStudentToClass(student, courseCode));
          break;
        }
        case "addClass": {
          System.out.print("Enter course code: ");
          String courseCode = StringUtils.trimToEmpty(scanner.nextLine());
          System.out.print("Enter course name: ");
          String courseName = StringUtils.trimToEmpty(scanner.nextLine());
          System.out.println(new ClassAction().addClass(courseCode, courseName));
          break;
        }
        case "getClasses":
          List<Class> classes = new ClassDao().getClasses();
          for (Class course : classes) {
            System.out.println(course.getCourseCode() + " " + course.getClassName());
          }
          break;
        case "getStudents": {
          System.out.print("Enter course code: ");
          String courseCode = StringUtils.trimToEmpty(scanner.nextLine());
          List<Student> students = new StudentDao().getStudentsInClass(courseCode);
          for (Student student : students) {
            System.out.println(
                student.getUtorId() + ": " + student.getFirstName() + " " + student.getLastName());
          }
          break;
        }
        case "exit":
          exit = true;
          break;
        default:
          System.out.println("Enter 'help' for commands");
          break;
      }
    }
  }
}
