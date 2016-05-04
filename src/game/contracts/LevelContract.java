package game.contracts;

import game.decorators.LevelDecorator;
import game.enums.Nature;
import game.services.LevelService;

public class LevelContract extends LevelDecorator {
    public LevelContract(LevelService delegate) {
        super(delegate);
    }
    
    private void checkInvariant() {
        // \inv: \forall i \in [0, getHeight()[ ^ \forall j in [0, getWidth()[,
        //         squareExist(i, j)
        for (int h = 0; h < getHeight(); ++h) {
            for (int w = 0; w < getWidth(); ++w) {
                if (!squareExist(h, w)) {
                    throw new InvariantError("!squareExist(i, j)");
                }
            }
        }
        
        // \inv: \forall i \in [0 ≤ i < getHeight()[ ^ \forall j \in [0, getWidth()[,
        //       isAnObstacle(i, j) = (getNature(i, j) == Nature::DIRT or getNature(i, j) = Nature::METAL)
        //                            (minimisation)
        //
        for (int h = 0; h < getHeight(); ++h) {
            for (int w = 0; w < getWidth(); ++w) {
                if (!(isAnObstacle(h, w) == (getNature(h, w) == Nature.DIRT || getNature(h, w) == Nature.METAL))) {
                    throw new InvariantError("!(isAnObstacle(i, j) = (getNature(i, j) == Nature::DIRT or getNature(i, j) = Nature::METAL))");
                }
            }
        }
    }
    
    @Override
    public Nature getNature(int h, int w) {
        /* Pre-condition(s) */
        // \pre: squareExist(h, w)
        if (!squareExist(h, w)) {
            throw new PreconditionError("!squareExist(h, w)");
        }
        
        return super.getNature(h, w);
    }
    
    @Override
    public boolean isAnObstacle(int h, int w) {
        /* Pre-condition(s) */
        // \pre: squareExist(h, w)
        if (!squareExist(h, w)) {
            throw new PreconditionError("!squareExist(h, w)");
        }
        
        return super.isAnObstacle(h, w);
    }
    
