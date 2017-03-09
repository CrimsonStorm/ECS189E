import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vincent on 23/2/2017.
 */
public class TestExample {

    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }



    @Test
    public void studentIsRegistered(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.student.registerForClass("student","Test",2017);
        assertTrue(this.student.isRegisteredFor("student","Test",2017));
    }






}
