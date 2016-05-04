package game.contracts;

import game.components.Level;
import game.decorators.LemmingDecorator;
import game.enums.Behaviour;
import game.enums.Direction;
import game.enums.State;
import game.services.LemmingService;
import game.services.LevelService;

public class LemmingContract extends LemmingDecorator {
    public LemmingContract(LemmingService delegate) {
        super(delegate);
    }
    
    private void checkInvariant() {
        // \inv: !getLevel().isAnObstacle(getHPos() - 1, getWPos())
        if (getLevel().isAnObstacle(getHPos() - 1, getWPos())) {
            throw new InvariantError("getLevel().isAnObstacle(getHPos() - 1, getWPos())");
        }

        // \inv: getCounterFaller() > -1
        if (!(getCounterFaller() > -1)) {
            throw new InvariantError("!(getCounterFaller() > -1)");
        }
    }
    
    @Override
    public void init(int num, int h, int w) {
        /* Pre-condition(s) */
        // \pre: num ≥ 0 ^ h ≥ MIN_H_POS ^ w ≥ MIN_W_POS
        if (!(num >= 0 && h >= MIN_H_POS && w >= MIN_W_POS)) {
            throw new PreconditionError("!(num ≥ 0 ^ h ≥ MIN_H_POS ^ w ≥ MIN_W_POS)");
        }
        
        /* Initialisation */
        super.init(num, h, w);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getNum() == num
        if (!(getNum() == num)) {
            throw new PostconditionError("!(getNum() == num)");
        }
        
        // \post: getHPos() == h
        if (!(getHPos() == h)) {
            throw new PostconditionError("!(getHPos() == h)");
        }
        
        // \post: getWPos() == w
        if (!(getWPos() == w)) {
            throw new PostconditionError("!(getWPos() == w)");
        }
        
        // \post: getDirection() == Direction::RIGHT
        if (!(getDirection() == Direction.RIGHT)) {
            throw new PostconditionError("!(getDirection() == Direction::RIGHT)");
        }
        
        // \post: getBehaviour() == Behaviour::FALLER
        if (!(getBehaviour() == Behaviour.FALLER)) {
            throw new PostconditionError("!(getBehaviour() == Behaviour::FALLER)");
        }
        
        // \post: !isDead()
        if (isDead()) {
            throw new PostconditionError("!(isDead())");
        }
        
        // \post: getCounterFaller() == 0
        if (!(getCounterFaller() == 0)) {
            throw new PostconditionError("!(getCounterFaller() == 0)");
        }
    }
    
