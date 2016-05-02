package game.components.display;

import game.components.Lemming;
import game.enums.Nature;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Display {
    private JFrame mainFrame = new JFrame();
    
    private GameEngService gameEngine;
    private LevelService level;
    
    private Nature currentNature = Nature.EMPTY;
    private boolean editing = true;
    private boolean selectingEntrance = true;
    
    private TileLevel entrance = null;
    private TileLevel exit = null;
    private TileLevel [][]tiles;
    
    private JButton jButtonPlay;
    private JButton jButtonValidate;
    private JButton jButtonStart;
    private JButton jbuttonNextTurn;
    
    private int i = 7;

    
    public Display(GameEngService gameEngine) {
        this.gameEngine = gameEngine;
        level = this.gameEngine.getLevel();
        
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
        PanelSelectToken panelSelectToken = new PanelSelectToken();
        
        // Play button
        jButtonPlay = new JButton("Jouer");
        jButtonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editing = false;
                
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
                gameEngine.newLemming(i++);
                refreshMainFrame();
            }
        });

        // Next turn button
        jbuttonNextTurn = new JButton("Tour suivant");
        jbuttonNextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.callStepLemmings();
                
                for (int h = 0; h < level.getHeight(); ++h) {
                    for (int w = 0; w < level.getWidth(); ++w) {
                        tiles[h][w].setLemming(null);
                    }
                }
                
                List<Integer> numLemmings = gameEngine.getNumLemmingsActive();
                //System.out.println(numLemmings.size());
               // System.out.println(gameEngine.getNbLemmingsActive());
                for (Integer numLemming : numLemmings) {
                    //System.out.println(numLemming);
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
                
                if (gameEngine.getNbLemmingsCreated() < gameEngine.getSizeColony()) {
                    gameEngine.newLemming(i++);
                }
            }
        });
        
        
        // Main frame init
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Lemmings");
        mainFrame.add(panelLevel, BorderLayout.NORTH);
        mainFrame.add(panelEditor, BorderLayout.CENTER);
        //mainFrame.add(panelInitGameEngine, BorderLayout.CENTER);

        mainFrame.add(jButtonPlay, BorderLayout.SOUTH);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
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
    
    public GameEngService getGameEngine() {
        return gameEngine;
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
}
