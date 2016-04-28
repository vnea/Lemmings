package game.components;

import game.enums.Behaviour;
import game.enums.Direction;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.RequireGameEngService;

public class Lemming implements
    /* require */
    RequireGameEngService,

    /* provide */
    LemmingService {
    private int num;
    private Direction direction;
    private Behaviour behaviour;
    
    private int hPos;
    private int wPos;
    private boolean dead;
    
    private GameEngService gameEngine;
    
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
    public int getHPos() {
        return hPos;
    }

    @Override
    public int getWPos() {
        return wPos;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public GameEngService getGameEngine() {
        return gameEngine;
    }

    @Override
    public void init(int num, int h, int w) {
        this.num = num;
        hPos = h;
        wPos = w;
        direction = Direction.RIGHT;
        behaviour = Behaviour.FALLER;
        dead = false;
    }

    @Override
    public void step() {
        switch (behaviour) {
            case WALKER:
                if (direction == Direction.RIGHT) {
                    if (!gameEngine.isAnObstacle(hPos + 1, wPos)) {
                        behaviour = Behaviour.FALLER;
                    }
                    else if (gameEngine.isAnObstacle(hPos - 1, wPos + 1)) {
                        direction = Direction.LEFT;
                    }
                    else if (!gameEngine.isAnObstacle(hPos, wPos + 1)) {
                        if (gameEngine.isAnObstacle(hPos - 2,  wPos + 1)) {
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
                    if (!gameEngine.isAnObstacle(hPos + 1, wPos)) {
                        behaviour = Behaviour.FALLER;
                    }
                    else if (gameEngine.isAnObstacle(hPos - 1, wPos - 1)) {
                        direction = Direction.RIGHT;
                    }
                    else if (gameEngine.isAnObstacle(hPos, wPos - 1)) {
                        if (gameEngine.isAnObstacle(hPos - 2, wPos - 1)) {
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
            break;
            
            case FALLER:
                if (gameEngine.isAnObstacle(hPos + 1, wPos)) {
                    ++hPos;
                }
                else {
                    for (int i = 0; i < 8 && !dead; ++i) {
                        dead = gameEngine.isAnObstacle(hPos - i, wPos);
                    }
                    
                    if (!dead) {
                        behaviour = Behaviour.WALKER;
                    }
                }
            break;
        }
    }

    @Override
    public void bindGameEngService(GameEngService service) {
        gameEngine = service;
    }
}
