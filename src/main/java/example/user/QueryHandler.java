package example.user;

import example.server.Position;

public class QueryHandler {

    public QueryHandler() {

    }

    public boolean printBoard(String query) {
        return query.charAt(0) == '-';
    }
    public Position convertQuery(String query) {
        int col = query.charAt(0) - 'a';
        int row = 8 - Character.getNumericValue(query.charAt(1));
        return new Position(row, col);
    }

    public String getFirstPosition(String query) {
        return query.substring(0, 2);
    }

    public String getSecondPosition(String query) {
        return query.substring(3);
    }



}
