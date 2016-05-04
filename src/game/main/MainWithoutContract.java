package game.main;

import game.components.GameEng;
import game.components.Level;
import game.components.Player;
import game.components.display.Display;

public class MainWithoutContract {
    public static void main(String[] args) throws InterruptedException {
        // Composant(s)
        GameEng gameEngine = new GameEng();
        Level level = new Level();
        Player player = new Player();
        
        @SuppressWarnings("unused")
        Display display = new Display(gameEngine, level, player);
    }
}
