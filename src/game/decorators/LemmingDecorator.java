package game.decorators;

import game.enums.Behaviour;
import game.enums.Direction;
import game.enums.State;
import game.services.LemmingService;
import game.services.LevelService;

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
    public State getState() {
        return delegate.getState();
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
    public int getCounterFaller() {
        return delegate.getCounterFaller();
    }

    @Override
    public boolean isDead() {
        return delegate.isDead();
    }

    @Override
    public LevelService getLevel() {
        return delegate.getLevel();
    }

    @Override
    public void init(int num, int h, int w) {
        delegate.init(num, h, w);
    }

    @Override
    public void setBehaviour(Behaviour b) {
        delegate.setBehaviour(b);
    }
    
    @Override
    public void setState(State s) {
        delegate.setState(s);
    }
    
    @Override
    public void step() {
        delegate.step();
    }

    @Override
    public void bindLevelService(LevelService service) {
        delegate.bindLevelService(service);
    }
}
