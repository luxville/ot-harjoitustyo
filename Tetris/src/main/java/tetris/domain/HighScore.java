package tetris.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Luokka sisältää parhaiden tulosten listan luomiseen ja käsittelyyn
 * tarvittavat metodit.
 */
public class HighScore implements Serializable {

    private static final long serialVersionUID = 1L;
    private int level;
    private int lines;
    private int score;
    private String name;
    private static final String HIGHSCOREFILENAME = "HighScores.dat";

    public HighScore(int level, int lines, int score, String name) {
        this.level = level;
        this.lines = lines;
        this.score = score;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vertailee parametrinaan saamansa tuloksen pisteitä tarkasteltavana olevan
     * tuloksen pisteisiin.
     *
     * @param highScore tulos, johon sisältyvät saavutetut pisteet, taso,
     * tuhotut rivit ja pelaajan antama nimi.
     *
     * @return parametrina saadun ollessa pienempi palauttaa positiivisen, yhtä
     * suuri palauttaa nollan ja suurempi palauttaa negatiivisen arvon
     */
    public int compareTo(HighScore highScore) {
        return new Integer(this.score).compareTo(highScore.score);
    }

    /**
     * Luo parhaiden tulosten listan sisältävän tiedoston sisältämään pelkästään
     * tuloksia 0 sisältäväksi tuloslistaksi.
     *
     * @param filename merkkijono, muodostettavan tuloslistan sisältävän
     * tiedoston nimi
     */
    private static void initializeFile(String filename) {
        HighScore[] highScores = {new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "),
            new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "),
            new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "),
            new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " ")};

        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
            o.writeObject(highScores);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Avaa tiedoston, jossa tuloslista sijaitsee. Mikäli kyseistä tiedostoa ei
     * löydy, kutsutaan tyhjän tuloslistan luovaa metodia. Kun tiedosto on
     * olemassa, haetaan siellä oleva tuloslista.
     *
     * @return tuloslista tuloksista koostuvana taulukkona
     */
    public static HighScore[] getHighScores() {
        if (!new File(HIGHSCOREFILENAME).exists()) {
            initializeFile(HIGHSCOREFILENAME);
        }
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(HIGHSCOREFILENAME));
            HighScore[] highScores = (HighScore[]) o.readObject();
            o.close();
            return highScores;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Hakee parhaat tulokset tiedostosta, vertailee parametrinaan saamaansa
     * tulosta olemassaolevaan tuloslistaan ja sijoittaa tuloksen oikeaan
     * kohtaan tuloslistalla. Tallentaa lopuksi päivitetyn tuloslistan
     * tiedostoon.
     *
     * @param highScore tulos, johon sisältyvät saavutetut pisteet, taso,
     * tuhotut rivit ja pelaajan antama nimi.
     */
    public static void addHighScore(HighScore highScore) {
        HighScore[] highScores = getHighScores();
        highScores[highScores.length - 1] = highScore;
        for (int i = highScores.length - 2; i >= 0; i--) {
            if (highScores[i + 1].compareTo(highScores[i]) > 0) {
                HighScore temp = highScores[i];
                highScores[i] = highScores[i + 1];
                highScores[i + 1] = temp;
            }
        }
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(HIGHSCOREFILENAME));
            o.writeObject(highScores);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Luo tuloksesta tulostettavan version, jossa ovat nimi, pisteet, taso ja
     * tuhotut rivit.
     *
     * @return tulos merkkijonona
     */
    @Override
    public String toString() {
        name = rightPad(name, 10);
        String scoreString = rightPad(String.valueOf(score), 8);
        String levelString = rightPad(String.valueOf(level), 5);
        String linesString = rightPad(String.valueOf(lines), 6);

        return name + scoreString + levelString + linesString + "\n";
    }

    /**
     * Luo otsikon ja seliterivin tuloslistaa varten.
     *
     * @return tulostaulun otsikko ja seliterivi merkkijonona
     */
    public static String hiscoreHeaderToString() {
        String hiscoresHeader = "TOP 10\n\n" + rightPad(" ", 4) + rightPad("NIMI", 10) + rightPad("PISTEET", 8)
                + rightPad("TASO", 5) + rightPad("RIVIT", 6) + "\n\n";

        return hiscoresHeader;
    }

    /**
     * Luo merkkijonosta oikeaan reunaan tasoittuvan korkeintaan 10 merkkiä
     * pitkän merkkijonon tehden merkkijonosta halutun pituisen.
     *
     * @param string merkkijono, jota muokataan
     *
     * @param length haluttu merkkijonon pituus
     *
     * @return halutunmittaiseksi muokattu merkkijono
     */
    public static String rightPad(String string, int length) {
        if (string.length() > 10) {
            string = string.substring(0, 10);
        }
        return String.format("%" + length + "s", string) + " ";
    }

}
