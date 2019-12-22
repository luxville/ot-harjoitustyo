package tetris.ui;

import tetris.domain.Board;
import tetris.domain.HighScore;
import tetris.domain.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

public class TetrisGame extends Application {

    private Arc halfCircle;
    private Board board;
    private boolean firstTry;
    private boolean gameOver;
    private boolean running;
    private BorderPane borderPane;
    private BorderPane pauseCenter;
    Button continueButton;
    Button newGameButton;
    Button instructionsButton;
    Button hiscoresButton;
    Button exitGameButton;
    private double shadeThick;
    private GridPane tetrisGrid;
    private Group cellGroup;
    private HBox root;
    private int shapeSpeed;
    private Label gameOverSub;
    private Label gameOverTitle;
    private Label level;
    private Label line;
    private Label score;
    private Label spacePause;
    private Label subLevel;
    private Label subLine;
    private Label subScore;
    private List<Point> points;
    private Map<Integer, Color> colors;
    private PauseTransition pauseTransition;
    private Polygon bottomShade;
    private Point currentPoint;
    private Polygon topShade;
    private Rectangle boardShade;
    private Rectangle square;
    private Rectangle topRec;
    private Scene scene;
    private SequentialTransition shapeTransition;
    private StackPane stackPane;
    private Text instructions;
    private VBox hiscoresBox;
    private VBox instructionsBox;
    private VBox menuBox;
    private VBox vBoxBottom;
    private VBox vBoxGameOver;
    private VBox vBoxTop;

    private final static int PIXEL = 30;

