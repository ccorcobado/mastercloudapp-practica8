package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        game = new GameBuilder()
            .row(" n      ")
            .row("        ")
            .row("  b   n ")
            .row("    n   ")
            .row("   b   b")
            .row("  b     ")
            .row("        ")            
            .row("   b    ")
            .build();
    }

    @Test
    public void testGivenNewBoardThenGoodLocations() {
        Game initialGame = new Game();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < initialGame.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Color color = initialGame.getColor(coordinate);
                if (coordinate.isBlack()) {
                    assertEquals(Color.BLACK, color);
                } else {
                    assertNull(color);
                }
            }
        }
        for (int i = 5; i < initialGame.getDimension(); i++) {
            for (int j = 0; j < initialGame.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Color color = initialGame.getColor(coordinate);
                if (coordinate.isBlack()) {
                    assertEquals(Color.WHITE, color);
                } else {
                    assertNull(color);
                }
            }
        }
    }

//    private Error advance(Coordinate[][] coordinates){
//        Error error = null;
//        System.out.println(game);
//        for (int i = 0; i < coordinates.length; i++) {
//            assertNull(error);
//            error = game.isCorrect(coordinates[i][0], coordinates[i][1]);
//            if (error == null){
//                game.move(coordinates[i][0], coordinates[i][1]);
//                System.out.println(game);
//            } else {
//                return error;
//            }
//        }
//        return error;
//    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError() {
        assertEquals(Error.EMPTY_ORIGIN,
                this.game.isCorrect(new Coordinate(7,0), new Coordinate(6,1)));
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        assertEquals(Error.OPPOSITE_PIECE,
                this.game.isCorrect(new Coordinate(0, 1), new Coordinate(3, 0)));
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError() {
        assertEquals(Error.NOT_DIAGONAL,
                this.game.isCorrect(new Coordinate(2, 2), new Coordinate(4, 2)));
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        assertEquals(Error.NOT_ADVANCED,
                this.game.isCorrect(new Coordinate(2, 2), new Coordinate(3, 3)));
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        assertEquals(Error.NOT_EMPTY_TARGET,
            this.game.isCorrect(new Coordinate(5, 2), new Coordinate(4, 3)));
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 2);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        origin = new Coordinate(0, 1);
        target = new Coordinate(1, 2);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        Coordinate origin = new Coordinate(4, 3);
        Coordinate middle = new Coordinate(3, 4);
        Coordinate target = new Coordinate(2, 5);
        assertEquals(Color.WHITE, game.getColor(origin));
        assertEquals(Color.BLACK, game.getColor(middle));
        assertNull(game.isCorrect(origin, target));
        game.move(origin, target);
        assertNull(game.getColor(origin));
        assertNull(game.getColor(middle));
        assertEquals(Color.WHITE, game.getColor(target));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        assertEquals(Error.EATING_EMPTY,
            this.game.isCorrect(new Coordinate(2, 2), new Coordinate(0, 4)));
    }

    @Test
    public void testGivenGameWhenMoveBadDistanceThenError() {
        assertEquals(Error.BAD_DISTANCE,
            this.game.isCorrect(new Coordinate(7, 3), new Coordinate(4, 6)));
    }

}