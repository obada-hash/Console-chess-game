package example.server;

import example.server.enums.PieceColor;

import java.util.List;

public abstract class Piece {

    protected Position position;
    protected PieceColor color;

    public Piece(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public PieceColor getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract boolean isValidMove(Position newPosition, Piece[][] board);

    public abstract List<Position> findCheckPath(Position newPosition, Piece[][] board);

}
