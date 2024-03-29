package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game {

    private Board board;
    private Turn turn;

    public Game() {
        this.turn = new Turn();
        this.board = new Board();
        for (int i = 0; i < this.board.getDimension(); i++) {
            for (int j = 0; j < this.board.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = this.getInitialPiece(coordinate);
                if (piece != null) {
                    this.board.put(coordinate, piece);
                }
            }
        }
    }

    void setBoard(Board board) {
        this.board = board;
    }

    void nextTurn() {
        this.turn.change();
    }

    private Pawn getInitialPiece(Coordinate coordinate) {
        assert coordinate != null;
        if (coordinate.isBlack()) {
            final int row = coordinate.getRow();
            Color color = null;
            if (row <= 2) {
                color = Color.BLACK;
            } else if (row >= 5) {
                color = Color.WHITE;
            }
            if (color != null) {
                return new Pawn(color);
            }
        }
        return null;
    }

    public void move(Coordinate origin, Coordinate target) {
        assert this.isCorrect(origin, target) == null;

        Piece piece = this.board.getPiece(origin);
        if (piece.isEatingJump(origin, target)) {
            this.board.remove(piece.betweenDiagonal(origin, target));
        }
        this.board.move(origin, target);
        this.nextTurn();
    }

    public Error isCorrect(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        if (board.isEmpty(origin)) {
            return Error.EMPTY_ORIGIN;
        }
        if (this.turn.getColor() != this.board.getColor(origin)) {
            return Error.OPPOSITE_PIECE;
        }
        return this.board.getPiece(origin).isCorrect(origin, target, board);
    }

    public Color getColor(Coordinate coordinate) {
        assert coordinate != null;
        return this.board.getColor(coordinate);
    }

    public Color getColor() {
        return this.turn.getColor();
    }

    public boolean isBlocked() {
        return this.arePieces(this.turn.getColor()) || !areMovements();
    }

    private boolean arePieces(Color color) {
        return this.board.getPieces(color).isEmpty();
    }

    private boolean areMovements() {
        for (int i = 0; i < this.board.getDimension(); i++) {
            for (int j = 0; j < this.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = this.getPiece(coordinate);
                if (piece != null && this.areMovements(coordinate))
                    return true;
            }
        }
        return false;
    }

    private boolean areMovements(Coordinate origin) {
        for (int i = 0; i < this.board.getDimension(); i++) {
            for (int j = 0; j < this.getDimension(); j++) {
                if (isCorrect(origin, new Coordinate(i, j)) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getDimension() {
        return this.board.getDimension();
    }

    public Piece getPiece(Coordinate coordinate) {
        assert coordinate != null;
        return this.board.getPiece(coordinate);
    }

    @Override
    public String toString() {
        return this.board + "\n" + this.turn;
    }

}
