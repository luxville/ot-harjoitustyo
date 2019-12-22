package tetris.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board2;
    Board board4;

    public BoardTest() {
    }

    @Before
    public void setUp() {
        board2 = new Board(2);
        board4 = new Board(4);
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
    }

    @Test
    public void calculateCurrentScoreTest() {
        board2 = new Board(2);
        assertEquals(40, board2.calculateCurrentScore(1));
        assertEquals(100, board2.calculateCurrentScore(2));
        assertEquals(300, board2.calculateCurrentScore(3));
        assertEquals(1000, board2.calculateCurrentScore(4));
    }

    @Test
    public void updateSpeedTest() {
        board2 = new Board(2);
        board2.setLevel(3);
        board2.updateSpeed();
        assertEquals(550, board2.getTimePerBlock());
        board2.setLevel(9);
        board2.updateSpeed();
        assertEquals(100, board2.getTimePerBlock());
        board2.setLevel(10);
        board2.updateSpeed();
        assertEquals(83, board2.getTimePerBlock());
        board2.setLevel(20);
        board2.updateSpeed();
        assertEquals(33, board2.getTimePerBlock());
        board2.setLevel(29);
        board2.updateSpeed();
        assertEquals(16, board2.getTimePerBlock());
    }
}
