package tetris.domain;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private int type;
    private int rotation;
    private List<Point> points;

    public Shape(int num) {
        this.type = num;
        this.rotation = 0;
        this.points = new ArrayList<Point>();

        createPoints();
    }

    public Shape(Shape shape) {
        this.type = shape.type;
        this.rotation = shape.rotation;
        this.points = new ArrayList<Point>(shape.points.size());
        for (Point point : shape.points) {
            this.points.add(new Point(point.getX(), point.getY(), type));
        }
    }

    private void createPoints() {
        if (type != 7 && type != 4 && type != 3 && type != 1) {
            this.points.add(new Point(3, 0, type));
        }

        if (type != 6 && type != 1) {
            this.points.add(new Point(4, 0, type));
        }

        if (type != 6 && type != 5 && type != 3) {
            this.points.add(new Point(5, 0, type));
        }

        if (type == 2) {
            this.points.add(new Point(6, 0, type));
        } else {
            this.points.add(new Point(4, 1, type));
        }

        if (type != 4 && type != 2) {
            this.points.add(new Point(5, 1, type));
        }

        if (type == 6 || type == 4 || type == 3 || type == 1) {
            this.points.add(new Point(3, 1, type));
        }
    }

    public void moveDown() {
        for (Point point : points) {
            point.modY(1);
        }
    }

    public void moveLeft() {
        for (Point point : points) {
            point.modX(-1);
        }
    }

    public void moveRight() {
        for (Point point : points) {
            point.modX(1);
        }
    }

    public void rotate() {
        int lowX = 100;
        int lowY = 100;

        if (type != 7) {
            for (Point point : points) {
                if (point.getX() < lowX) {
                    lowX = point.getX();
                }
                if (point.getY() < lowY) {
                    lowY = point.getY();
                }
            }

            if (type == 2) {
                for (Point point : points) {
                    if (rotation == 0) {
                        point.setLocation(point.getY() - lowY + lowX + 2, point.getX() - lowX + lowY - 1);
                    } else if (rotation == 1) {
                        point.setLocation(point.getY() - lowY + lowX - 2, point.getX() - lowX + lowY + 2);
                    } else if (rotation == 2) {
                        point.setLocation(point.getY() - lowY + lowX + 1, point.getX() - lowX + lowY - 2);
                    } else {
                        point.setLocation(point.getY() - lowY + lowX - 1, point.getX() - lowX + lowY + 1);
                    }
                }
            } else {
                for (Point point : points) {
                    if (rotation == 1 || rotation == 2) {
                        point.setLocation(2 - (point.getY() - lowY) + lowX - 1, (point.getX() - lowX - 1 + (rotation % 2 * 2)) + lowY);
                    } else {
                        point.setLocation(2 - (point.getY() - lowY) + lowX, (point.getX() - lowX) + lowY);
                    }
                }
            }
        }
        rotation = (rotation + 1) % 4;
    }
    
    public List<Point> getRotatePoints() {
        Shape rotated = new Shape(this);
        
        rotated.rotate();
        return rotated.points;
    }
    
    public int getType() {
        return type;
    }
    
    public List<Point> getPoints() {
        return points;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (Point point : points) {
            str += "x: " + point.getX() + " y: " + point.getY() + "\n";
        }
        
        return str;
    }
}
