package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;

/**
 * Snake model.
 *
 * @author skorkmaz
 */
public class Model {

    enum Status {

        CONTINUE,
        FAIL,
        SUCCESS
    }

    private static final int HAS_NOT_BIT_ITSELF = -1;
    private static int indexBite = HAS_NOT_BIT_ITSELF;
    private static final List<Rectangle> GRID = new ArrayList<>();
    static final int NROW = 30;
    static final int NCOL = 50;
    static final int CELL_WIDTH = 10;
    static final int CELL_HEIGHT = CELL_WIDTH;
    private static int downSpeed = 0;
    private static int rightSpeed = 1;
    private static int iActiveCol = 5;
    private static int iActiveRow = 5;
    private static int iFoodCol = 10;
    private static int iFoodRow = 7;
    private static int iFoodEaten = 0;
    private static final int NB_AVAILABLE_FOOD = 3;
    private static Random random;
    private static final List<Integer> snakeList = new LinkedList<>();

    static Drawer drawer = (Graphics g) -> {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.clearRect(GRID.get(0).x, GRID.get(0).y, CELL_WIDTH * NCOL, CELL_HEIGHT * NROW);
        //draw grid:
        for (Rectangle rect : GRID) {
            g2.draw(rect);
        }
        //draw snake:        
        g2.setColor(Color.GREEN);
        for (int i = 0; i < snakeList.size() - 1; i++) {
            g2.fill(GRID.get(snakeList.get(i)));
        }
        g2.setColor(Color.BLACK);
        g2.fill(GRID.get(snakeList.get(snakeList.size() - 1)));
        //draw food:
        if (!isAllFoodEaten()) { //To prevent drawing of food after all food is eaten
            g2.setColor(Color.ORANGE);
            g2.fill(GRID.get(get1DIndex(iFoodRow, iFoodCol, NROW)));
        }
        //if snake has bit itself:
        if (indexBite != HAS_NOT_BIT_ITSELF) {
            g2.setColor(Color.RED);
            g2.fill(GRID.get(indexBite));
        }
    };

    static AbstractAction upAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            downSpeed = -1;
            rightSpeed = 0;
        }
    };
    static AbstractAction downAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            downSpeed = 1;
            rightSpeed = 0;
        }
    };
    static AbstractAction rightAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            downSpeed = 0;
            rightSpeed = 1;
        }
    };
    static AbstractAction leftAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            downSpeed = 0;
            rightSpeed = -1;
        }
    };

    static void init() {
        random = new Random();
        createGrid();
        indexBite = HAS_NOT_BIT_ITSELF;
        snakeList.add(get1DIndex(iActiveRow, iActiveCol - 3, NROW));
        snakeList.add(get1DIndex(iActiveRow, iActiveCol - 2, NROW));
        snakeList.add(get1DIndex(iActiveRow, iActiveCol - 1, NROW));
        snakeList.add(get1DIndex(iActiveRow, iActiveCol, NROW));
    }

    /**
     * First rows, then columns.
     */
    private static void createGrid() {
        for (int iCol = 0; iCol < NCOL; iCol++) {
            for (int iRow = 0; iRow < NROW; iRow++) {
                Rectangle rect = new Rectangle(iCol * CELL_WIDTH, iRow * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                GRID.add(rect);
            }
        }
    }

    /**
     * First rows, then columns.
     */
    static int get1DIndex(int iRow, int iCol, int nRow) {
        return iRow + iCol * nRow;
    }

    static int[] get2DIndices(int index1D, int nRow) {
        double x = (double) index1D / nRow;
        int iCol = (int) Math.floor(x);
        int iRow = index1D % nRow;
        return new int[]{iRow, iCol};
    }

    static Status update() {
        Status status = Status.CONTINUE;
        iActiveRow += downSpeed;
        iActiveCol += rightSpeed;
        if (!snakeBitItself() && !isAllFoodEaten() && isInsideGrid()) {
            if (isSnakeOnFood()) {
                iFoodRow = random.nextInt(NROW - 1);
                iFoodCol = random.nextInt(NCOL - 1);
                iFoodEaten++;
            } else {
                snakeList.remove(0);
            }
            snakeList.add(get1DIndex(iActiveRow, iActiveCol, NROW));
        } else {
            if (isAllFoodEaten()) {
                status = Status.SUCCESS;
            } else {
                status = Status.FAIL;
            }
        }
        return status;
    }

    private static boolean snakeBitItself() {
        boolean isBitItselft = false;
        for (Integer snakeIndex : snakeList) {
            int i1D = get1DIndex(iActiveRow, iActiveCol, NROW);
            if (i1D == snakeIndex) {
                isBitItselft = true;
                indexBite = i1D;
                break;
            }
        }
        return isBitItselft;
    }

    private static boolean isAllFoodEaten() {
        return iFoodEaten >= NB_AVAILABLE_FOOD;
    }

    private static boolean isInsideGrid() {
        return iActiveRow >= 0 && iActiveRow <= NROW - 1 && iActiveCol >= 0 && iActiveCol <= NCOL - 1;
    }

    private static boolean isSnakeOnFood() {
        return iActiveRow == iFoodRow && iActiveCol == iFoodCol;
    }

}