    public void paint() {
        if (!board.getGameOver()) {
            if (shapeSpeed != board.getTimePerBlock()) {
                shapeTransition.stop();
                createTransition();
            }
            score.setText(String.valueOf(board.getScore()));
            line.setText(String.valueOf(board.getNumClearedLines()));
            level.setText(String.valueOf(board.getLevel()));
            points = board.getPoints();
            tetrisGrid.getChildren().clear();
            for (int i = 0; i < Board.HEIGHT; i++) {
                for (int j = 0; j < Board.WIDTH; j++) {
                    currentPoint = new Point(j, i);
                    cellGroup = new Group();
                    square = new Rectangle(PIXEL, PIXEL);
                    topShade = new Polygon();
                    bottomShade = new Polygon();
                    shadeThick = (double) PIXEL / 7.5;
                    if (points.contains(currentPoint)) {
                        setShades(true);
                        square.setFill(colors.get(points.get(points.indexOf(currentPoint)).getType()));
                        cellGroup.getChildren().addAll(square, topShade, bottomShade);
                    } else {
                        setShades(false);
                        topRec = new Rectangle(PIXEL, PIXEL / 2.65);
                        topRec.setOpacity(0.05);
                        topRec.setFill(Color.WHITE);
                        halfCircle = setHalfCircle();
                        square.setFill(Color.GRAY);
                        square.setOpacity((55.0 / 62.0 - ((double) i + 30.0) / ((double) Board.HEIGHT + 50.0)));
                        cellGroup.getChildren().addAll(square, topShade, bottomShade, halfCircle, topRec);
                    }
                    tetrisGrid.add(cellGroup, j, i);
                }
            }
        } else {
            gameOver = true;
            running = false;
            setSceneDisable(true);
            shapeTransition.stop();
            gameOverTitle = new Label("PELI LOPPUI");
            gameOverTitle.setTextFill(Color.WHITE);
            gameOverTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15.0));
            gameOverTitle.setTextAlignment(TextAlignment.CENTER);
            gameOverSub = new Label("Pisteesi ovat " + board.getScore() + ",\n"
                    + "saavutit tason " + board.getLevel() + "\n"
                    + "tuhoamalla " + board.getNumClearedLines() + " riviä!");
            gameOverSub.setTextFill(Color.WHITE);
            gameOverSub.setFont(Font.font("Segoe UI Semilight", 13.0));
            gameOverSub.setTextAlignment(TextAlignment.CENTER);
            vBoxGameOver = new VBox();
            Button backToMenu = backToMenuButton(vBoxGameOver);
            vBoxGameOver.getChildren().addAll(gameOverTitle, gameOverSub, backToMenu);
            vBoxGameOver.setAlignment(Pos.CENTER);
            stackPane.getChildren().addAll(boardShade, vBoxGameOver);
            updateHighScores();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        startGameSetup();
        buttonsSetup();
        menuBox = new VBox();
        menuBox.setSpacing(10);
        menuBox.getChildren().addAll(continueButton, newGameButton,
                hiscoresButton, instructionsButton, exitGameButton);
        pauseCenter.setCenter(menuBox);
        boardShade = new Rectangle(Board.WIDTH * PIXEL + 2 * (Board.WIDTH - 1),
                Board.HEIGHT * PIXEL + 2 * (Board.HEIGHT - 1));
        boardShade.setOpacity(0.5);
        boardShade.setFill(Color.BLACK);
        boardShade.toFront();
        scene = new Scene(root);
        setControls();
        firstTry = true;
        startNewGame();
        primaryStage.setScene(scene);
        primaryStage.setTitle("TETRIS");
        primaryStage.show();
    }

    public void setControls() {
        scene.setOnKeyPressed((key) -> {
            if (running) {
                if (key.getCode().equals(KeyCode.LEFT) || key.getCode().equals(KeyCode.A)) {
                    board.moveLeft();
                } else if (key.getCode().equals(KeyCode.RIGHT) || key.getCode().equals(KeyCode.D)) {
                    board.moveRight();
                } else if (key.getCode().equals(KeyCode.UP) || key.getCode().equals(KeyCode.W)) {
                    board.rotate();
                } else if (key.getCode().equals(KeyCode.DOWN) || key.getCode().equals(KeyCode.S)) {
                    board.moveDown();
                }
                paint();
            }
            if (key.getCode().equals(KeyCode.SPACE)) {
                pauseGame();
            }
        });
    }

    public void createTransition() {
        shapeSpeed = board.getTimePerBlock();
        shapeTransition = new SequentialTransition();
        pauseTransition = new PauseTransition(Duration.millis(shapeSpeed));
        pauseTransition.setOnFinished(event -> {
            board.moveDown();
            paint();
        });
        shapeTransition.getChildren().add(pauseTransition);
        shapeTransition.setCycleCount(Timeline.INDEFINITE);
        shapeTransition.play();
    }

    private void setSceneDisable(boolean b) {
        level.setDisable(b);
        score.setDisable(b);
        line.setDisable(b);
        subLevel.setDisable(b);
        subScore.setDisable(b);
        subLine.setDisable(b);
        spacePause.setDisable(b);
    }

    public void startNewGame() {
        board = new Board();
        running = true;
        gameOver = false;
        shapeSpeed = board.getTimePerBlock();
        paint();
        createTransition();
        if (firstTry) {
            pauseGame();
        }
        firstTry = false;
    }

    public void pauseGame() {
        if (gameOver) {
            shapeTransition.stop();
            stackPane.getChildren().remove(vBoxGameOver);
            stackPane.getChildren().add(pauseCenter);
        }
        if (running) {
            running = false;
            shapeTransition.stop();
            stackPane.getChildren().addAll(boardShade, pauseCenter);
        } else {
            running = true;
            shapeTransition.play();
            stackPane.getChildren().removeAll(boardShade, pauseCenter);
        }
        setSceneDisable(!running);
    }

    private VBox instructions() {

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #f5f5f5");
        final String INSTRUCTIONS
                = "TETRIS\n\n"
                + "Pelissä on tavoitteena saada ylhäältä \n"
                + "putoavat palikat järjesteltyä siten, että\n"
                + "ne täyttävät rivin kokonaan. Kun rivi\n"
                + "täyttyy, se poistetaan ja sen yläpuolella\n"
                + "olevat rivit putoavat alaspäin. Pelissä\n"
                + "on käytössä vetovoima, jonka vaikutuksesta\n"
                + "välittömästi poistetun rivin yläpuolella olevat\n"
                + "ruudut putoavat niin alas kuin niillä on\n"
                + "tilaa pudota. Pelissä saa pisteitä täyteen\n"
                + "saaduista riveistä. Korkeammalla tasolla\n"
                + "ja enemmän rivejä kerralla täyttämällä\n"
                + "pisteitä saa nopeammin. Peli päättyy, kun\n"
                + "pelattava palikka ei mahdu enää putoamaan\n"
                + "ylimmältä riviltä alaspäin. Aina 10 täyteen\n"
                + "saadun rivin jälkeen siirtyy seuraavalle\n"
                + "tasolle, joka ilmenee palikoiden nopeampana\n"
                + "putoamisena.\n\n"
                + "Ylhäältä putoavia palikoita on mahdollista\n"
                + "siirtää oikealle ja vasemmalle, niitä voi myös\n"
                + "kääntää tai nopeuttaa niiden putoamista. Nämä\n"
                + "toimenpiteet voidaan toteuttaa vain silloin,\n"
                + "kun pelialueen reunat tai jo pelialueella\n"
                + "olevat palikat eivät ole toimenpiteiden tiellä.\n"
                + "Palikan ohjaamiseen käytettävät näppäimet ovat\n"
                + "kirjaimet W, A, S ja D tai nuolinäppäimet.\n"
                + "Kaikki näitä näppäimiä voi käyttää aina\n"
                + "pelattaessa.\n\n "
                + "Nuoli ylös ja W:  Palikka kääntyy\n "
                + "Nuoli vasemmalle ja A: palikka siirtyy vasemmalle\n"
                + "Nuoli oikealle ja D: Palikka siirtyy oikealle\n"
                + "Nuoli alas ja S: Palikka putoaa nopeammin\n"
                + "Välilyönti: Peli keskeytyy ja valikko avautuu\n\n";
        instructions = new Text(INSTRUCTIONS);
        Button backToMenu = backToMenuButton(vBox);
        vBox.getChildren().addAll(instructions, backToMenu);
        return vBox;
    }

    private VBox hiscores() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #f5f5f5");
        HighScore[] highScores = HighScore.getHighScores();
        String top10 = "";
        top10 = HighScore.hiscoreHeaderToString();
        for (int i = 0; i < highScores.length; i++) {
            top10 += HighScore.rightPad(String.valueOf(i + 1) + ".", 4) + highScores[i].toString();
        }
        top10 += HighScore.hiscoreAttention();
        Text text = new Text();
        text.setText(top10);
        text.setFont(Font.font("monospace", 12));
        Button backToMenu = backToMenuButton(vBox);
        vBox.getChildren().addAll(text, backToMenu);
        return vBox;
    }

    private void updateHighScores() {
        int score = board.getScore();
        int level = board.getLevel();
        int lines = board.getNumClearedLines();
        if (score > HighScore.getHighScores()[9].getScore()) {
            String name = JOptionPane.showInputDialog(null, "Pääsit 10 parhaan joukkoon!\n"
                    + "Syötä nimesi.\nHuomio! Vain 10 ensimmäistä merkkiä tulee listalle.",
                    "Tetris", JOptionPane.INFORMATION_MESSAGE);
            if (name != null) {
                HighScore.addHighScore(new HighScore(level, lines, score,
                        (name.length() > 10) ? name.substring(0, 10) : name));
            }
        }
    }

    private Arc setHalfCircle() {
        halfCircle = new Arc((double) PIXEL / 2.0, (double) PIXEL / 2.0,
                (double) PIXEL / 2.0, (double) PIXEL / 8.0, 0.0f, 180.0f);
        halfCircle.setOpacity(0.05);
        halfCircle.setFill(Color.WHITE);
        halfCircle.setType(ArcType.ROUND);
        halfCircle.setRotate(180.0);
        return halfCircle;
    }

    private void setShades(boolean b) {
        if (b) {
            topShade.setOpacity(0.5);
            topShade.setFill(Color.WHITE);
            bottomShade.setOpacity(0.5);
            bottomShade.setFill(Color.BLACK);
        } else {
            topShade.setOpacity(0.1);
            topShade.setFill(Color.BLACK);
            bottomShade.setOpacity(0.25);
            bottomShade.setFill(Color.BLACK);
        }
        topShade.getPoints().addAll(new Double[]{
            0.0, 0.0, (double) PIXEL, 0.0, (double) PIXEL - shadeThick,
            shadeThick, shadeThick, shadeThick, shadeThick,
            (double) PIXEL - shadeThick, 0.0, (double) PIXEL
        });
        bottomShade.getPoints().addAll(new Double[]{
            0.0, (double) PIXEL, (double) PIXEL, (double) PIXEL,
            (double) PIXEL, 0.0, (double) PIXEL - shadeThick,
            shadeThick, (double) PIXEL - shadeThick,
            (double) PIXEL - shadeThick, shadeThick,
            (double) PIXEL - shadeThick
        });
    }

    private Button backToMenuButton(VBox vBox) {
        Button backToMenu = new Button("Takaisin valikkoon");
        backToMenu.setOnAction((event) -> {
            stackPane.getChildren().remove(vBox);
            stackPane.getChildren().add(pauseCenter);
        });
        return backToMenu;
    }

    private void startGameSetup() {
        colors = new HashMap();
        colors.put(1, Color.ORANGE);
        colors.put(2, Color.CYAN);
        colors.put(3, Color.PURPLE);
        colors.put(4, Color.GREEN);
        colors.put(5, Color.RED);
        colors.put(6, Color.BLUE);
        colors.put(7, Color.YELLOW);
        tetrisGrid = new GridPane();
        for (int i = 0; i < Board.WIDTH; i++) {
            tetrisGrid.getColumnConstraints().add(new ColumnConstraints(PIXEL));
        }
        for (int i = 0; i < Board.HEIGHT; i++) {
            tetrisGrid.getRowConstraints().add(new RowConstraints(PIXEL));
        }
        sidePanelSetup();
        borderPane = new BorderPane();
        borderPane.setTop(vBoxTop);
        borderPane.setBottom(vBoxBottom);
        stackPane = new StackPane();
        stackPane.getChildren().add(tetrisGrid);
        root = new HBox();
        root.setPadding(new Insets(5.0));
        root.setSpacing(25.0);
        root.getChildren().addAll(borderPane, stackPane);
        pauseCenter = new BorderPane();
        pauseCenter.setPadding(new Insets(10, 20, 10, 20));
    }

    private void buttonsSetup() {
        continueButton = new Button("Jatka peliä");
        newGameButton = new Button("Uusi peli");
        instructionsButton = new Button("Ohjeet");
        hiscoresButton = new Button("Parhaat tulokset");
        exitGameButton = new Button("Lopeta Tetris");
        continueButton.setOnAction((event) -> {
            if (!gameOver) {
                running = true;
                shapeTransition.play();
                stackPane.getChildren().removeAll(boardShade, pauseCenter);
            }
        });
        newGameButton.setOnAction((event) -> {
            startNewGame();
            shapeTransition.play();
            stackPane.getChildren().removeAll(boardShade, pauseCenter);
        });
        hiscoresBox = hiscores();
        hiscoresButton.setOnAction((event) -> {
            stackPane.getChildren().remove(pauseCenter);
            stackPane.getChildren().add(hiscoresBox);
        });
        instructionsBox = instructions();
        instructionsButton.setOnAction((event) -> {
            stackPane.getChildren().remove(pauseCenter);
            stackPane.getChildren().add(instructionsBox);
        });
        exitGameButton.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void sidePanelSetup() {
        vBoxBottom = new VBox();
        score = new Label();
        level = new Label();
        line = new Label();
        subScore = new Label("pisteet");
        subLevel = new Label("taso");
        subLine = new Label("tuhotut rivit");
        vBoxTop = new VBox();
        vBoxTop.getChildren().addAll(subScore, score, subLevel, level, subLine, line);
        spacePause = new Label("keskeytä painamalla välilyöntiä");
        vBoxBottom.getChildren().add(spacePause);
    }
}
