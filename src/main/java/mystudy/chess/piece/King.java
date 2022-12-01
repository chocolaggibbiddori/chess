package mystudy.chess.piece;

import mystudy.chess.board.Point;

import java.util.List;

public class King extends Piece{

    public King(String point, String teamName) {
        super(point, teamName);
    }

    @Override
    public List<Point> moveList() {
        return null;
    }
}
