package zodiac.action;

import java.util.List;

import zodiac.dao.coursework.AssignmentDao;
import zodiac.definition.coursework.Assignment;

public class AssignmentAction {

    /**
     * Add API to create a an assignment.
     */
    public int addAssignment(String assignmentName, String courseCode) {
        return new AssignmentDao().addAssignment(assignmentName, courseCode);
    }

    /**
     * Added api to check all assignments in a course
     * @param courseCode
     * @return List<Assignment>
     */
    public List<Assignment> checkAssignments(String courseCode) {
        return new AssignmentDao().getAssignments(courseCode);
    }
}

    public boolean changeAssignmentVisibility(Assignment assignment){
        return new AssignmentDao().changeAssignmentVisibility(assignment);
    }

}
