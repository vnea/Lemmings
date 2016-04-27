package game.decorators;

import game.enums.TokenType;
import game.services.GameEngService;
import game.services.PlayerService;

public class PlayerDecorator implements PlayerService {
    private final PlayerService delegate;
    
    public PlayerDecorator(PlayerService delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getNbTokenWalker() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public TokenType getTokenSelected() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameEngService getGameEngine() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(int nbTokenWalker) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void useToken(int numLemming) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resetGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void selectToken(TokenType tokenType) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getNbTokenWalkerInit() {
        // TODO Auto-generated method stub
        return 0;
    }
}
