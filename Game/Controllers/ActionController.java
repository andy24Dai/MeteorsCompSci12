package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Model;
import Game.View;

public class ActionController implements ActionListener {

    protected Model model;
    // protected View view;

    public ActionController() {
        model = Model.getInstance();
        // view = View.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
