package mystudy.chess.board;

import mystudy.chess.piece.Piece;

import java.util.List;

public interface Repository {

    void setUpPiece(Piece piece, Point point);

    boolean move(Piece piece, Point endPoint);

    Piece replace(Piece piece, Point endPoint);

    Piece findByPoint(Point point);

    String findTeamByPoint(Point point);

    boolean isInBoard(Point point);

    List<Point> canMovePoint(Piece Piece, String teamName);

    boolean isChecked(String teamName);

    List<Point> isGetOutOfCheck(Piece piece, String teamName);

    boolean isCheckmate(String teamName);
}
