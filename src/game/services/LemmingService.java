package game.services;

import game.enums.Behaviour;
import game.enums.Direction;

public interface LemmingService {
    //**CONSTANT(S)**********************************************************//
    
    /** Minimum vertical position of the Lemming */
    final static int MIN_H_POS = 19;
    
    /** Minimum horitontal position of the Lemming */
    final static int MIN_W_POS = 51;
    
    //***********************************************************************//

    
    //**OBSERVATOR(S)********************************************************//
    
    /** Number of the Lemming */
    public int getNum();

    /** Direction of the Lemming */
    public Direction getDirection();
    
    /** Behaviour of the Lemming */
    public Behaviour getBehaivour();
    
    /** Vertical position of the Lemming */
    public int getHPos();
    
    /** Horitontal position of the Lemming */
    public int getWPos();
    
    /** Tells is the Lemming is dead */
    public boolean isDead();
    
    /** Game engine */
    public GameEngService getGameEngine();
    
    //***********************************************************************//

    
    //**INVARIANT(S)*********************************************************//
    
    /** \inv: !getGameEngine().isAnObstacle(getHPos() - 1, getWPos())*/
    
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
     */
    public void init(int num, int h, int w);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//
    
    /** Run Lemming action
     * \post: if getBehaviour() == Behaviour::WALKER then
     *          if getDirection() == Direction::RIGHT then
     *              if !getGameEngine().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getGameEngine().isAnObstacle(getHPos() - 1, getWPos() + 1) then
     *                  getDirection() == Direction::LEFT
     *              else if getGameEngine().isAnObstacle(getHPos(), getWPos() + 1) then
     *                  if getGameEngine().isAnObstacle(getHPos() - 2, getWPos() + 1) then
     *                      getDirection() == Direction::LEFT
     *                  else
     *                      getHPos() == getHPos()@pre - 1 ^
     *                      getWPos() == getWPos()@pre + 1
     *             else
     *                  getWPos() == getWPos()@pre + 1
     *          else
     *              if !getGameEngine().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getBehaviour() == Behaviour::FALLER
     *              else if getGameEngine().isAnObstacle(getHPos() - 1, getWPos() - 1) then
     *                  getDirection() == Direction::RIGHT
     *              else if getGameEngine().isAnObstacle(getHPos(), getWPos() - 1) then
     *                  if getGameEngine().isAnObstacle(getHPos() - 2, getWPos() - 1) then
     *                     getDirection() == Direction::LEFT
     *                  else: 
     *                      getHPos() == getHPos()@pre - 1 ^
     *                      getWPos() == getWPos()@pre - 1
     *              else:
     *                  getWPos() == getWPos()@pre - 1
     *        else if getBehaviour() == Behaviour::FALLER then
     *              if getGameEngine().isAnObstacle(getHPos() + 1, getWPos()) then
     *                  getHPos() == getHPos()@pre + 1
     *              else
     *                  if \forall i \in ]0, 8[, getGameEngine().isAnObstacle(getHPos() - i, getWPos()) then <------------- TO CHANGE
     *                      isDead()
     *                  else
     *                      getBehaviour() == Behaviour::WALKER    
     */
    public void step();
    
    //***********************************************************************//
}
