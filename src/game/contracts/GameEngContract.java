package game.contracts;

import game.decorators.GameEngDecorator;
import game.services.GameEngService;

public class GameEngContract extends GameEngDecorator {
    public GameEngContract(GameEngService delegate) {
        super(delegate);
    }
}
