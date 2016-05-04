package game.components;

import game.enums.Nature;
import game.services.LevelService;

public class Level implements
    /* provide */
    LevelService {
    private int height;
    private int width;
    
    private boolean editing;
    private Nature[][] squares;
    
    private int hEntrance;
    private int wEntrance;
    
    public Level() {
    }
    
    public Level(LevelService level) {
    	height = level.getHeight();
    	width = level.getWidth();
    	editing = level.isEditing();
    	
    	squares = new Nature[height][width];
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                squares[h][w] = level.getNature(h, w);
            }
        }
        
        hEntrance = level.getHEntrance();
        wEntrance = level.getWEntrance();
    }
    
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
    public boolean isAnObstacle(int h, int w) {
        return squares[h][w] == Nature.DIRT || squares[h][w] == Nature.METAL;
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
    public void remove(int h, int w) {
        squares[h][w] = Nature.EMPTY;        
    }

    @Override
    public void build(int h, int w) {
        squares[h][w] = Nature.DIRT;        
    }
    
    @Override
    public void goPlay(int h1, int w1, int h2, int w2) {
        defEntrace(h1, w1);
        squares[h2][w2] = Nature.EXIT;
        editing = false;
    }
    
    private void defEntrace(int h, int w) {
        hEntrance = h;
        wEntrance = w;
        squares[h][w] = Nature.ENTRANCE;
    }
    
    public static boolean areEqual(LevelService level, LevelService other) {
        // Check height
        if (level.getHeight() != other.getHeight()) {
            return false;
        }
        
        // Check width
        if (level.getWidth() != other.getWidth()) {
            return false;
        }
        
        // Check editing
        if (level.isEditing() != other.isEditing()) {
            return false;
        }
        
        // Check natures
        for (int h = 0; h < level.getWidth(); ++h) {
            for (int w = 0; w < level.getHeight(); ++w) {
                if (level.getNature(h, w) != other.getNature(h, w)) {
                    return false;
                }
            }
        }
        
        // Check entrance
        if (level.getHEntrance() != other.getHEntrance()) {
            return false;
        }
        
        // Check exit
        if (level.getWEntrance() != other.getWEntrance()) {
            return false;
        }
        
        return true;
    }
}
