package tetris.domain;

/**
 * Luokka määrittelee pisteet, joista palikat muodostuvat, sekä niihin liittyvät
 * toiminnot.
 */
public class Point {

    public int x;
    public int y;
    public int type;

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

    /**
     * Muuttaa pisteen x-koordinaattia.
     *
     * @param mod kertoo uuden x-koordinaatin suhteessa nykyiseen
     */
    public void modX(int mod) {
        x += mod;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    /**
     * Muuttaa pisteen y-koordinaattia.
     *
     * @param mod kertoo uuden y-koordinaatin suhteessa nykyiseen
     */
    public void modY(int mod) {
        y += mod;
    }

    /**
     * Tarkistaa, onko parametrina saatu olio Point-tyyppinen. Jos 
     * näin on, tarkistaa onko parametrina saatu Point-olio sama piste kuin
     * tarkasteltavana oleva.
     * 
     * @param obj   olio, jota epäillään Point-olioksi
     * 
     * @return      totuusarvo siitä, ovatko parametrina saatu olio ja 
     * tarkasteltavana oleva piste samoja
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            return x == ((Point) obj).x && y == ((Point) obj).y;
        }
        return false;
    }

    /**
     * Palauttaa tarkasteltavan Point-olion koordinaatit ja tyypin.
     * 
     * @return merkkijono, jossa ovat pisteen x- ja y-koordinaatit sekä tyyppi
     */
    @Override
    public String toString() {
        return "Point X: " + x + " Y: " + y + " Type: " + type;
    }
}