    @Override
    public int getHEntrance() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getHEntrance();
    }
    
    @Override
    public int getWEntrance() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getWEntrance();
    }
    
    @Override
    public void init(int h, int w) {
        /* Pre-condition(s) */
        // \pre: MIN_HEIGHT <= h <= MAX_HEIGHT
        if (!(MIN_HEIGHT <= h && h <= MAX_HEIGHT)) {
            throw new PreconditionError("!(MIN_HEIGHT < h < MAX_HEIGHT)");
        }
        
        // \pre: MIN_WIDTH <= w <= MAX_WIDTH
        if (!(MIN_WIDTH <= w && w <= MAX_WIDTH)) {
            throw new PreconditionError("!(MIN_WIDTH < w < MAX_WIDTH)");
        }
        
        /* Initialisation */
        super.init(h, w);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getHeight() == h
        if (!(getHeight() == h)) {
            throw new PostconditionError("!(getHeight() == h)");
        }
        
        // \post: getWidth() == w
        if (!(getWidth() == w)) {
            throw new PostconditionError("!(getWidth() == w)");
        }
        
        // \post: !isEditing()
        if (!isEditing()) {
            throw new PostconditionError("isEditing()");
        }
    }
    
    @Override
    public void setNature(int h, int w, Nature n) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: isEditing()
        if (!isEditing()) {
            throw new PreconditionError("!isEditing()");
        }
        
        // \pre: squareExist(h, w)
        if (!squareExist(h, w)) {
            throw new PreconditionError("!squareExist(h, w)");
        }
        
        // \pre: n != Nature::ENTRANCE ^ n != Nature::EXIT
        if (!(n != Nature.ENTRANCE && n != Nature.EXIT)) {
            throw new PreconditionError("!(n != Nature::ENTRANCE ^ n != Nature::EXIT)");
        }
        
        /* Processing */
        super.setNature(h, w, n);
        
        /* Post-invariant */
        checkInvariant();
    }
    
    @Override
    public void remove(int h, int w) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        // \pre: getNature(h, w) == Nature::DIRT
        if (!(getNature(h, w) == Nature.DIRT)) {
            throw new PreconditionError("!(getNature(h, w) == Nature.DIRT)");
        }
        
        /* Processing */
        super.remove(h, w);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getNature(h, w) == Nature::EMPTY
        if (!(getNature(h, w) == Nature.EMPTY)) {
            throw new PostconditionError("!(getNature(h, w) == Nature.EMPTY)");
        }
    }
    
    @Override
    public void build(int h, int w) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        // \pre: getNature(h, w) == Nature::EMPTY
        if (!(getNature(h, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h, w) == Nature.EMPTY)");
        }
        
        /* Processing */
        super.build(h, w);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getNature(h, w) == Nature::DIRT
        if (!(getNature(h, w) == Nature.DIRT)) {
            throw new PostconditionError("(getNature(h, w) == Nature.DIRT)");
        }
    }
    
    @Override
    public void goPlay(int h1, int w1, int h2, int w2) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: \forall i in [0, getHeight() - 1],
        //         getNature(i, 0) == Nature::METAL ^ getNature(i, getWidth() - 1) == Nature::METAL
        for (int i = 0; i < getHeight(); ++i) {
            if (!(getNature(i, 0) == Nature.METAL && getNature(i,  getWidth() - 1) == Nature.METAL)) {
                throw new PreconditionError("!(getNature(i, 0) == Nature::METAL ^ getNature(i, getWidth() - 1) == Nature::METAL)");
            }
        }
        
        // \pre: \forall j \in [0, getWidth() - 1],
        //         getNature(0, j) == Nature::METAL ^ getNature(getHeight() - 1, j) == Nature::METAL
        for (int j = 0; j < getWidth(); ++j) {
            if (!(getNature(0, j) == Nature.METAL && getNature(getHeight() - 1,  j) == Nature.METAL)) {
                throw new PreconditionError("!(getNature(0, j) == Nature::METAL ^ getNature(getHeight() - 1,  j) == Nature::METAL)");
            }
        }
        
        // \pre: isEditing()
        if (!isEditing()) {
            throw new PreconditionError("!isEditing()");
        }
        
        // \pre: getNature(h1, w1) == Nature::EMPTY ^ getNature(h1 - 1, w1) == Nature::EMPTY ^ getNature(h1 + 1, w1) == Nature::EMPTY 
        if (!(getNature(h1, w1) == Nature.EMPTY && getNature(h1 - 1, w1) == Nature.EMPTY && getNature(h1 + 1, w1) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h1, w1) == Nature.EMPTY ^ getNature(h1 - 1, w1) == Nature.EMPTY ^ getNature(h1 + 1, w1) == Nature.EMPTY)");
        }
        
        // \pre: getNature(h2, w2) == Nature::EMPTY ^ getNature(h2 - 1, w2) == Nature::EMPTY ^ getNature(h2 + 1, w2) == Nature::METAL 
        if (!(getNature(h2, w2) == Nature.EMPTY && getNature(h2 - 1, w2) == Nature.EMPTY && getNature(h2 + 1, w2) == Nature.METAL)) {
            throw new PreconditionError("!(getNature(h2, w2) == Nature.EMPTY ^ getNature(h2 - 1, w2) == Nature.EMPTY ^ getNature(h2 + 1, w2) == Nature.METAL)");
        }
        
        /* Processing */
        super.goPlay(h1, w1, h2, w2);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: !isEditing()
        if (isEditing()) {
            throw new PostconditionError("isEditing()");
        }
        
        // \post: getHEntrance() == h1
        if (!(getHEntrance() == h1)) {
            throw new PostconditionError("!(getHEntrance() == h1)");
        }
        
        // \post: getWEntrance() == w1
        if (!(getWEntrance() == w1)) {
            throw new PostconditionError("!(getHEntrance() == w1)");
        }
        
        // \post: getNature(h1, w1) == Nature::ENTRANCE
        if (!(getNature(h1, w1) == Nature.ENTRANCE)) {
            throw new PostconditionError("!(getNature(h1, w1) == Nature.ENTRANCE)");
        }
        
        // \post: getNature(h2, w2) == Nature::EXIT
        if (!(getNature(h2, w2) == Nature.EXIT)) {
            throw new PostconditionError("!(getNature(h2, w2) == Nature.EXIT)");
        }
    }
}
