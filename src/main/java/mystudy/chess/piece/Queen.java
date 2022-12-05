package mystudy.chess.piece;

import mystudy.chess.point.Point;

import java.util.List;

public class Queen extends Piece{

    public Queen(Point point, String teamName) {
        super(point, teamName);
    }

    @Override
    public List<Point> moveList() {
        return null;
    }
}
