package game.enums;

public enum State {
    BASIC(-1),
    CLIMBER(-2),
    FLOATER(-3),
    BOMBER(-4);
    
    private final int value;
    
    private State(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
