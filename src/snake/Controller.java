package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Clone of classic snake game.
 *
 * @author skorkmaz, August 2016
 */
public class Controller {

    private static Timer timer;
    private static final int CANVAS_WIDTH = Model.CELL_WIDTH * Model.NCOL;
    private static final int CANVAS_HEIGHT = Model.CELL_HEIGHT * Model.NROW;

    public static void main(String[] args) {        
        java.awt.EventQueue.invokeLater(() -> {
            Model.init();
            View.createAndShowGUI(CANVAS_WIDTH, CANVAS_HEIGHT);
            set();
        });
    }

    private static void set() {
        View.setDrawer(Model.drawer);
        View.setKeyboardActions(Model.upAction, Model.downAction, Model.rightAction, Model.leftAction);
        timer = new Timer(100, new TimerAction());
        timer.start();
    }

    private static class TimerAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Model.Status status = Model.update();
            if (status != Model.Status.CONTINUE) {
                timer.stop();
                if (status == Model.Status.FAIL) {
                    View.setLevelFail();
                }
                if (status == Model.Status.SUCCESS) {
                    View.setLevelSuccess();
                }
            }
            View.updatePaint();
        }

    }

}
