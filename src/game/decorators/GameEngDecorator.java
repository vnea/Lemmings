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
        return delegate.getScore();
    }

    @Override
    public int getTurn() {
        return delegate.getTurn();
    }

    @Override
    public boolean isAnObstacle(int h, int w) {
        return delegate.isAnObstacle(h, w);
    }

    @Override
    public int getSizeColony() {
        return delegate.getSizeColony();
    }

    @Override
    public int getNbLemmingsDead() {
        return delegate.getNbLemmingsDead();
    }

    @Override
    public int getNbLemmingsSaved() {
        return delegate.getNbLemmingsSaved();
    }

    @Override
    public int getNbLemmingsActive() {
        return delegate.getNbLemmingsActive();
    }

    @Override
    public int getNbLemmingsCreated() {
        return delegate.getNbLemmingsCreated();
    }

    @Override
    public LemmingService getLemming(int num) {
        return delegate.getLemming(num);
    }

    @Override
    public boolean isActive(int num) {
        return delegate.isActive(num);
    }

    @Override
    public List<Integer> getNumLemmingsActive() {
        return delegate.getNumLemmingsActive();
    }

    @Override
    public int getSpawnSpeed() {
        return delegate.getSpawnSpeed();
    }

    @Override
    public boolean isGameOver() {
        return delegate.isGameOver();
    }

    @Override
    public LevelService getLevel() {
        return delegate.getLevel();
    }

    @Override
    public void init(int sizeC, int spawnS) {
        delegate.init(sizeC, spawnS);
    }

    @Override
    public void callStepLemmings() {
        delegate.callStepLemmings();
    }
    
    @Override
    public void newLemming(int num) {
        delegate.newLemming(num);
    }

    @Override
    public void checkSaved() {
        delegate.checkSaved();
    }

    @Override
    public void checkDead() {
        delegate.checkDead();        
    }

    @Override
    public void checkWin() {
        delegate.checkWin();
    }
}
