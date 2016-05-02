package game.components.display;

import game.enums.Nature;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelLevel extends JPanel {
    private static final long serialVersionUID = 590776110111562415L;
    
    private TileLevel [][]tiles;
    
    public PanelLevel(int height, int width, Display display) {
        super();
        setLayout(new GridLayout(height, width));
        
        tiles = new TileLevel[height][width];
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                tiles[h][w] = new TileLevel(Nature.EMPTY, display, h, w);
                add(tiles[h][w]);
            }
        }
        
        display.setTilesLevel(tiles);
    }
}
