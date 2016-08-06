package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Snake View.<br>
 * TODO:<br>
 * - Display remaining number of food to be eaten to complete level.<br>
 * - Add new levels: Speed increases and number of food to be eaten increases with level.<br>
 *
 * @author skorkmaz, August 2016
 */
public class View extends JPanel {

    private static Drawer drawer;
    private static int prefWidth;
    private static int prefHeight;

    private static View instance;
    private static final JFrame frame = new JFrame("Snake - Şamil Korkmaz, August 2016");
    private static final Font SUCCESS_FONT = new Font("Tahoma", 1, 24);
    private static final Font FAIL_FONT = new Font("Tahoma", 1, 24);
    private static final int SUCCESS_FAIL_X = 10;
    private static final int SUCCESS_FAIL_Y = 10;
    private static final int SUCCESS_FAIL_WIDTH = 150;
    private static final int SUCCESS_FAIL_HEIGHT = 50;
    private static final Color SUCCESS_FG_COLOR = Color.BLUE;
    private static final Color SUCCESS_BG_COLOR = Color.LIGHT_GRAY;
    private static final Color FAIL_FG_COLOR = Color.RED;
    private static final Color FAIL_BG_COLOR = Color.YELLOW;
    private static final String FAIL_TEXT = "Game over!";
    private static final String SUCCESS_TEXT = "Win!";
    private static final JLabel jlSuccessFail = new JLabel();

    @Override
    public final Dimension getPreferredSize() {
        return new Dimension(prefWidth, prefHeight);
    }

    public static void createAndShowGUI(int canvasWidth, int canvasHeight) {
        if (instance == null) {
            View.prefWidth = canvasWidth;
            View.prefHeight = canvasHeight;

            instance = new View();
            instance.add(jlSuccessFail);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(instance);
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
        frame.setVisible(true);
    }

    public static void setKeyboardActions(AbstractAction upAction, AbstractAction downAction, AbstractAction rightAction, AbstractAction leftAction) {
        instance.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        instance.getActionMap().put("upAction", upAction);

        instance.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        instance.getActionMap().put("downAction", downAction);

        instance.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        instance.getActionMap().put("rightAction", rightAction);

        instance.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        instance.getActionMap().put("leftAction", leftAction);
    }

    private View() {
        setLayout(null);
    }

    public static void setDrawer(Drawer drawer) {
        View.drawer = drawer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawer.draw(g);
    }

    public static void makeVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public static void initLevel() {
        jlSuccessFail.setOpaque(false);
        jlSuccessFail.setText("");
    }

    private static void initSuccessFail() {
        jlSuccessFail.setBounds(SUCCESS_FAIL_X, SUCCESS_FAIL_Y, SUCCESS_FAIL_WIDTH, SUCCESS_FAIL_HEIGHT);
        jlSuccessFail.setOpaque(true);
    }

    public static void setLevelSuccess() {
        initSuccessFail();
        jlSuccessFail.setFont(SUCCESS_FONT);
        jlSuccessFail.setForeground(SUCCESS_FG_COLOR);
        jlSuccessFail.setBackground(SUCCESS_BG_COLOR);
        jlSuccessFail.setText(SUCCESS_TEXT);
    }

    public static void setLevelFail() {
        initSuccessFail();
        jlSuccessFail.setFont(FAIL_FONT);
        jlSuccessFail.setForeground(FAIL_FG_COLOR);
        jlSuccessFail.setBackground(FAIL_BG_COLOR);
        jlSuccessFail.setText(FAIL_TEXT);
    }

    public static void updatePaint() {
        instance.repaint();
    }
}
