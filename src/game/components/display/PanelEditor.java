package game.components.display;

import game.enums.Nature;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelEditor extends JPanel {
    private static final long serialVersionUID = -7448472722235374135L;
        
    public PanelEditor(Display display) {
        super();
        setLayout(new GridLayout(1, 3));
        
        TileSelectNature tsEmpty = new TileSelectNature(Nature.EMPTY, display);
        add(tsEmpty);
        
        TileSelectNature tsDirty = new TileSelectNature(Nature.DIRT, display);
        add(tsDirty);
        
        TileSelectNature tsMetal = new TileSelectNature(Nature.METAL, display);
        add(tsMetal);
    }
}
