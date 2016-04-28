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
        return delegate.getNum();
    }

    @Override
    public Direction getDirection() {
        return delegate.getDirection();
    }

    @Override
    public Behaviour getBehaviour() {
        return delegate.getBehaviour();
    }

    @Override
    public int getHPos() {
        return delegate.getHPos();
    }

    @Override
    public int getWPos() {
        return delegate.getWPos();
    }

    @Override
    public boolean isDead() {
        return delegate.isDead();
    }

    @Override
    public GameEngService getGameEngine() {
        return delegate.getGameEngine();
    }

    @Override
    public void init(int num, int h, int w) {
        delegate.init(num, h, w);
    }

    @Override
    public void step() {
        delegate.step();
    }
}
