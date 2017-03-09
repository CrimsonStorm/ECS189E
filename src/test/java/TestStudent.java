import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Joe on 3/7/2017.
 */
public class TestStudent {

    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();

    }
    // Test to make sure a student can register for a class.
    // assertTrue should return true.
    // After testing this is not a bug
    @Test
    public void testRegister1(){
        this.admin.createClass("Test",2017, "Instructor",10);
        this.student.registerForClass("Student1","Test",2017);
        assertTrue(this.student.isRegisteredFor("Student1","Test",2017));

    }

    // Test to make sure a student cannot register after the capacity has been reached.
    // assertFalse should return true.
    // After testing this is a bug
    @Test
    public void testRegisterForClass2(){
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("Student1","Test",2017);
        this.student.registerForClass("Student2","Test",2017);
        this.student.registerForClass("Student3","Test",2017);

        assertFalse(this.student.isRegisteredFor("Student2","Test",2017));

    }


    //Test case to make sure a an Instructor can't have more than 2 classes.
    // assertNull should return true.
    // After testing this is a bug.
    @Test
    public void testDropClass(){
        this.admin.createClass("Test3", 2017, "Instructor", 2);
        this.student.registerForClass("Student1","Test3",2017);
        this.student.dropClass("Student1","Test3",2017);
        assertFalse(this.student.isRegisteredFor("Student1","Test3",2017));
    }



    //Test case to make sure a student can submit homework.
    // assertTrue should return true.
    // After testing this is not bug.

    @Test
    public void testSubmitHomework(){
        this.admin.createClass("Test3", 2017, "Instructor", 2);
        this.instructor.addHomework("Instructor","Test3",2017,"HW4","Homework 4");
        this.student.registerForClass("Student1","Test3",2017);
        this.student.submitHomework("Student1","HW4","yes","Test3",2017);
        assertTrue(this.student.hasSubmitted("Student1","HW4","Test3",2017));
    }

    //Test case to make sure homework cannot be submitted other than the current year.
    // assertFalse should return true.
    // After testing this is not bug.
    @Test
    public void testSubmitHomework2(){
        this.admin.createClass("Test3", 2017, "Instructor", 2);
        this.instructor.addHomework("Instructor","Test3",2017,"HW4","Homework 4");
        this.student.registerForClass("Student1","Test3",2017);
        this.student.submitHomework("Student1","HW4","yes","Test3",2018);
        assertFalse(this.student.hasSubmitted("Student1","HW4","Test3",2018));
    }





}
