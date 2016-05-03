package game.services.display;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public interface TileService {
    /** Border */
    final static Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    
    /** Dimension */
    final static Dimension dim = new Dimension(40, 40);
    
    /** String for natures */
    final static String emptyStr = "Empty";
    final static String dirtStr = "Dirt";
    final static String metalStr = "Metal";

    /** Images for natures */
    final static ImageIcon emptyImg = new ImageIcon("res/tiles/EMPTY.png");
    final static ImageIcon dirtImg = new ImageIcon("res/tiles/DIRT.png");
    final static ImageIcon metalImg = new ImageIcon("res/tiles/METAL.png");
    final static ImageIcon entranceImg = new ImageIcon("res/tiles/ENTRANCE.png");
    final static ImageIcon exitImg = new ImageIcon("res/tiles/EXIT.png");
    
    /** Images for walkers */
    final static ImageIcon walkerBodyLeftImg = new ImageIcon("res/walker/WALKER_BODY_LEFT.png");
    final static ImageIcon walkerBodyRightImg = new ImageIcon("res/walker/WALKER_BODY_RIGHT.png");
    final static ImageIcon walkerHeadLeftImg = new ImageIcon("res/walker/WALKER_HEAD_LEFT.png");
    final static ImageIcon walkerHeadRightImg = new ImageIcon("res/walker/WALKER_HEAD_RIGHT.png"); 
    
    /** Images for fallers */
    final static ImageIcon fallerBodyLeftImg = new ImageIcon("res/faller/FALLER_BODY_LEFT.png");
    final static ImageIcon fallerBodyRightImg = new ImageIcon("res/faller/FALLER_BODY_RIGHT.png");
    final static ImageIcon fallerHeadLeftImg = new ImageIcon("res/faller/FALLER_HEAD_LEFT.png");
    final static ImageIcon fallerHeadRightImg = new ImageIcon("res/faller/FALLER_HEAD_RIGHT.png");
    
    /** Images for digger */
    final static ImageIcon diggerBodyLeftImg = new ImageIcon("res/digger/DIGGER_BODY_LEFT.png");
    final static ImageIcon diggerBodyRightImg = new ImageIcon("res/digger/DIGGER_BODY_RIGHT.png");
    final static ImageIcon diggerHeadLeftImg = new ImageIcon("res/digger/DIGGER_HEAD_LEFT.png");
    final static ImageIcon diggerHeadRightImg = new ImageIcon("res/digger/DIGGER_HEAD_RIGHT.png");
    
    /** Tokens */
    final static ImageIcon walkerToken = new ImageIcon("res/walker/WALKER_TOKEN.png");
    final static ImageIcon diggerToken = new ImageIcon("res/digger/DIGGER_TOKEN.png");
}
