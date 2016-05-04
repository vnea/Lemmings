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
    public int getNbTokenInit(TokenType tokenType) {
        return delegate.getNbTokenInit(tokenType);
    }
    
    @Override
    public int getNbToken(TokenType tokenType) {
        return delegate.getNbToken(tokenType);
    }

    @Override
    public TokenType getTokenSelected() {
        return delegate.getTokenSelected();
    }

    @Override
    public GameEngService getGameEngine() {
        return delegate.getGameEngine();
    }

    @Override
    public void init(int nbTW, int nbDI, int nbBU, int nbST, int nbBA, int nbB,
                     int nbCL, int nbFL, int nbBO) {
        delegate.init(nbTW, nbDI, nbBU, nbST, nbBA, nbB, nbCL, nbFL, nbBO);
    }

    @Override
    public void useToken(int numLemming) {
        delegate.useToken(numLemming);
    }

    @Override
    public void resetGame() {
        delegate.resetGame();
    }

    @Override
    public void selectToken(TokenType tokenType) {
        delegate.selectToken(tokenType);
    }

    @Override
    public void bindGameEngService(GameEngService service) {
        delegate.bindGameEngService(service);        
    }
}
