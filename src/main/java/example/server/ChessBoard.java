package example.server;

import example.server.enums.PieceColor;

public class ChessBoard {

    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8]; // 8x8 chessboard
        setupPieces();
    }

    private void setupPieces() {
        setUpKnights();
        setUpRooks();
        setUpBishops();
        setUpQueens();
        setUpKings();
        setUpPawns();
    }

    public void printBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j] != null) {
                    char cc = board[i][j].getClass().getSimpleName().toLowerCase().charAt(0);
                    System.out.print(cc + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void movePiece(Position currPosition, Position newPosition) {

        if(board[currPosition.getRow()][currPosition.getCol()] != null &&
              board[currPosition.getRow()][currPosition.getCol()].isValidMove(newPosition, board)) {

            board[newPosition.getRow()][newPosition.getCol()] = board[currPosition.getRow()][currPosition.getCol()];
            board[newPosition.getRow()][newPosition.getCol()].setPosition(newPosition);
            board[currPosition.getRow()][currPosition.getCol()] = null;
        }
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getCol()];
    }

    public void setPiece(Position pos, Piece piece) {
        board[pos.getRow()][pos.getCol()] = piece;
        if (piece != null) {
            piece.setPosition(new Position(pos.getRow(), pos.getCol()));
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    private void setUpKnights(){

        board[0][1] = new Knight(PieceColor.BLACK, new Position(0, 1));
        board[0][6] = new Knight(PieceColor.BLACK, new Position(0, 6));
        board[7][6] = new Knight(PieceColor.WHITE, new Position(7, 6));
        board[7][1] = new Knight(PieceColor.WHITE, new Position(7, 1));
    }
    private void setUpKings(){
        board[0][4] = new King(PieceColor.BLACK, new Position(0, 4));
        board[7][4] = new King(PieceColor.WHITE, new Position(7, 4));

    }
    private void setUpBishops(){
        board[0][2] = new Bishop(PieceColor.BLACK, new Position(0, 2));
        board[0][5] = new Bishop(PieceColor.BLACK, new Position(0, 5));
        board[7][5] = new Bishop(PieceColor.WHITE, new Position(7, 5));
        board[7][2] = new Bishop(PieceColor.WHITE, new Position(7, 2));
    }
    private void setUpQueens(){
        board[0][3] = new Queen(PieceColor.BLACK, new Position(0, 3));
        board[7][3] = new Queen(PieceColor.WHITE, new Position(7, 3));

    }
    private void setUpRooks(){
        board[0][0] = new Rook(PieceColor.BLACK, new Position(0, 0));
        board[0][7] = new Rook(PieceColor.BLACK, new Position(0, 7));
        board[7][7] = new Rook(PieceColor.WHITE, new Position(7, 7));
        board[7][0] = new Rook(PieceColor.WHITE, new Position(7, 0));
    }
    private void setUpPawns(){
        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(PieceColor.BLACK, new Position(1, i));
            board[6][i] = new Pawn(PieceColor.WHITE, new Position(6, i));
        }
    }
}
