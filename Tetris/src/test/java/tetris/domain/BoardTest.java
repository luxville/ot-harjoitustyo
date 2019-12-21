package tetris.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board2;
    Board board4;
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
        board2 = new Board(2);
        board4 = new Board(4);
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
    public void closeToBottomAndCanRotateTest() {
        board4 = new Board(4);
        for (int k = 0; k < 19; k++) {
            board4.moveDown();
        }
        assertTrue(board4.canRotate());
        assertFalse(board4.closeToBottomBorder());
        board4.moveDown();
        assertFalse(board4.canRotate());
        assertTrue(board4.closeToBottomBorder());
        board4.moveDown();
    }

    @Test
    public void moveAndCloseToSLeftBorderTest() {
        board2 = new Board(2);
        for (int k = 0; k < 3; k++) {
            board2.moveLeft();
        }
        assertTrue(board2.closeToLeftBorder());
        board2.moveRight();
        assertFalse(board2.closeToLeftBorder());
        board2.moveLeft();
        board2.moveLeft();
        assertTrue(board2.closeToLeftBorder());
        /*assertFalse(board2.closeToRightBorder());
        for (int k = 0; k < 5; k++) {
            board2.moveRight();
        }
        assertTrue(board2.closeToRightBorder());
        for (int k = 0; k < 18; k++) {
            board2.moveDown();
        }
        board2.rotate();
        board2.moveDown();
        board2.moveDown();*/
    }
    
    @Test
    public void calculateCurrentScoreTest() {
        board2 = new Board(2);
        assertEquals(40, board2.calculateCurrentScore(1));
        assertEquals(100, board2.calculateCurrentScore(2));
        assertEquals(300, board2.calculateCurrentScore(3));
        assertEquals(1000, board2.calculateCurrentScore(4));
    }
}
