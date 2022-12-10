package mystudy.chess.view;

import lombok.RequiredArgsConstructor;
import mystudy.chess.piece.Piece;
import mystudy.chess.point.Point;
import mystudy.chess.repository.BoardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class View {

    private final BoardRepository boardRepository;

    private StringBuilder sb;
    private String[] rowNumber = {"8", "7", "6", "5", "4", "3", "2", "1"};
    private String[] colAlphabet = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private int rowNumberIdx;

    public String drawBoard() {
        sb = new StringBuilder();
        rowNumberIdx = 0;
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

    public String drawBoard(List<Point> moveList) {
        sb = new StringBuilder();
        rowNumberIdx = 0;
        sb.append("<br>\n");
        drawAlphabet();
        for (int i = 0; i < 4; i++) {
            drawRowLine();
            drawColLineFirstWhite();
            drawColLineWithPieceFirstWhite(moveList);
            drawColLineFirstWhite();
            drawRowLine();
            drawColLineFirstBlack();
            drawColLineWithPieceFirstBlack(moveList);
            drawColLineFirstBlack();
        }
        drawRowLine();
        drawAlphabet();
        return sb.toString();
    }

    private void drawAlphabet() {
        sb.append(" ".repeat(12));
        for (String alphabet : colAlphabet) {
            sb.append("<b>").append(alphabet).append("</b>").append(" ".repeat(9));
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
        sb.append("<b>").append(rowNumber[rowNumberIdx]).append("</b>");
        sb.append("   ");
        Piece piece;
        for (int i = 0; i < colAlphabet.length; i += 2) {
            sb.append("|");
            sb.append("   ");
            piece = boardRepository.findByPoint(rowNumberIdx, i);
            drawPiece(piece);
            sb.append("   |---");
            piece = boardRepository.findByPoint(rowNumberIdx, i + 1);
            drawPiece(piece);
            sb.append("---");
        }
        sb.append("|   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx++]).append("</b>");
        sb.append("<br>\n");
    }

    private void drawColLineWithPieceFirstWhite(List<Point> moveList) {
        sb.append("   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx]).append("</b>");
        sb.append("   ");
        Piece piece;
        for (int i = 0; i < colAlphabet.length; i += 2) {
            sb.append("|");
            sb.append("   ");
            piece = boardRepository.findByPoint(rowNumberIdx, i);
            drawPiece(piece, moveList, i);
            sb.append("   |---");
            piece = boardRepository.findByPoint(rowNumberIdx, i + 1);
            drawPiece(piece, moveList, i + 1);
            sb.append("---");
        }
        sb.append("|   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx++]).append("</b>");
        sb.append("<br>\n");
    }

    private void drawPiece(Piece piece) {
        if (piece == null) {
            sb.append("   ");
        } else {
            sb.append("<b>").append(piece).append("</b>");
        }
    }

    private void drawPiece(Piece piece, List<Point> moveList, int yIdx) {
        if (moveList.size() == 0) {
            drawPiece(piece);
            return;
        }

        Point point = new Point(rowNumberIdx, yIdx);
        if (piece == null) {
            if (moveList.contains(point)) {
                sb.append(" o ");
            }else {
                sb.append("   ");
            }
        } else {
            if (moveList.contains(point)) {
                sb.append("<b>")
                        .append(piece.toString().substring(0, 1))
                        .append("o")
                        .append(piece.toString().substring(2))
                        .append("</b>");
            } else {
                sb.append("<b>").append(piece).append("</b>");
            }
        }
    }

    private void drawColLineWithPieceFirstBlack() {
        sb.append("   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx]).append("</b>");
        sb.append("   ");
        Piece piece;
        for (int i = 0; i < colAlphabet.length; i += 2) {
            sb.append("|---");
            piece = boardRepository.findByPoint(rowNumberIdx, i);
            drawPiece(piece);
            sb.append("---|   ");
            piece = boardRepository.findByPoint(rowNumberIdx, i + 1);
            drawPiece(piece);
            sb.append("   ");
        }
        sb.append("|   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx++]).append("</b>");
        sb.append("<br>\n");
    }

    private void drawColLineWithPieceFirstBlack(List<Point> moveList) {
        sb.append("   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx]).append("</b>");
        sb.append("   ");
        Piece piece;
        for (int i = 0; i < colAlphabet.length; i += 2) {
            sb.append("|---");
            piece = boardRepository.findByPoint(rowNumberIdx, i);
            drawPiece(piece, moveList, i);
            sb.append("---|   ");
            piece = boardRepository.findByPoint(rowNumberIdx, i + 1);
            drawPiece(piece, moveList, i + 1);
            sb.append("   ");
        }
        sb.append("|   ");
        sb.append("<b>").append(rowNumber[rowNumberIdx++]).append("</b>");
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
