package tetris.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class Board {

    public boolean gameOver;
    public boolean gravity;
    public int numClearedLines;
    public int level;
    public int score;
    public int timePerBlock;
    public List<Point> points;
    public Random rand;
    public Shape currentShape;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 22;

    public Board() {
        this.gameOver = false;
        this.gravity = true;
        this.numClearedLines = 0;
        this.level = 0;
        this.score = 0;
        this.timePerBlock = 800;
        this.points = new ArrayList<Point>();
        this.rand = new Random();

        createCurrentShape();
    }

    public void createCurrentShape() {
        int num = rand.nextInt(8);

        if (num == 7 || (currentShape != null && num == currentShape.getType())) {
            num = rand.nextInt(7);
        }

        if (currentShape != null) {
            points.addAll(currentShape.getPoints());
        }

        currentShape = new Shape(num + 1);
    }

    private boolean hasPointsDown() {
        for (Point point : currentShape.getPoints()) {
            if (points.contains(new Point(point.getX(), point.getY() + 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPointsRight() {
        for (Point point : currentShape.getPoints()) {
            if (points.contains(new Point(point.getX() + 1, point.getY()))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPointsLeft() {
        for (Point point : currentShape.getPoints()) {
            if (points.contains(new Point(point.getX() - 1, point.getY()))) {
                return true;
            }
        }
        return false;
    }

    private boolean closeToTopBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getY() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean closeToLeftBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getX() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean closeToRightBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getX() == WIDTH - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean closeToBottomBorder() {
        for (Point point : currentShape.getPoints()) {
            if (point.getY() == HEIGHT - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean canRotate() {
        List<Point> rotated = currentShape.getRotatePoints();

        for (Point point : rotated) {
            if (point.getX() >= WIDTH || point.getY() >= HEIGHT || point.getX() < 0 
                    || points.contains(point)) {
                return false;
            }
        }
        return true;
    }

    public void rotate() {
        if (canRotate()) {
            currentShape.rotate();
        }
    }

    public void moveLeft() {
        if (!hasPointsLeft() && !closeToLeftBorder()) {
            currentShape.moveLeft();
        }
    }

    public void moveRight() {
        if (!hasPointsRight() && !closeToRightBorder()) {
            currentShape.moveRight();
        }
    }

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

    private void removeLines() {
        boolean gravityTriggered;

        do {
            gravityTriggered = false;
            List<Integer> fullLines = new ArrayList<Integer>(HEIGHT);
            List<Point> allPoints = getPoints();

            if (allPoints.size() != 0) {
                for (int i = 0; i < HEIGHT; i++) {
                    boolean full = true;
                    row:
                    for (int j = 0; j < WIDTH; j++) {
                        if (!allPoints.contains(new Point(j, i))) {
                            full = false;
                            break row;
                        }
                    }
                    if (full) {
                        fullLines.add(i);
                    }
                }
            }
            if (fullLines.size() != 0) {
                numClearedLines += fullLines.size();
                score += calculateCurrentScore(fullLines.size());

                int mostBottomLine = 0;

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
                if (mostBottomLine != HEIGHT - 1 && gravity) {
                    allPoints = getPoints();

                    for (int i = 0; i < WIDTH; i++) {
                        int numOfEmpty = 0;

                        for (int j = mostBottomLine + 1; j < HEIGHT; j++) {
                            if (!allPoints.contains((new Point(i, j)))) {
                                numOfEmpty++;
                            } else {
                                break;
                            }
                        }

                        if (numOfEmpty != 0) {
                            gravityTriggered = false;
                            for (int j = 0; j < points.size(); j++) {
                                if (points.get(j).getX() == i && points.get(j).getY() <= mostBottomLine) {
                                    points.get(j).modY(numOfEmpty);
                                    gravityTriggered = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (gravityTriggered);
        level = numClearedLines / 10;
        updateSpeed();
    }

    private int calculateCurrentScore(int num) {
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

    private void updateSpeed() {
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

    public void getStatus() {
        StringBuffer sb = new StringBuffer();

        sb.append(toString());
        sb.append("--- Border ---\n");
        sb.append("Left " + closeToLeftBorder() + "\n");
        sb.append("Right " + closeToRightBorder() + "\n");
        sb.append("Bottom " + closeToBottomBorder() + "\n");
        sb.append("--- Points ---\n");
        sb.append("Left " + hasPointsLeft() + "\n");
        sb.append("Right " + hasPointsRight() + "\n");
        sb.append("Bottom " + hasPointsDown() + "\n");
        sb.append("--- Rotate ---\n");
        sb.append(canRotate());

        System.out.println(sb.toString());
    }

    public int getNumClearedLines() {
        return numClearedLines;
    }

    public boolean getGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
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
}
