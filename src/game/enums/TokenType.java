package game.enums;

public enum TokenType {
    /* Behaviour: positive value */
    WALKER(0),
    DIGGER(1),
    BUILDER(2),
    STOPPER(3),
    BASHER(4),
    
    /* State: negative value */
    BASIC(-1),
    CLIMBER(-2),
    FLOATER(-3),
    BOMBER(-4);
    
    private final int value;
    
    private TokenType(int value) {
        this.value = value;
    }
    
    public boolean isABehaviour() {
        return value >= 0;
    }
}
