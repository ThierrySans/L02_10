package zodiac.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import zodiac.dao.coursework.AssignmentDao;
import zodiac.dao.security.UtilDao;
import zodiac.definition.MessageConstants;
import zodiac.definition.coursework.Assignment;
import zodiac.definition.security.SecurityConstants;
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
   * @throws SQLException Catch this exception and display error message on UI when caught
   */
  public List<Assignment> checkAssignments(String courseCode) throws SQLException {
    if (ActiveUser.INSTANCE.canWrite(courseCode)) {
      return new AssignmentDao().getAssignments(courseCode);
    } else if (ActiveUser.INSTANCE.canRead(courseCode)) {
      return onlyVisibleAssignments(new AssignmentDao().getAssignments(courseCode));
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
    if (ActiveUser.INSTANCE.canWrite(
        new AssignmentDao().getCourseOfAssignment(assignment.getId()))) {
      return new AssignmentDao().changeAssignmentVisibility(assignment);
    } else {
      return false;
    }
  }

  /**
   * Change the open time of an assignment.
   *
   * @param id the id of the assignment
   * @param year the year to set it to
   * @param month the month to set it to
   * @param day the day of month to set it to
   * @param hour the hour of day to set it to
   * @param minute the minute of hour to set it to
   * @return message to display on the UI
   */
  public String setAssignmentOpenTime(int id, int year, int month, int day, int hour, int minute) {
    if (ActiveUser.INSTANCE.canWrite(new AssignmentDao().getCourseOfAssignment(id))) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(year, month, day, hour, minute);
      if (new AssignmentDao().editOpenTime(id, calendar.getTime())) {
        return MessageConstants.EDIT_SUCCEED;
      } else {
        return MessageConstants.EDIT_FAILED;
      }
    } else {
      return MessageConstants.NO_PERMISSION_MESSAGE;
    }
  }

  /**
   * Change the close time of an assignment.
   *
   * @param id the id of the assignment
   * @param year the year to set it to
   * @param month the month to set it to
   * @param day the day of month to set it to
   * @param hour the hour of day to set it to
   * @param minute the minute of hour to set it to
   * @return message to display on the UI
   */
  public String setAssignmentCloseTime(int id, int year, int month, int day, int hour, int minute) {
    if (ActiveUser.INSTANCE.canWrite(new AssignmentDao().getCourseOfAssignment(id))) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(year, month, day, hour, minute);
      if (new AssignmentDao().editCloseTime(id, calendar.getTime())) {
        return MessageConstants.EDIT_SUCCEED;
      } else {
        return MessageConstants.EDIT_FAILED;
      }
    } else {
      return MessageConstants.NO_PERMISSION_MESSAGE;
    }
  }

  private List<Assignment> onlyVisibleAssignments(List<Assignment> assignments)
      throws SQLException {
    // Returns a list if only the visible assignments based on their open/close dates or
    // visibility setting
    List<Assignment> visibleAssignments = new ArrayList<>();

    for (Assignment assignment : assignments) {
      if (isVisible(assignment)) {
        visibleAssignments.add(assignment);
      }
    }

    return visibleAssignments;
  }

  private boolean isVisible(Assignment assignment) throws SQLException {

    Date openDate = assignment.getOpenDate();
    Date closeDate = assignment.getCloseDate();

    if (openDate == null && closeDate == null) {
      return assignment.getVisibility();
    } else {
      // Ignore assignment visibility if both are not null
      Date now = new UtilDao().getServerTime();
      // Null open date means always open before close
      // Null close date means always open after open
      return (openDate == null || now.compareTo(openDate) >= 0)
          && (closeDate == null || now.before(closeDate));
    }
  }

}
