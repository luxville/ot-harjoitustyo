package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {
Point point;
Point typedPoint;

    @Before
    public void setUp() {
        point = new Point(1, 1);
        typedPoint = new Point(1, 1, 2);
    }

    @Test
    public void createPoint() {
        assertEquals(point.x, 1);
        assertEquals(point.y, 1);
    }

    @Test
    public void createTypedPoint() {
        assertEquals(typedPoint.x, 1);
        assertEquals(typedPoint.y, 1);
        assertEquals(typedPoint.type, 2);
    }

    @Test
    public void modX() {
        point.modX(2);
        assertEquals(point.x, 3);
        assertEquals(point.y, 1);
    }
    
    @Test
    public void modY() {
        point.modY(2);
        assertEquals(point.x, 1);
        assertEquals(point.y, 3);
    }
    
    @Test
    public void getType() {
        assertEquals(2, typedPoint.getType());
    }
    
    @Test
    public void notPoint() {
        Shape shape = new Shape(7);
        assertNotEquals(point, shape);
    }
}
