package Game.Controllers;

/*  StartMenuController
 *  Andy Dai
 *  June 13 2023
 *  controller for viewResults button of SummaryScreen
 */

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewResultsController implements ActionListener {

    // opens file when action is detected
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Desktop.getDesktop().open(new File("Game/output.txt"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}// class
