package example.server;

import example.server.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int[] knightMoves1 = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] knightMoves2 = {2, -2, 2, -2, 1, -1, 1, -1};
        boolean isKnightMove = false;
        for(int i = 0; i<8;i++){
            if(position.getRow()+knightMoves1[i] == newPosition.getRow() &&
                   position.getCol()+knightMoves2[i] == newPosition.getCol()){
                       isKnightMove = true;
                break;
            }
        }
        if(!isKnightMove) return false;
        if(board[newPosition.getRow()][newPosition.getCol()] != null) {
            return color != board[newPosition.getRow()][newPosition.getCol()].getColor();
        } else return true;
    }

    public List<Position> findCheckPath(Position newPosition, Piece[][] board) {
        return List.of(position);
    }
}
