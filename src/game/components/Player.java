package game.components;

import game.enums.TokenType;
import game.services.GameEngService;
import game.services.PlayerService;
import game.services.RequireGameEngService;

public class Player implements
    /* require */
    RequireGameEngService,

    /* provide */
    PlayerService {
    private int nbTokenWalkerInit;
    private int nbTokenWalker;
    
    private TokenType tokenSelected;
    
    private GameEngService gameEngine;
    
    @Override
    public int getNbTokenWalker() {
        return nbTokenWalker;
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
    public void init(int nbTokenWalker) {
        this.nbTokenWalker = nbTokenWalker;
        nbTokenWalkerInit = nbTokenWalker;
        tokenSelected = TokenType.WALKER;
    }

    @Override
    public void useToken(int numLemming) {
        switch (tokenSelected) {
            case WALKER:
                --nbTokenWalker;
                //<---------------------------------- TO COMPLETE
                // change behaviour lemming
            break;
        }
    }

    @Override
    public void resetGame() {
        //<---------------------------------- TO COMPLETE
        // gameEngine reset/init
        gameEngine.init(gameEngine.getSizeColony(), gameEngine.getSpawnSpeed());
        nbTokenWalker = nbTokenWalkerInit;
        tokenSelected = TokenType.WALKER;
    }

    @Override
    public void selectToken(TokenType tokenType) {
        tokenSelected = tokenType;
    }

    @Override
    public int getNbTokenWalkerInit() {
        return nbTokenWalkerInit;
    }

    @Override
    public void bindGameEngService(GameEngService service) {
        gameEngine = service;
    }
}
