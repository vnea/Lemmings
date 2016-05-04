package game.main;

import game.components.GameEng;
import game.components.Level;
import game.components.Player;
import game.components.display.Display;
import game.contracts.GameEngContract;
import game.contracts.LevelContract;
import game.contracts.PlayerContract;

public class MainWithContract {
    public static void main(String[] args) throws InterruptedException {
        // Composant(s)
        GameEngContract gameEngineContract = new GameEngContract(new GameEng());
        LevelContract levelContract = new LevelContract(new Level());
        PlayerContract playerContract = new PlayerContract(new Player());
        
        @SuppressWarnings("unused")
        Display display = new Display(gameEngineContract, levelContract,
                                      playerContract);
    }
}
