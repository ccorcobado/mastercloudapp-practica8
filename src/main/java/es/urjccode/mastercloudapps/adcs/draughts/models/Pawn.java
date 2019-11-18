package es.urjccode.mastercloudapps.adcs.draughts.models;

class Pawn extends Piece {

    private static final int MAX_DISTANCE = 2;
    
    Pawn(Color color) {
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
        if (!this.isAdvanced(origin, target)) {
            return Error.NOT_ADVANCED;
        }
        int distance = origin.diagonalDistance(target);
        if (distance > Pawn.MAX_DISTANCE) {
            return Error.BAD_DISTANCE;
        }
        if (distance == Pawn.MAX_DISTANCE) {
            if (pieceProvider.getPiece(origin.betweenDiagonal(target)) == null) {
                return Error.EATING_EMPTY;
            }
        }
        return null;
    }

    @Override
    boolean isEatingJump(Coordinate origin, Coordinate target) {
        return origin.diagonalDistance(target) == 2;
    }

    boolean isAdvanced(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        int difference = origin.getRow() - target.getRow();
        if (color == Color.WHITE) {
            return difference > 0;
        }
        return difference < 0;
    }
}
