package mystudy.chess.piece;

import mystudy.chess.repository.BoardRepository;
import mystudy.chess.repository.MemoryBoardRepository;
import mystudy.chess.point.Point;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean isFirstMove;

    public Pawn(Point point, String teamName) {
        super(point, teamName);
        isFirstMove = true;
    }

    public void setIsFirstMoveFalse() {
        isFirstMove = false;
    }

    @Override
    public List<Point> moveList() {
        List<Point> moveList = new ArrayList<>();
        boolean isOnEndLine = isTeamWhite ? point.getX() == 0 : point.getX() == 7;
        if (isOnEndLine) {
            return moveList;
        }

        BoardRepository boardRepository = new MemoryBoardRepository();
        makePoint(moveList);
        makeDiagonalPoint(moveList, boardRepository);
        makeEnpassantPoint(moveList, boardRepository);
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

    private void makeDiagonalPoint(List<Point> moveList, BoardRepository boardRepository) {
        int pointX = point.getX();
        int moveDirect = isTeamWhite ? -1 : 1;
        int nextX = pointX + moveDirect;
        Point point1 = new Point(nextX, point.getY() - 1);
        Point point2 = new Point(nextX, point.getY() + 1);
        Piece findPiece1 = null;
        Piece findPiece2 = null;

        if (boardRepository.isInBoard(point1)) {
            findPiece1 = boardRepository.findByPoint(point1);
        }
        if (boardRepository.isInBoard(point2)) {
            findPiece2 = boardRepository.findByPoint(point2);
        }

        if (findPiece1 != null && !findPiece1.teamName.equals(this.teamName)) {
            moveList.add(point1);
        }
        if (findPiece2 != null && !findPiece2.teamName.equals(this.teamName)) {
            moveList.add(point2);
        }
    }

    // TODO: 2022/12/05 앙파상
    private void makeEnpassantPoint(List<Point> moveList, BoardRepository boardRepository) {
        int pointX = point.getX();
        boolean isEnpassantPoint = (isTeamWhite) ? pointX == 3 : pointX == 4;
        if (!isEnpassantPoint) {
            return;
        }


    }

    public void promotion(Promotion toPiece, BoardRepository boardRepository) {
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
