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
public class TestAdmin {

    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();

    }

    //Test case to make sure capacity can be set.
    // assertTrue should return true.
    // After testing this is not a bug.
    @Test
    public void testchangeCapacity(){
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.admin.changeCapacity("Test",2017,11);
        assertTrue(this.admin.getClassCapacity("Test",2017) > 10);
    }


    //Test case to make sure capacity can't be set to 0 or below.
    // assertTrue should return true.
    // After testing this is a bug.
    @Test
    public void testMakeClassCapacity(){
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertTrue(this.admin.getClassCapacity("Test",2017) > 0);
    }


    //Test case to make sure a an Instructor can't have more than 2 classes.
    // assertNull should return true.
    // After testing this is a bug.
    @Test
    public void testInstructorClassNumbers(){
        this.admin.createClass("Test1", 2017, "Instructor", 20);
        this.admin.createClass("Test2", 2017, "Instructor", 20);
        this.admin.createClass("Test3", 2017, "Instructor", 20);
        assertNull(this.admin.classExists("Test3",2017));

    }

    //Test case to make sure there isn't a duplicate of a class.
    // assertFalse should return true.
    // After testing this is a bug.
    @Test
    public void testMultipleInstanesOfClass(){
        this.admin.createClass("Test1", 2017, "Instructor", 20);
        this.admin.createClass("Test1", 2017, "Instructor1", 20);
        assertFalse(this.admin.classExists("Test1",2017));

    }

    //Test case to make sure the capacity can't be set lower than the original maximum capacity.
    // assertTrue should return true.
    // After testing this is a bug.
    @Test
    public void testChangeCapacity(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        int currentCapacity = admin.getClassCapacity("Test",2017);
        this.admin.changeCapacity("Test",2017,19);
        assertTrue(this.admin.getClassCapacity("Test",2017) >= currentCapacity);
    }



}
