package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
