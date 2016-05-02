package game.components.display;

import game.enums.Nature;
import game.enums.TokenType;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;
import game.services.PlayerService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Display {
    private JFrame mainFrame = new JFrame();
    
    private GameEngService gameEngine;
    private LevelService level;
    private PlayerService player;
    
    private Nature currentNature = Nature.EMPTY;
    private TokenType currentTokenType = null;
    
    private boolean editing = true;
    private boolean selectingDoors = false;
    
    private boolean selectingEntrance = true;
    
    private TileLevel entrance = null;
    private TileLevel exit = null;
    private TileLevel [][]tiles;
    private Map<TokenType, TileToken> mapToken;
    
    private JButton jButtonPlay;
    private JButton jButtonValidate;
    private JButton jButtonStart;
    private JButton jbuttonNextTurn;
    
    private int idLemming = 0;
    private Integer initWidth = null;
    
    
    public Display(GameEngService gameEngine, LevelService level, PlayerService player) {
        this.gameEngine = gameEngine; 
        this.level = level;
        this.player = player;
        
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
        jButtonPlay = new JButton("Jouer");
        jButtonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editing = false;
                selectingDoors = true;

                mainFrame.remove(panelEditor);
                mainFrame.remove(jButtonPlay);
                
                mainFrame.add(panelEntranceExit, BorderLayout.CENTER);
                mainFrame.add(jButtonValidate, BorderLayout.SOUTH);
                refreshMainFrame();
            }
        });
        
        // Validate button
        jButtonValidate = new JButton("Valider");
        jButtonValidate.setEnabled(false);
        jButtonValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectingDoors = false;

                mainFrame.remove(panelEntranceExit);
                mainFrame.remove(jButtonValidate);
                
                mainFrame.add(panelInitGameEngine, BorderLayout.CENTER);
                mainFrame.add(jButtonStart, BorderLayout.SOUTH);
                
                tiles = panelLevel.getTiles();
                
                level.goPlay(entrance.getH(), entrance.getW(),
                             exit.getH(), exit.getW());
                refreshMainFrame();
            }
        });
        
        // Start button
        jButtonStart = new JButton("Commencer");
        jButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.init(panelInitGameEngine.getSizeColony(),
                                panelInitGameEngine.getSpawnSpeed());

                mainFrame.remove(panelInitGameEngine);
                mainFrame.remove(jButtonStart);
                
                mainFrame.add(panelSelectToken, BorderLayout.CENTER);
                mainFrame.add(jbuttonNextTurn, BorderLayout.SOUTH);
                gameEngine.newLemming(idLemming++);
                refreshMainFrame();
                mainFrame.pack();
            }
        });

        // Next turn button
        jbuttonNextTurn = new JButton("Tour suivant");
        jbuttonNextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameEngine.isGameOver()) {
                    gameEngine.callStepLemmings();
                    
                    for (int h = 0; h < level.getHeight(); ++h) {
                        for (int w = 0; w < level.getWidth(); ++w) {
                            tiles[h][w].setLemming(null);
                        }
                    }
                    
                    List<Integer> numLemmings = gameEngine.getNumLemmingsActive();
                    for (Integer numLemming : numLemmings) {
                        LemmingService lemming = gameEngine.getLemming(numLemming);
                        tiles[lemming.getHPos()][lemming.getWPos()].setLemming(lemming);
                    }
                    
                    for (int h = 0; h < level.getHeight(); ++h) {
                        for (int w = 0; w < level.getWidth(); ++w) {
                            tiles[h][w].update();
                        }
                    }
    
                    gameEngine.checkSaved();
                    gameEngine.checkDead();
                    gameEngine.checkWin();
                    
                    if (!gameEngine.isGameOver() && 
                        gameEngine.getNbLemmingsCreated() < gameEngine.getSizeColony() &&
                        gameEngine.getTurn() % gameEngine.getSpawnSpeed() == 0) {
                        gameEngine.newLemming(idLemming++);
                    }
                }
                if (gameEngine.isGameOver()) {
                    mainFrame.remove(jbuttonNextTurn);
                    refreshMainFrame();
                    
                    JOptionPane.showMessageDialog(mainFrame,
                            "Score : " + gameEngine.getScore(),
                            "Fin du jeu",
                            JOptionPane.PLAIN_MESSAGE);
                }
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
        return currentTokenType;
    }
    
    public void setCurrentTokenType(TokenType tokenType) {
        currentTokenType = tokenType;
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
    
    public TileLevel getTile(int h, int w) {
        return tiles[h][w];
    }
}
