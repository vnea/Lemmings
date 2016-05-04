package game.components.display;

import game.components.Level;
import game.enums.Nature;
import game.enums.TokenType;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;
import game.services.PlayerService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Display {
    private JFrame mainFrame = new JFrame();
    
    private GameEngService gameEngine;
    private LevelService level;
    private PlayerService player;
    
    private Nature currentNature = Nature.EMPTY;
    
    private boolean editing = true;
    private boolean selectingDoors = false;
    
    private boolean selectingEntrance = true;
    
    private TileLevel entrance = null;
    private TileLevel exit = null;
    private TileLevel [][]tiles;
    private Map<TokenType, TileToken> mapToken;
    
    private JButton jButtonPlay;
    private JButton jButtonReturn;
    private JButton jButtonValidate;
    private JButton jButtonStart;
    private JButton jbuttonNextTurn;
    private JButton jButtonResetGame;
    
    private Integer initWidth = null;
    
    
    public Display(GameEngService gameEngine, LevelService level, PlayerService player) {
        this.gameEngine = gameEngine; 
        this.level = level;
        this.player = player;
        Display ref = this;
        
        
        player.bindGameEngService(gameEngine);
        player.init(5, 5, 0, 0, 0, 0, 0, 0, 0);
        
        if (!askHeightWidthOk()) {
            mainFrame.dispose();

        }
        else {
            // Panel Level
            PanelLevel panelLevel = new PanelLevel(level.getHeight(),
                                                   level.getWidth(), this);
            
            // Panel editor
            PanelEditor panelEditor = new PanelEditor(this);
            
            // Panel entrance exit
            PanelEntranceExit panelEntranceExit = new PanelEntranceExit(this);
            
            // Panel init game engine
            PanelInitGameEngine panelInitGameEngine = new PanelInitGameEngine();
            
            // Panel select token
            PanelSelectToken panelSelectToken = new PanelSelectToken(this);
            
            // Play button
            jButtonPlay = new JButton("Choose entrance/exit");
            jButtonPlay.setEnabled(false);
            jButtonPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    editing = false;
                    selectingDoors = true;
    
                    mainFrame.remove(panelEditor);
                    mainFrame.remove(jButtonPlay);
                    
                    mainFrame.add(panelEntranceExit, BorderLayout.CENTER);
                    mainFrame.add(jButtonValidate, BorderLayout.SOUTH);
                    mainFrame.add(jButtonReturn, BorderLayout.EAST);
                    
                    refreshMainFrame();
                }
            });
            
            // Return button
            jButtonReturn = new JButton("Back");
            jButtonReturn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editing = true;
                    selectingDoors = false;
                    
                    mainFrame.remove(panelEntranceExit);
                    mainFrame.remove(jButtonValidate);
                    mainFrame.remove(jButtonReturn);
                    
                    mainFrame.add(panelEditor, BorderLayout.CENTER);
                    mainFrame.add(jButtonPlay, BorderLayout.SOUTH);
                    jButtonValidate.setEnabled(false);
                    
                    if (entrance != null) {
                        entrance.removeDoor();
                        entrance = null;
                    }
                    
                    if (exit != null) {
                        exit.removeDoor();
                        exit = null;
                    }
                    
                    refreshMainFrame();
                }
            });
            
            // Validate button
            jButtonValidate = new JButton("Go play");
            jButtonValidate.setEnabled(false);
            jButtonValidate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectingDoors = false;
    
                    mainFrame.remove(panelEntranceExit);
                    mainFrame.remove(jButtonValidate);
                    mainFrame.remove(jButtonReturn);
                    
                    mainFrame.add(panelInitGameEngine, BorderLayout.CENTER);
                    mainFrame.add(jButtonStart, BorderLayout.SOUTH);
                                    
                    ref.level.goPlay(entrance.getH(), entrance.getW(),
                                 exit.getH(), exit.getW());
                    refreshMainFrame();
                }
            });
            
            // Start button
            jButtonStart = new JButton("Start");
            jButtonStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameEngine.bindLevelService(ref.level);
                    gameEngine.init(panelInitGameEngine.getSizeColony(),
                                    panelInitGameEngine.getSpawnSpeed());
    
                    mainFrame.remove(panelInitGameEngine);
                    mainFrame.remove(jButtonStart);
                    
                    mainFrame.add(panelSelectToken, BorderLayout.CENTER);
                    mainFrame.add(jbuttonNextTurn, BorderLayout.SOUTH);
                    mainFrame.add(jButtonResetGame, BorderLayout.EAST);
                    
                    refreshMainFrame();
                    mainFrame.pack();
                }
            });
    
            // Next turn button
            jbuttonNextTurn = new JButton("Next turn");
            jbuttonNextTurn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!gameEngine.isGameOver()) {
                        gameEngine.executeTurn();
                        
                        for (int h = 0; h < ref.level.getHeight(); ++h) {
                            for (int w = 0; w < ref.level.getWidth(); ++w) {
                                tiles[h][w].setLemming(null);
                            }
                        }
                        
                        List<Integer> numLemmings = gameEngine.getNumLemmingsActive();
                        for (Integer numLemming : numLemmings) {
                            LemmingService lemming = gameEngine.getLemming(numLemming);
                            tiles[lemming.getHPos()][lemming.getWPos()].setLemming(lemming);
                        }
                        
                        for (int h = 0; h < ref.level.getHeight(); ++h) {
                            for (int w = 0; w < ref.level.getWidth(); ++w) {
                                tiles[h][w].setNature(ref.level.getNature(h, w));
                                tiles[h][w].update();
                            }
                        }
       
                    }
                    if (gameEngine.isGameOver()) {
                        jbuttonNextTurn.setEnabled(false);
                        jButtonResetGame.setEnabled(false);
                        refreshMainFrame();
                        
                        JOptionPane.showMessageDialog(mainFrame,
                                "Score : " + gameEngine.getScore(),
                                "End of game",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
            
            // Reset button
            jButtonResetGame = new JButton("Reset");
            jButtonResetGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.resetGame();
                    ref.level = (Level) gameEngine.getLevel();
                    for (int h = 0; h < ref.level.getHeight(); ++h) {
                        for (int w = 0; w < ref.level.getWidth(); ++w) {
                            tiles[h][w].setLemming(null);
                            tiles[h][w].setNature(ref.level.getNature(h, w));
                            tiles[h][w].update();
                        }
                    }
                    
                    mapToken.values().forEach(tileToken -> tileToken.updateText());
                }
            });
            
            // Main frame init
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setTitle("Lemmings");
            mainFrame.add(panelLevel, BorderLayout.NORTH);
            mainFrame.add(panelEditor, BorderLayout.CENTER);
            mainFrame.add(jButtonPlay, BorderLayout.SOUTH);
            
            mainFrame.setResizable(false);
            mainFrame.pack();
            
            // Center in screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2,
                                  dim.height / 2 - mainFrame.getSize().height / 2);
            
            mainFrame.setVisible(true);
            initWidth = mainFrame.getWidth();
            mainFrame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    if (initWidth != null) {
                        mainFrame.setSize(new Dimension(initWidth, mainFrame.getPreferredSize().height));
                    }
                    super.componentResized(e);
                }
            });
        }
    }
    
    public boolean askHeightWidthOk() {
        SpinnerNumberModel sModelHeight = new SpinnerNumberModel(Level.MIN_HEIGHT, Level.MIN_HEIGHT, Level.MAX_HEIGHT, 1);
        SpinnerNumberModel sModelWidth = new SpinnerNumberModel(Level.MIN_WIDTH, Level.MIN_WIDTH, Level.MAX_WIDTH, 1);
        JSpinner spinnerHeight = new JSpinner(sModelHeight);
        JSpinner spinnerWidth = new JSpinner(sModelWidth);

        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Height: "));
        jPanel.add(spinnerHeight);
        
        jPanel.add(new JLabel("Width: "));
        jPanel.add(spinnerWidth);
 
        int option = JOptionPane.showOptionDialog(null, jPanel, "Select height and width", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            level.init((Integer) spinnerHeight.getValue(), (Integer) spinnerWidth.getValue());
            return true;
        }
        
        return false;
    }
    
    public void setTilesLevel(TileLevel [][] tiles) {
        this.tiles = tiles;
    }
    
    public void setMapToken(Map<TokenType, TileToken> mapToken) {
        this.mapToken = mapToken;
    }
    
    public Nature getCurrentNature() {
        return currentNature;
    }
    
    public void setCurrentNature(Nature nature) {
        currentNature = nature;
    }
    
    public boolean isSelectingEntrance() {
        return selectingEntrance;
    }
    
    public void setSelectingEntrance(boolean selectingEntrance) {
        this.selectingEntrance = selectingEntrance;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public void setEntrance(TileLevel tile) {
        if (entrance != null) {
            entrance.removeDoor();
        }
        entrance = tile;
        
        if (tile == exit) {
            exit = null;
        }
        
        jButtonValidate.setEnabled(canValidateDoors());
    }
    
    public void setExit(TileLevel tile) {
        if (exit != null) {
            exit.removeDoor();
        }
        exit = tile;
        
        if (tile == entrance) {
            entrance = null;
        }
        
        jButtonValidate.setEnabled(canValidateDoors());
    }
    
    public LevelService getLevel() {
        return level;
    }
    
    public PlayerService getPlayer() {
        return player;
    }
    
    public GameEngService getGameEngine() {
        return gameEngine;
    }
    
    public boolean isSelectingDoors() {
        return selectingDoors;
    }
    
    public TokenType getCurrentTokenType() {
        return player.getTokenSelected();
    }
    
    public void setCurrentTokenType(TokenType tokenType) {
        player.selectToken(tokenType);
    }
    
    // To update
    private boolean canValidateDoors() {
        return entrance != null && exit != null;
    }
    
    private void refreshMainFrame() {
        mainFrame.invalidate();
        mainFrame.validate();
        mainFrame.repaint();
    }
    
    public void updateTextTileToken(TokenType tokenType) {
        mapToken.get(tokenType).updateText();
    }
    
    public TileLevel getTile(int h, int w) {
        return tiles[h][w];
    }
    
    private boolean isLineFilledOfMetal(List<Nature> line) {
        return line.stream().allMatch(nature -> nature == Nature.METAL);
    }
    
    public void updateStateButtonPlay() {
        // Left line
        List<Nature> leftLine = new ArrayList<>();
        // Right line
        List<Nature> rightLine = new ArrayList<>();
        for (int h = 0; h < level.getHeight(); ++h) {
            rightLine.add(level.getNature(h, level.getWidth() - 1));
            leftLine.add(level.getNature(h, 0));
        }
        
        // Top line
        List<Nature> topLine = new ArrayList<>();
        // Bottom line
        List<Nature> bottomLine = new ArrayList<>();
        for (int w = 0; w < level.getWidth(); ++w) {
            topLine.add(level.getNature(0, w));
            bottomLine.add(level.getNature(level.getHeight() - 1, w));
        }
        
        jButtonPlay.setEnabled(isLineFilledOfMetal(leftLine) &&
                               isLineFilledOfMetal(rightLine) &&
                               isLineFilledOfMetal(topLine) &&
                               isLineFilledOfMetal(bottomLine));
    }
    
    public boolean isValidEntrance(TileLevel tile) {
        return level.getNature(tile.getH() + 1 , tile.getW()) == Nature.EMPTY &&
               level.getNature(tile.getH() - 1 , tile.getW()) == Nature.EMPTY;
        
    }
    
    public boolean isValidExit(TileLevel tile) {
        return level.getNature(tile.getH() - 1 , tile.getW()) == Nature.EMPTY &&
               level.getNature(tile.getH() + 1 , tile.getW()) == Nature.METAL;
    }
}
