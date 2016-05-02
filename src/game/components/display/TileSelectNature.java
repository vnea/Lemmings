package game.components.display;

import game.enums.Nature;
import game.services.display.TileService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class TileSelectNature extends JLabel implements TileService {
    private static final long serialVersionUID = -6820627352625172913L;
    
    private Nature nature;
    
    @SuppressWarnings("unused")
    private Display display;
    
    @SuppressWarnings("incomplete-switch")
    public TileSelectNature(Nature nature, Display display) {
        this.display = display;
        this.nature = nature;
        
        setBorder(border);
        setPreferredSize(dim);

        switch (this.nature) {
            case EMPTY:
                setIcon(emptyImg);
                setText(emptyStr);
            break;
            
            case DIRT:
                setIcon(dirtImg);
                setText(dirtStr);
            break;
            
            case METAL:
                setIcon(metalImg);
                setText(metalStr);
            break;
        }
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                display.setCurrentNature(nature);
            }
        });
    }
}
