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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEditing() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Nature getNature(int h, int w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean squareExist(int h, int w) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getHEntrance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWEntrance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getHExit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWExit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void init(int h, int w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNature(int h, int w, Nature n) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void goPlay() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(int h, int w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void build(int h, int w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void defEntrance(int h, int w) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void defExit(int h, int w) {
        // TODO Auto-generated method stub
        
    }
}