    @Override
    public void setBehaviour(Behaviour b) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Prcessing */
        super.setBehaviour(b);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getBehaviour() == b
        if (!(getBehaviour() == b)) {
            throw new PostconditionError("!(getBehaviour() == b)");
        }
    }
    
    @Override
    public void setState(State s) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Processing */
        super.setState(s);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getState() == s
        if (!(getState() == s)) {
            throw new PostconditionError("!(getState() == s)");
        }
    }
    
    @Override
    public void step() {
        /* Pre-invariant */
        checkInvariant();
        
        /* Capture(s) */
        final int hPos_atPre = getHPos();
        final int wPos_atPre = getWPos();
        final int counterFaller_atPre = getCounterFaller();
        final Behaviour behaviour_atPre = getBehaviour();
        final Direction direction_atPre = getDirection();
        final LevelService level_atPre = new Level(getLevel());
        
        /* Processing */
        super.step();
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post:
        
        /* Walker */
        // if getBehaviour()@pre == Behaviour::WALKER then
        //      if getDirection()@pre == Direction::RIGHT then
        //          if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
        //              getBehaviour() == Behaviour::FALLER
        //          else if getLevel()@pre.isAnObstacle(getHPos()@pre - 1, getWPos()@pre + 1) then
        //              getDirection() == Direction::LEFT
        //          else if getLevel()@pre.isAnObstacle(getHPos()@pre, getWPos()@pre + 1) then
        //              if getLevel()@pre.isAnObstacle(getHPos()@pre - 2, getWPos()@pre + 1) then
        //                  getDirection() == Direction::LEFT
        //              else
        //                  getHPos() == getHPos()@pre - 1 ^
        //                  getWPos() == getWPos()@pre + 1
        //         else
        //              getWPos() == getWPos()@pre + 1
        //      else
        //          if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
        //              getBehaviour() == Behaviour::FALLER
        //          else if getLevel()@pre.isAnObstacle(getHPos()@pre - 1, getWPos()@pre - 1) then
        //              getDirection() == Direction::RIGHT
        //          else if getLevel()@pre.isAnObstacle(getHPos()@pre, getWPos()@pre - 1) then
        //              if getLevel()@pre.isAnObstacle(getHPos()@pre - 2, getWPos()@pre - 1) then
        //                 getDirection() == Direction::LEFT
        //              else: 
        //                  getHPos() == getHPos()@pre - 1 ^
        //                  getWPos() == getWPos()@pre - 1
        //          else:
        //              getWPos() == getWPos()@pre - 1
        if (behaviour_atPre == Behaviour.WALKER) {
            if (direction_atPre == Direction.RIGHT) {
                if (!level_atPre.isAnObstacle(hPos_atPre + 1, wPos_atPre)) {
                    if (!(getBehaviour() == Behaviour.FALLER)) {
                        throw new PostconditionError("!(getBehaviour() == Behaviour::FALLER)");
                    }
                }
                else if (level_atPre.isAnObstacle(hPos_atPre - 1, wPos_atPre + 1)) {
                    if (!(getDirection() == Direction.LEFT)) {
                        throw new PostconditionError("!(getDirection() == Direction::LEFT)");
                    }
                }
                else if (level_atPre.isAnObstacle(hPos_atPre, wPos_atPre + 1)) {
                    if (level_atPre.isAnObstacle(hPos_atPre - 2,  wPos_atPre + 1)) {
                        if (!(getDirection() == Direction.LEFT)) {
                            throw new PostconditionError("!(getDirection() == Direction::LEFT)");
                        }
                    }
                    else {
                        if (!(getHPos() == hPos_atPre - 1 && getWPos() == wPos_atPre + 1)) {
                            throw new PostconditionError("!(getHPos() == getHPos()@pre - 1 ^ getWPos() == getWPos()@pre + 1)");
                        }
                    }
                }
                else {
                    if (!(getWPos() == wPos_atPre + 1)) {
                        throw new PostconditionError("!(getWPos() == getWPos()@pre + 1)");
                    }
                }
            }
            else {
                if (!level_atPre.isAnObstacle(hPos_atPre + 1, wPos_atPre)) {
                    if (!(getBehaviour() == Behaviour.FALLER)) {
                        throw new PostconditionError("!(getBehaviour() == Behaviour::FALLER)");
                    }
                }
                else if (level_atPre.isAnObstacle(hPos_atPre- 1, wPos_atPre - 1)) {
                    if (!(getDirection() == Direction.RIGHT)) {
                        throw new PostconditionError("!(getDirection() == Direction::RIGHT)");
                    }
                }
                else if (level_atPre.isAnObstacle(hPos_atPre, wPos_atPre - 1)) {
                    if (level_atPre.isAnObstacle(hPos_atPre - 2, wPos_atPre - 1)) {
                        if (!(getDirection() == Direction.LEFT)) {
                            throw new PostconditionError("!(getDirection() == Direction::LEFT)");
                        }
                    }
                    else {
                        if (!(getHPos() == hPos_atPre - 1 && getWPos() == wPos_atPre - 1)) {
                            throw new PostconditionError("!(getHPos() == getHPos()@pre - 1 ^ getWPos() == getWPos()@pre - 1)");
                        }
                    }
                }
                else {
                    if (!(getWPos() == wPos_atPre - 1)) {
                        throw new PostconditionError("!(getWPos() == getWPos()@pre - 1)");
                    }
                }
            }
        }
        
        /* Faller */
        //    else if getBehaviour()@pre == Behaviour::FALLER then
        //          if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
        //              getHPos() == getHPos()@pre + 1 ^
        //              getCounterFaller() == getCounterFaller()@pre + 1
        //          else
        //              if getCounterFaller()@pre > MAX_COUNTER_FALLER_BEFORE_DEATH
        //                  isDead()
        //              else
        //                  getBehaviour() == Behaviour::WALKER ^
        //                  getCounterFaller() == 0
        else if (behaviour_atPre == Behaviour.FALLER) {
            if (!level_atPre.isAnObstacle(hPos_atPre + 1, wPos_atPre)) {
                if (!(getHPos() == hPos_atPre + 1 && getCounterFaller() == counterFaller_atPre + 1)) {
                    throw new PostconditionError("!(getHPos() == getHPos()@pre + 1 ^ getCounterFaller() == getCounterFaller()@pre + 1)");
                }
            }
            else {
                if (counterFaller_atPre > MAX_COUNTER_FALLER_BEFORE_DEATH) {
                    if (!isDead()) {
                        throw new PostconditionError("!isDead()");
                    }
                }
                else {
                    if (!(getBehaviour() == Behaviour.WALKER && getCounterFaller() == 0)) {
                        throw new PostconditionError("!(getBehaviour() == Behaviour::WALKER ^ getCounterFaller() == 0)");
                    }
                }
            }
        }
        
        /* Digger */
        //    else if getBehaviour()@pre == Behaviour::DIGGER then
        //          if !getLevel().isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
        //              getBehaviour() == Behaviour::FALLER
        //          else if getLevel().getNature(getHPos()@pre + 1, getWPos()@pre) == Nature.METAL then
        //              getBehaviour() == Behaviour::WALKER
        //          else if getLevel().getNature(getHPos()@pre + 1, getWPos()@pre) == Nature.DIRT then
        //              getLevel().getNature(getHPos() + 1, getWPos()) == Nature.EMPTY
        //              
        //              if getLevel().getNature(getHPos()@pre + 1, getWPos()@pre - 1) == Nature.DIRT then
        //                  getLevel().getNature(getHPos()@pre + 1, getWPos()@pre - 1) == Nature.EMPTY
        //              if getLevel().getNature(getHPos()@pre + 1, getWPos()@pre + 1) == Nature.DIRT then
        //                  getLevel().getNature(getHPos()@pre + 1, getWPos()@pre + 1) == Nature.EMPTY
        //              getHPos() = getHPos()@pre + 1
//        else if (behaviour_atPre == Behaviour.DIGGER) {
//            if (!level_atPre.isAnObstacle(hPos_atPre + 1, wPos_atPre)) {
//                if (!(getBehaviour() == Behaviour.FALLER)) {
//                    throw new PostconditionError("!(getBehaviour() == Behaviour::FALLER)");
//                }
//            }
//            else if (level_atPre.getNature(hPos_atPre + 1, wPos_atPre) == Nature.METAL){
//                if (!(getBehaviour() == Behaviour.WALKER)) {
//                    throw new PostconditionError("!(getBehaviour() == Behaviour::WALKER)");
//                }
//            }
//            else if (level_atPre.getNature(hPos_atPre + 1, wPos_atPre) == Nature.DIRT) {
//                if (!(level_atPre.getNature(getHPos() + 1, getWPos()) == Nature.EMPTY)) {
//                    throw new PostconditionError("!(getLevel().getNature(getHPos() + 1, getWPos()) == Nature.EMPTY)");
//                }
//                
//                if (level_atPre.getNature(hPos_atPre + 1, wPos_atPre - 1)  == Nature.DIRT) {
//                    if (!(level_atPre.getNature(getHPos() + 1, getWPos() - 1) == Nature.EMPTY)) {
//                        throw new PostconditionError("!(getLevel().getNature(getHPos() + 1, getWPos() - 1) == Nature.EMPTY)");
//                    }
//                }
//                if (level_atPre.getNature(hPos_atPre + 1, wPos_atPre + 1)  == Nature.DIRT) {
//                    if (!(level_atPre.getNature(getHPos() + 1, getWPos() + 1) == Nature.EMPTY)) {
//                        throw new PostconditionError("!(getLevel().getNature(getHPos() + 1, getWPos() + 1) == Nature.EMPTY)");
//                    }
//                }
//                
//                if (!(getHPos() == hPos_atPre + 1)) {
//                    throw new PostconditionError("!(getHPos() == getHPos()@pre + 1)");
//                }
//            }
//        }
    }
}
