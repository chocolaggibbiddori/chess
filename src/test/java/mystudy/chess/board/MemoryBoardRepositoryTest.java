package mystudy.chess.board;

import mystudy.chess.piece.King;
import mystudy.chess.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryBoardRepositoryTest {

    MemoryBoardRepository memoryBoardRepository = new MemoryBoardRepository();

    @AfterEach
    void afterEach() {
        memoryBoardRepository.clear();
    }

    @Test
    void setUpPiece() {
        Point point1 = new Point("D1");
        Point point2 = new Point("A1");
        Point point3 = new Point("H8");
        Piece king1 = new King(point1, "Black");
        Piece king2 = new King(point2, "Black");
        Piece king3 = new King(point3, "Black");
        memoryBoardRepository.setUpPiece(king1, point1);
        memoryBoardRepository.setUpPiece(king2, point2);
        memoryBoardRepository.setUpPiece(king3, point3);

        Piece piece1 = memoryBoardRepository.findByPoint(point1);
        Piece piece2 = memoryBoardRepository.findByPoint(point2);
        Piece piece3 = memoryBoardRepository.findByPoint(point3);
        assertThat(piece1).isEqualTo(king1);
        assertThat(piece2).isEqualTo(king2);
        assertThat(piece3).isEqualTo(king3);
    }

    @Test
    void move() {
        Point point1 = new Point("D1");
        Point point2 = new Point("E6");
        Point point3 = new Point("g4");
        Point point4 = new Point("c2");
        Piece king1 = new King(point1, "Black");
        Piece king2 = new King(point2, "Black");
        Piece king3 = new King(point3, "White");

        memoryBoardRepository.setUpPiece(king1, point1);
        memoryBoardRepository.setUpPiece(king2, point2);
        memoryBoardRepository.setUpPiece(king3, point3);

        memoryBoardRepository.move(king1, point4);
        Piece findPiece = memoryBoardRepository.findByPoint(point4);
        assertThat(findPiece).isEqualTo(king1);
        assertThat(memoryBoardRepository.findByPoint(point1)).isNull();

        assertThat(memoryBoardRepository.move(king1, point2)).isFalse();
        assertThat(memoryBoardRepository.move(king1, point3)).isTrue();
    }

    @Test
    void replace() {
        Point point1 = new Point("A8");
        Point point2 = new Point("h1");
        Point point3 = new Point("g5");
        Piece king1 = new King(point1, "Black");
        Piece king2 = new King(point1, "Black");
        Piece king3 = new King(point1, "White");

        memoryBoardRepository.setUpPiece(king1, point1);
        memoryBoardRepository.setUpPiece(king2, point2);
        memoryBoardRepository.setUpPiece(king3, point3);

        assertThat(memoryBoardRepository.replace(king1, point2)).isNull();
        assertThat(memoryBoardRepository.replace(king1, point3)).isNotNull();
    }

    @Test
    void findTeamByPoint() {
        Point point1 = new Point("A8");
        Point point2 = new Point("h1");
        Piece king1 = new King(point1, "Black");
        Piece king2 = new King(point1, "White");

        memoryBoardRepository.setUpPiece(king1, point1);
        memoryBoardRepository.setUpPiece(king2, point2);

        assertThat(memoryBoardRepository.findTeamByPoint(point1)).isEqualTo("Black");
        assertThat(memoryBoardRepository.findTeamByPoint(point2)).isEqualTo("White");
    }

    @Test
    void isInBoard() {
        Point point1 = new Point("a8");
        Point point2 = new Point("I2");
        Point point3 = new Point("K0");
        Point point4 = new Point("g9");

        assertThat(memoryBoardRepository.isInBoard(point1)).isTrue();
        assertThat(memoryBoardRepository.isInBoard(point2)).isFalse();
        assertThat(memoryBoardRepository.isInBoard(point3)).isFalse();
        assertThat(memoryBoardRepository.isInBoard(point4)).isFalse();
    }

    @Test
    void canMovePoint() {

    }

    @Test
    void isChecked() {
    }

    @Test
    void isGetOutOfCheck() {
    }

    @Test
    void isCheckmate() {
    }
}