
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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

    private Board board;
    private boolean pressed;
    private boolean running;
    private boolean gameOver;
    private BorderPane borderPane;
    private BorderPane gameOverCenter;
    private BorderPane pauseCenter;
    private double shadeThick;
    private GridPane tetrisGrid;
    private Group cellGroup;
    private int colorChoice;
    private int shapeSpeed;
    private Label subScore;
    private Label subLevel;
    private Label subLine;
    private Label score;
    private Label level;
    private Label line;
    private Label spacePause;
    private Label gameOverTitle;
    private Label gameOverSub;
    private List<Point> points;
    private Map<Integer, Color> color1;
    private Map<Integer, Color> color2;
    private Map<Integer, Map<Integer, Color>> colors;
    private PauseTransition pauseTransition;
    private Point currentPoint;
    private Polygon topShade;
    private Polygon bottomShade;
    private Rectangle boardShade;
    private Rectangle square;
    private Rectangle topRec;
    private Arc halfCircle;
    private Scene scene;
    private SequentialTransition shapeTransition;
    private StackPane stackPane;
    private HBox root;
    private Stage stage;
    private VBox vBoxTop;
    private VBox vBoxBottom;
    private VBox vBoxGameOver;
    
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
                        topShade.getPoints().addAll(new Double[] {
                            0.0, 0.0, (double) PIXEL, 0.0, (double) PIXEL - shadeThick, 
                            shadeThick, shadeThick, shadeThick, shadeThick, 
                            (double) PIXEL - shadeThick, 0.0, (double) PIXEL
                        });
                        
                        bottomShade.setOpacity(0.5);
                        bottomShade.setFill(Color.BLACK);
                        bottomShade.getPoints().addAll(new Double[] {
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
                        topShade.getPoints().addAll(new Double[] {
                            0.0, 0.0, (double) PIXEL, 0.0, 
                            (double) PIXEL - shadeThick, shadeThick, shadeThick, 
                            shadeThick, shadeThick, (double) PIXEL - shadeThick, 
                            0.0, (double) PIXEL
                        });
                        
                        bottomShade.setOpacity(0.25);
                        bottomShade.setFill(Color.BLACK);
                        bottomShade.getPoints().addAll(new Double[] {
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
                    tetrisGrid.add(cellGroup, i, j);
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
            
            vBoxGameOver = new VBox();
            vBoxGameOver.getChildren().addAll(gameOverTitle, gameOverSub);
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
        tetrisGrid.getStyleClass().add("grid");
        tetrisGrid.getStyleClass().add("background");
        
        for (int i = 0; i < Board.WIDTH; i++) {
            tetrisGrid.getColumnConstraints().add(new ColumnConstraints(PIXEL));
        }
        
        for (int i = 0; i < Board.HEIGHT; i++) {
            tetrisGrid.getRowConstraints().add(new RowConstraints(PIXEL));
        }
        
        vBoxBottom = new VBox();
        vBoxBottom.getStyleClass().add("background");
        
        score = new Label();
        level = new Label();
        line = new Label();
        
        score.getStyleClass().add("score");
        level.getStyleClass().add("score");
        line.getStyleClass().add("score");
        
        subScore = new Label("pisteet");
        subLevel = new Label("taso");
        subLine = new Label("tuhotut rivit");
        
        subScore.getStyleClass().add("subScore");
        subLevel.getStyleClass().add("subScore");
        subLine.getStyleClass().add("subScore");
        
        vBoxTop = new VBox();
        vBoxTop.getStyleClass().add("background");
        vBoxTop.getChildren().addAll(score, subScore, level, subLevel, line, subLine);
        
        spacePause = new Label("keskeytä painamalla välilyöntiä");
        spacePause.getStyleClass().add("pause");
        
        vBoxBottom.getChildren().add(spacePause);
        
        borderPane = new BorderPane();
        borderPane.getStyleClass().add("background");
        borderPane.setTop(vBoxTop);
        borderPane.setBottom(vBoxBottom);
        
        stackPane = new StackPane();
        stackPane.getChildren().add(tetrisGrid);
        
        root = new HBox();
        root.getStyleClass().add("background");
        root.setPadding(new Insets(5.0));
        root.setSpacing(25.0);
        root.getChildren().addAll(borderPane, stackPane);
        
        pauseCenter = new BorderPane();
        
        boardShade = new Rectangle(Board.WIDTH * PIXEL + 2 * (Board.WIDTH - 1), 
            Board.HEIGHT * PIXEL + 2 * (Board.HEIGHT - 1));
        boardShade.setOpacity(0.5);
        boardShade.setFill(Color.BLACK);
        boardShade.toFront();
        
        scene = new Scene(root);
        scene.getStylesheets().add("file:resurssit/application.css");
        scene.setOnKeyPressed((key) -> {
            if (running) {
                if (key.getCode().equals(KeyCode.LEFT) || key.getCode().equals(KeyCode.A)) {
                    board.moveLeft();
                    paint();
                } else if (key.getCode().equals(KeyCode.RIGHT) || key.getCode().equals(KeyCode.D)) {
                    board.moveRight();
                    paint();
                } else if (key.getCode().equals(KeyCode.UP) || key.getCode().equals(KeyCode.W) && pressed) {
                    pressed = false;
                    board.rotate();
                    paint();
                } else if (key.getCode().equals(KeyCode.DOWN) || key.getCode().equals(KeyCode.S)) {
                    board.moveDown();
                    paint();
                }
            }
            if (key.getCode().equals(KeyCode.SPACE)) {
                if (gameOver) {
                    shapeTransition.stop();
                    startNewGame();
                    stackPane.getChildren().removeAll(boardShade, gameOverCenter);
                } else {
                    if (running) {
                        running = false;
                        shapeTransition.stop();
                        stackPane.getChildren().addAll(boardShade, pauseCenter);
                    } else {
                        running = true;
                        shapeTransition.play();
                        stackPane.getChildren().removeAll(boardShade, pauseCenter);
                    }
                }
                setSceneDisable(!running);
            }
        });
        
        startNewGame();
        
        stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle("TETRIS");
        stage.show();
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
        pressed = true;
        shapeSpeed = board.getTimePerBlock();
        
        paint();
        createTransition();
    }
}
