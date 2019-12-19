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
     * Metodissa luodaan palikat sijoittamalla niiden tyypin mukaan ruutuja
     * pelialueen ylälaitaan.
     */
    private void createPoints() {
        switch (type) {
            case 1:
                createL();
            case 2:
                createI();
            case 3:
                createT();
            case 4:
                createS();
            case 5:
                createZ();
            case 6:
                createJ();
            case 7:
                createO();
        }
    }

    /**
     * Metodissa palikkaa siirretään alaspäin.
     */
    public void moveDown() {
        for (Point point : points) {
            point.modY(1);
        }
    }

    /**
     * Metodissa palikkaa siirretään vasemmalle.
     */
    public void moveLeft() {
        for (Point point : points) {
            point.modX(-1);
        }
    }

    /**
     * Metodissa palikkaa siirretään oikealle.
     */
    public void moveRight() {
        for (Point point : points) {
            point.modX(1);
        }
    }

    /**
     * Metodissa palikan pisteille lasketaan sen tyypin perusteella kulloinkin
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
     * Metodissa vuorossa olevaa palikkaa käännetään.
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
     * Metodissa luodaan merkkijono kaikkien palikan pisteiden koordinaateista.
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

    private void rotateOtherTypes() {
        for (Point point : points) {
            if (rotation == 1 || rotation == 2) {
                point.setLocation(2 - (point.getY() - lowY) + lowX - 1, (point.getX() - lowX - 1 + (rotation % 2 * 2)) + lowY);
            } else {
                point.setLocation(2 - (point.getY() - lowY) + lowX, (point.getX() - lowX) + lowY);
            }
        }
    }

    private void createL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createT() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createZ() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createJ() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
