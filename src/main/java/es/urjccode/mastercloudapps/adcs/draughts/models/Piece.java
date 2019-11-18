package es.urjccode.mastercloudapps.adcs.draughts.models;

public abstract class Piece {

    protected Color color;

    Piece(Color color) {
        assert color != null;
        this.color = color;
    }

    abstract Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider);

    abstract boolean isEatingJump(Coordinate origin, Coordinate target);
    
    boolean isLimit(Coordinate coordinate) {
        return coordinate.getRow() == 0 && this.getColor() == Color.WHITE
                || coordinate.getRow() == 7 && this.getColor() == Color.BLACK;
    }

    Color getColor() {
        return this.color;
    }

}
