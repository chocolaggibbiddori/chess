package mystudy.chess.board;

import mystudy.chess.piece.King;
import mystudy.chess.piece.Piece;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepository implements mystudy.chess.board.Repository {

    private static final Piece[][] pieces = new Piece[8][8];

    @Override
    public void setUpPiece(Piece piece, Point point) {
        pieces[point.getX()][point.getY()] = piece;
    }

    @Override
    public boolean move(Piece piece, Point endPoint) {
        if (findTeamByPoint(endPoint).equals(piece.getTeamName())) {
            return false;
        }
        Point startPoint = piece.getPoint();
        pieces[startPoint.getX()][startPoint.getY()] = null;
        pieces[endPoint.getX()][endPoint.getY()] = piece;
        piece.setPoint(endPoint);
        return true;
    }

    @Override
    public Piece replace(Piece piece, Point endPoint) {
        Piece deadPiece = pieces[endPoint.getX()][endPoint.getY()];
        if (move(piece, endPoint)) {
            return deadPiece;
        }
        return null;
    }

    @Override
    public Piece findByPoint(Point point) {
        return pieces[point.getX()][point.getY()];
    }

    @Override
    public String findTeamByPoint(Point point) {
        Piece piece = findByPoint(point);
        if (piece == null) {
            return "";
        }
        return piece.getTeamName();
    }

    @Override
    public boolean isInBoard(Point point) {
        int x = point.getX();
        int y = point.getY();
        if (x < 0 || x > 7) {
            return false;
        }
        if (y < 0 || y > 7) {
            return false;
        }
        return true;
    }

    @Override
    public List<Point> canMovePoint(Piece piece, String ourTeamName) {
        List<Point> movePointList = piece.moveList();
        List<Point> canMovePointList = new ArrayList<>();
        for (Point point : movePointList) {
            int x = point.getX();
            int y = point.getY();
            Piece target = pieces[x][y];
            if (target != null && findTeamByPoint(point).equals(ourTeamName)) {
                continue;
            }
            canMovePointList.add(point);
        }
        return canMovePointList;
    }

    @Override
    public boolean isChecked(String ourTeamName) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece == null) {
                    continue;
                }
                if (piece.getTeamName().equals(ourTeamName)) {
                    continue;
                }
                List<Point> moveList = piece.moveList();
                if (isKingAttacked(ourTeamName, moveList)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isKingAttacked(String ourTeamName, List<Point> moveList) {
        Point point = moveList.stream().filter(p -> {
            Piece findPiece = findByPoint(p);
            if (!(findPiece instanceof King)) {
                return false;
            }
            return findPiece.getTeamName().equals(ourTeamName);
        }).findFirst().orElse(null);
        return point != null;
    }

    @Override
    public List<Point> isGetOutOfCheck(Piece piece, String ourTeamName) {
        List<Point> getOutOfCheckPointList = new ArrayList<>();
        List<Point> moveList = canMovePoint(piece, ourTeamName);
        for (Point point : moveList) {
            Piece[][] clone = makePiecesClone();
            Point piecePoint = piece.getPoint();
            int x = point.getX();
            int y = point.getY();
            clone[piecePoint.getX()][piecePoint.getY()] = null;
            clone[x][y] = piece;
            if (isChecked(ourTeamName)) {
                continue;
            }
            getOutOfCheckPointList.add(point);
        }
        return getOutOfCheckPointList;
    }

    private Piece[][] makePiecesClone() {
        int row = pieces.length;
        int col = pieces[0].length;
        Piece[][] clone = new Piece[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                clone[i][j] = pieces[i][j];
            }
        }
        return clone;
    }

    @Override
    public boolean isCheckmate(String teamName) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                List<Point> getOutOfCheckPointList = isGetOutOfCheck(piece, teamName);
                if (getOutOfCheckPointList.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                pieces[i][j] = null;
            }
        }
    }

    public String readPieces() {
        return Arrays.deepToString(pieces);
    }
}
