package tetris.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;
    Shape currentShape;
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

    public BoardTest() {
    }

    @Before
    public void setUp() {
        board = new Board(4);
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

    @After
    public void tearDown() {
    }

    @Test
    public void hasPointsDown() {
        //board.createCurrentShape(4);
        //currentShape = s;
        for (int k = 0; k < 19; k++) {
            board.moveDown();
        }
        assertTrue(board.canRotate());
        assertFalse(board.closeToBottomBorder());
        board.moveDown();
        assertFalse(board.canRotate());
        assertTrue(board.closeToBottomBorder());
        board.moveDown();
 /*       board.createCurrentShape(2);
        board.moveDown();
        board.moveDown();
        board.moveDown();
        assertTrue(board.canRotate());
        for (int k = 0; k < 4; k++) {
            board.moveRight();            
        }
        assertFalse(board.canRotate());*/
    }
}
