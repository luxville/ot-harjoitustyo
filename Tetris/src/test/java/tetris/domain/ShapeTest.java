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
    public void createPoints() {
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

    @Test
    public void moveShapeLeft() {
        Shape l = new Shape(1);
        Shape i = new Shape(2);
        Shape t = new Shape(3);

        List<Point> points = l.points;

        l.moveLeft();
        assertThat(points.get(0), equalTo(new Point(4, 0)));
        assertThat(points.get(1), equalTo(new Point(3, 1)));
        assertThat(points.get(2), equalTo(new Point(4, 1)));
        assertThat(points.get(3), equalTo(new Point(2, 1)));

        points = i.points;
        i.moveLeft();
        assertThat(points.get(0), equalTo(new Point(2, 0)));
        assertThat(points.get(1), equalTo(new Point(3, 0)));
        assertThat(points.get(2), equalTo(new Point(4, 0)));
        assertThat(points.get(3), equalTo(new Point(5, 0)));

        points = t.points;
        t.moveLeft();
        assertThat(points.get(0), equalTo(new Point(3, 0)));
        assertThat(points.get(1), equalTo(new Point(3, 1)));
        assertThat(points.get(2), equalTo(new Point(4, 1)));
        assertThat(points.get(3), equalTo(new Point(2, 1)));
    }

    @Test
    public void moveShapeRight() {

        Shape s = new Shape(4);
        Shape z = new Shape(5);

        List<Point> points = s.points;
        points = s.points;
        s.moveRight();
        assertThat(points.get(0), equalTo(new Point(5, 0)));
        assertThat(points.get(1), equalTo(new Point(6, 0)));
        assertThat(points.get(2), equalTo(new Point(5, 1)));
        assertThat(points.get(3), equalTo(new Point(4, 1)));

        points = z.points;
        z.moveRight();
        assertThat(points.get(0), equalTo(new Point(4, 0)));
        assertThat(points.get(1), equalTo(new Point(5, 0)));
        assertThat(points.get(2), equalTo(new Point(5, 1)));
        assertThat(points.get(3), equalTo(new Point(6, 1)));
    }

    @Test
    public void moveShapeDown() {
        Shape j = new Shape(6);
        Shape o = new Shape(7);

        List<Point> points = j.points;

        j.moveDown();
        assertThat(points.get(0), equalTo(new Point(3, 1)));
        assertThat(points.get(1), equalTo(new Point(4, 2)));
        assertThat(points.get(2), equalTo(new Point(5, 2)));
        assertThat(points.get(3), equalTo(new Point(3, 2)));

        points = o.points;
        o.moveDown();
        assertThat(points.get(0), equalTo(new Point(4, 1)));
        assertThat(points.get(1), equalTo(new Point(5, 1)));
        assertThat(points.get(2), equalTo(new Point(4, 2)));
        assertThat(points.get(3), equalTo(new Point(5, 2)));
    }

    @Test
    public void getShapeType() {
        Shape l = new Shape(1);
        Shape i = new Shape(2);
        Shape t = new Shape(3);
        Shape s = new Shape(4);
        Shape z = new Shape(5);
        Shape j = new Shape(6);
        Shape o = new Shape(7);
        
        assertThat(l.type, equalTo(l.getType()));
        assertThat(z.type, equalTo(z.getType()));
        assertThat(o.type, equalTo(o.getType()));
    }
}
