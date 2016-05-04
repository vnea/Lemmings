package game.services;

import java.util.List;

public interface GameEngService extends RequireLevelService {
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
    
    /** Level init for reset game */
    public LevelService getLevelInit();
    
    //***********************************************************************//

    
    //**INVARIANT(S)*********************************************************//
    
    /** \inv: \forall num \in getNumLemmingsActive(), isActive(num) (minimisation) */
    
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
     * \post: getTurn() == 0
     * \post: getSizeColony() == sizeC
     * \post: getNbLemmingsSaved() == 0
     * \post: getNbLemmingsDead() == 0
     * \post: card(getNumLemmingsActive()) == 0
     * \post: getSpawnSpeed() == spawnS
     * \post: getLevelInit() == getLevel() (copy)
     */
    public void init(int sizeC, int spawnS);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//

    /** Execute the turn
     * \pre: !isGameOver()
     * \post: getTurn() == getTurn()@pre + 1
     * \post: \forall num \in getNumLemmingsActive()@pre,
     *              if getLevel().getNature(getLemming(num).getHPos(), getLemming(num).getWPos()) == Nature::EXIT then:
     *                  num \not \in getNumLemmingsActive() ^ getNbLemmingsSaved()++
     * \post: \forall num \in getNumLemmingsActive()@pre,
     *              if getLemming(num).isDead() then:
     *                  num \not \in getNumLemmingsActive() ^ getNbLemmingsDead()++
     * \post: if getNbLemmingsCreated()@pre * getSpawnSpeed()@pre == getTurn()@pre ^ getNbLemmingsCreated()@pre < getSizeColony() then:
     *             getLemming(getNbLemmingsCreated()@pre) == Lemming::init(getNbLemmingsCreated(), getLevel().getHEntrance(), getLevel().getWEntrance())
     * \post if isGameOver() then:
     *          getScore() == (getNbLemmingsSaved() / getTurn()@pre) * 100
     */
    public void executeTurn();
    
    //***********************************************************************//
}
