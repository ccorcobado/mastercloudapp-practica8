package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.WithConsoleView;

class StartView extends WithConsoleView {

    StartView() {
        super();
    }

    void interact(StartController startController) {
        assert startController != null;
        MessageView.TITLE.writeln();
        new GameView().write(startController);
        startController.start();
    }

}
