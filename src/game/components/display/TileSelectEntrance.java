package game.components.display;

import game.services.display.TileService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class TileSelectEntrance extends JLabel implements TileService {
    private static final long serialVersionUID = -1241090233710704081L;
    
    @SuppressWarnings("unused")
    private Display display;
    
    public TileSelectEntrance(Display display) {
        super(entranceImg);
        setBorder(border);
        setPreferredSize(dim);
        
        this.display = display;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                display.setSelectingEntrance(true);
            }
        });
    }
}
