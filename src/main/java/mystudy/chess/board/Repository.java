package mystudy.chess.board;

import mystudy.chess.peice.Piece;

import java.util.List;

public interface Repository {

    void move(Piece piece, Point endPoint);

    Piece replace(Piece piece, Point endPoint);

    Piece findByPoint(Point point);

    String findTeamByPoint(Point point);

    boolean isInBoard(Point point);

    List<Point> canMovePoint(List<Point> movePointList, String teamName);

    boolean isCheck(String teamName);

    boolean isGetOutOfCheck(String teamName);

    boolean isCheckmate(String teamName);
}
