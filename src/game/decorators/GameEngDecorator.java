package game.decorators;

import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;

import java.util.List;

public class GameEngDecorator implements GameEngService {
    private final GameEngService delegate;
    
    public GameEngDecorator(GameEngService delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTurn() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isAnObstacle(int h, int w) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getSizeColony() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNbLemmingsDead() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNbLemmingsSaved() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNbLemmingsActive() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNbLemmingsCreated() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public LemmingService getLemming(int num) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isActive(int num) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Integer> getNumLemmingsActive() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSpawnSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public LevelService getLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(int sizeC, int spawnS) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newLemming(int num, int h, int w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void callStepLemmings() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkSaved() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkDead() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkWin() {
        // TODO Auto-generated method stub
        
    }
}
