package example.user;

import example.server.Position;

import java.util.Scanner;

public class UserHandler {

    private Communicator communicator;
    private QueryHandler queryHandler;

    public UserHandler() {
        this.communicator = new Communicator();
        this.queryHandler = new QueryHandler();
    }

    public void printBoard() {
        communicator.printBoard();
    }

    public void start(){

        while(!communicator.isCheckMated()) {
            message();
            String query = getQuery();
            if(isPrintBoard(query)) printBoard();
            else makeMove(query);
        }
        end();
    }

    public boolean isPrintBoard(String query) {
        return queryHandler.printBoard(query);
    }

    public String getQuery(){
        String query;
        Scanner scanner = new Scanner(System.in);
        query = scanner.nextLine();
        return query;
    }

    public void makeMove(String query){
        Position pos1 = queryHandler.convertQuery(queryHandler.getFirstPosition(query));
        Position pos2 = queryHandler.convertQuery(queryHandler.getSecondPosition(query));
        if(communicator.makeMove(pos1, pos2)){
            System.out.println("Move successful");
        } else {
            System.out.println("Move failed");
        }
    }

    public void message() {
        System.out.println("Make your Move, it's " + currPlayer() + "'s turn");
        System.out.println("Query example: a2-a4");
        System.out.println("press \"-\" for board");
    }

    public String currPlayer() {
        return communicator.getWhiteTurn()? "White" : "Black";
    }

    public void end(){
        if(communicator.isWhiteCheckMated()){
            System.out.println("Black Won!");
        } else {
            System.out.println("White Won!");
        }
    }

}
