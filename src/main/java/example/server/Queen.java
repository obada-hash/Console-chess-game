package example.server;

import example.server.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {

        if (newPosition.equals(this.position)) {
            return false;
        }

        int rowGap = Math.abs(newPosition.getRow() - this.position.getRow());
        int colGap = Math.abs(newPosition.getCol() - this.position.getCol());

        boolean straightLine = this.position.getRow() == newPosition.getRow()
                || this.position.getCol() == newPosition.getCol();

        boolean diagonal = rowGap == colGap;

        if (!straightLine && !diagonal) {
            return false;
        }

        int rowDirection = Integer.compare(newPosition.getRow(), position.getRow());
        int colDirection = Integer.compare(newPosition.getCol(), position.getCol());

        int currRow = this.position.getRow() + rowDirection;
        int currCol = this.position.getCol() + colDirection;
        while (currRow != newPosition.getRow() || currCol != newPosition.getCol()) {
            if (board[currRow][currCol] != null) {
                return false;
            }
            currRow += rowDirection;
            currCol += colDirection;
        }

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getCol()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public List<Position> findCheckPath(Position newPosition, Piece[][] board) {
        List<Position> path = new ArrayList<>();

        int rowDirection = Integer.compare(newPosition.getRow(), this.position.getRow());
        int colDirection = Integer.compare(newPosition.getCol(), this.position.getCol());

        int currRow = this.position.getRow();
        int currCol = this.position.getCol();
        while (currRow != newPosition.getRow() || currCol != newPosition.getCol()) {
            if(newPosition.equals(new Position(currRow, currCol))) continue;
            path.add(new Position(currRow, currCol));
            currRow += rowDirection;
            currCol += colDirection;
        }
        return path;
    }
}
