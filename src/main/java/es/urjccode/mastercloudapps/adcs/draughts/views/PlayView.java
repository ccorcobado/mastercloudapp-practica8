package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.utils.WithConsoleView;

class PlayView extends WithConsoleView {

    private static final String FORMAT = "xx.xx";
    private static final String FORMAT_COMMAND = "([0-9]{2,2})+(?:[.][0-9]{2,2})$";

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
            String command = this.readCommand(color, playController);
            origin = Coordinate.getInstance(command.substring(0, 2));
            target = Coordinate.getInstance(command.substring(3, 5));
            error = playController.isCorrect(origin, target);
            if (error == null) 
                playController.move(origin, target);
            else
                this.writeMessageError(error, playController);
        } while (error != null);
        
        this.evaluateMessageEndGame(playController);
    }
    
    String readCommand(String color, PlayController playController) {
        Error error;
        String command;
        do {
            error = null;
            command = this.console.readString("Mueven las " + color + ": ");
            if (!command.matches(FORMAT_COMMAND)) {
                error = Error.BAD_FORMAT;
                writeMessageError(error, playController);
            }
            else {
                Coordinate origin = Coordinate.getInstance(command.substring(0, 2));
                Coordinate target = Coordinate.getInstance(command.substring(3, 5));

                if (origin == null || target == null) {
                    error = Error.BAD_FORMAT;
                    writeMessageError(error, playController);
                }
            }
        } while (error != null);
        
        return command;
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
