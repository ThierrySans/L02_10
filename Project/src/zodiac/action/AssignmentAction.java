package zodiac.action;

import java.util.ArrayList;
import java.util.List;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.MessageConstants;
import zodiac.definition.coursework.Assignment;
import zodiac.util.ActiveUser;

public class AssignmentAction {

  /**
   * Add API to create a an assignment.
   */
  public int addAssignment(String assignmentName, String courseCode) {
    if (ActiveUser.INSTANCE.canWrite(courseCode)) {
      return new AssignmentDao().addAssignment(assignmentName, courseCode);
    } else {
      return -1;
    }
  }

  /**
   * Added api to check all assignments in a course
   *
   * @return List<Assignment>
   */
  public List<Assignment> checkAssignments(String courseCode) {
    if (ActiveUser.INSTANCE.canRead(courseCode)) {
      return new AssignmentDao().getAssignments(courseCode);
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * Set the max attempt of an assignment.
   * 0 max attempts means infinite max attempts
   *
   * @param id the id of the assignment
   * @param maxAttempt the max attempt to set it to
   * @return message to display on the GUI
   */
  public String setAssignmentMaxAttempt(int id, int maxAttempt) {
    if (ActiveUser.INSTANCE.canWrite(new AssignmentDao().getCourseOfAssignment(id))) {
      if (new AssignmentDao().editAssignmentMaxAttempt(id, maxAttempt)) {
        return "Max attempt changed";
      } else {
        return "Unable to change max attempt";
      }
    } else {
      return MessageConstants.NO_PERMISSION_MESSAGE;
    }
  }

  public String setAssignmentMaxAttempt(Assignment assignment, int maxAttempt) {
    return setAssignmentMaxAttempt(assignment.getId(), maxAttempt);
  }


  public boolean changeAssignmentVisibility(Assignment assignment) {
    return new AssignmentDao().changeAssignmentVisibility(assignment);
  }

}
