package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBuilder {
    Board board;

    private List<String> boardList;

    GameBuilder(){
        this.boardList = new ArrayList<>();
    }

    GameBuilder row(String boardString){
        this.boardList.add(boardString);
        return this;
    }

    Game build() {
        return this.build(new Turn());
    }
    
    Game build(Turn turn){
        this.board = new Board();
        Game game = new Game();
        game.setTurn(turn);
        for (int row = 0 ; row < this.boardList.size() ; row++) {
            String boardString = this.boardList.get(row);
            for (int col = 0; col < boardString.length(); col++){
                if (boardString.charAt(col)!=' '){
                    this.putPiece(boardString.charAt(col),row,col);
               }
            }
        }
        game.setBoard(this.board);
        return game;
    }
    
    private void putPiece(char charPiece, int row, int col){
        Map<Character, Piece> pieces = new HashMap<>(); 
        pieces.put('b', new Pawn(Color.WHITE));
        pieces.put('n', new Pawn(Color.BLACK));
        pieces.put('B', new Draught(Color.WHITE));
        pieces.put('N', new Draught(Color.BLACK));
        this.board.putPiece(new Coordinate(row,col), pieces.get(charPiece));
    }
}