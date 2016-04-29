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
        //         squareExist(i, j) */
        for (int h = 0; h < getHeight(); ++h) {
            for (int w = 0; w < getWidth(); ++w) {
                if (!squareExist(h, w)) {
                    throw new InvariantError("\\forall i \\in [0, getHeight()["
                            + " ^ \forall j in [0, getWidth()[,"
                            + " !squareExist(i, j)");
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
    public int getHExit() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getHExit();
    }
    
    @Override
    public int getWExit() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getWExit();
    }
    
    @Override
    public void init(int h, int w) {
        /* Pre-condition(s) */
        // \pre: MIN_HEIGHT < h < MAX_HEIGHT
        if (!(MIN_HEIGHT < h && h < MAX_HEIGHT)) {
            throw new PreconditionError("!(MIN_HEIGHT < h < MAX_HEIGHT)");
        }
        
        // \pre: MIN_WIDTH < w < MAX_WIDTH
        if (!(MIN_WIDTH < h && h < MAX_WIDTH)) {
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
        if (isEditing()) {
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
        
        /* Processing */
        super.setNature(h, w, n);
        
        /* Post-invariant */
        checkInvariant();
    }
    
    @Override
    public void goPlay() {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: \forall i in [0, getHeight() - 1],
        //         getNature(i, 0) == Nature::METAL ^ getNature(i, getWidth() - 1) == Nature::METAL
        for (int i = 0; i < super.getHeight(); ++i) {
            if (!(getNature(i, 0) == Nature.METAL &&
                getNature(i,  getWidth() - 1) == Nature.METAL)) {
                throw new PreconditionError("\forall i in [0, getHeight() - 1],"
                        + "!(getNature(i, 0) == Nature::METAL ^ "
                        + "getNature(i, getWidth() - 1) == Nature::METAL)");
            }
        }
        
        // \pre: \forall j \in [0, getWidth() - 1],
        //         getNature(0, j) == Nature::METAL ^ getNature(getHeight() - 1, j) == Nature::METAL
        for (int j = 0; j < super.getWidth(); ++j) {
            if (!(getNature(0, j) == Nature.METAL &&
                getNature(getHeight() - 1,  j) == Nature.METAL)) {
                throw new PreconditionError("\forall i in [0, getHeight() - 1],"
                        + "!(getNature(0, j) == Nature::METAL ^ "
                        + "getNature(getHeight() - 1,  j) == Nature::METAL)");
            }
        }
        
        // \pre: isEditing()
        if (!isEditing()) {
            throw new PreconditionError("!isEditing()");
        }
        
        /* Processing */
        super.goPlay();
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: !isEditing()
        if (isEditing()) {
            throw new PostconditionError("isEditing()");
        }
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
    public void defEntrance(int h, int w) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: getNature(h, w) == Nature::EMPTY
        if (!(getNature(h, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h, w) == Nature.EMPTY)");
        }
        
        // \pre: getNature(h - 1, w) == Nature::EMPTY
        if (!(getNature(h - 1, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h - 1, w) == Nature.EMPTY)");
        }
        
        // \pre: getNature(h + 1, w) == Nature::EMPTY
        if (!(getNature(h + 1, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h + 1, w) == Nature.EMPTY)");
        }
        
        /* Processing */
        super.defEntrance(h, w);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getHEntrance() == h
        if (!(getHEntrance() == h)) {
            throw new PostconditionError("!(getHEntrance() == h)");
        }
        
        // \post: getWEntrance() == w
        if (!(getHEntrance() == w)) {
            throw new PostconditionError("!(getHEntrance() == w)");
        }
    }
    
    @Override
    public void defExit(int h, int w) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: getNature(h, w) == Nature::EMPTY
        if (!(getNature(h, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h, w) == Nature.EMPTY)");
        }
        
        // \pre: getNature(h - 1, w) == Nature::EMPTY
        if (!(getNature(h - 1, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h - 1, w) == Nature.EMPTY)");
        }
        
        // \pre: getNature(h + 1, w) == Nature::EMPTY
        if (!(getNature(h + 1, w) == Nature.EMPTY)) {
            throw new PreconditionError("!(getNature(h + 1, w) == Nature.EMPTY)");
        }
        
        /* Processing */
        super.defExit(h, w);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getHExit() == h
        if (!(getHExit() == h)) {
            throw new PostconditionError("!(getHExit() == h)");
        }
        
        // \post: getWExit() == w
        if (!(getWExit() == w)) {
            throw new PostconditionError("!(getWExit() == w)");
        }
    }
}
