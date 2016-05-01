package game.components;

import game.enums.Nature;
import game.services.GameEngService;
import game.services.LevelService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class Display {
    private JFrame mainFrame = new JFrame();
    private JLabel [][]tiles;
    
    private GameEngService gameEngine;
    private LevelService level;
    ImageIcon emptyImage = new ImageIcon("res/EMPTY.png");
    ImageIcon dirtImage = new ImageIcon("res/DIRT.png");
    ImageIcon metalImage = new ImageIcon("res/METAL.png");

    private Nature currentNature;
    
    public Display(GameEngService gameEngine) {
        this.gameEngine = gameEngine;
        level = this.gameEngine.getLevel();
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        JPanel gridLevelPanel = new JPanel();
        GridLayout gridLayoutLevel = new GridLayout(level.getHeight(), level.getWidth());
        gridLevelPanel.setLayout(gridLayoutLevel);
        
        tiles = new JLabel[level.getHeight()][level.getWidth()];
        for (int h = 0; h < level.getHeight(); ++h) {
            for (int w = 0; w < level.getWidth(); ++w) {
                tiles[h][w] = new JLabel(emptyImage);
                tiles[h][w].setBorder(border);
                gridLevelPanel.add(tiles[h][w]);
                
                final int currentH = h;
                final int currentW = w;
                tiles[h][w].addMouseListener(new MouseListener() {  
                    @Override
                    public void mouseReleased(MouseEvent arg0) {                        
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent arg0) {                        
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent arg0) {                        
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent arg0) {                        
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        level.setNature(currentH, currentW, currentNature);
                        if (currentNature == Nature.EMPTY) {
                            tiles[currentH][currentW].setIcon(emptyImage);
                        }
                        else if (currentNature == Nature.DIRT) {
                            tiles[currentH][currentW].setIcon(dirtImage);
                        }
                        else if (currentNature == Nature.METAL) {
                            tiles[currentH][currentW].setIcon(metalImage);
                        }
                    }
                });
            }
        }
        
        JPanel casesPanel = new JPanel();
        GridLayout casesLevel = new GridLayout(1, 4);
        casesPanel.setLayout(casesLevel);
        
        JLabel jLabelEmpty = new JLabel(emptyImage);
        jLabelEmpty.setBorder(border);
        jLabelEmpty.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {                        
            }
            
            @Override
            public void mousePressed(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseExited(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseEntered(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseClicked(MouseEvent arg0) {
                currentNature = Nature.EMPTY;
            }
        });
        casesPanel.add(jLabelEmpty);
        
        JLabel jLabelDirt = new JLabel(dirtImage);
        jLabelDirt.setBorder(border);
        jLabelDirt.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {                        
            }
            
            @Override
            public void mousePressed(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseExited(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseEntered(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseClicked(MouseEvent arg0) {
                currentNature = Nature.DIRT;
            }
        });
        casesPanel.add(jLabelDirt);
        
        JLabel jLabelMetal= new JLabel(metalImage);
        jLabelMetal.setBorder(border);
        jLabelMetal.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {                        
            }
            
            @Override
            public void mousePressed(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseExited(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseEntered(MouseEvent arg0) {                        
            }
            
            @Override
            public void mouseClicked(MouseEvent arg0) {
                currentNature = Nature.METAL;
            }
        });
        casesPanel.add(jLabelMetal);

        JButton jButtonPlay = new JButton("Jouer");
        jButtonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Jouer");
            }
        });
        casesPanel.add(jButtonPlay);

        
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Lemmings");
        mainFrame.getContentPane().add(gridLevelPanel, BorderLayout.NORTH);
        mainFrame.getContentPane().add(new JSeparator(), BorderLayout.CENTER);
        mainFrame.getContentPane().add(casesPanel, BorderLayout.SOUTH);
        
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    public void stepEdit() {

        
    }
    
    public void stepPlay() {
        
    }
}
