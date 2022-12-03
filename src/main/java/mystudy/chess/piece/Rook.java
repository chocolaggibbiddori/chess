package mystudy.chess.piece;

import mystudy.chess.board.Point;

import java.util.List;

public class Rook extends Piece{

    public Rook(Point point, String teamName) {
        super(point, teamName);
    }

    @Override
    public List<Point> moveList() {
        return null;
    }
}
