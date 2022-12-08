package mystudy.chess.view;

import lombok.RequiredArgsConstructor;
import mystudy.chess.piece.Piece;
import mystudy.chess.repository.BoardRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class View {

    private final BoardRepository boardRepository;

    private StringBuilder sb = new StringBuilder();
    private String[] rowNumber = {"8", "7", "6", "5", "4", "3", "2", "1"};
    private String[] colAlphabet = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private int rowNumberIdx = 0;

    public String drawBoard() {
        sb.append("<br>\n");
        drawAlphabet();
        for (int i = 0; i < 4; i++) {
            drawRowLine();
            drawColLineFirstWhite();
            drawColLineWithPieceFirstWhite();
            drawColLineFirstWhite();
            drawRowLine();
            drawColLineFirstBlack();
            drawColLineWithPieceFirstBlack();
            drawColLineFirstBlack();
        }
        drawRowLine();
        drawAlphabet();
        return sb.toString();
    }

    private void drawAlphabet() {
        sb.append(" ".repeat(12));
        for (String alphabet : colAlphabet) {
            sb.append(alphabet).append(" ".repeat(9));
        }
        sb.append("<br>\n");
    }

    private void drawRowLine() {
        sb.append(" ".repeat(7));
        sb.append(" ---------".repeat(8));
        sb.append("<br>\n");
    }

    private void drawColLineFirstWhite() {
        String col = "|" + " ".repeat(9) + "|---------";
        sb.append(" ".repeat(7));
        sb.append(col.repeat(4));
        sb.append("|");
        sb.append("<br>\n");
    }

    private void drawColLineFirstBlack() {
        String col = "|---------|" + " ".repeat(9);
        sb.append(" ".repeat(7));
        sb.append(col.repeat(4));
        sb.append("|");
        sb.append("<br>\n");
    }

    private void drawColLineWithPieceFirstWhite() {
        sb.append("   ");
        sb.append(rowNumber[rowNumberIdx]);
        sb.append("   ");
        Piece piece;
        for (int i = 0; i < colAlphabet.length; i += 2) {
            sb.append("|");
            sb.append("   ");
            piece = boardRepository.findByPoint(rowNumberIdx, i);
            if (piece == null) {
                sb.append("   ");
            } else {
                sb.append(piece);
            }
            sb.append("   |---");
            piece = boardRepository.findByPoint(rowNumberIdx, i + 1);
            if (piece == null) {
                sb.append("   ");
            } else {
                sb.append(piece);
            }
            sb.append("---");
        }
        sb.append("|   ");
        sb.append(rowNumber[rowNumberIdx++]);
        sb.append("<br>\n");
    }

    private void drawColLineWithPieceFirstBlack() {
        sb.append("   ");
        sb.append(rowNumber[rowNumberIdx]);
        sb.append("   ");
        Piece piece;
        for (int i = 0; i < colAlphabet.length; i += 2) {
            sb.append("|---");
            piece = boardRepository.findByPoint(rowNumberIdx, i);
            if (piece == null) {
                sb.append("   ");
            } else {
                sb.append(piece);
            }
            sb.append("---|   ");
            piece = boardRepository.findByPoint(rowNumberIdx, i + 1);
            if (piece == null) {
                sb.append("   ");
            } else {
                sb.append(piece);
            }
            sb.append("   ");
        }
        sb.append("|   ");
        sb.append(rowNumber[rowNumberIdx++]);
        sb.append("<br>\n");
    }
}
//            A         B         C         D         E         F         G         H
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |         |---------|         |---------|         |---------|         |---------|
//   8   |   B R   |---B N---|   B B   |---B Q---|   B K   |---B B---|   B N   |---B R---|   8
//       |         |---------|         |---------|         |---------|         |---------|
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |---------|         |---------|         |---------|         |---------|         |
//   7   |---B P---|   B P   |---B P---|   B P   |---B P---|   B P   |---B P---|   B P   |   7
//       |---------|         |---------|         |---------|         |---------|         |
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |         |---------|         |---------|         |---------|         |---------|
//   6   |         |---------|         |---------|         |---------|         |---------|   6
//       |         |---------|         |---------|         |---------|         |---------|
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |---------|         |---------|         |---------|         |---------|         |
//   5   |---------|         |---------|         |---------|         |---------|         |   5
//       |---------|         |---------|         |---------|         |---------|         |
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |         |---------|         |---------|         |---------|         |---------|
//   4   |         |---------|         |---------|         |---------|         |---------|   4
//       |         |---------|         |---------|         |---------|         |---------|
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |---------|         |---------|         |---------|         |---------|         |
//   3   |---------|         |---------|         |---------|         |---------|         |   3
//       |---------|         |---------|         |---------|         |---------|         |
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |         |---------|         |---------|         |---------|         |---------|
//   2   |   W P   |---W P---|   W P   |---W P---|   W P   |---W P---|   W P   |---W P---|   2
//       |         |---------|         |---------|         |---------|         |---------|
//        --------- --------- --------- --------- --------- --------- --------- ---------
//       |---------|         |---------|         |---------|         |---------|         |
//   1   |---W R---|   W N   |---W B---|   W Q   |---W K---|   W B   |---W N---|   W R   |   1
//       |---------|         |---------|         |---------|         |---------|         |
//        --------- --------- --------- --------- --------- --------- --------- ---------
//            A         B         C         D         E         F         G         H
