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
        return delegate.getNbTokenWalker();
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
    public void init(int nbTokenWalker) {
        delegate.init(nbTokenWalker);
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
    public int getNbTokenWalkerInit() {
        return delegate.getNbTokenWalker();
    }
}
