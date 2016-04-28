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
        for (int h = 0; h < super.getHeight(); ++h) {
            for (int w = 0; w < super.getWidth(); ++w) {
                if (!super.squareExist(h, w)) {
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
        if (!super.squareExist(h, w)) {
            throw new PreconditionError("!squareExist(h, w)");
        }
        
        return super.getNature(h, w);
    }
    
    @Override
    public int getHEntrance() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (super.isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getHEntrance();
    }
    
    @Override
    public int getWEntrance() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (super.isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getWEntrance();
    }
    
    @Override
    public int getHExit() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (super.isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getHExit();
    }
    
    @Override
    public int getWExit() {
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (super.isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        return super.getWExit();
    }
    
    @Override
    public void init(int h, int w) {
        super.init(h, w);
    }
    
    @Override
    public void setNature(int h, int w, Nature n) {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: !isEditing()
        if (super.isEditing()) {
            throw new PreconditionError("isEditing()");
        }
        
        // \pre: squareExist(h, w)
        if (!super.squareExist(h, w)) {
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
           // if (getNature(i, 0))
        }
        
        
        // \pre: \forall j \in [0, getWidth() - 1],
        //         getNature(0, j) == Nature::METAL ^ getNature(getHeight() - 1, j) == Nature::METAL
        
        /* Processing */
        super.goPlay();
    }
    
    @Override
    public void remove(int h, int w) {
        super.remove(h, w);
    }
    
    @Override
    public void build(int h, int w) {
        super.build(h, w);
    }
    
    @Override
    public void defEntrance(int h, int w) {
        super.defEntrance(h, w);
    }
    
    @Override
    public void defExit(int h, int w) {
        super.defExit(h, w);
    }
}
