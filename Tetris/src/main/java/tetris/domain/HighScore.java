package tetris.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HighScore implements Serializable {

    private static final long serialVersionUID = 1L;
    private int level;
    private int lines;
    private int score;
    private String name;

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

    public int compareTo(HighScore highScore) {
        return new Integer(this.score).compareTo(highScore.score);
    }

    private static void initializeFile() {
        HighScore[] highScores = {new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "),
            new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "),
            new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " "),
            new HighScore(0, 0, 0, " "), new HighScore(0, 0, 0, " ")};

        try {
            System.out.println("Hi1");
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("HighScores.dat"));
            o.writeObject(highScores);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HighScore[] getHighScores() {
        if (!new File("HighScores.dat").exists()) {
            initializeFile();
        }
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("HighScores.dat"));
            HighScore[] highScores = (HighScore[]) o.readObject();
            return highScores;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addHighScore(HighScore highScore) {
        HighScore[] highScores = getHighScores();
        int length = highScores.length;
        highScores[length - 1] = highScore;
        for (int i = length - 2; i >= 0; i--) {
            if (highScores[i + 1].compareTo(highScores[i]) > 0) {
                HighScore temp = highScores[i];
                highScores[i] = highScores[i + 1];
                highScores[i + 1] = temp;
            }
        }
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("HighScores.dat"));
            o.writeObject(highScores);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        name = RightPad(name, 10);
        String scoreString = RightPad(String.valueOf(score), 8);
        String levelString = RightPad(String.valueOf(level), 5);
        String linesString = RightPad(String.valueOf(lines), 6);

        return name + scoreString + levelString + linesString + "\n";
    }
    
    public static String hiscoreHeaderToString() {
        String hiscoresHeader = "TOP 10\n\n" + RightPad(" ", 4) + RightPad("NIMI", 10) + RightPad("PISTEET", 8)
                + RightPad("TASO", 5) + RightPad("RIVIT", 6) + "\n\n";

        return hiscoresHeader;
    }

    public static String RightPad(String string, int length) {
        if (string.length() > 10) {
            string = string.substring(0, 10);
        }
        return String.format("%" + length + "s", string) + " ";
    }
    
    
}
