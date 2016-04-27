package game.contracts;

import game.decorators.LevelDecorator;
import game.services.LevelService;

public class LevelContract extends LevelDecorator {
    public LevelContract(LevelService delegate) {
        super(delegate);
    }
}
