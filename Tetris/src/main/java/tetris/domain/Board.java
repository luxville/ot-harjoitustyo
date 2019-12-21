package tetris.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Luokka pelialueen luomiseen ja siinä tapahtuviin toimintoihin.
 */
public class Board {

    private boolean gameOver;
    private boolean gravityTriggered;
    private int numClearedLines;
    private int level;
    private int mostBottomLine;
    private int numOfEmpty = 0;
    private int score;
    private int timePerBlock;
    private List<Point> points;
    private List<Integer> fullLines;
    private List<Point> allPoints;
    private Random rand;
    private Shape currentShape;

    /**
     * Pelialueen leveys
     */
    public static final int WIDTH = 10;

    /**
     * Pelialueen korkeus
     */
    public static final int HEIGHT = 22;

    public Board() {
        this.gameOver = false;
        this.numClearedLines = 0;
        this.level = 0;
        this.score = 0;
        this.timePerBlock = 800;
        this.points = new ArrayList<Point>();
        this.rand = new Random();
        createCurrentShape();
    }
    
    public Board(int num) {
        this.gameOver = false;
        this.numClearedLines = 0;
        this.level = 0;
        this.score = 0;
        this.timePerBlock = 800;
        this.points = new ArrayList<Point>();
        this.rand = new Random();
        createCurrentShape(num);
    }

    /**
     * Luo satunnaisen palikan, joka tulee välittömästi pelattavaksi.
     */
    public void createCurrentShape() {
        int num = rand.nextInt(8);
        if (num == 7 || (currentShape != null && num == currentShape.getType())) {
            num = rand.nextInt(7);
        }
        if (currentShape != null) {
            points.addAll(currentShape.getPoints());
        }
        currentShape = new Shape(num);
    }

    /**
     * Luo halutun palikan testejä varten.
     *
     * @param num kokonaisluku, määrittää seuraavana pelattavan palikan tyypin.
     * 1 = L, 2 = I, 3 = T, 4 = S, 5 = Z, 6 = J ja 7 = O
     */
    public void createCurrentShape(int num) {
        if (num < 0 || num >= 7) {
            return;
        }
        if (currentShape != null) {
            points.addAll(currentShape.getPoints());
        }
        currentShape = new Shape(num + 1);
    }

