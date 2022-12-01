package mystudy.chess.peice;

import mystudy.chess.board.Point;

import java.util.List;

public abstract class Piece {

    Point point;
    final String teamName;

    public Piece(String point, String teamName) {
        this.point = new Point(point);
        this.teamName = teamName;
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
