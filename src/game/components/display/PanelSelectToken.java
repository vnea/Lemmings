package game.components.display;

import game.enums.TokenType;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PanelSelectToken extends JPanel {
    private static final long serialVersionUID = -8786163654032592844L;
    
    private Map<TokenType, TileToken> mapToken = new HashMap<>();
    
    public PanelSelectToken(Display display) {
        super();
        final int NB_TOKENS = TokenType.values().length;
        setLayout(new GridLayout(1, NB_TOKENS));
        
        TokenType[] tokensType = TokenType.values();
        for (TokenType tokenType : tokensType) {
            TileToken tileToken = new TileToken(tokenType, display);
            mapToken.put(tokenType, tileToken);
            add(tileToken);
            
            display.setMapToken(mapToken);
        }
    }
}
