package example.user;

import example.server.ChessBoard;
import example.server.GameLogic;
import example.server.Position;
import example.server.enums.PieceColor;

public class Communicator {

      private GameLogic gameLogic;
      private ChessBoard board;
      public Communicator() {
          this.board = new ChessBoard();
          this.gameLogic = new GameLogic(board);
      }

      public boolean makeMove(Position from, Position to) {
          return gameLogic.makeMove(from, to);
      }

      public boolean isCheckMated() {
          return gameLogic.isCheckMate(PieceColor.WHITE) || gameLogic.isCheckMate(PieceColor.BLACK);
      }

      public boolean getWhiteTurn() {
          return gameLogic.getWhiteTurn();
      }

      public boolean isWhiteCheckMated() {
          return gameLogic.isCheckMate(PieceColor.WHITE);
      }

      public void printBoard() {
          gameLogic.printBoard();
      }
}
