package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        if (!origin.isDiagonal(target)) {
            return Error.NOT_DIAGONAL;
        }
        if (!pieceProvider.isEmpty(target)) {
            return Error.NOT_EMPTY_TARGET;
        }
        return null;
    }
    
    @Override
    Coordinate betweenDiagonal(Coordinate origin, Coordinate target) {
        return origin.betweenDiagonal(target);
    }

    @Override
    boolean isEatingJump(Coordinate origin, Coordinate target) {
        return origin.diagonalDistance(target) > 1;
    }
}
