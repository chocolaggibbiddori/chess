package mystudy.chess.game;

import mystudy.chess.repository.BoardRepository;
import mystudy.chess.repository.MemoryBoardRepository;
import mystudy.chess.view.View;
import org.junit.jupiter.api.Test;

class GameSettingTest {

    BoardRepository boardRepository = new MemoryBoardRepository();
    View view = new View(boardRepository);
    GameSetting gameSetting = new GameSetting(boardRepository);

    @Test
    void init() {
        gameSetting.gameInit();
        System.out.println(view.drawBoard());
    }
}