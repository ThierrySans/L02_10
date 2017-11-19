package zodiac.dao.coursework;

import org.junit.Test;
import zodiac.action.AssignmentAction;
import zodiac.definition.coursework.Assignment;

import static org.junit.Assert.*;
import java.util.List;

public class AssignmentDaoTest {

    @Test
    public void testGetAssignment()
    {
        List<Assignment> res = new AssignmentDao().getAssignments("CSCC01");
        int tick = 0;
        for (Assignment a : res)
        {
            if (a.getName().equals("Test01") || a.getName().equals("Test03"))
            {
                tick++;
            }
        }
        assertTrue(tick >= 2);
    }

    @Test
    public void testGetAssignmentCourseDne()
    {
        List<Assignment> res = new AssignmentDao().getAssignments("XDXD69");
        assertTrue(res.size() == 0);
    }

    @Test
    public void testAddAssignment()
    {
        new AssignmentDao().addAssignment("UnitTesting", "CSCC01");
        List<Assignment> res = new AssignmentDao().getAssignments("CSCC01");
        int i = 0;
        boolean found = false;
        while (!found && i < res.size())
        {
            if (res.get(i).getName().equals("UnitTesting"))
            {
                found = true;
            }
            i++;
        }
        assertTrue(found);
    }

    @Test
    public void testEditMaxAttemptId()
    {
        int newVal = 12;
        int targetId = 27;
        new AssignmentDao().editAssignmentMaxAttempt(targetId, newVal);
        assertEquals(new AssignmentAction().getAssignment(targetId).getMaxAttempt(), newVal);
    }

    @Test
    public void testEditMaxAttemptAss()
    {
        int targetId = 27;
        int newVal = 14;
        Assignment a = new Assignment("testAssign", targetId);
        a.setMaxAttempt(newVal);
        new AssignmentDao().editAssignmentMaxAttempt(a);
        assertEquals(new AssignmentAction().getAssignment(targetId).getMaxAttempt(), newVal);
    }
}
