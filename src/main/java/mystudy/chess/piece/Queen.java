package mystudy.chess.piece;

import mystudy.chess.board.Point;

import java.util.List;

public class Queen extends Piece{

    public Queen(String point, String teamName) {
        super(point, teamName);
    }

    @Override
    public List<Point> moveList() {
        return null;
    }
}
