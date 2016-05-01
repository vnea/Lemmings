package game.services.display;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public interface TileService {
    final static Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    final static Dimension dim = new Dimension(40, 40);
    
    final static String emptyStr = "Vide";
    final static String dirtStr = "Boue";
    final static String metalStr = "Métal";

    final static ImageIcon emptyImg = new ImageIcon("res/EMPTY.png");
    final static ImageIcon dirtImg = new ImageIcon("res/DIRT.png");
    final static ImageIcon metalImg = new ImageIcon("res/METAL.png");
    final static ImageIcon entranceImg = new ImageIcon("res/ENTRANCE.png");
    final static ImageIcon exitImg = new ImageIcon("res/EXIT.png");
    
    final static ImageIcon walkerLeftImg = new ImageIcon("res/WALKER_LEFT.png");
    final static ImageIcon walkerRightImg = new ImageIcon("res/WALKER_RIGHT.png");
    
    final static ImageIcon fallerLeftImg = new ImageIcon("res/FALLER_LEFT.png");
    final static ImageIcon fallerRightImg = new ImageIcon("res/FALLER_RIGHT.png");
}
