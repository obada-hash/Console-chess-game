package example.server;

import example.server.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowGap = Math.abs(position.getRow() - newPosition.getRow());
        int colGap = Math.abs(position.getCol() - newPosition.getCol());

        if(rowGap != colGap) return false;

        int rowStep = newPosition.getRow() > position.getRow() ? 1 : -1;
        int colStep = newPosition.getCol() > position.getCol() ? 1 : -1;
        int steps = rowGap - 1;

        for (int i = 1; i <= steps; i++) {
            if (board[position.getRow() + i * rowStep][position.getCol() + i * colStep] != null) {
                return false;
            }
        }
        if (board[newPosition.getRow()][newPosition.getCol()] == null) {
            return true;
        } else return board[newPosition.getRow()][newPosition.getCol()].getColor() != this.getColor();
    }

    @Override
    public List<Position> findCheckPath(Position newPosition, Piece[][] board) {
        List<Position> path = new ArrayList<>();
        int rowStep = newPosition.getRow() > position.getRow() ? 1 : -1;
        int colStep = newPosition.getCol() > position.getCol() ? 1 : -1;
        int steps = Math.abs(position.getRow() - newPosition.getRow());

        for (int i = 0; i <= steps; i++) {
            if((position.getRow() + i * rowStep) == newPosition.getRow() &&
                    (position.getCol() + i * colStep) == newPosition.getCol()) continue;
            path.add(new Position(position.getRow() + i * rowStep, position.getCol() + i * colStep));
        }
        return path;
    }

}
