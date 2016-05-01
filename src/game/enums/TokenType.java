package game.enums;

public enum TokenType {
    /* Behaviour: positive value */
    WALKER(0),
    FALLER(1),
    DIGGER(2),
    BUILDER(3),
    STOPPER(4),
    BASHER(5),
    
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
        return value < 0;
    }
}