    /**
     * Tarkistaa, onko tarkasteltavan pisteen alapuolella jo jokin toinen piste.
     *
     * @return totuusarvo siitä, onko tarkasteltavan pisteen alapuolella jo
     * jokin toinen piste.
     */
    public boolean hasPointsDown() {
        for (Point point : currentShape.getPoints()) {
            if (points.contains(new Point(point.getX(), point.getY() + 1))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, onko tarkasteltavan pisteen oikealla puolella jo jokin toinen
     * piste.
     *
     * @return totuusarvo siitä, onko tarkasteltavan pisteen oikealla puolella
     * jo jokin toinen piste.
     */
    public boolean hasPointsRight() {
        for (Point point : currentShape.getPoints()) {
            if (points.contains(new Point(point.getX() + 1, point.getY()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, onko tarkasteltavan pisteen vasemmalla puolella jo jokin
     * toinen piste.
     *
     * @return totuusarvo siitä, onko tarkasteltavan pisteen vasemmalla puolella
     * jo jokin toinen piste.
     */
    public boolean hasPointsLeft() {
        for (Point point : currentShape.getPoints()) {
            if (points.contains(new Point(point.getX() - 1, point.getY()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, onko tarkasteltava piste lähellä pelialueen yläreunaa.
     *
     * @return totuusarvo siitä, onko tarkasteltava piste pelialueen ylimmällä
     * rivillä.
     */
    public boolean closeToTopBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getY() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, onko tarkasteltava piste lähellä pelialueen vasenta reunaa.
     *
     * @return totuusarvo siitä, onko tarkasteltavan pisteen vasemmalla puolella
     * pelialueen vasen reuna.
     */
    public boolean closeToLeftBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getX() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, onko tarkasteltava piste lähellä pelialueen oikeaa reunaa.
     *
     * @return totuusarvo siitä, onko tarkasteltavan pisteen oikealla puolella
     * pelialueen oikea reuna.
     */
    private boolean closeToRightBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getX() == WIDTH - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, onko jokin tarkasteltavan palikan piste lähellä pelialueen
     * alareunaa.
     *
     * @return totuusarvo siitä, onko tarkasteltavan pisteen alapuolella
     * pelialueen alareuna.
     */
    public boolean closeToBottomBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getY() == HEIGHT - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, voiko pelattavaa palikkaa kääntää.
     *
     * @return totuusarvo siitä, mahtuuko pelattava palikka kääntymään
     * nykyisessä sijainnissaan pelialueen raunojen tai pelialueelta ennestään
     * varattujen ruutujen sitä estämättä.
     */
    public boolean canRotate() {
        List<Point> rotated = currentShape.getRotatePoints();
        for (Point point : rotated) {
            if (point.getX() >= WIDTH || point.getY() >= HEIGHT || point.getX() < 0
                    || points.contains(point)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kutsuu palikkaa kääntävää metodia, jos pyörittäminen on mahdollista.
     */
    public void rotate() {
        if (canRotate()) {
            currentShape.rotate();
        }
    }

    /**
     * Kutsuu palikkaa vasemmalle liikuttavaa metodia, jos vasemmalla puolella
     * ei ole ennestään varattuja ruutuja eikä vasen reuna ole liian lähellä.
     */
    public void moveLeft() {
        if (!hasPointsLeft() && !closeToLeftBorder()) {
            currentShape.moveLeft();
        }
    }

    /**
     * Kutsuu palikkaa oikealle liikuttavaa metodia, jos oikealla puolella ei
     * ole ennestään varattuja ruutuja eikä oikea reuna ole liian lähellä.
     */
    public void moveRight() {
        if (!hasPointsRight() && !closeToRightBorder()) {
            currentShape.moveRight();
        }
    }

    /**
     * Kutsuu palikkaa alaspäin liikuttavaa metodia, jos alapuolella ei ole
     * ennestään varattuja ruutuja eikä alareuna ole liian lähellä. Muussa
     * tapauksessa metodi tarkistaa, onko pelialueen ylimmällä rivillä
     * palikoita. Jos näin on, ilmoittaa metodi pelin päättyneen, muussa
     * tapauksessa luodaan uusi palikka ja kutsutaan täydet rivit poistavaa
     * metodia.
     */
    public void moveDown() {
        if (!hasPointsDown() && !closeToBottomBorder()) {
            currentShape.moveDown();
        } else {
            if (closeToTopBorder()) {
                gameOver = true;
            } else {
                createCurrentShape();
                removeLines();
            }
        }
    }

    /**
     * Kutsuu muuttujat alustavaa metodia ja sen jälkeen metodeja, jotka
     * laskevat täysien rivien määrän ja poistavat ne sekä lisäävät niistä
     * tulevat pisteet pelaajan pisteisiin. Toistaa tätä niin kauan kuin
     * putoaminen jatkuu. Lopuksi tarkistaa pelaajan tason ja päivittää
     * palikoiden putoamisnopeuden tasoa vastaavaksi.
     */
    public void removeLines() {
        do {
            removeLinesSetup();
            if (allPoints.size() != 0) {
                countFullLines();
            }
            if (fullLines.size() != 0) {
                fullinesSizeNotZero();
            }
        } while (gravityTriggered);
        level = numClearedLines / 10;
        updateSpeed();
    }

    /**
     * Laskee nykyisellä palikalla saatavien pisteiden määrän tuhottujen rivien
     * määrän perusteella.
     *
     * @param num kokonaisluku, tuhottujen rivien määrä
     *
     * @return kokonaisluku, nykyisellä palikalla tuhotuista riveistä tulevat
     * pisteet
     */
    public int calculateCurrentScore(int num) {
        int base = 40;
        if (num == 2) {
            base = 100;
        } else if (num == 3) {
            base = 300;
        } else if (num == 4) {
            base = 1000;
        }
        return base * (level + 1);
    }

    /**
     * Luo listan kaikista pelialueella olevista pisteistä, myös parhaillaan
     * pelattavana olevasta palikasta, siirtää pisteet HashSetiin varmistaen
     * että jokainen piste esiintyy pelialueella vain kerran ja palauttaa sitten
     * kaikki pisteet takaisin listalle.
     *
     * @return lista kaikista pelialueella olevista pisteistä
     */
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<Point>();
        points.addAll(this.points);
        points.addAll(currentShape.getPoints());
        Set<Point> set = new HashSet<Point>();
        set.addAll(points);
        points.clear();
        points.addAll(set);
        return points;
    }

    /**
     * Päivittää palikoiden putoamisnopeuden saavutetun tason mukaan.
     */
    public void updateSpeed() {
        double baseFrame = 48.0;
        if (-1 < level && level < 9) {
            timePerBlock = (int) (((baseFrame - (level * 5.0)) / 60.0) * 1000.0);
        } else if (level == 9) {
            timePerBlock = (int) ((6.0 / 60.0) * 1000.0);
        } else if (9 < level && level < 19) {
            timePerBlock = (int) (((8.0 - (level - 1.0) / 3.0) / 60.0) * 1000.0);
        } else if (18 < level && level < 29) {
            timePerBlock = (int) ((2.0 / 60.0) * 1000.0);
        } else {
            timePerBlock = (int) ((1.0 / 60.0) * 1000.0);
        }
    }

    public int getNumClearedLines() {
        return numClearedLines;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public int getLevel() {
        return level;
    }

    public int getTimePerBlock() {
        return timePerBlock;
    }

    public int getScore() {
        return score;
    }

    /**
     * Muodostaa ensin kokonaislukutaulukon pelialueesta pisteiden tyyppien
     * mukaan numeroituna. Muodostaa sitten tästä kokonaislukutaulukosta
     * merkkijonon.
     *
     * @return pelialueen pisteet merkkijonona tyypin mukaisesti numeroituna
     */
    @Override
    public String toString() {
        int[][] board = new int[HEIGHT][WIDTH];
        for (Point point : points) {
            board[point.getY()][point.getX()] = point.getType();
        }
        for (Point point : currentShape.getPoints()) {
            board[point.getY()][point.getX()] = currentShape.getType();
        }
        String str = "";
        for (int[] is : board) {
            str += Arrays.toString(is) + "\n";
        }
        return str;
    }

    /**
     * Tarkistaa parametrina saadun rivltä, onko palikan ruutujen tiellä
     * entuudestaan varattuja ruutuja. Mikäli alapuolella on vielä tyhjää,
     * jatkuu palikan putoaminen.
     *
     * @param i kokonaisluku, tarkasteltavan rivin numero
     *
     * @return totuusarvo, jatkuuko palikan putoaminen
     */
    public boolean continueMoveDown(int i) {
        for (int j = 0; j < points.size(); j++) {
            if (points.get(j).getX() == i && points.get(j).getY() <= mostBottomLine) {
                points.get(j).modY(numOfEmpty);
                gravityTriggered = true;
            }
        }
        return gravityTriggered;
    }

    /**
     * Tarkistaa, onko jokin pelialueen riveistä täysi ja lisää niiden
     * lukumäärän täysien rivien laskuriin.
     */
    public void countFullLines() {
        for (int i = 0; i < HEIGHT; i++) {
            boolean full = true;
            row:
            for (int j = 0; j < WIDTH; j++) {
                if (!this.allPoints.contains(new Point(j, i))) {
                    full = false;
                    break row;
                }
            }
            if (full) {
                fullLines.add(i);
            }
        }
    }

    /**
     * Alustaa muuttujat täydet rivit poistavaa ja niistä saatavat pisteet
     * laskevaa metodia varten.
     */
    public void removeLinesSetup() {
        gravityTriggered = false;
        fullLines = new ArrayList<Integer>(HEIGHT);
        allPoints = getPoints();
        mostBottomLine = 0;
        numOfEmpty = 0;
    }

    /**
     * Päivittää tuhottujen rivien määrän ja pisteet sekä tiedon alimmasta
     * rivistä. Poistaa täydet rivit pelialueelta
     */
    public void fullinesSizeNotZero() {
        numClearedLines += fullLines.size();
        score += calculateCurrentScore(fullLines.size());
        for (int i : fullLines) {
            if (i > mostBottomLine) {
                mostBottomLine = i;
            }
            Predicate<Point> pointsPredicate = p -> p.getY() == i;
            points.removeIf(pointsPredicate);
            for (int j = 0; j < points.size(); j++) {
                if (points.get(j).getY() < i) {
                    points.get(j).modY(1);
                }
            }
        }
        if (mostBottomLine != HEIGHT - 1) {
            mostBottomLineBoardNotEmpty();
        }
    }

    /**
     * Laskee alapuolella olevan vapaan tilan määrän ja tarkistaa mahtuuko
     * palikka vielä putoamaan alemmas.
     */
    public void mostBottomLineBoardNotEmpty() {
        allPoints = getPoints();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = mostBottomLine + 1; j < HEIGHT; j++) {
                if (!allPoints.contains((new Point(i, j)))) {
                    numOfEmpty++;
                } else {
                    break;
                }
            }
            if (numOfEmpty != 0) {
                gravityTriggered = false;
                gravityTriggered = continueMoveDown(i);
            }
        }
    }
}
