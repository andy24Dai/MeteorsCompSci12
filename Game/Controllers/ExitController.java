package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ExitController implements ActionListener {

    private static ExitController mInstance;
    private JFrame frame;

    public static ExitController getInstance() {
        if (mInstance == null) {
            mInstance = new ExitController();
        }
        return mInstance;
    }

    private ExitController() {

    }

    public void setFrameInstance(JFrame mFrame) {
        frame = mFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

}
