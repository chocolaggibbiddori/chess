package mystudy.chess.game;

import lombok.RequiredArgsConstructor;
import mystudy.chess.repository.BoardRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Turn {

    private static final List<Notation> notationList = new ArrayList<>();
    private static String currentTeam = "White";
    private static int turnCount = 1;

    private final BoardRepository boardRepository;
    private final Notation notation;

    public void turnCount() {
        turnCount++;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void changeCurrentTeam() {
        currentTeam = currentTeam.equals("White") ? "Black" : "White";
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    // TODO: 2022/12/06 기보 보여주기

}
