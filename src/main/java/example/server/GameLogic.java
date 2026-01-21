package example.server;

import example.server.enums.PieceColor;

import java.util.List;

public class GameLogic {

     private ChessBoard board;
     private boolean whiteTurn = true;


     public GameLogic(ChessBoard board) {
         this.board = board;
     }

     public void printBoard() {
         board.printBoard();
     }

     public boolean makeMove(Position currentPosition, Position newPosition) {

         Piece intendedPiece = board.getPiece(currentPosition);
         if(intendedPiece == null || intendedPiece.getColor() != (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)){
             return false;
         }


         if(intendedPiece.isValidMove(newPosition, board.getBoard())){
               board.movePiece(currentPosition, newPosition);
               whiteTurn = !whiteTurn;
               return true;
         }
         return false;
     }

     public Position findKingPositionByColor(PieceColor color){
         for(int i = 0; i < 8; i++){
             for(int j = 0; j < 8; j++){
                 Piece piece = board.getPiece(new Position(i, j));
                 if(piece != null && piece.getColor() == color && piece.getClass().getSimpleName().equals("King")){
                     return new Position(i, j);
                 }
             }
         }
         return null;
     }

     public int howManyChecks(PieceColor color){
         Position kingPosition = findKingPositionByColor(color);
         int causedCheckPieces = 0;
         for(int i = 0; i < 8; i++){
             for(int j = 0; j < 8; j++){
                 Piece piece = board.getPiece(new Position(i, j));
                 if(piece == null) continue;
                 if(piece.getColor() == color) continue;
                 if(piece.isValidMove(kingPosition, board.getBoard()))
                       causedCheckPieces++;
             }
         }
         return causedCheckPieces;
     }

     public boolean wouldKingBeInCheckAfterMove(PieceColor color, Position from, Position to){
         Piece temp = board.getPiece(to);
         board.setPiece(to, board.getPiece(from));
         board.setPiece(from, null);

         boolean inCheck = howManyChecks(color) > 0;
         // undo the move
         board.setPiece(from, board.getPiece(to));
         board.setPiece(to, temp);

         return inCheck;
     }

     public Piece findPieceCausedCheck(PieceColor color){
         Position kingPosition = findKingPositionByColor(color);
         for(int i = 0; i < 8; i++){
             for(int j = 0; j < 8; j++){
                 Piece piece = board.getPiece(new Position(i, j));
                 if(piece == null) continue;
                 if(piece.getColor() == color) continue;
                 if(piece.isValidMove(kingPosition, board.getBoard()))
                     return board.getPiece(piece.getPosition());
             }
         }
         return null;
     }

     public boolean canPieceRemoveCheck(Piece piece, PieceColor kingColor){
         Position kingPosition = findKingPositionByColor(kingColor);
         List<Position> path = piece.findCheckPath(kingPosition, board.getBoard());
         for(int i = 0; i < 8; i++){
             for(int j = 0; j < 8; j++){
                 Piece pieceToCheck = board.getPiece(new Position(i, j));
                 if(pieceToCheck == null) continue;
                 if(pieceToCheck.getColor() != kingColor || kingPosition.equals(pieceToCheck.position)) continue;
                 for (Position position : path) {
                     if (pieceToCheck.isValidMove(position, board.getBoard()))
                         return true;
                 }
             }
         }
         return false;
     }

     public static boolean isPositionOnBoard(Position position){
         return position.getRow() >= 0 && position.getRow() < 8 &&
                 position.getCol() >= 0 && position.getCol() < 8;
     }

     public boolean isCheckMate(PieceColor color){
         int piecesCausedCheck = howManyChecks(color);
         if(piecesCausedCheck == 0) return false;
         Position kingPosition = findKingPositionByColor(color);
         for(int i=-1;i<=1;i++){
             for(int j=-1;j<=1;j++){
                 if(i == 0 && j == 0) continue;

                 Position newPosition = new Position(kingPosition.getRow()+i, kingPosition.getCol()+j);
                 if(isPositionOnBoard(newPosition) && board.getPiece(kingPosition).isValidMove(newPosition, board.getBoard())
                         && !wouldKingBeInCheckAfterMove(color, kingPosition, newPosition)){
                     return false;
                 }
             }
         }
         return !(piecesCausedCheck==1 && canPieceRemoveCheck(findPieceCausedCheck(color), color));
     }

     public boolean getWhiteTurn(){
         return whiteTurn;
     }

}
