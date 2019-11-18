package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameWithDraughtsTest {
   
    private Game game;
    
    public GameWithDraughtsTest() {
        game = new GameBuilder()
            .row("        ")
            .row(" b n    ")
            .row("  b     ")
            .row("        ")
            .row("        ")
            .row("     n  ")
            .row("    b n ")
            .row("        ")
            .build();
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(1,1);
        Coordinate target = new Coordinate(0,0);
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertNull(game.getPiece(origin));
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(2,2);
        Coordinate target = new Coordinate(0,4);
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertNull(game.getPiece(origin));
    }
    
    private Turn BlackTurn() {
        Turn turn = new Turn();
        turn.change();
        return turn;
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(6,6);
        Coordinate target = new Coordinate(7,7);
        game.setTurn(this.BlackTurn());
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertNull(game.getPiece(origin));
    }
    
    @Test
    public void testGivenGameWhenBlackPawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(5,5);
        Coordinate target = new Coordinate(7,3);
        game.setTurn(this.BlackTurn());
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertNull(game.getPiece(origin));
    }
}