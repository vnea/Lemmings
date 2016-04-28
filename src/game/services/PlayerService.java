package game.services;

import game.enums.TokenType;

public interface PlayerService {
    //**OBSERVATOR(S)********************************************************//
    
    /** Number of WALKER tokens (needed when reseting the game) */
    public int getNbTokenWalkerInit();
    
    /** Number of WALKER tokens */
    public int getNbTokenWalker();
    
    /** Current slected token */
    public TokenType getTokenSelected();
    
    /** Game engine */
    public GameEngService getGameEngine();
    
    //***********************************************************************//
    
    
    //**INVARIANT(S)*********************************************************//
    
    /** \inv: getNbTokenWalker() ≥ 0 */
    
    //***********************************************************************//

    
    //**INIT*****************************************************************//
    
    /** Initialisation
     * \pre: nbTokenWalker > 0
     * \post: getNbTokenWalker() == nbTokenWalker
     * \post: getNbTokenWalkerInit() == nbTokenWalker
     * \post: getTokenSelected() == TokenType::WALKER
     */
    public void init(int nbTokenWalker);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//
    
    /** Use a token
     * \pre: if getTokenSelected() == TokenType::WALKER then
     *           getGameEngine().getLemming(numLemming).getBehaivour() != Behaviour::WALKER ^
     *           getNbTokenWalker() > 0
     * \post: if getTokenSelected() == TokenType::WALKER then
     *           getNbTokenWalker() == getNbTokenWalker()@pre - 1 ^
     *           getGameEngine().getLemming(numLemming).getBehaivour() == Behaviour::WALKER
     */
    public void useToken(int numLemming);
    
    /** Reset the game
     * \post: getGameEngine() == getGameEngine()@pre.init(getGameEngine()@pre.getSizeColony(), getGameEngine()@pre.getSpawnSpeed()) <----- TO CHANGE
     * \post: getNbTokenWalker() = getNbTokenWalkerInit()
     * \post: getTokenSelected() = TokenType::WALKER
     */
    public void resetGame();
    
    /** Select a token
     * \post: getTokenSelected() == tokenType
     */
    public void selectToken(TokenType tokenType);
    
    //***********************************************************************//
}
