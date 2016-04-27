package game.decorators;

import game.enums.Behaviour;
import game.enums.Direction;
import game.services.GameEngService;
import game.services.LemmingService;

public class LemmingDecorator implements LemmingService {
    private final LemmingService delegate;
    
    public LemmingDecorator(LemmingService delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getNum() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Direction getDirection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Behaviour getBehaivour() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getHPos() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWPos() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isDead() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public GameEngService getGameEngine() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(int num, int h, int w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
        
    }
}
