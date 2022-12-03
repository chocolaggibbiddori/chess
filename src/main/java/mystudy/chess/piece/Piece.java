package mystudy.chess.piece;

import mystudy.chess.board.Point;

import java.util.List;

public abstract class Piece {

    Point point;
    final String teamName;

    public Piece(Point point, String teamName) {
        this.point = point;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return teamName + this.getClass();
    }

    public Point getPoint() {
        return point;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setPoint(String point) {
        this.point.setPoint(point);
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public abstract List<Point> moveList();
}
