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

    Shape l;
    Shape i;
    Shape t;
    Shape s;
    Shape z;
    Shape j;
    Shape o;
    List<Point> lPoints;
    List<Point> iPoints;
    List<Point> tPoints;
    List<Point> sPoints;
    List<Point> zPoints;
    List<Point> jPoints;
    List<Point> oPoints;

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
        Shape l = new Shape(1);
        Shape i = new Shape(2);
        Shape t = new Shape(3);
        Shape s = new Shape(4);
        Shape z = new Shape(5);
        Shape j = new Shape(6);
        Shape o = new Shape(7);

        List<Point> lPoints = l.getPoints();
        List<Point> iPoints = i.getPoints();
        List<Point> tPoints = t.getPoints();
        List<Point> sPoints = s.getPoints();
        List<Point> zPoints = z.getPoints();
        List<Point> jPoints = j.getPoints();
        List<Point> oPoints = o.getPoints();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createPointsTest() {
        /*Shape l = new Shape(1);
        Shape i = new Shape(2);
        Shape t = new Shape(3);
        Shape s = new Shape(4);
        Shape z = new Shape(5);
        Shape j = new Shape(6);
        Shape o = new Shape(7);
         */
        //List<Point> points = l.getPoints();
        assertThat(lPoints.get(0), equalTo(new Point(5, 0)));
        assertThat(lPoints.get(1), equalTo(new Point(4, 1)));
        assertThat(lPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(lPoints.get(3), equalTo(new Point(3, 1)));

        //points = i.getPoints();
        assertThat(iPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(iPoints.get(1), equalTo(new Point(4, 0)));
        assertThat(iPoints.get(2), equalTo(new Point(5, 0)));
        assertThat(iPoints.get(3), equalTo(new Point(6, 0)));

        //tPoints = t.getPoints();
        assertThat(tPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(tPoints.get(1), equalTo(new Point(4, 1)));
        assertThat(tPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(tPoints.get(3), equalTo(new Point(3, 1)));

        // sPoints = s.getPoints();
        assertThat(sPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(sPoints.get(1), equalTo(new Point(5, 0)));
        assertThat(sPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(sPoints.get(3), equalTo(new Point(3, 1)));

        //zPoints = z.getPoints();
        assertThat(zPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(zPoints.get(1), equalTo(new Point(4, 0)));
        assertThat(zPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(zPoints.get(3), equalTo(new Point(5, 1)));

        // jPoints = j.getPoints();
        assertThat(jPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(jPoints.get(1), equalTo(new Point(4, 1)));
        assertThat(jPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(jPoints.get(3), equalTo(new Point(3, 1)));

        // oPoints = o.getPoints();
        assertThat(oPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(oPoints.get(1), equalTo(new Point(5, 0)));
        assertThat(oPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(oPoints.get(3), equalTo(new Point(5, 1)));
    }

    @Test
    public void moveShapeLeft() {
        //Shape l = new Shape(1);
        //Shape i = new Shape(2);
        //Shape t = new Shape(3);

        //List<Point> points = l.getPoints();
        l.moveLeft();
        assertThat(lPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(lPoints.get(1), equalTo(new Point(3, 1)));
        assertThat(lPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(lPoints.get(3), equalTo(new Point(2, 1)));

        //points = i.getPoints();
        i.moveLeft();
        assertThat(iPoints.get(0), equalTo(new Point(2, 0)));
        assertThat(iPoints.get(1), equalTo(new Point(3, 0)));
        assertThat(iPoints.get(2), equalTo(new Point(4, 0)));
        assertThat(iPoints.get(3), equalTo(new Point(5, 0)));

        //points = t.getPoints();
        t.moveLeft();
        assertThat(tPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(tPoints.get(1), equalTo(new Point(3, 1)));
        assertThat(tPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(tPoints.get(3), equalTo(new Point(2, 1)));
    }

    @Test
    public void moveShapeRight() {

        //Shape s = new Shape(4);
        //Shape z = new Shape(5);

        //List<Point> points = s.getPoints();
        //points = s.getPoints();
        s.moveRight();
        assertThat(sPoints.get(0), equalTo(new Point(5, 0)));
        assertThat(sPoints.get(1), equalTo(new Point(6, 0)));
        assertThat(sPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(sPoints.get(3), equalTo(new Point(4, 1)));

        //points = z.getPoints();
        z.moveRight();
        assertThat(zPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(zPoints.get(1), equalTo(new Point(5, 0)));
        assertThat(zPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(zPoints.get(3), equalTo(new Point(6, 1)));
    }

    @Test
    public void moveShapeDown() {
        Shape j = new Shape(6);
        Shape o = new Shape(7);

        List<Point> points = j.getPoints();

        j.moveDown();
        assertThat(points.get(0), equalTo(new Point(3, 1)));
        assertThat(points.get(1), equalTo(new Point(4, 2)));
        assertThat(points.get(2), equalTo(new Point(5, 2)));
        assertThat(points.get(3), equalTo(new Point(3, 2)));

        points = o.getPoints();
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

        assertThat(l.getType(), equalTo(l.getType()));
        assertThat(z.getType(), equalTo(z.getType()));
        assertThat(o.getType(), equalTo(o.getType()));
    }
}
