package example.server;

import example.server.enums.PieceColor;

import java.util.List;

public class King extends Piece {

    public King(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {

        int rowGap = Math.abs(newPosition.getRow() - position.getRow());
        int colGap = Math.abs(newPosition.getCol() - position.getCol());
        boolean isKingMove = rowGap <=1 && colGap <=1 &&
                !(rowGap == colGap && rowGap == 0);
        if(!isKingMove) return false;
        if(board[newPosition.getRow()][newPosition.getCol()] != null) {
             return board[newPosition.getRow()][newPosition.getCol()].getColor() != color;
        } else return true;
    }

    @Override
    public List<Position> findCheckPath(Position newPosition, Piece[][] board) {
        throw new UnsupportedOperationException("King can't cause a check!");
    }
}
