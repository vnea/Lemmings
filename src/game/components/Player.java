package game.components;

import game.enums.TokenType;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.PlayerService;
import game.services.RequireGameEngService;

import java.util.HashMap;
import java.util.Map;

public class Player implements
    /* require */
    RequireGameEngService,

    /* provide */
    PlayerService {
    private Map<TokenType, Integer> mapNbToken = new HashMap<>();
    private Map<TokenType, Integer> mapNbInitToken = new HashMap<>();
    
    private TokenType tokenSelected;
    
    private GameEngService gameEngine;

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
    public void init(int nbTW, int nbFA, int nbDI, int nbBU, int nbST, int nbBA,
                     int nbB, int nbCL, int nbFL, int nbBO) {
        // Walker
        mapNbToken.put(TokenType.WALKER, nbTW);
        mapNbInitToken.put(TokenType.WALKER, nbTW);
        
       // Faller
        mapNbToken.put(TokenType.FALLER, nbFA);
        mapNbInitToken.put(TokenType.FALLER, nbFA);
        
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
        // Behvaiour case
        if (tokenSelected.isABehaviour()) {
            // change behaviour lemming
            
        }
        // State case
        else {
            // change state lemming
        }
    }

    @Override
    public void resetGame() {
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
