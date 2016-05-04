package game.services;

import game.enums.Behaviour;
import game.enums.Direction;
import game.enums.State;

public interface LemmingService extends RequireLevelService {
    //**CONSTANT(S)**********************************************************//
    
    /** Minimum vertical position of the Lemming */
    final static int MIN_H_POS = 2;
    
    /** Minimum horitontal position of the Lemming */
    final static int MIN_W_POS = 1;
    
    /** Maximum counter of a faller before he dies */
    final static int MAX_COUNTER_FALLER_BEFORE_DEATH = 8;
    
    //***********************************************************************//

    
    //**OBSERVATOR(S)********************************************************//
    
    /** Number of the Lemming */
    public int getNum();

    /** Direction of the Lemming */
    public Direction getDirection();
    
    /** Behaviour of the Lemming */
    public Behaviour getBehaviour();
    
    /** State of the Lemming */
    public State getState();
    
    /** Vertical position of the Lemming */
    public int getHPos();
    
    /** Horitontal position of the Lemming */
    public int getWPos();
    
    /** Counter of a faller */
    public int getCounterFaller();
    
    /** Tells is the Lemming is dead */
    public boolean isDead();
    
    /** Level */
    public LevelService getLevel();
    
    //***********************************************************************//

    
    //**INVARIANT(S)*********************************************************//
    
    /** \inv: !getLevel().isAnObstacle(getHPos() - 1, getWPos()) */
    
    /** \inv: getCounterFaller() > -1 */
    
    //***********************************************************************//

    
    //**INIT*****************************************************************//
    
    /** Initialisation
     * \pre: num ≥ 0 ^ h ≥ MIN_H_POS ^ w ≥ MIN_W_POS
     * \post: getNum() == num
     * \post: getHPos() == h
     * \post: getWPos() == w
     * \post: getDirection() == Direction::RIGHT
     * \post: getBehaviour() == Behaviour::FALLER
     * \post: !isDead()
     * \post: getCounterFaller() == 0
     */
    public void init(int num, int h, int w);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//
    
    /** Set a new behaviour
     * \post: getBehaviour() == b
     */
    public void setBehaviour(Behaviour b);
    
    /** Set a new state
     * \post: getState() == s
     */
    public void setState(State s);
    
    /** Run Lemming action
     * \post: if getBehaviour() == Behaviour::WALKER then
     *          if getDirection() == Direction::RIGHT then
     *              if !getLevel().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getLevel().isAnObstacle(getHPos() - 1, getWPos() + 1) then
     *                  getDirection() == Direction::LEFT
     *              else if getLevel().isAnObstacle(getHPos(), getWPos() + 1) then
     *                  if getLevel().isAnObstacle(getHPos() - 2, getWPos() + 1) then
     *                      getDirection() == Direction::LEFT
     *                  else
     *                      getHPos() == getHPos()@pre - 1 ^
     *                      getWPos() == getWPos()@pre + 1
     *             else
     *                  getWPos() == getWPos()@pre + 1
     *          else
     *              if !getLevel().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getLevel().isAnObstacle(getHPos() - 1, getWPos() - 1) then
     *                  getDirection() == Direction::RIGHT
     *              else if getLevel().isAnObstacle(getHPos(), getWPos() - 1) then
     *                  if getLevel().isAnObstacle(getHPos() - 2, getWPos() - 1) then
     *                     getDirection() == Direction::LEFT
     *                  else: 
     *                      getHPos() == getHPos()@pre - 1 ^
     *                      getWPos() == getWPos()@pre - 1
     *              else:
     *                  getWPos() == getWPos()@pre - 1
     *        else if getBehaviour() == Behaviour::FALLER then
     *              if !getLevel().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getHPos() == getHPos()@pre + 1
     *                  getCounterFaller() == getCounterFaller()@pre + 1
     *              else
     *                  if getCounterFaller() > MAX_COUNTER_FALLER_BEFORE_DEATH
     *                      isDead()
     *                  else
     *                      getBehaviour() == Behaviour::WALKER
     *                      getCounterFaller() == 0
     *        else if getBehaviour() == Behaviour::DIGGER then
     *              if !getLevel().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getLevel().getNature(getHPos() + 1, getWPos()) == Nature.METAL then
     *                  getBehaviour() == Behaviour::WALKER
     *              else if getLevel().getNature(getHPos() + 1, getWPos()) == Nature.DIRT then
     *                  getLevel().getNature(getHPos() + 1, getWPos()) == Nature.EMPTY
     *                  
     *                  if getLevel().getNature(getHPos() + 1, getWPos() - 1) == Nature.DIRT then
     *                      getLevel().getNature(getHPos() + 1, getWPos() - 1) == Nature.EMPTY
     *                  if getLevel().getNature(getHPos() + 1, getWPos() + 1) == Nature.DIRT then
     *                      getLevel().getNature(getHPos() + 1, getWPos() + 1) == Nature.EMPTY
     *                  getHPos() = getHPos()@pre + 1
     *                  
     */
    public void step();
    
    //***********************************************************************//
}
