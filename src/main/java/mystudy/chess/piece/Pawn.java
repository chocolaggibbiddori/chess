package mystudy.chess.piece;

import mystudy.chess.board.BoardRepository;
import mystudy.chess.board.Point;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean isFirstMove = true;

    public Pawn(Point point, String teamName) {
        super(point, teamName);
    }

    @Override
    public List<Point> moveList() {
        List<Point> moveList = new ArrayList<>();
        boolean isOnEndLine = isTeamWhite ? point.getX() == 0 : point.getX() == 7;
        if (isOnEndLine) {
            return moveList;
        }

        makePoint(moveList);
        makeDiagonalPoint(moveList);
        return moveList;
    }

    private void makePoint(List<Point> moveList) {
        int moveDirect = isTeamWhite ? -1 : 1;
        Point firstPoint = new Point(point.getX() + moveDirect, point.getY());
        moveList.add(firstPoint);
        if (isFirstMove) {
            isFirstMove = false;
            Point secondPoint = new Point(point.getX() + moveDirect * 2, point.getY());
            moveList.add(secondPoint);
        }
    }

    private void makeDiagonalPoint(List<Point> moveList) {
        BoardRepository boardRepository = new BoardRepository();
        int pointX = point.getX();
        int moveDirect = isTeamWhite ? -1 : 1;
        int nextX = pointX + moveDirect;
        Point point1 = new Point(nextX, point.getY() - 1);
        Point point2 = new Point(nextX, point.getY() + 1);
        Piece findPiece1 = boardRepository.findByPoint(point1);
        Piece findPiece2 = boardRepository.findByPoint(point2);

        if (findPiece1 != null && !findPiece1.teamName.equals(this.teamName)) {
            moveList.add(point1);
        }
        if (findPiece2 != null && !findPiece2.teamName.equals(this.teamName)) {
            moveList.add(point2);
        }
    }

    public void promotion(Promotion toPiece) {
        BoardRepository boardRepository = new BoardRepository();
        Piece newPiece = null;
        switch (toPiece) {
            case PAWN -> newPiece = this;
            case BISHOP -> newPiece = new Bishop(point, teamName);
            case KNIGHT -> newPiece = new Knight(point, teamName);
            case ROOK -> newPiece = new Rook(point, teamName);
            case QUEEN -> newPiece = new Queen(point, teamName);
        }
        boardRepository.setUpPiece(newPiece, point);
    }
}
