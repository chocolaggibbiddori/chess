package mystudy.chess.view;

import mystudy.chess.piece.King;
import mystudy.chess.piece.Knight;
import mystudy.chess.point.Point;
import mystudy.chess.repository.BoardRepository;
import mystudy.chess.repository.MemoryBoardRepository;
import org.junit.jupiter.api.Test;

class ViewTest {

    BoardRepository boardRepository = new MemoryBoardRepository();
    View view = new View(boardRepository);

    @Test
    void drawBoard() {
        boardRepository.setUpPiece(new King(new Point(0, 4), "Black"), (new Point(0, 4)));
        boardRepository.setUpPiece(new King(new Point(7, 4), "White"), (new Point(7, 4)));
        boardRepository.setUpPiece(new Knight(new Point(7, 1), "White"), (new Point(7, 1)));
        System.out.println(view.drawBoard());
    }
}