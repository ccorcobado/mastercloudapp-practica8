package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

public enum MessageView {
    
    TITLE("Draughts"),
    OTHER_GAME("¿Queréis jugar otra"),
    LOSE("Derrota!!! No puedes mover tus fichas!!!"),
    CANCEL("¿Quieres cancelar?");
    
    private final String message;
    private final Console console;
    
    private MessageView(String message) {
        this.message = message;
        this.console = new Console();
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void write() {
        this.console.write(this.message);
    }
    
    public void writeln() {
        this.console.writeln(this.message);
    }
}