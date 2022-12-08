package mystudy.chess.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mystudy.chess.game.GameSetting;
import mystudy.chess.repository.BoardRepository;
import mystudy.chess.view.View;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "/game";
    }
}
