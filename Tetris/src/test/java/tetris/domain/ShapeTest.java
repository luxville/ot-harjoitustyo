package tetris.domain;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ShapeTest {
    
    public ShapeTest() {
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
    public void create_points() {
        Shape l = new Shape(1);
        Shape i = new Shape(2);
        Shape t = new Shape(3);
        Shape s = new Shape(4);
        Shape z = new Shape(5);
        Shape j = new Shape(6);
        Shape o = new Shape(7);
        
        List<Point> points = l.points;
        assertThat(points.get(0), equalTo(new Point(5, 0)));
        assertThat(points.get(1), equalTo(new Point(4, 1)));
        assertThat(points.get(2), equalTo(new Point(5, 1)));
        assertThat(points.get(3), equalTo(new Point(3, 1)));
        
        points = i.points;
        assertThat(points.get(0), equalTo(new Point(3, 0)));
        assertThat(points.get(1), equalTo(new Point(4, 0)));
        assertThat(points.get(2), equalTo(new Point(5, 0)));
        assertThat(points.get(3), equalTo(new Point(6, 0)));
        
        points = t.points;
        assertThat(points.get(0), equalTo(new Point(4, 0)));
        assertThat(points.get(1), equalTo(new Point(4, 1)));
        assertThat(points.get(2), equalTo(new Point(5, 1)));
        assertThat(points.get(3), equalTo(new Point(3, 1)));
        
        points = s.points;
        assertThat(points.get(0), equalTo(new Point(4, 0)));
        assertThat(points.get(1), equalTo(new Point(5, 0)));
        assertThat(points.get(2), equalTo(new Point(4, 1)));
        assertThat(points.get(3), equalTo(new Point(3, 1)));
        
        points = z.points;
        assertThat(points.get(0), equalTo(new Point(3, 0)));
        assertThat(points.get(1), equalTo(new Point(4, 0)));
        assertThat(points.get(2), equalTo(new Point(4, 1)));
        assertThat(points.get(3), equalTo(new Point(5, 1)));
        
        points = j.points;
        assertThat(points.get(0), equalTo(new Point(3, 0)));
        assertThat(points.get(1), equalTo(new Point(4, 1)));
        assertThat(points.get(2), equalTo(new Point(5, 1)));
        assertThat(points.get(3), equalTo(new Point(3, 1)));
        
        points = o.points;
        assertThat(points.get(0), equalTo(new Point(4, 0)));
        assertThat(points.get(1), equalTo(new Point(5, 0)));
        assertThat(points.get(2), equalTo(new Point(4, 1)));
        assertThat(points.get(3), equalTo(new Point(5, 1)));
    }
}
