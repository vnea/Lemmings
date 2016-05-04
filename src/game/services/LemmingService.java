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
     * \post: if getBehaviour()@pre == Behaviour::WALKER then
     *          if getDirection()@pre == Direction::RIGHT then
     *              if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getLevel()@pre.isAnObstacle(getHPos()@pre - 1, getWPos()@pre + 1) then
     *                  getDirection() == Direction::LEFT
     *              else if getLevel().isAnObstacle(getHPos()@pre, getWPos()@pre + 1) then
     *                  if getLevel()@pre.isAnObstacle(getHPos()@pre - 2, getWPos()@pre + 1) then
     *                      getDirection() == Direction::LEFT
     *                  else
     *                      getHPos() == getHPos()@pre - 1 ^
     *                      getWPos() == getWPos()@pre + 1
     *             else
     *                  getWPos() == getWPos()@pre + 1
     *          else
     *              if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getLevel()@pre.isAnObstacle(getHPos()@pre - 1, getWPos()@pre - 1) then
     *                  getDirection() == Direction::RIGHT
     *              else if getLevel()@pre.isAnObstacle(getHPos()@pre, getWPos()@pre - 1) then
     *                  if getLevel()@pre.isAnObstacle(getHPos()@pre - 2, getWPos()@pre - 1) then
     *                     getDirection() == Direction::LEFT
     *                  else: 
     *                      getHPos() == getHPos()@pre - 1 ^
     *                      getWPos() == getWPos()@pre - 1
     *              else:
     *                  getWPos() == getWPos()@pre - 1
     *        else if getBehaviour()@pre == Behaviour::FALLER then
     *              if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
     *                  getHPos() == getHPos()@pre + 1 ^
     *                  getCounterFaller() == getCounterFaller()@pre + 1
     *              else
     *                  if getCounterFaller()@pre > MAX_COUNTER_FALLER_BEFORE_DEATH
     *                      isDead()
     *                  else
     *                      getBehaviour() == Behaviour::WALKER ^
     *                      getCounterFaller() == 0
     *        else if getBehaviour()@pre == Behaviour::DIGGER then
     *              if !getLevel()@pre.isAnObstacle(getHPos()@pre + 1, getWPos()@pre) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getLevel()@pre.getNature(getHPos()@pre + 1, getWPos()@pre) == Nature.METAL then
     *                  getBehaviour() == Behaviour::WALKER
     *              else if getLevel()@pre.getNature(getHPos()@pre + 1, getWPos()@pre) == Nature.DIRT then
     *                  getLevel().getNature(getHPos()@pre + 1, getWPos()@pre) == Nature.EMPTY
     *                  
     *                  if getLevel()@pre.getNature(getHPos()@pre + 1, getWPos()@pre - 1) == Nature.DIRT then
     *                      getLevel().getNature(getHPos()@pre + 1, getWPos()@pre - 1) == Nature.EMPTY
     *                  if getLevel()@pre.getNature(getHPos()@pre + 1, getWPos()@pre + 1) == Nature.DIRT then
     *                      getLevel().getNature(getHPos()@pre + 1, getWPos()@pre + 1) == Nature.EMPTY
     *                  getHPos() = getHPos()@pre + 1              
     */
    public void step();
    
    //***********************************************************************//
}
