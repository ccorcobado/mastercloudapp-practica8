package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Coordinate {

    private int row;
    private int column;
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 7;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate getInstance(String format) {
        assert format != null;
        try {
            int value = Integer.parseInt(format);
            int row = value / 10 - 1;
            int column = value % 10 - 1;
            if (isValid(row, column))
                return new Coordinate(row, column);
            else
                return null;

        } catch (Exception ex) {
            return null;
        }
    }
    
    public static boolean isValid(int row, int column) {
        return Coordinate.LOWER_LIMIT <= row && row <= Coordinate.UPPER_LIMIT && Coordinate.LOWER_LIMIT <= column
                && column <= Coordinate.UPPER_LIMIT;
    }
    
    boolean isValid() {
        return isValid(this.row, this.column);
    }
    
    boolean isDiagonal(Coordinate coordinate) {
        assert coordinate != null;
        return this.row + this.column == coordinate.row + coordinate.column
                || this.row - this.column == coordinate.row - coordinate.column;
    }

    int diagonalDistance(Coordinate coordinate) {
        assert coordinate != null;
        assert this.isDiagonal(coordinate);
        return Math.abs(this.row - coordinate.row);
    }

    Coordinate betweenDiagonal(Coordinate coordinate) {
        assert coordinate != null && coordinate.isValid();
        assert this.isValid();
        assert this.diagonalDistance(coordinate) > 1;
        int rowShift = (coordinate.row - this.row < 0)? 1 : -1;
        int columnShift = (coordinate.column - this.column < 0)? 1 : -1;
        return new Coordinate(coordinate.row + rowShift, coordinate.column + columnShift);
    }

    boolean isBlack() {
        return (this.row + this.column) % 2 != 0;
    }

    int getRow() {
        return this.row;
    }

    int getColumn() {
        return this.column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        if (column != other.column) {
            return false;
        }
        if (row != other.row) {
            return false;
        }
        return true;
    }

    private static Coordinate getCoordinateFromCommand(String command, int i, int j) {
        String format = command.substring(i, j);
        return Coordinate.getInstance(format);
    }
    
    public static Coordinate origin(String command) {
        return getCoordinateFromCommand(command, 0, 2);
    }
    
    public static Coordinate target(String command) {
        return getCoordinateFromCommand(command, 3, 5);
    }
}
