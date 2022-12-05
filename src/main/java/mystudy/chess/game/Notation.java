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
        this.teamName = teamName.substring(0, 1);
        this.piece = piece;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(teamName).append(" ").append(piece).append(" ").append(startPoint).append(" -> ").append(endPoint);
        return sb.toString();
    }
}
