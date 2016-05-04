package game.services;

import game.enums.TokenType;

public interface PlayerService extends RequireGameEngService {
    //**OBSERVATOR(S)********************************************************//
    
    /** Number of init tokens (needed when reseting the game) */
    public int getNbTokenInit(TokenType tokenType);
    
    /** Number of tokens */
    public int getNbToken(TokenType tokenType);
    
    /** Current slected token */
    public TokenType getTokenSelected();
    
    /** Game engine */
    public GameEngService getGameEngine();
    
    //***********************************************************************//
    
    
    //**INVARIANT(S)*********************************************************//
    
    /** \inv: ∀ X ∈ TokenType, getNbToken(X) ≥ 0 */
    
    //***********************************************************************//

    
    //**INIT*****************************************************************//
    
    /** Initialisation
     * \pre: nbTW ≥ 0, nbDI ≥ 0, nbBU ≥ 0, nbST ≥ 0, nbBA ≥ 0, nbB ≥ 0, nbCL ≥ 0, nbFL ≥ 0, nbBO > ≥ 0
     * \post: getNbToken(TokenType::WALKER) == nbTW
     * \post: getNbTokenInit(TokenType::WALKER) == nbTW
     * \post: getNbToken(TokenType::DIGGER) == nbDI
     * \post: getNbTokenInit(TokenType::DIGGER) == nbDI
     * \post: getNbToken(TokenType::BUILDER) == nbBU
     * \post: getNbTokenInit(TokenType::BUILDER) == nbBU
     * \post: getNbToken(TokenType::STOPPER) == nbST
     * \post: getNbTokenInit(TokenType::STOPPER) == nbST
     * \post: getNbToken(TokenType::BASHER) == nbBA
     * \post: getNbTokenInit(TokenType::BASHER) == nbBA
     * \post: getNbToken(TokenType::BASIC) == nbB
     * \post: getNbTokenInit(TokenType::BASIC) == nbB
     * \post: getNbToken(TokenType::CLIMBER) == nbCL
     * \post: getNbTokenInit(TokenType::CLIMBER) == nbCL
     * \post: getNbToken(TokenType::FLOATER) == nbFL
     * \post: getNbTokenInit(TokenType::FLOATER) == nbFL
     * \post: getNbToken(TokenType::BOMBER) == nbBO
     * \post: getNbTokenInit(TokenType::BOMBER) == nbBO
     * \post: getTokenSelected() == TokenType::WALKER
     */
    public void init(int nbTW, int nbDI, int nbBU, int nbST, int nbBA, int nbB,
                     int nbCL, int nbFL, int nbBO);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//
    
    /** Use a token
     * \pre: getNbToken(getTokenSelected()) > 0
     * \post: if getTokenSelected() == X, X ∈ {WALKER, FALLER, DIGGER, BUILDER, STOPPER, BASHER} then:
     *           getNbToken(X) == getNbToken(X)@pre - 1 ^ getGameEngine().getLemming(numLemming).getBehaviour() == X
     *        else getTokenSelected() == X, X ∈  {BASIC, CLIMBER, FLOATER, BOMBER} then:
     *           getNbToken(X) == getNbToken(X)@pre - 1 ^ getGameEngine().getLemming(numLemming).getState() == X
     */
    public void useToken(int numLemming);
    
    /** Reset the game
     * \post: getGameEngine() == getGameEngine()@pre.init(getGameEngine()@pre.getSizeColony(), getGameEngine()@pre.getSpawnSpeed(), getGameEngine()@pre.getLevelInit())
     * \post: \forall X \in TokenType, getNbToken(X) == getNbTokenInit(X)@pre()
     * \post: \forall X \in TokenType, getNbTokenInit(X) == getNbTokenInit(X)@pre()
     * \post: getTokenSelected() == TokenType::WALKER
     */
    public void resetGame();
    
    /** Select a token
     * \post: getTokenSelected() == tokenType
     */
    public void selectToken(TokenType tokenType);
    
    //***********************************************************************//
}
