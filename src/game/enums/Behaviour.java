package game.enums;

public enum Behaviour {
    WALKER(0),
    DIGGER(1),
    BUILDER(2),
    STOPPER(3),
    BASHER(4),
    FALLER(5);

    private final int value;
    
    private Behaviour(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
