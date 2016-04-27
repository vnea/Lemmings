package game.components;

import game.enums.Nature;
import game.services.LevelService;

public class Level implements LevelService {
    private int height;
    private int width;
    
    private boolean editing;
    private Nature[][] squares;
    
    private int hEntrance;
    private int wEntrance;
    
    private int hExit;
    private int wExit;
    
    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean isEditing() {
        return editing;
    }

    @Override
    public Nature getNature(int h, int w) {
        return squares[h][w];
    }

    @Override
    public boolean squareExist(int h, int w) {
        return 0 <= h && h < height && 0 <= w && w < width;
    }

    @Override
    public int getHEntrance() {
        return hEntrance;
    }

    @Override
    public int getWEntrance() {
        return wEntrance;
    }

    @Override
    public int getHExit() {
        return hExit;
    }

    @Override
    public int getWExit() {
        return wExit;
    }

    @Override
    public void init(int h, int w) {
        height = h;
        width = w;
        editing = true;
        
        squares = new Nature[height][width];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                squares[y][x] = Nature.EMPTY;
            }
        }
    }

    @Override
    public void setNature(int h, int w, Nature n) {
        squares[h][w] = n;        
    }

    @Override
    public void goPlay() {
        editing = false;
    }

    @Override
    public void remove(int h, int w) {
        squares[h][w] = Nature.EMPTY;        
    }

    @Override
    public void build(int h, int w) {
        squares[h][w] = Nature.DIRT;        
    }

    @Override
    public void defEntrance(int h, int w) {
        hEntrance = h;
        wEntrance = w;
    }

    @Override
    public void defExit(int h, int w) {
        hExit = h;
        wExit = w;
    }
}
