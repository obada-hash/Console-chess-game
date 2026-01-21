package example.server;

import example.server.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{


    public Rook(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {

        if(position.getRow() == newPosition.getRow() && position.getCol() != newPosition.getCol()){
            int colStart = Math.min(position.getCol(), newPosition.getCol()) + 1;
            int colEnd = Math.max(position.getCol(), newPosition.getCol());
            for(int i = colStart; i < colEnd; i++){
                if(board[position.getRow()][i] == null)
                    continue;
                return false;
            }
        } else if(position.getRow() != newPosition.getRow() && position.getCol() == newPosition.getCol()){
             int rowStart = Math.min(position.getRow(), newPosition.getRow()) + 1;
             int rowEnd = Math.max(position.getRow(), newPosition.getRow());
             for(int i = rowStart; i < rowEnd; i++){
                 if(board[i][position.getCol()] == null)
                     continue;
                 return false;
             }
        } else {
            return false;
        }

        if(board[newPosition.getRow()][newPosition.getCol()] != null){
            return board[newPosition.getRow()][newPosition.getCol()].getColor() != color;
        } else return true;
    }

    public List<Position> findCheckPath(Position newPosition, Piece[][] board) {
        List<Position> path = new ArrayList<Position>();
        if(position.getRow() == newPosition.getRow()){
            int colStart = Math.min(position.getCol(), newPosition.getCol());
            int colEnd = Math.max(position.getCol(), newPosition.getCol());
            for(int i = colStart; i <= colEnd; i++){
                if(i == newPosition.getCol()) continue;
                path.add(new Position(position.getRow(), i));
            }
        } else {
            int rowStart = Math.min(position.getRow(), newPosition.getRow());
            int rowEnd = Math.max(position.getRow(), newPosition.getRow());
            for(int i = rowStart; i <= rowEnd; i++){
                if(i == newPosition.getRow()) continue;
                path.add(new Position(i, position.getCol()));
            }
        }
        return path;
    }
}
