package mystudy.chess.game;

import mystudy.chess.piece.Piece;
import mystudy.chess.point.Point;
import org.springframework.stereotype.Component;

@Component
public class Notation {

    private String teamName;
    private Piece piece;
    private Point startPoint;
    private Point endPoint;

    public void setNotation(String teamName, Piece piece, Point startPoint, Point endPoint) {
        this.teamName = teamName;
        this.piece = piece;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
