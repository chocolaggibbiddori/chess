package mystudy.chess.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mystudy.chess.game.GameSetting;
import mystudy.chess.piece.Piece;
import mystudy.chess.point.Point;
import mystudy.chess.repository.BoardRepository;
import mystudy.chess.view.View;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final BoardRepository boardRepository;
    private final View view;
    private final GameSetting gameSetting;

    private Point point = new Point();
    private Piece piece;

    @GetMapping
    public String gameStart(Model model) {
        gameSetting.gameInit();
        String board = view.drawBoard();
        model.addAttribute("board", board);
        log.info("Game Start");
        return "/select";
    }

    @PostMapping("/select")
    public String select(@RequestParam String strPiece, Model model) {
        point.setPoint(strPiece);
        piece = boardRepository.findByPoint(point);
        log.info("Select Point, Piece : {}, {}", point, piece);
        List<Point> canMovePoints = boardRepository.canMovePoint(piece, piece.getTeamName());
        String board = view.drawBoard(canMovePoints);
        model.addAttribute("board", board);
        return "/move";
    }

    @PostMapping("/next")
    public String move(@RequestParam String strPoint, Model model) {
        point.setPoint(strPoint);
        List<Point> canMovePoints = boardRepository.canMovePoint(piece, piece.getTeamName());
        boardRepository.replace(piece, point);
        String board = view.drawBoard();
        model.addAttribute("board", board);
        return "redirect:/game";
    }
}
