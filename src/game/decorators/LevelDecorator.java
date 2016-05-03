package game.decorators;

import game.enums.Nature;
import game.services.LevelService;

public class LevelDecorator implements LevelService {
    private final LevelService delegate;
    
    public LevelDecorator(LevelService delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getHeight() {
        return delegate.getHeight();
    }

    @Override
    public int getWidth() {
        return delegate.getWidth();
    }

    @Override
    public boolean isEditing() {
        return delegate.isEditing();
    }

    @Override
    public Nature getNature(int h, int w) {
        return delegate.getNature(h, w);
    }

    @Override
    public boolean isAnObstacle(int h, int w) {
        return delegate.isAnObstacle(h, w);
    }
    
    @Override
    public boolean squareExist(int h, int w) {
        return delegate.squareExist(h, w);
    }

    @Override
    public int getHEntrance() {
        return delegate.getHEntrance();
    }

    @Override
    public int getWEntrance() {
        return delegate.getWEntrance();
    }

    @Override
    public void init(int h, int w) {
        delegate.init(h, w);
    }

    @Override
    public void setNature(int h, int w, Nature n) {
        delegate.setNature(h, w, n);
    }

    @Override
    public void remove(int h, int w) {
        delegate.remove(h, w);
    }

    @Override
    public void build(int h, int w) {
        delegate.build(h, w);
    }
    
    @Override
    public void goPlay(int h1, int w1, int h2, int w2) {
        delegate.goPlay(h1, w1, h2, w2);
    }
}
