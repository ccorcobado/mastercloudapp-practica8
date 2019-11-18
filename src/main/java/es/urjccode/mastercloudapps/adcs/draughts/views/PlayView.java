package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.utils.WithConsoleView;

class PlayView extends WithConsoleView {

    private static final String FORMAT = "xx.xx";

    private GameView gameView;
    
    PlayView() {
        super();
        this.gameView = new GameView();
    }

    void interact(PlayController playController) {
        assert playController != null;
        
        Coordinate origin = null;
        Coordinate target = null;
        String color = ColorView.values()[playController.getColor().ordinal()].getMessage();
        Error error;
        
        do {
            String command = this.console.readString("Mueven las " + color + ": ");
            if (command.length() != PlayView.FORMAT.length()) {
                error = Error.BAD_FORMAT;
                this.writeMessageError(error, playController);
            }
            else {
                origin = Coordinate.getInstance(command.substring(0, 2));
                target = Coordinate.getInstance(command.substring(3, 5));
                if (origin == null || target == null) {
                    error = Error.BAD_FORMAT;
                    this.writeMessageError(error, playController);
                }
                else {
                    error = playController.isCorrect(origin, target);
                    if (error == null) 
                        playController.move(origin, target);
                    else
                        this.writeMessageError(error, playController);
                }
            }
        } while (error != null);
        
        this.evaluateMessageEndGame(playController);
    }

    private void writeMessageError(Error error, PlayController playController) {
        assert error != null;
        assert playController != null;
        new ErrorView(error).writeln();
        this.gameView.write(playController);
    }
    
    private void evaluateMessageEndGame(PlayController playController) {
        if (playController.isBlocked()) {
            MessageView.LOSE.write();
        }
    }
}
