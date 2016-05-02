package game.components.display;

import game.enums.TokenType;
import game.services.display.TileService;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TileToken extends JPanel implements TileService {
    private static final long serialVersionUID = -5710070533764368741L;
    
    private TokenType tokenType;
    private Display display;
    
    private JLabel counter;
    private JLabel img;
    
    public TileToken(TokenType tokenType, Display display) {
        super();
        setBorder(border);
        setLayout(new GridLayout(2, 1));
        
        counter = new JLabel();
        add(counter);

        img = new JLabel();
        add(img);
       
        this.tokenType = tokenType;
        this.display = display;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                display.setCurrentTokenType(tokenType);
            }
        });
        
        updateImg();
        updateText();
    }

    private void updateText() {
        counter.setText(Integer.toString(display.getPlayer().getNbToken(tokenType)));
    }
    
    private void updateImg() {
        switch (tokenType) {
            case FALLER:
                img.setIcon(fallerToken);
            break;
               
            case BASHER:
                img.setText("BR");
            break;
                
            case BASIC:
                img.setText("BC");
            break;
                
            case BOMBER:
                img.setText("BO");
            break;
                
            case BUILDER:
                img.setText("BUI");
            break;
                
            case CLIMBER:
                img.setText("CL");
            break;
                
            case DIGGER:
                img.setText("DI");
            break;
                
            case FLOATER:
                img.setText("FLO");
            break;
                
            case STOPPER:
                img.setText("ST");

            break;
            
            case WALKER:
                img.setIcon(walkerToken);
            break;
        }
    }
}
