package game.main;

import game.components.GameEng;
import game.components.Level;
import game.components.Player;
import game.components.display.Display;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Composant(s)
        GameEng gameEngine = new GameEng();
        Level level = new Level();
        Player player = new Player();
        
        // Initialisation(s)
        gameEngine.init(10, 1);
        level.init(10, 10);
        player.init(5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
        
        // Binding(s)
        gameEngine.bindLevelService(level);
        player.bindGameEngService(gameEngine);
        
        Display display = new Display(gameEngine);

        
    }
}
