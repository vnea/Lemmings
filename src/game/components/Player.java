package game.components;

import game.enums.Behaviour;
import game.enums.State;
import game.enums.TokenType;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.PlayerService;

import java.util.HashMap;
import java.util.Map;

public class Player implements
    /* require */
    /*RequireGameEngService,*/

    /* provide */
    PlayerService {
    private Map<TokenType, Integer> mapNbToken = new HashMap<>();
    private Map<TokenType, Integer> mapNbInitToken = new HashMap<>();
    
    private TokenType tokenSelected;
    
    private GameEngService gameEngine;

    private Map<TokenType, Behaviour> mapTokenTypeBehaviour = new HashMap<>();
    private Map<TokenType, State> mapTokenTypeState = new HashMap<>();
    
    public Player() {
        mapTokenTypeBehaviour.put(TokenType.WALKER, Behaviour.WALKER);
        mapTokenTypeBehaviour.put(TokenType.DIGGER, Behaviour.DIGGER);
        mapTokenTypeBehaviour.put(TokenType.BUILDER, Behaviour.BUILDER);
        mapTokenTypeBehaviour.put(TokenType.STOPPER, Behaviour.STOPPER);
        mapTokenTypeBehaviour.put(TokenType.BASHER, Behaviour.BASHER);

        mapTokenTypeState.put(TokenType.BASIC, State.BASIC);
        mapTokenTypeState.put(TokenType.CLIMBER, State.CLIMBER);
        mapTokenTypeState.put(TokenType.FLOATER, State.FLOATER);
        mapTokenTypeState.put(TokenType.BOMBER, State.BOMBER);
    }
    
    @Override
    public int getNbTokenInit(TokenType tokenType) {
        return mapNbInitToken.get(tokenType);
    }
    
    @Override
    public int getNbToken(TokenType tokenType) {
        return mapNbToken.get(tokenType);
    }

    @Override
    public TokenType getTokenSelected() {
        return tokenSelected;
    }

    @Override
    public GameEngService getGameEngine() {
        return gameEngine;
    }

    @Override
    public void init(int nbTW, int nbDI, int nbBU, int nbST, int nbBA, int nbB,
                     int nbCL, int nbFL, int nbBO) {
        // Walker
        mapNbToken.put(TokenType.WALKER, nbTW);
        mapNbInitToken.put(TokenType.WALKER, nbTW);
        
        // Digger
        mapNbToken.put(TokenType.DIGGER, nbDI);
        mapNbInitToken.put(TokenType.DIGGER, nbDI);
        
        // Builder
        mapNbToken.put(TokenType.BUILDER, nbBU);
        mapNbInitToken.put(TokenType.BUILDER, nbBU);
        
        // Stopper
        mapNbToken.put(TokenType.STOPPER, nbST);
        mapNbInitToken.put(TokenType.STOPPER, nbST);
        
        // Basher
        mapNbToken.put(TokenType.BASHER, nbBA);
        mapNbInitToken.put(TokenType.BASHER, nbBA);
        
        // Basic
        mapNbToken.put(TokenType.BASIC, nbB);
        mapNbInitToken.put(TokenType.BASIC, nbB);
        
        // Climber
        mapNbToken.put(TokenType.CLIMBER, nbCL);
        mapNbInitToken.put(TokenType.CLIMBER, nbCL);
        
        // Floater
        mapNbToken.put(TokenType.FLOATER, nbFL);
        mapNbInitToken.put(TokenType.FLOATER, nbFL);
        
        // Bomber
        mapNbToken.put(TokenType.BOMBER, nbBO);
        mapNbInitToken.put(TokenType.BOMBER, nbBO);
       
        tokenSelected = TokenType.WALKER;
    }

    @Override
    public void useToken(int numLemming) {
        // Use a token
        mapNbToken.put(tokenSelected, mapNbToken.get(tokenSelected) - 1);

        LemmingService lemming = gameEngine.getLemming(numLemming);
        // Behaviour case
        if (tokenSelected.isABehaviour()) {
            lemming.setBehaviour(mapTokenTypeBehaviour.get(tokenSelected));
        }
        // State case
        else {
            lemming.setState(mapTokenTypeState.get(tokenSelected));
        }
    }

    @Override
    public void resetGame() {
    	gameEngine.bindLevelService(gameEngine.getLevelInit());
        gameEngine.init(gameEngine.getSizeColony(), gameEngine.getSpawnSpeed());
        mapNbInitToken.entrySet().forEach(entry ->
                              mapNbToken.put(entry.getKey(), entry.getValue()));
        tokenSelected = TokenType.WALKER;
    }

    @Override
    public void selectToken(TokenType tokenType) {
        tokenSelected = tokenType;
    }

    @Override
    public void bindGameEngService(GameEngService service) {
        gameEngine = service;
    }
}
