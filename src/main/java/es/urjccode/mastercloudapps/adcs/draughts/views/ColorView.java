package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;

public enum ColorView {
    WHITE("blancas"),
    BLACKS("negras"),
    EMPTY(" ");
    
    private final String message;
    
    private ColorView(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getPieceMessage() {
        return new String(new char[] { this.getMessage().charAt(0) });
    }
    
    public static ColorView valueOf(Color color) {
        return (color == null) ? 
                ColorView.EMPTY : ColorView.values()[color.ordinal()];
    }
}
