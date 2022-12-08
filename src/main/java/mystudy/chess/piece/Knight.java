package mystudy.chess.piece;

import mystudy.chess.point.Point;

import java.util.List;

public class Knight extends Piece{

    public Knight(Point point, String teamName) {
        super(point, teamName);
    }

    @Override
    public String toString() {
        String firstTeamName = isTeamWhite ? "W" : "B";
        return firstTeamName + " N";
    }

    @Override
    public List<Point> moveList() {
        return null;
    }
}
