import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Joe on 3/7/2017.
 */
public class TestInstructor {

    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();

    }

    //Test to make sure a different instructor can't add hw to a class
    // After test, AssertFalse should return true and it should pass.
    // After testing, test fails. This is a bug.
    @Test
    public void testAddHomework(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.instructor.addHomework("Instructor1","Test",2017,"HW1","First homework.");
        assertFalse(this.instructor.homeworkExists("Test",2017,"HW1"));

    }



    // make sure a homework assignment exists in the class.
    // assertTrue should return true.
    // After testing, not a bug.
    @Test
    public void testHomeworkExists(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.instructor.addHomework("Instructor","Test",2017,"HW1","First homework.");
        assertTrue(this.instructor.homeworkExists("Test",2017,"HW1"));

    }

    // Make a homework assignment for a different class and test it.
    // assertFalse should return true.
    // After testing, not a bug.
    @Test
    public void testHomeworkExists2(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.instructor.addHomework("Instructor","Test",2017,"HW1","First homework.");
        assertFalse(this.instructor.homeworkExists("Test2",2017,"HW1"));

    }

    //Test case to make sure a different instructor can't assign a grade.
    // assertNull should return true.
    // After testing this is a bug.
    @Test
    public void testAssignGrade(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.student.registerForClass("Student","Test",2017);
        this.instructor.addHomework("Instructor","Test",2017,"HW1","First HW");
        this.student.submitHomework("Student","HW1","yes","Test2",2017);

        this.instructor.assignGrade("Instructor1","Test",2017,"HW1","Student",100);
        assertNull(this.instructor.getGrade("Test",2017,"HW1","Student"));

    }

    //Test case to make sure a homework not assigned cannot be graded.
    // assertnull should return true.
    // After testing this is not a bug.
    @Test
    public void testAssignGrade2(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.student.registerForClass("Student","Test",2017);
        this.instructor.addHomework("Instructor","Test",2017,"HW2","Second HW");
        this.instructor.assignGrade("Instructor","Test",2017,"HW3","Student",100);
        assertNull(this.instructor.getGrade("Test",2017,"HW3","Student"));

    }


    //Test case to make sure a homework assignment has been submitted by a student
    // assertnull should return true.
    // After testing this is not a bug.
    @Test
    public void testAssignGrade3(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.student.registerForClass("Student","Test",2017);
        this.instructor.addHomework("Instructor","Test",2017,"HW2","Second HW");
        this.student.submitHomework("Student","HW2","yes","Test2",2017);
        this.instructor.assignGrade("Instructor","Test",2017,"HW2","Student",100);

        assertNull(this.instructor.getGrade("Test",2017,"HW2","Student"));

    }
}
