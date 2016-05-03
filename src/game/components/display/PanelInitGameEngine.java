package game.components.display;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PanelInitGameEngine extends JPanel {
    private static final long serialVersionUID = -1712958264576955567L;
    
    private JSpinner jSpinnerSizeColony;
    private JSpinner jSpinnerSpawnSpeed;
    
    public PanelInitGameEngine() {
        super();
        
        setLayout(new GridLayout(1, 4));
        
        JLabel jLabelSizeColony = new JLabel("Nb lemmings:");
        add(jLabelSizeColony);
        jSpinnerSizeColony = new JSpinner(new SpinnerNumberModel(2, 1, 20, 1));
        add(jSpinnerSizeColony);
        
        JLabel jLabelSpawnSpeed = new JLabel("Spawn speed:");
        add(jLabelSpawnSpeed);
        jSpinnerSpawnSpeed = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        add(jSpinnerSpawnSpeed);
    }
    
    public int getSizeColony() {
        return (Integer) jSpinnerSizeColony.getValue();
    }
    
    public int getSpawnSpeed() {
        return (Integer) jSpinnerSpawnSpeed.getValue();
    }
}
