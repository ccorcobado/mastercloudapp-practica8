package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameWithDraughtsTest {
   
    private Game game;
    
    public GameWithDraughtsTest() {
        game = new GameBuilder()
            .row("       B")
            .row(" b n    ")
            .row("  b     ")
            .row("    n   ")
            .row("        ")
            .row("     n  ")
            .row("    b n ")
            .row("N       ")
            .build();
    }
    
    @Test
    public void givenGameWhenWhiteDraughtMoveBackPositionThenOk() {
        Coordinate origin = new Coordinate(0,7);
        Coordinate target = new Coordinate(1,6);
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertNull(game.getPiece(origin));
    }
    
    @Test
    public void givenGameWhenBlackDraughtMoveBackPositionThenOk() {
        Coordinate origin = new Coordinate(7,0);
        Coordinate target = new Coordinate(6,1);
        game.nextTurn();
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertNull(game.getPiece(origin));
    }
    
//    @Test
//    public void givenGameWhenWhiteDraughtEatThenOk(){
//        Coordinate origin = new Coordinate(0,7);
//        Coordinate target = new Coordinate(6,1);
//        Coordinate middle = new Coordinate(3,4);
//        game.move(origin, target);
//        assertTrue(game.getPiece(target) instanceof Draught);
//        assertNull(game.getPiece(origin));
//        assertNull(game.getPiece(middle));
//    }
    
    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(1,1);
        Coordinate target = new Coordinate(0,0);
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertTrue(game.getPiece(target).getColor() == Color.WHITE);
        assertNull(game.getPiece(origin));
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(2,2);
        Coordinate target = new Coordinate(0,4);
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertTrue(game.getPiece(target).getColor() == Color.WHITE);
        assertNull(game.getPiece(origin));
    }
    
    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(6,6);
        Coordinate target = new Coordinate(7,7);
        game.nextTurn();
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertTrue(game.getPiece(target).getColor() == Color.BLACK);
        assertNull(game.getPiece(origin));
    }
    
    @Test
    public void testGivenGameWhenBlackPawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(5,5);
        Coordinate target = new Coordinate(7,3);
        game.nextTurn();
        game.move(origin, target);
        assertTrue(game.getPiece(target) instanceof Draught);
        assertTrue(game.getPiece(target).getColor() == Color.BLACK);
        assertNull(game.getPiece(origin));
    }
}