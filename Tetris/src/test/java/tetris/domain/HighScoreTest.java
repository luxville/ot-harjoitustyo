package tetris.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

public class HighScoreTest {

    HighScore highScore;
    HighScore[] highScores;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

//    @Rule
//    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws IOException {
        highScore = new HighScore(3, 13, 1000, "testplayer");
        highScores = new HighScore[10];
        for (int i = 0; i < highScores.length; i++) {
            highScores[i] = new HighScore(0, 0, 0, " ");
        }
    }

    @Test
    public void isHighScore() {
        assertEquals("testplayer", highScore.getName());
        assertEquals(3, highScore.getLevel());
        assertEquals(13, highScore.getLines());
        assertEquals(1000, highScore.getScore());
    }

    @Test
    public void testHighScoresDatFile() throws IOException {
        Path path2 = null;
        File copy = null;
        if (new File("HighScores.dat").exists()) {
            Path path = Paths.get("HighScores.dat");
            File original = path.toFile();
            Files.move(path, path.resolveSibling("HighScores3.dat"));
            path2 = Paths.get("HighScores3.dat");
            copy = path2.toFile();
            original.delete();
        }
        HighScore[] testList = HighScore.getHighScores();
        for (int i = 0; i < testList.length; i++) {
            assertEquals(highScores[i].toString(), testList[i].toString());
        }
        if (copy != null) {
            Files.move(path2, path2.resolveSibling("HighScores.dat"), REPLACE_EXISTING);
            copy.delete();
        }
    }

    @Test
    public void addHighScore() throws IOException {
        Path path2 = null;
        File copy = null;
        if (new File("HighScores.dat").exists()) {
            Path path = Paths.get("HighScores.dat");
            File original = path.toFile();
            Files.move(path, path.resolveSibling("HighScores3.dat"));
            path2 = Paths.get("HighScores3.dat");
            copy = path2.toFile();
            original.delete();
        }
        HighScore.addHighScore(highScore);
        HighScore[] testList = HighScore.getHighScores();
        assertEquals(highScore.toString(), testList[0].toString());
        if (copy != null) {
            Files.move(path2, path2.resolveSibling("HighScores.dat"), REPLACE_EXISTING);
            copy.delete();
        }
    }

    @Test
    public void hiscoreHeader() {
        assertEquals("TOP 10\n\n           NIMI  PISTEET  TASO  RIVIT \n\n",
                HighScore.hiscoreHeaderToString());
    }

    @Test
    public void rightPadTest() {
        String test1 = "12345678901234567890";
        String test2 = "123456";
        assertEquals("1234567890 ", HighScore.rightPad(test1, 10));
        assertEquals("    123456 ", HighScore.rightPad(test2, 10));
    }

    @Test
    public void toStringTest() {
        assertEquals("testplayer     1000     3     13 \n", highScore.toString());
    }
}
