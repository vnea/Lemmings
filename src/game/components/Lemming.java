package game.components;

import game.enums.Behaviour;
import game.enums.Direction;
import game.enums.Nature;
import game.enums.State;
import game.services.LemmingService;
import game.services.LevelService;

import java.lang.reflect.InvocationTargetException;

public class Lemming implements
    /* require */
    /*RequireLevelService,*/

    /* provide */
    LemmingService {
    private int num;
    private Direction direction;
    private Behaviour behaviour;
    private State state;
    
    private int hPos;
    private int wPos;
    private int counterFaller;
    private boolean dead;
    
    private LevelService level;
    
    @Override
    public int getNum() {
        return num;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Behaviour getBehaviour() {
        return behaviour;
    }
    
    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getHPos() {
        return hPos;
    }

    @Override
    public int getWPos() {
        return wPos;
    }

    @Override
    public int getCounterFaller() {
        return counterFaller;
    }
    
    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public LevelService getLevel() {
        return level;
    }

    @Override
    public void init(int num, int h, int w) {
        this.num = num;
        hPos = h;
        wPos = w;
        direction = Direction.RIGHT;
        behaviour = Behaviour.FALLER;
        dead = false;
        resetCounterFaller();
    }

    @Override
    public void setBehaviour(Behaviour b) {
        behaviour = b;
    }
    
    @Override
    public void setState(State s) {
        state = s;
    }
    
    @Override
    public void step() {
        try {
            // Call method stepXXX(), where XXX = WALKER, FALLER, ...
            getClass().getDeclaredMethod("step" + behaviour).invoke(this);
        }
        catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindLevelService(LevelService service) {
        level = service;
    }
    
    @SuppressWarnings("unused")
    private void stepWALKER() {
        if (direction == Direction.RIGHT) {
            if (!level.isAnObstacle(hPos + 1, wPos)) {
                behaviour = Behaviour.FALLER;
            }
            else if (level.isAnObstacle(hPos - 1, wPos + 1)) {
                direction = Direction.LEFT;
            }
            else if (level.isAnObstacle(hPos, wPos + 1)) {
                if (level.isAnObstacle(hPos - 2,  wPos + 1)) {
                    direction = Direction.LEFT;
                }
                else {
                    --hPos;
                    ++wPos;
                }
            }
            else {
                ++wPos;
            }
        }
        else {
            if (!level.isAnObstacle(hPos + 1, wPos)) {
                behaviour = Behaviour.FALLER;
            }
            else if (level.isAnObstacle(hPos - 1, wPos - 1)) {
                direction = Direction.RIGHT;
            }
            else if (level.isAnObstacle(hPos, wPos - 1)) {
                if (level.isAnObstacle(hPos - 2, wPos - 1)) {
                    direction = Direction.LEFT;
                }
                else {
                    --hPos;
                    --wPos;
                }
            }
            else {
                --wPos;
            }
        }
    }
    
    @SuppressWarnings("unused")
    private void stepFALLER() {
        if (!level.isAnObstacle(hPos + 1, wPos)) {
            ++hPos;
            ++counterFaller;
        }
        else {
            if (counterFaller > MAX_COUNTER_FALLER_BEFORE_DEATH) {
                dead = true;
            }
            else {
                behaviour = Behaviour.WALKER;
                resetCounterFaller();
            }
        }
    }
    
    @SuppressWarnings("unused")
    private void stepDIGGER() {
        final int POS_BELOW = hPos + 1;
        if (!level.isAnObstacle(POS_BELOW, wPos)) {
            behaviour = Behaviour.FALLER;
        }
        else if (level.getNature(POS_BELOW, wPos) == Nature.METAL){
            behaviour = Behaviour.WALKER;
        }
        else if (level.getNature(POS_BELOW, wPos) == Nature.DIRT) {
            level.remove(POS_BELOW, wPos);
            
            if (level.getNature(POS_BELOW, wPos - 1)  == Nature.DIRT) {
                level.remove(POS_BELOW, wPos - 1);
            }
            if (level.getNature(POS_BELOW, wPos + 1)  == Nature.DIRT) {
                level.remove(POS_BELOW, wPos + 1);
            }
            ++hPos;
        }
    }
    
    @SuppressWarnings("unused")
    private void stepBUILDER() {
    }
    
    @SuppressWarnings("unused")
    private void setSTOPPER() {
    }
    
    @SuppressWarnings("unused")
    private void setBASHER() {
    }
    
    private void resetCounterFaller() {
        counterFaller = 0;
    }
}
