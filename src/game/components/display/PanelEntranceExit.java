package game.components.display;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelEntranceExit extends JPanel {
    private static final long serialVersionUID = 1184543332668333375L;
    
    public PanelEntranceExit(Display display) {
        super();
        setLayout(new GridLayout(1, 2));
        
        TileSelectEntrance entrance = new TileSelectEntrance(display);
        add(entrance);
        
        TileSelectExit exit = new TileSelectExit(display);
        add(exit);
    }
}
