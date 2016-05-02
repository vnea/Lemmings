package game.components.display;

import game.enums.Direction;
import game.enums.Nature;
import game.services.LemmingService;
import game.services.display.TileService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class TileLevel extends JLabel implements TileService {
    private static final long serialVersionUID = 6147108601724660567L;
    
    private int h;
    private int w;
    private boolean isADoor = false;
    
    private Nature nature;
    private LemmingService lemming = null;
    
    private final Display display;
    

    public TileLevel(Nature nature, Display display, int h, int w) {
        super();
        this.display = display;
        this.nature = nature;
        updateNature(this.nature);
        
        this.h = h;
        this.w = w;
        
        setBorder(border);
        setPreferredSize(dim);
        
        TileLevel ref = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (display.isEditing()) {
                        updateNature(display.getCurrentNature());
                    }
                    else {
                        if (ref.nature == Nature.EMPTY) {
                            isADoor = true;
                            if (display.isSelectingEntrance()) {
                                display.setEntrance(ref);
                                setIcon(entranceImg);
                            }
                            else {
                                display.setExit(ref);
                                setIcon(exitImg);
                            }
                        }
                    }
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getModifiers() == MouseEvent.BUTTON1_MASK && display.isEditing()) {
                    updateNature(display.getCurrentNature());
                }
            }
        });
    }
    
    public void removeDoor() {
        isADoor = false;
        updateNature(this.nature);
    }
    
    private void updateNature(Nature nature) {
        this.nature = nature;
        display.getLevel().setNature(h, w, this.nature);
        switch (this.nature) {
            case EMPTY:
                    setIcon(emptyImg);
            break;
            
            case DIRT:
                setIcon(dirtImg);
            break;
            
            case METAL:
                setIcon(metalImg);
            break;
        }
    }
    
    public void update() {
        // Show the door
        if (isADoor) {
            if (display.isSelectingEntrance()) {
                setIcon(entranceImg);
            }
            else {
                setIcon(exitImg);
            }
        }
        // Show the Lemming
        else if (lemming != null) {
            switch(lemming.getBehaviour()) {
                case WALKER:
                    setIcon(lemming.getDirection() == Direction.LEFT
                                            ? walkerLeftImg
                                            : walkerRightImg);
                break;
                
                case FALLER:
                    setIcon(lemming.getDirection() == Direction.LEFT
                                            ? fallerLeftImg
                                            : fallerRightImg);
                break;
            }
        }
        // Show the Nature
        else {
            updateNature(this.nature);
        }
    }
    
    public void setLemming(LemmingService lemming) {
        this.lemming = lemming;
    }
    
    public int getH() {
        return h;
    }
    
    public int getW() {
        return w;
    }
}
