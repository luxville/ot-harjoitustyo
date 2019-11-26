package tetris.domain;

public class Point {

    private int x;
    private int y;
    private int type;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void modX(int mod) {
        x += mod;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void modY(int mod) {
        y += mod;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            return x == ((Point) obj).x && y == ((Point) obj).y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Point X: " + x + " Y: " + y + " Type: " + type;
    }
}
