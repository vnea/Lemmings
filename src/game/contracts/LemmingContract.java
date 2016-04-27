package game.contracts;

import game.decorators.LemmingDecorator;
import game.services.LemmingService;

public class LemmingContract extends LemmingDecorator {
    public LemmingContract(LemmingService delegate) {
        super(delegate);
    }
}
