package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    public PointTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void create_point() {
        Point point = new Point(1, 1);
        assertEquals(point.x, 1);
        assertEquals(point.y, 1);
    }

    @Test
    public void create_typed_point() {
        Point point = new Point(1, 1, 2);
        assertEquals(point.x, 1);
        assertEquals(point.y, 1);
        assertEquals(point.type, 2);
    }

    @Test
    public void mod_X() {
        Point point = new Point(1, 1);
        point.modX(2);
        assertEquals(point.x, 3);
        assertEquals(point.y, 1);
    }
    
    @Test
    public void mod_Y() {
        Point point = new Point(1, 1);
        point.modY(2);
        assertEquals(point.x, 1);
        assertEquals(point.y, 3);
    }
    
    @Test
    public void get_type() {
        Point point = new Point(1, 1, 2);
        assertEquals(2, point.getType());
    }
}
