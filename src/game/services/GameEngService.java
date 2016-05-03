package game.services;

import java.util.List;

public interface GameEngService {
    //**OBSERVATOR(S)********************************************************//
    
    /** Score of the game
     * \pre: isGameOver()
     * */
    public int getScore();
    
    /** Turn of the game */
    public int getTurn();
    
    /** Size of the colony */
    public int getSizeColony();
    
    /** Number of dead Lemmings */
    public int getNbLemmingsDead();
    
    /** Number of saved Lemmings */
    public int getNbLemmingsSaved();
    
    /** Number of active Lemmings */
    public int getNbLemmingsActive();
    
    /** Number of created Lemmings */
    public int getNbLemmingsCreated();
    
    /** Get a Lemming
     * \pre: isActive(num)
     */
    public LemmingService getLemming(int num);
    
    /** Check if a Lemming is active */
    public boolean isActive(int num);
    
    /** Get the nums of the active Lemmings */
    public List<Integer> getNumLemmingsActive();
    
    /** Spawn speed of the Lemmings */
    public int getSpawnSpeed();
    
    /** Tells if the game is over */
    public boolean isGameOver();
    
    /** Level of the game */
    public LevelService getLevel();
    
    //***********************************************************************//

    
    //**INVARIANT(S)*********************************************************//
    
    /** \inv: isAnObstacle(h, w) = 
     *         getLevel().getNature(h, w) == Nature::DIRT v 
     *           getLevel().getNature(h, w) == Nature::METAL (minimisation)
     */
    
    /** \inv: isActive(num) = num \in getNumLemmingsActive() (minimisation) */
    
    /** \inv: isGameOver() = getNbLemmingsSaved() + getNbLemmingsDead() ==
     *                       getSizeColony() (minimisation)
     */
    
    /** \inv: getNbLemmingsActive() = card(getNumLemmingsActive())
     *        (minimisation)
     */
    
    /** \inv: getNbLemmingsCreated() = getNbLemmingsDead() +
     *           getNbLemmingsSaved() + getNbLemmingsActive() (minimisation)
     */
    
    /** \inv: getSizeColony() ≥ getNbLemmingsCreated() */
    
    /** \inv: getNbLemmingsActive() ≥ 0 */
    
    /** \inv: getNbLemmingsDead()≥ 0 */
    
    /** \inv: getNbLemmingsSaved() ≥ 0 */
    
    /** \inv: getNbLemmingsCreated() ≥ 0 */
    
    //***********************************************************************//

    
    //**INIT*****************************************************************//

    /** Initialisation
     * \pre: sizeC > 0
     * \pre: spawnS > 0
     * \post: getTurn() == 0
     * \post: getSizeColony() == sizeC
     * \post: getNbLemmingsSaved() == 0
     * \post: card(getNumLemmingsActive()) == 0
     * \post: getSpawnSpeed() == spawnS
     */
    public void init(int sizeC, int spawnS);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//

    /** Execute the turn
     * \pre: !isGameOver()
     * \post: getTurn() == getTurn()@pre + 1
     * \post: \forall num \in getNumLemmingsActive()@pre,
     *              if Level::getNature(Lemming::getHPos(num), Lemming::getWPos(num)) == Nature::EXIT then:
     *                         {num} ∉  getNumLemmingsActive() ^ getNbLemmingsSaved()++
     * \post: \forall num \in getNumLemmingsActive()@pre,
     *              if Lemming::isDead() then:
     *                  {num} ∉  getNumLemmingsActive() ^ getNbLemmingsDead()++
     * \post: if getNbLemmingsCreated()@pre * getSpawnSpeed()@pre == getTurn()@pre ^ getNbLemmingsCreated()@pre < getSizeColony() then:
     *             getLemming(getNbLemmingsCreated()@pre) == Lemming::init(getNbLemmingsCreated(), Level::getHEntrance(), Level::getWEntrance(), getLevel())
     * \post if isGameOver() then:
     *          getScore() = (getNbLemmingsSaved() / getTurn()) * 100                
     */
    public void executeTurn();
    
//    /** Call step operator for each Lemming
//     * \pre: !isGameOver()
//     * \post: getTurn() == getTurn()@pre + 1    
//     */
//    public void callStepLemmings();
//    
//    /** Create a new Lemming
//     * \pre: !isActive(num)
//     * \pre: getNbLemmingsCreated() < getSizeColony()
//     * \pre: getNbLemmingsCreated() * getSpawnSpeed() == getTurn()
//     * \pre: !isGameOver()
//     * \post: getNumLemmingsActive() = getNumLemmingsActive()@pre U {num}
//     * \post: getLemming(num) == Lemming::init(num, h, w)
//     * \post: \forall n \in getNumLemmingsActive@pre(),
//     *         getLemming(n) == getLemming(n)@pre
//     */
//    public void newLemming(int num);
//    
//    /**
//     * \pre: !isGameOver()
//     * \post: \forall num \in getNumLemmingsActive()@pre(),
//     *           if getNature(getLemming(num)@pre().getHPos(), getLemming(num)@pre().getWPos()) == Nature::EXIT then:
//     *                getNumLemmingsActive() == getNumLemmingsActive()@pre() \ {num}
//     * \post: getNbLemmingSaved() == getNbLemmingsCreated() -
//     *                              getNbLemmingsActive() + getNbLemmingsDead()    
//     */
//    public void checkSaved();
//    
//    /**
//     * \pre: !isGameOver()
//     * \post: \forall num \in getNumLemmingsActive()@pre(),
//     *          if getLemming(num)@pre().isDead() then
//     *                getNumLemmingsActive() = getNumLemmingsActive()@pre \ {num}
//     * \post: getNbLemmingsDead() == getNbLemmingsCreated() -
//     *                             getNbLemmingsActive() + getNbLemmingsSaved()
//     */
//    public void checkDead();
//    
//    /**
//     * \pre: !isGameOver()
//     * \post: if isGameOver() then
//     *          getScore() == (getNbLemmingsSaved() / getTurn()) * 100
//     */
//    public void checkWin();
    
    //***********************************************************************//
}
