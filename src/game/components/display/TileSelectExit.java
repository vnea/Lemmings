package game.components.display;

import game.services.display.TileService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class TileSelectExit extends JLabel implements TileService {
    private static final long serialVersionUID = -4694316028097420137L;
    
    @SuppressWarnings("unused")
    private Display display;
    
    public TileSelectExit(Display display) {
        super(exitImg);
        setBorder(border);
        setPreferredSize(dim);
        
        this.display = display;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                display.setSelectingEntrance(false);
            }
        });
    }
}
