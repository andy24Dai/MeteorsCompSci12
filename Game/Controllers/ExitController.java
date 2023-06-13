package Game.Controllers;

/*  ExitController
 *  Andy Dai
 *  June 12 2023
 *  controller for exiting the game
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ExitController implements ActionListener {

    private static ExitController mInstance; // singleton instance
    private JFrame frame;

    // returns singleton instance of the class
    public static ExitController getInstance() {
        if (mInstance == null) {
            mInstance = new ExitController();
        }
        return mInstance;
    }

    // makes contstructor private
    private ExitController() {
    }

    // sets frame reference
    public void setFrameInstance(JFrame mFrame) {
        frame = mFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

}
