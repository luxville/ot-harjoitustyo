package Tetris;



/**
 * Tässä luokassa luodaan Tetriksen ruudukko ja ylläpidetään pudonneiden 
 * palikoiden sijainteja.
 */
public class Board {
    public static final Point MOVE_RIGHT = new Point(0, 1);
    public static final Point MOVE_LEFT = new Point(0, -1);
    public static final Point MOVE_DOWN = new Point(1, 0);
    private static final char EMPTY = '.';
    private final int rows;
    private final int cols;
    private char[][] board;
    private Tetromino current;
    private Point location;
    
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new char[rows][cols];
    }

    private boolean allow(Point movedPoint) {
        return allow(current.shape, movedPoint);
    }

    private boolean allow(Shape shape, Point location) {
        return shape.points.stream()
                .map(p -> p.movePoint(location))
                .allMatch(p -> isWithinBoard(p) && isBlank(p));
    }
    
    private boolean allow(Tetromino tetromino) {
        return allow(tetromino.shape, location);
    }

    private void clear() {
        current = null;
        location = null;
    }
        
    private char[][] currentBoard() {
        if (current == null) return board;
        
        final char[][] board2 = this.board;
        current.getPoints().stream().forEach(p -> {
            Point at = location.movePoint(p);
            board2[at.row][at.col] = current.ch;
        });
        return board;
    }
    
    public boolean drop(Tetromino tetromino) {
        if (current != null) throw new IllegalStateException("Tippuu jo");
        this.current = tetromino;
        this.location = new Point(0, cols / 2);
        if (!allow(location)) {
            clear();
            return false;
        }
        return true;
    }

    public boolean hasFalling() {
        return this.current != null;
    }
    
    private boolean isBlank(Point p) {
        return board[p.row][p.col] == 0;
    }
    
    private boolean isWithinBoard(Point p) {
        return p.row >= 0 && p.col >= 0 && p.row < rows && p.col < cols;
    }
      
    private boolean move(Point movedPoint) {
        if (allow(movedPoint)) {
            this.location = movedPoint;
            return true;
        }
        return false;
    }
    
    public void moveDown() {
        if (!move(this.location.movePoint(MOVE_DOWN))) stickIt();
    }
    
    public void moveLeft() {
        move(this.location.movePoint(MOVE_LEFT));
    }
    
    public void moveRight() {
        move(this.location.movePoint(MOVE_RIGHT));
    }
    
    public void rotate(Tetromino next) {
        if (allow(next)) {
            current = next;
        }
    }
    
    public void rotateLeft() {
        rotate(current.rotateLeft());
    }
    
    public void rotateRight() {
        rotate(current.rotateRight());
    }
    
    private void stickIt() {
        current.getPoints().stream()
                .map(p -> p.movePoint(location))
                .forEach(p -> board[p.row][p.col] = this.current.ch);
        clear();
    }
    
    public String toString() {
        return toString(currentBoard());
    }
    
    public static String toString(char[][] board) {
        StringBuilder sb = new StringBuilder(board.length * board[0].length + board.length);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char cs = board[row][col];
                sb.append(cs > 0 ? cs : EMPTY);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
