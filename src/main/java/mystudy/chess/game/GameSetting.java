package mystudy.chess.game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mystudy.chess.piece.*;
import mystudy.chess.point.Point;
import mystudy.chess.repository.BoardRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameSetting {

    private final BoardRepository boardRepository;

    public void gameInit() {
        Point[][] pointArray = setPointArray();
        setBlackBackLine(pointArray[0]);
        setBlackPawnLine(pointArray[1]);
        setWhitePawnLine(pointArray[2]);
        setWhiteBackLine(pointArray[3]);
        log.info("Game Setting Complete");
    }

    private Point[][] setPointArray() {
        Point[][] pointArray = new Point[4][8];
        for (int i = 0; i < pointArray.length; i++) {
            for (int j = 0; j < pointArray[i].length; j++) {
                if (i < 2) {
                    pointArray[i][j] = new Point(i, j);
                } else {
                    pointArray[i][j] = new Point(i + 4, j);
                }
            }
        }
        return pointArray;
    }

    private void setBlackBackLine(Point[] points) {
        Piece[] pieces = setBlackBackPieces(points);
        for (int i = 0; i < points.length; i++) {
            boardRepository.setUpPiece(pieces[i], points[i]);
        }
    }

    private Piece[] setBlackBackPieces(Point[] points) {
        Piece[] pieces = new Piece[8];
        String teamName = "Black";
        pieces[0] = new Rook(points[0], teamName);
        pieces[1] = new Knight(points[1], teamName);
        pieces[2] = new Bishop(points[2], teamName);
        pieces[3] = new Queen(points[3], teamName);
        pieces[4] = new King(points[4], teamName);
        pieces[5] = new Bishop(points[5], teamName);
        pieces[6] = new Knight(points[6], teamName);
        pieces[7] = new Rook(points[7], teamName);
        return pieces;
    }

    private void setBlackPawnLine(Point[] points) {
        for (Point point : points) {
            Piece pawn = new Pawn(point, "Black");
            boardRepository.setUpPiece(pawn, point);
        }
    }

    private void setWhitePawnLine(Point[] points) {
        for (Point point : points) {
            Piece pawn = new Pawn(point, "White");
            boardRepository.setUpPiece(pawn, point);
        }
    }

    private void setWhiteBackLine(Point[] points) {
        Piece[] pieces = setWhiteBackPieces(points);
        for (int i = 0; i < points.length; i++) {
            boardRepository.setUpPiece(pieces[i], points[i]);
        }
    }

    private Piece[] setWhiteBackPieces(Point[] points) {
        Piece[] pieces = new Piece[8];
        String teamName = "White";
        pieces[0] = new Rook(points[0], teamName);
        pieces[1] = new Knight(points[1], teamName);
        pieces[2] = new Bishop(points[2], teamName);
        pieces[3] = new Queen(points[3], teamName);
        pieces[4] = new King(points[4], teamName);
        pieces[5] = new Bishop(points[5], teamName);
        pieces[6] = new Knight(points[6], teamName);
        pieces[7] = new Rook(points[7], teamName);
        return pieces;
    }
}
