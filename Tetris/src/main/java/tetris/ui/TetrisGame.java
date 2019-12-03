package tetris.ui;

import tetris.domain.Board;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TetrisGame extends Application {

    private Arc halfCircle;
    private Board board;
    private boolean firstTry = true;
    private boolean gameOver;
    private boolean running;
    private BorderPane borderPane;
    private BorderPane gameOverCenter;
    private BorderPane pauseCenter;
    private double shadeThick;
    private GridPane tetrisGrid;
    private Group cellGroup;
    private HBox root;
    private int colorChoice;
    private int shapeSpeed;
    private Label gameOverSub;
    private Label gameOverTitle;
    private Label hiscoreLabel;
    private Label instructions;
    private Label level;
    private Label line;
    private Label score;
    private Label spacePause;
    private Label subLevel;
    private Label subLine;
    private Label subScore;
    private List<Point> points;
    private Map<Integer, Map<Integer, Color>> colors;
    private Map<Integer, Color> color1;
    private Map<Integer, Color> color2;
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
    private Stage stage;
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
                        topShade.setOpacity(0.5);
                        topShade.setFill(Color.WHITE);
                        topShade.getPoints().addAll(new Double[]{
                            0.0, 0.0, (double) PIXEL, 0.0, (double) PIXEL - shadeThick,
                            shadeThick, shadeThick, shadeThick, shadeThick,
                            (double) PIXEL - shadeThick, 0.0, (double) PIXEL
                        });

                        bottomShade.setOpacity(0.5);
                        bottomShade.setFill(Color.BLACK);
                        bottomShade.getPoints().addAll(new Double[]{
                            0.0, (double) PIXEL, (double) PIXEL, (double) PIXEL,
                            (double) PIXEL, 0.0, (double) PIXEL - shadeThick,
                            shadeThick, (double) PIXEL - shadeThick,
                            (double) PIXEL - shadeThick, shadeThick,
                            (double) PIXEL - shadeThick
                        });

                        square.setFill(colors.get(colorChoice).get(points.get(points.indexOf(currentPoint)).getType()));
                        cellGroup.getChildren().addAll(square, topShade, bottomShade);
                    } else {
                        topShade.setOpacity(0.1);
                        topShade.setFill(Color.BLACK);
                        topShade.getPoints().addAll(new Double[]{
                            0.0, 0.0, (double) PIXEL, 0.0,
                            (double) PIXEL - shadeThick, shadeThick, shadeThick,
                            shadeThick, shadeThick, (double) PIXEL - shadeThick,
                            0.0, (double) PIXEL
                        });

                        bottomShade.setOpacity(0.25);
                        bottomShade.setFill(Color.BLACK);
                        bottomShade.getPoints().addAll(new Double[]{
                            0.0, (double) PIXEL, (double) PIXEL, (double) PIXEL,
                            (double) PIXEL, 0.0, (double) PIXEL - shadeThick,
                            shadeThick, (double) PIXEL - shadeThick,
                            (double) PIXEL - shadeThick, shadeThick,
                            (double) PIXEL - shadeThick
                        });

                        topRec = new Rectangle(PIXEL, PIXEL / 2.65);
                        topRec.setOpacity(0.05);
                        topRec.setFill(Color.WHITE);

                        halfCircle = new Arc((double) PIXEL / 2.0, (double) PIXEL / 2.0,
                                (double) PIXEL / 2.0, (double) PIXEL / 8.0, 0.0f, 180.0f);
                        halfCircle.setOpacity(0.05);
                        halfCircle.setFill(Color.WHITE);
                        halfCircle.setType(ArcType.ROUND);
                        halfCircle.setRotate(180.0);

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

            gameOverSub = new Label("Pisteesi ovat " + board.getScore() + "\n "
                    + "ja saavutit tason " + board.getLevel() + "!");
            gameOverSub.setTextFill(Color.WHITE);
            gameOverSub.setFont(Font.font("Segoe UI Semilight", 13.0));
            gameOverSub.setTextAlignment(TextAlignment.CENTER);

            Button backToMenu = new Button("Takaisin valikkoon");
            backToMenu.setOnAction((event) -> {
                stackPane.getChildren().remove(gameOverCenter);
                stackPane.getChildren().add(pauseCenter);
            });            

            vBoxGameOver = new VBox();
            vBoxGameOver.getChildren().addAll(gameOverTitle, gameOverSub, backToMenu);
            vBoxGameOver.setAlignment(Pos.CENTER);

            gameOverCenter = new BorderPane();
            gameOverCenter.setCenter(vBoxGameOver);

            stackPane.getChildren().addAll(boardShade, gameOverCenter);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        colorChoice = 0;
        colors = new HashMap<Integer, Map<Integer, Color>>();

        color1 = new HashMap<Integer, Color>();
        color1.put(1, Color.ORANGE);
        color1.put(2, Color.CYAN);
        color1.put(3, Color.PURPLE);
        color1.put(4, Color.GREEN);
        color1.put(5, Color.RED);
        color1.put(6, Color.BLUE);
        color1.put(7, Color.YELLOW);
        colors.put(0, color1);

        color2 = new HashMap<Integer, Color>();
        color2.put(1, Color.rgb(248, 121, 41));
        color2.put(2, Color.rgb(11, 165, 223));
        color2.put(3, Color.rgb(192, 58, 180));
        color2.put(4, Color.rgb(135, 212, 47));
        color2.put(5, Color.rgb(215, 23, 53));
        color2.put(6, Color.rgb(44, 87, 220));
        color2.put(7, Color.rgb(251, 187, 49));
        colors.put(1, color2);

        tetrisGrid = new GridPane();
        //tetrisGrid.getStyleClass().add("grid");
        //tetrisGrid.getStyleClass().add("background");

        for (int i = 0; i < Board.WIDTH; i++) {
            tetrisGrid.getColumnConstraints().add(new ColumnConstraints(PIXEL));
        }

        for (int i = 0; i < Board.HEIGHT; i++) {
            tetrisGrid.getRowConstraints().add(new RowConstraints(PIXEL));
        }

        vBoxBottom = new VBox();
        //vBoxBottom.getStyleClass().add("background");

        score = new Label();
        level = new Label();
        line = new Label();

        //score.getStyleClass().add("score");
        //level.getStyleClass().add("score");
        //line.getStyleClass().add("score");

        subScore = new Label("pisteet");
        subLevel = new Label("taso");
        subLine = new Label("tuhotut rivit");

        //subScore.getStyleClass().add("subScore");
        //subLevel.getStyleClass().add("subScore");
        //subLine.getStyleClass().add("subScore");

        vBoxTop = new VBox();
        //vBoxTop.getStyleClass().add("background");
        vBoxTop.getChildren().addAll(score, subScore, level, subLevel, line, subLine);

        spacePause = new Label("keskeytä painamalla välilyöntiä");
        //spacePause.getStyleClass().add("pause");

        vBoxBottom.getChildren().add(spacePause);

        borderPane = new BorderPane();
        //borderPane.getStyleClass().add("background");
        borderPane.setTop(vBoxTop);
        borderPane.setBottom(vBoxBottom);

        stackPane = new StackPane();
        stackPane.getChildren().add(tetrisGrid);

        root = new HBox();
        //root.getStyleClass().add("background");
        root.setPadding(new Insets(5.0));
        root.setSpacing(25.0);
        root.getChildren().addAll(borderPane, stackPane);

        pauseCenter = new BorderPane();
        pauseCenter.setPadding(new Insets(10, 20, 10, 20));

        Button continueButton = new Button("Jatka peliä");
        Button newGameButton = new Button("Uusi peli");
        Button instructionsButton = new Button("Ohjeet");
        Button hiscoresButton = new Button("Parhaat tulokset");
        Button exitGameButton = new Button("Lopeta Tetris");

        continueButton.setOnAction((event) -> {
            if (gameOver) {
                pauseGame();
            }
            running = true;
            shapeTransition.play();
            stackPane.getChildren().removeAll(boardShade, pauseCenter);
        });

        newGameButton.setOnAction((event) -> {
            startNewGame();
            shapeTransition.play();
            stackPane.getChildren().removeAll(boardShade, pauseCenter);
        });

        hiscoresBox = hiscores();

        hiscoresButton.setOnAction((event) -> {
            stackPane.getChildren().add(hiscoresBox);
        });

        instructionsBox = instructions();

        instructionsButton.setOnAction((event) -> {
            stackPane.getChildren().add(instructionsBox);
        });

        exitGameButton.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });

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
        //scene.getStylesheets().add("file:resurssit/application.css");
        scene.setOnKeyPressed((key) -> {
            if (running) {
                if (key.getCode().equals(KeyCode.LEFT) || key.getCode().equals(KeyCode.A)) {
                    board.moveLeft();
                    paint();
                } else if (key.getCode().equals(KeyCode.RIGHT) || key.getCode().equals(KeyCode.D)) {
                    board.moveRight();
                    paint();
                } else if (key.getCode().equals(KeyCode.UP) || key.getCode().equals(KeyCode.W)) {
                    board.rotate();
                    paint();
                } else if (key.getCode().equals(KeyCode.DOWN) || key.getCode().equals(KeyCode.S)) {
                    board.moveDown();
                    paint();
                }
            }
            if (key.getCode().equals(KeyCode.SPACE)) {
                pauseGame();
            }
        });

        startNewGame();

        primaryStage.setScene(scene);
        primaryStage.setTitle("TETRIS");
        primaryStage.show();
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
            startNewGame();
            stackPane.getChildren().removeAll(boardShade, gameOverCenter);
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
                + "Pelissä on tavoitteena saada ylhäältä putoavat\n "
                + "neljästä ruudusta muodostuvat palikat \n"
                + "järjesteltyä siten, että ruudut täyttävät rivit kokonaan.\n"
                + "Kun rivi tulee täyteen, se poistetaan ja sen\n"
                + "yläpuolella olevat rivit putoavat alaspäin.\n"
                + "Pelissä on käytössä vetovoima, jonka\n"
                + "vaikutuksesta välittömästi poistetun rivin yläpuolella\n"
                + "olevat ruudut putoavat niin alas kuin niillä on tilaa\n"
                + "pudota. Pelissä saa pisteitä täyteen saaduista riveistä,\n"
                + " lisäksi pisteitä saa sitä enemmän, mitä enemmän rivejä\n"
                + "onnistuu saamaan kerralla täyteen. Peli päättyy, kun\n"
                + "palikat eivät mahdu enää tippumaan ylimmältä riviltä\n"
                + "alaspäin. Aina kymmenen täyteen saadun rivin jälkeen\n"
                + "pelaaja pääsee seuraavalle tasolle, joka ilmenee\n"
                + "palikoiden putoamisen nopeutumisena.\n\n"
                + "Ylhäältä putoavia palikoita on mahdollista siirtää\n"
                + "oikealle ja vasemmalle, niitä on mahdollista myös\n"
                + "kääntää sekä nopeuttaa niiden putoamista. Nämä\n"
                + "toimenpiteet voidaan toteuttaa vain silloin, kun\n"
                + "niille on tilaa eli pelialueen reunat tai aiemmin\n"
                + "tippuneet palikat eivät ole toimenpiteiden tiellä.\n"
                + "Palikan ohjaamiseen käytettävät näppäimet ovat\n"
                + "kirjaimet W, A, S ja D tai vaihtoehtoisesti\n"
                + "nuolinäppäimet. Kaikki edellämainitut näppäimet\n"
                + "ovat käytettävissä aina pelattaessa, joten niitä\n"
                + "voidaan käyttää myös sekaisin. Nuoli ylös samoin\n"
                + "kuin W-kirjain saa palikan kääntymään, nuoli vasemmalle\n"
                + "ja A-kirjain palikan siirtymään vasemmalle, nuoli\n"
                + "oikealle ja D-kirjain palikan siirtymään oikealle ja\n"
                + "nuoli alas sekä S-kirjain nopeuttavat palikan\n"
                + "putoamista. Peli voidaan keskeyttää painamalla\n"
                + "välilyöntiä, jonka jälkeen avautuvasta valikosta\n"
                + "voidaan joko jatkaa peliä, aloittaa uusi peli, \n"
                + "tutustua parhaisiin tuloksiin tai ohjeisiin tai\n"
                + "sulkea sovellus kokonaan. Peli jatkuu myös painamalla\n"
                + "välilyöntiä uudestaan.";

        Button backToMenu = new Button("Takaisin valikkoon");
        backToMenu.setOnAction((event) -> {
            stackPane.getChildren().remove(instructionsBox);
        });

        instructions = new Label(INSTRUCTIONS);
        vBox.getChildren().addAll(instructions, backToMenu);

        return vBox;
    }

    private VBox hiscores() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #f5f5f5");

        String hiscores = "Parhaat tulokset\n\n"
                + "Tänne on tarkoitus toteuttaa lista parhaista tuloksista\n";

        Button backToMenu = new Button("Takaisin valikkoon");
        backToMenu.setOnAction((event) -> {
            stackPane.getChildren().remove(hiscoresBox);
        });

        hiscoreLabel = new Label(hiscores);
        vBox.getChildren().addAll(hiscoreLabel, backToMenu);

        return vBox;
    }
}
