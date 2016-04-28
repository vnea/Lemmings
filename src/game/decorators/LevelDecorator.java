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
    public int getHExit() {
        return delegate.getHExit();
    }

    @Override
    public int getWExit() {
        return delegate.getWExit();
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
    public void goPlay() {
        delegate.goPlay();
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
    public void defEntrance(int h, int w) {
        delegate.defEntrance(h, w);
    }

    @Override
    public void defExit(int h, int w) {
        delegate.defExit(h, w);
    }
}
