package mystudy.chess.peice;

import mystudy.chess.board.Point;

import java.util.List;

public abstract class Piece {

    private Point point;
    private final String teamName;

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

    public abstract List<Point> moveList();
}
