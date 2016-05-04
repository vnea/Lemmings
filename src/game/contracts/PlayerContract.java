package game.contracts;

import game.components.GameEng;
import game.decorators.PlayerDecorator;
import game.enums.TokenType;
import game.services.GameEngService;
import game.services.PlayerService;

import java.util.HashMap;
import java.util.Map;

public class PlayerContract extends PlayerDecorator {
    public PlayerContract(PlayerService delegate) {
        super(delegate);
    }
    
    private void checkInvariant() {
        // \inv: ∀ X ∈ TokenType, getNbToken(X) ≥ 0
        for (TokenType tokenType : TokenType.values()) {
            if (!(getNbToken(tokenType) >= 0)) {
                throw new InvariantError("!(getNbToken(X) ≥ 0)");
            }
        }
    }
    
    @Override
    public void init(int nbTW, int nbDI, int nbBU, int nbST, int nbBA, int nbB,
                     int nbCL, int nbFL, int nbBO) {
        /* Pre-condition(s) */
        // \pre: nbTW ≥ 0, nbDI ≥ 0, nbBU ≥ 0, nbST ≥ 0, nbBA ≥ 0, nbB ≥ 0, nbCL ≥ 0, nbFL ≥ 0, nbBO > ≥ 0
        if (!(nbTW >= 0 && nbDI >= 0 && nbBU >= 0 && nbST >= 0 && nbBA >= 0 &&
              nbB >= 0 && nbCL >= 0 && nbFL >= 0 && nbBO >= 0)) {
            throw new PreconditionError("!(nbTW ≥ 0, nbDI ≥ 0, nbBU ≥ 0, nbST ≥ 0, nbBA ≥ 0, nbB ≥ 0, nbCL ≥ 0, nbFL ≥ 0, nbBO > ≥ 0)");
        }
        
        /* Initialisation */
        super.init(nbTW, nbDI, nbBU, nbST, nbBA, nbB, nbCL, nbFL, nbBO);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getNbToken(TokenType::WALKER) == nbTW
        if (!(getNbToken(TokenType.WALKER) == nbTW)) {
            throw new PostconditionError("!(getNbToken(TokenType::WALKER) == nbTW)");
        }
        
        // \post: getNbTokenInit(TokenType::WALKER) == nbTW
        if (!(getNbTokenInit(TokenType.WALKER) == nbTW)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::WALKER) == nbTW)");
        }
        
        // \post: getNbToken(TokenType::DIGGER) == nbDI
        if (!(getNbToken(TokenType.DIGGER) == nbDI)) {
            throw new PostconditionError("!(getNbToken(TokenType::DIGGER) == nbDI)");
        }
        
        // \post: getNbTokenInit(TokenType::DIGGER) == nbDI
        if (!(getNbTokenInit(TokenType.DIGGER) == nbDI)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::DIGGER) == nbDI)");
        }
        
        // \post: getNbToken(TokenType::BUILDER) == nbBU
        if (!(getNbToken(TokenType.BUILDER) == nbBU)) {
            throw new PostconditionError("!(getNbToken(TokenType::BUILDER) == nbBU)");
        }
        
        // \post: getNbTokenInit(TokenType::BUILDER) == nbBU
        if (!(getNbTokenInit(TokenType.BUILDER) == nbBU)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::BUILDER) == nbBU)");
        }
        
        // \post: getNbToken(TokenType::STOPPER) == nbST
        if (!(getNbToken(TokenType.STOPPER) == nbST)) {
            throw new PostconditionError("!(getNbToken(TokenType::STOPPER) == nbST)");
        }
        
        // \post: getNbTokenInit(TokenType::STOPPER) == nbST
        if (!(getNbTokenInit(TokenType.STOPPER) == nbST)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::STOPPER) == nbST)");
        }
        
        // \post: getNbToken(TokenType::BASHER) == nbBA
        if (!(getNbToken(TokenType.BASHER) == nbBA)) {
            throw new PostconditionError("!(getNbToken(TokenType::BASHER) == nbBA)");
        }
        
        // \post: getNbTokenInit(TokenType::BASHER) == nbBA
        if (!(getNbTokenInit(TokenType.BASHER) == nbBA)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::BASHER) == nbBA)");
        }
        
        // \post: getNbToken(TokenType::BASIC) == nbB
        if (!(getNbToken(TokenType.BASIC) == nbB)) {
            throw new PostconditionError("!(getNbToken(TokenType::BASIC) == nbB)");
        }
        
        // \post: getNbTokenInit(TokenType::BASIC) == nbB
        if (!(getNbTokenInit(TokenType.BASIC) == nbB)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::BASIC) == nbB)");
        }
        
        // \post: getNbToken(TokenType::CLIMBER) == nbCL
        if (!(getNbToken(TokenType.CLIMBER) == nbCL)) {
            throw new PostconditionError("!(getNbToken(TokenType::CLIMBER) == nbCL)");
        }
        
        // \post: getNbTokenInit(TokenType::CLIMBER) == nbCL
        if (!(getNbTokenInit(TokenType.CLIMBER) == nbCL)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::CLIMBER) == nbCL)");
        }
        
        // \post: getNbToken(TokenType::FLOATER) == nbFL
        if (!(getNbToken(TokenType.FLOATER) == nbFL)) {
            throw new PostconditionError("!(getNbToken(TokenType::FLOATER) == nbFL)");
        }
        
        // \post: getNbTokenInit(TokenType::FLOATER) == nbFL
        if (!(getNbTokenInit(TokenType.FLOATER) == nbFL)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::FLOATER) == nbFL)");
        }
        
        // \post: getNbToken(TokenType::BOMBER) == nbBO
        if (!(getNbToken(TokenType.BOMBER) == nbBO)) {
            throw new PostconditionError("!(getNbToken(TokenType::BOMBER) == nbBO)");
        }
        
        // \post: getNbTokenInit(TokenType::BOMBER) == nbBO
        if (!(getNbTokenInit(TokenType.BOMBER) == nbBO)) {
            throw new PostconditionError("!(getNbTokenInit(TokenType::BOMBER) == nbBO)");
        }
        
        // \post: getTokenSelected() == TokenType::WALKER
        if (!(getTokenSelected() == TokenType.WALKER)) {
            throw new PostconditionError("!(getTokenSelected() == TokenType::WALKER)");
        }
    }
    
    @Override
    public void useToken(int numLemming) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: getNbToken(getTokenSelected()) > 0
        if (!(getNbToken(getTokenSelected()) > 0)) {
            throw new PreconditionError("!(getNbToken(getTokenSelected()) > 0)");
        }
        
        /* Capture(s) */
        final int tokenType_atPre = getNbToken(getTokenSelected());
        
        /* Processing */
        super.useToken(numLemming);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: if getTokenSelected() == X, X ∈ {WALKER, FALLER, DIGGER, BUILDER, STOPPER, BASHER} then:
        //           getNbToken(X) == getNbToken(X)@pre - 1 ^ getGameEngine().getLemming(numLemming).getBehaviour() == X
        //        else getTokenSelected() == X, X ∈  {BASIC, CLIMBER, FLOATER, BOMBER} then:
        //           getNbToken(X) == getNbToken(X)@pre - 1 ^ getGameEngine().getLemming(numLemming).getState() == X
        if (getTokenSelected().isABehaviour()) {
            if (!(getNbToken(getTokenSelected()) == tokenType_atPre - 1 && getTokenSelected().equalsBehaviour(getGameEngine().getLemming(numLemming).getBehaviour()))) {
                throw new PostconditionError("!(getNbToken(X) == getNbToken(X)@pre - 1 ^ getGameEngine().getLemming(numLemming).getBehaviour() == X)");
            }
        }
        else {
            if (!(getNbToken(getTokenSelected()) == tokenType_atPre - 1 && getTokenSelected().equalsState(getGameEngine().getLemming(numLemming).getState()))) {
                throw new PostconditionError("!(getNbToken(X) == getNbToken(X)@pre - 1 ^ getGameEngine().getLemming(numLemming).getState() == X)");
            }
        }
    }
    
    @Override
    public void resetGame() {
        /* Pre-invariant */
        checkInvariant();
        
        /* Capture(s) */
        final GameEngService gameEngine_atPre = getGameEngine();
        
        TokenType []tokenTypes = TokenType.values();
        final Map<TokenType, Integer> nbTokenInit_atPre = new HashMap<>(tokenTypes.length);
        for (TokenType tokenType : tokenTypes) {
            nbTokenInit_atPre.put(tokenType, getNbTokenInit(tokenType));
        }

        /* Processing */
        super.resetGame();
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getGameEngine() == getGameEngine()@pre.init(getGameEngine()@pre.getSizeColony(), getGameEngine()@pre.getSpawnSpeed(), getGameEngine()@pre.getLevelInit())
        if (!GameEng.areEqual(getGameEngine(), gameEngine_atPre)) {
            throw new PostconditionError("!(getGameEngine() == getGameEngine()@pre.init(getGameEngine()@pre.getSizeColony(), getGameEngine()@pre.getSpawnSpeed(), getGameEngine()@pre.getLevelInit()) )");
        }
        
        // \post: \forall X \in TokenType, getNbToken(X) == getNbTokenInit(X)@pre()
        for (TokenType tokenType : tokenTypes) {
            if (!(getNbToken(tokenType) == nbTokenInit_atPre.get(tokenType))) {
                throw new PostconditionError("!(getNbToken(X) == getNbTokenInit(X)@pre())");
            }
        }
        
        // \post: \forall X \in TokenType, getNbTokenInit(X) == getNbTokenInit(X)@pre()
        for (TokenType tokenType : tokenTypes) {
            if (!(getNbToken(tokenType) == nbTokenInit_atPre.get(tokenType))) {
                throw new PostconditionError("!(getNbTokenInit(X) == getNbTokenInit(X)@pre())");
            }
        }
    }
    
    @Override
    public void selectToken(TokenType tokenType) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Processing */
        super.selectToken(tokenType);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getTokenSelected() == tokenType
        if (!(getTokenSelected() == tokenType)) {
            throw new PostconditionError("!(getTokenSelected() == tokenType)");
        }
    }
}
