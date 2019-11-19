package Tetris;


public class Point {
    public final int row;
    public final int col;
    
    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public Point movePoint(Point nextPoint) {
        return new Point(this.row + nextPoint.row, this.col + nextPoint.col);
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object instanceof Point) {
            Point other = (Point) object;
            return this.row == other.row && this.col == other.col;
        } else return false;
    }
    
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
