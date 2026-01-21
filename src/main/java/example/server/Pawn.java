package example.server;

import example.server.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int adder = color == PieceColor.WHITE ? 1 : -1;
        int rowGap = Math.abs(newPosition.getRow() - position.getRow());
        int colGap = Math.abs(newPosition.getCol() - position.getCol());
        int checkTwoMove = color == PieceColor.WHITE ? 6 : 1;
        if (rowGap == 2 && colGap == 0 && position.getRow() == checkTwoMove) {
            int add = color == PieceColor.WHITE ? -1 : 1;
            return board[newPosition.getRow()][newPosition.getCol()] == null &&
                    board[position.getRow()+add][position.getCol()] == null;
        }
        if(rowGap != 1 || colGap > 1 && newPosition.getRow()+adder==position.getRow()) return false;
        if(colGap > 0){
            if(board[newPosition.getRow()][newPosition.getCol()] != null){
                return board[newPosition.getRow()][newPosition.getCol()].getColor() != color;
            } else return false;
        } else {
            return board[newPosition.getRow()][newPosition.getCol()] == null;
        }
    }

    public List<Position> findCheckPath(Position newPosition, Piece[][] board) {
        return List.of(position);
    }
}
