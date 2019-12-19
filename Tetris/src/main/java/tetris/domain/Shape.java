package tetris.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokassa luodaan tetrominot eli palikat ja niiden tarvitsemat metodit
 */
public class Shape {

    private int lowX;
    private int lowY;
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

    /**
     * Kutsuu palikat luovia metodeja palikan tyypin mukaan.
     */
    private void createPoints() {
        switch (type) {
            case 1:
                createL();
                break;
            case 2:
                createI();
                break;
            case 3:
                createT();
                break;
            case 4:
                createS();
                break;
            case 5:
                createZ();
                break;
            case 6:
                createJ();
                break;
            case 7:
                createO();
        }
    }

    /**
     * Siirtää palikkaa alaspäin.
     */
    public void moveDown() {
        for (Point point : points) {
            point.modY(1);
        }
    }

    /**
     * Siirtää palikkaa vasemmalle.
     */
    public void moveLeft() {
        for (Point point : points) {
            point.modX(-1);
        }
    }

    /**
     * Siirtää palikkaa oikealle.
     */
    public void moveRight() {
        for (Point point : points) {
            point.modX(1);
        }
    }

    /**
     * Palikan pisteille lasketaan sen tyypin perusteella kulloinkin
     * oikea seuraava tila.
     */
    public void rotate() {
        lowX = 100;
        lowY = 100;
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
                rotateType2();
            } else {
                rotateOtherTypes();
            }
        }
        rotation = (rotation + 1) % 4;
    }

    /**
     * Vuorossa olevaa palikkaa käännetään.
     *
     * @return lista palikan pisteistä sen kääntämisen jälkeen
     */
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

    /**
     * Luo merkkijonon kaikkien palikan pisteiden koordinaateista.
     *
     * @return merkkijono, jossa on kaikkien palikan pisteiden koordinaatit
     */
    @Override
    public String toString() {
        String str = "";

        for (Point point : points) {
            str += "x: " + point.getX() + " y: " + point.getY() + "\n";
        }

        return str;
    }

    /**
     * Kääntää tyypin 2 eli I-palikkaa.
     */
    private void rotateType2() {
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
    }

    /** 
     * Kääntää muita kuin tyypin 2 eli I-palikoita.
     */
    private void rotateOtherTypes() {
        for (Point point : points) {
            if (rotation == 1 || rotation == 2) {
                point.setLocation(2 - (point.getY() - lowY) + lowX - 1, (point.getX() - lowX - 1 + (rotation % 2 * 2)) + lowY);
            } else {
                point.setLocation(2 - (point.getY() - lowY) + lowX, (point.getX() - lowX) + lowY);
            }
        }
    }

    /** 
     * Luo tyypin 1 eli L-palikan.
     */
    private void createL() {
        this.points.add(new Point(5, 0, type));
        this.points.add(new Point(4, 1, type));
        this.points.add(new Point(5, 1, type));
        this.points.add(new Point(3, 1, type));
    }

    /** 
     * Luo tyypin 2 eli I-palikan.
     */
    private void createI() {
        this.points.add(new Point(3, 0, type));
        this.points.add(new Point(4, 0, type));
        this.points.add(new Point(5, 0, type));
        this.points.add(new Point(6, 0, type));
    }

    /** 
     * Luo tyypin 3 eli T-palikan.
     */
    private void createT() {
        this.points.add(new Point(4, 0, type));
        this.points.add(new Point(4, 1, type));
        this.points.add(new Point(5, 1, type));
        this.points.add(new Point(3, 1, type));
    }

    /** 
     * Luo tyypin 4 eli S-palikan.
     */
    private void createS() {
        this.points.add(new Point(4, 0, type));
        this.points.add(new Point(5, 0, type));
        this.points.add(new Point(4, 1, type));
        this.points.add(new Point(3, 1, type));
    }

    /** 
     * Luo tyypin 5 eli Z-palikan.
     */
    private void createZ() {
        this.points.add(new Point(3, 0, type));
        this.points.add(new Point(4, 0, type));
        this.points.add(new Point(4, 1, type));
        this.points.add(new Point(5, 1, type));
    }

    /** 
     * Luo tyypin 6 eli J-palikan.
     */
    private void createJ() {
        this.points.add(new Point(3, 0, type));
        this.points.add(new Point(4, 1, type));
        this.points.add(new Point(5, 1, type));
        this.points.add(new Point(3, 1, type));
    }

    /** 
     * Luo tyypin 7 eli O-palikan.
     */
    private void createO() {
        this.points.add(new Point(4, 0, type));
        this.points.add(new Point(5, 0, type));
        this.points.add(new Point(4, 1, type));
        this.points.add(new Point(5, 1, type));
    }
}
