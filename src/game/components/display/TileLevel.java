package game.components.display;

import game.enums.Direction;
import game.enums.Nature;
import game.enums.TokenType;
import game.services.LemmingService;
import game.services.display.TileService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class TileLevel extends JLabel implements TileService {
    private static final long serialVersionUID = 6147108601724660567L;
    
    private int h;
    private int w;
    private boolean isEntrance = false;
    private boolean isExit = false;

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
                        display.updateStateButtonPlay();
                    }
                    else if (display.isSelectingDoors()) {
                        if (ref.nature == Nature.EMPTY) {
                            if (display.isSelectingEntrance()) {
                                if (display.isValidEntrance(ref)) {
                                    isEntrance = true;
                                    isExit = false;
                                    display.setEntrance(ref);
                                    setIcon(entranceImg);
                                }
                            }
                            else {
                                if (display.isValidExit(ref)) {
                                    isExit = true;
                                    isEntrance = false;
                                    display.setExit(ref);
                                    setIcon(exitImg);
                                }
                            }
                        }
                    }
                    else {
                        TokenType tokenType = display.getCurrentTokenType();
                        if (lemming != null && tokenType != null &&
                            display.getPlayer().getNbToken(tokenType) > 0) {
                            display.getPlayer().useToken(lemming.getNum());
                            display.updateTextTileToken(tokenType);
                            update();
                        }
                    }
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getModifiers() == MouseEvent.BUTTON1_MASK && display.isEditing()) {
                    updateNature(display.getCurrentNature());
                    display.updateStateButtonPlay();
                }
            }
        });
    }
    
    public void removeDoor() {
        isExit = false;
        isEntrance = false;
        updateNature(this.nature);
    }
    
    public void setNature(Nature nature) {
        this.nature = nature;
    }
    
    @SuppressWarnings("incomplete-switch")
    private void updateNature(Nature nature) {
        setNature(nature);
        
        if (display.getLevel().isEditing()) {
            display.getLevel().setNature(h, w, this.nature);
        }
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
        // Show entrance
        if (isEntrance) {
            setIcon(entranceImg);
            if (lemming != null) {
                switch(lemming.getBehaviour()) {
                    case WALKER:
                        showWalkerHead();
                    break;
                 
                    case BASHER:
                    break;
                    
                    case BUILDER:
                    break;
                    
                    case DIGGER:
                        showDiggerHead();
                    break;
                    
                    case FALLER:
                        showFallerHead();
                    break;
                    
                    case STOPPER:
                    break;
                }
            }
        }
        // Show entrance
        else if (isExit) {
            setIcon(exitImg);
        }
        // Show the Lemming
        else if (lemming != null) {
            switch(lemming.getBehaviour()) {
                case WALKER:
                    setIcon(lemming.getDirection() == Direction.LEFT
                                            ? walkerBodyLeftImg
                                            : walkerBodyRightImg);
                    showWalkerHead();
                break;
                
                case FALLER:
                    setIcon(lemming.getDirection() == Direction.LEFT
                                            ? fallerBodyLeftImg
                                            : fallerBodyRightImg);
                    showFallerHead();
                break;
                
                case BASHER:
                break;
                
                case BUILDER:
                break;
                
                case DIGGER:
                    setIcon(lemming.getDirection() == Direction.LEFT
                                            ? diggerBodyLeftImg
                                            : diggerBodyRightImg);
                    showDiggerHead();
                break;
                
                case STOPPER:
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
    
    public boolean isADoor() {
        return isEntrance || isExit;
    }
    
    private void showWalkerHead() {
        TileLevel tile = display.getTile(h - 1, w);
        if (!tile.isADoor()) {
            tile.setIcon(lemming.getDirection() == Direction.LEFT
                                    ? walkerHeadLeftImg
                                    : walkerHeadRightImg);
        }
    }
    
    private void showFallerHead() {
        TileLevel tile = display.getTile(h - 1, w);
        if (!tile.isADoor()) {
            tile.setIcon(lemming.getDirection() == Direction.LEFT
                                    ? fallerHeadLeftImg
                                    : fallerHeadRightImg);
        }
    }
    
    private void showDiggerHead() {
        TileLevel tile = display.getTile(h - 1, w);
        if (!tile.isADoor()) {
            tile.setIcon(lemming.getDirection() == Direction.LEFT
                                    ? diggerHeadLeftImg
                                    : diggerHeadRightImg);
        }
    }
}
