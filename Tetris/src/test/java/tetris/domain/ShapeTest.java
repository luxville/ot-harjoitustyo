package tetris.domain;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Before;
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

    @Before
    public void setUp() {
        l = new Shape(1);
        i = new Shape(2);
        t = new Shape(3);
        s = new Shape(4);
        z = new Shape(5);
        j = new Shape(6);
        o = new Shape(7);

        lPoints = l.getPoints();
        iPoints = i.getPoints();
        tPoints = t.getPoints();
        sPoints = s.getPoints();
        zPoints = z.getPoints();
        jPoints = j.getPoints();
        oPoints = o.getPoints();
    }

    @Test
    public void createPointsTest() {
        assertThat(lPoints.get(0), equalTo(new Point(5, 0)));
        assertThat(lPoints.get(1), equalTo(new Point(4, 1)));
        assertThat(lPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(lPoints.get(3), equalTo(new Point(3, 1)));

        assertThat(iPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(iPoints.get(1), equalTo(new Point(4, 0)));
        assertThat(iPoints.get(2), equalTo(new Point(5, 0)));
        assertThat(iPoints.get(3), equalTo(new Point(6, 0)));

        assertThat(tPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(tPoints.get(1), equalTo(new Point(4, 1)));
        assertThat(tPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(tPoints.get(3), equalTo(new Point(3, 1)));

        assertThat(sPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(sPoints.get(1), equalTo(new Point(5, 0)));
        assertThat(sPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(sPoints.get(3), equalTo(new Point(3, 1)));

        assertThat(zPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(zPoints.get(1), equalTo(new Point(4, 0)));
        assertThat(zPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(zPoints.get(3), equalTo(new Point(5, 1)));

        assertThat(jPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(jPoints.get(1), equalTo(new Point(4, 1)));
        assertThat(jPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(jPoints.get(3), equalTo(new Point(3, 1)));

        assertThat(oPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(oPoints.get(1), equalTo(new Point(5, 0)));
        assertThat(oPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(oPoints.get(3), equalTo(new Point(5, 1)));
    }

    @Test
    public void moveShapeLeft() {

        l.moveLeft();
        assertThat(lPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(lPoints.get(1), equalTo(new Point(3, 1)));
        assertThat(lPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(lPoints.get(3), equalTo(new Point(2, 1)));

        i.moveLeft();
        assertThat(iPoints.get(0), equalTo(new Point(2, 0)));
        assertThat(iPoints.get(1), equalTo(new Point(3, 0)));
        assertThat(iPoints.get(2), equalTo(new Point(4, 0)));
        assertThat(iPoints.get(3), equalTo(new Point(5, 0)));

        t.moveLeft();
        assertThat(tPoints.get(0), equalTo(new Point(3, 0)));
        assertThat(tPoints.get(1), equalTo(new Point(3, 1)));
        assertThat(tPoints.get(2), equalTo(new Point(4, 1)));
        assertThat(tPoints.get(3), equalTo(new Point(2, 1)));
    }

    @Test
    public void moveShapeRight() {

        s.moveRight();
        assertThat(sPoints.get(0), equalTo(new Point(5, 0)));
        assertThat(sPoints.get(1), equalTo(new Point(6, 0)));
        assertThat(sPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(sPoints.get(3), equalTo(new Point(4, 1)));

        z.moveRight();
        assertThat(zPoints.get(0), equalTo(new Point(4, 0)));
        assertThat(zPoints.get(1), equalTo(new Point(5, 0)));
        assertThat(zPoints.get(2), equalTo(new Point(5, 1)));
        assertThat(zPoints.get(3), equalTo(new Point(6, 1)));
    }

    @Test
    public void moveShapeDown() {

        j.moveDown();
        assertThat(jPoints.get(0), equalTo(new Point(3, 1)));
        assertThat(jPoints.get(1), equalTo(new Point(4, 2)));
        assertThat(jPoints.get(2), equalTo(new Point(5, 2)));
        assertThat(jPoints.get(3), equalTo(new Point(3, 2)));

        o.moveDown();
        assertThat(oPoints.get(0), equalTo(new Point(4, 1)));
        assertThat(oPoints.get(1), equalTo(new Point(5, 1)));
        assertThat(oPoints.get(2), equalTo(new Point(4, 2)));
        assertThat(oPoints.get(3), equalTo(new Point(5, 2)));
    }

    @Test
    public void getShapeType() {
        assertThat(1, equalTo(l.getType()));
        assertThat(5, equalTo(z.getType()));
        assertThat(7, equalTo(o.getType()));
    }

    @Test
    public void rotateTypeL() {
        for (Point lPoint : lPoints) {
            lPoint.modY(3);
        }
        l.rotate();
        assertThat(1, equalTo(l.getRotation()));
        assertThat(lPoints.get(0), equalTo(new Point(5, 5)));
        assertThat(lPoints.get(1), equalTo(new Point(4, 4)));
        assertThat(lPoints.get(2), equalTo(new Point(4, 5)));
        assertThat(lPoints.get(3), equalTo(new Point(4, 3)));
        l.rotate();
        assertThat(2, equalTo(l.getRotation()));
        l.rotate();
        assertThat(3, equalTo(l.getRotation()));
        assertThat(lPoints.get(0), equalTo(new Point(3, 3)));
        assertThat(lPoints.get(1), equalTo(new Point(4, 4)));
        assertThat(lPoints.get(2), equalTo(new Point(4, 3)));
        assertThat(lPoints.get(3), equalTo(new Point(4, 5)));
        l.rotate();
        assertThat(0, equalTo(l.getRotation()));
    }

    @Test
    public void rotateTypeI() {
        for (Point iPoint : iPoints) {
            iPoint.modY(3);
        }
        i.rotate();
        assertThat(iPoints.get(0), equalTo(new Point(5, 2)));
        assertThat(iPoints.get(1), equalTo(new Point(5, 3)));
        assertThat(iPoints.get(2), equalTo(new Point(5, 4)));
        assertThat(iPoints.get(3), equalTo(new Point(5, 5)));
        i.rotate();
        assertThat(iPoints.get(0), equalTo(new Point(3, 4)));
        assertThat(iPoints.get(1), equalTo(new Point(4, 4)));
        assertThat(iPoints.get(2), equalTo(new Point(5, 4)));
        assertThat(iPoints.get(3), equalTo(new Point(6, 4)));
        i.moveRight();
        i.moveRight();
        i.moveRight();
        i.rotate();
        assertThat(iPoints.get(0), equalTo(new Point(7, 2)));
        assertThat(iPoints.get(1), equalTo(new Point(7, 3)));
        assertThat(iPoints.get(2), equalTo(new Point(7, 4)));
        assertThat(iPoints.get(3), equalTo(new Point(7, 5)));
        i.rotate();
        assertThat(iPoints.get(0), equalTo(new Point(6, 3)));
        assertThat(iPoints.get(1), equalTo(new Point(7, 3)));
        assertThat(iPoints.get(2), equalTo(new Point(8, 3)));
        assertThat(iPoints.get(3), equalTo(new Point(9, 3)));
    }
}
