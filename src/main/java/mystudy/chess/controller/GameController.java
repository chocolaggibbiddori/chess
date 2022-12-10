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

@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final BoardRepository boardRepository;
    private final View view;
    private final GameSetting gameSetting;

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
        Point point = new Point(strPiece);
        Piece piece = boardRepository.findByPoint(point);
        log.info("Select Point, Piece : {}, {}", point, piece);
        String board = view.drawBoard(piece.moveList());
        model.addAttribute("board", board);
        return "/move";
    }
}
