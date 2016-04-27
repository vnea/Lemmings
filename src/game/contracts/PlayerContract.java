package game.contracts;

import game.decorators.PlayerDecorator;
import game.services.PlayerService;

public class PlayerContract extends PlayerDecorator {
    public PlayerContract(PlayerService delegate) {
        super(delegate);
    }
}
