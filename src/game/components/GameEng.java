package game.components;

import game.enums.Nature;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;

import java.util.ArrayList;
import java.util.List;

public class GameEng implements GameEngService {
    
    private int score;
    private int turn;
    
    private int sizeColony;
    private int spawnSpeed;
    
    private int nbLemmingsDead;
    private int nbLemmingsSaved;
    private int nbLemmingsActive;
    
    private List<LemmingService> lemmings;
    private LevelService level;
    
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public boolean isAnObstacle(int h, int w) {
        final Nature n = level.getNature(h, w);
        return n == Nature.DIRT || n == Nature.METAL;
    }

    @Override
    public int getSizeColony() {
        return sizeColony;
    }

    @Override
    public int getNbLemmingsDead() {
        return nbLemmingsDead;
    }

    @Override
    public int getNbLemmingsSaved() {
        return nbLemmingsSaved;
    }

    @Override
    public int getNbLemmingsActive() {
        return nbLemmingsActive;
    }

    @Override
    public int getNbLemmingsCreated() {
        return lemmings.size();
    }

    @Override
    public LemmingService getLemming(int num) {
        return lemmings.get(num);
    }

    @Override
    public boolean isActive(int num) {
        return num < lemmings.size();
    }

    @Override
    public List<Integer> getNumLemmingsActive() {
        List<Integer> numLemmingsActive = new ArrayList<>(lemmings.size());
        for (final LemmingService lemming : lemmings) {
            numLemmingsActive.add(lemming.getNum());
        }
        
        return numLemmingsActive;
    }

    @Override
    public int getSpawnSpeed() {
        return spawnSpeed;
    }

    @Override
    public boolean isGameOver() {
        return nbLemmingsSaved + nbLemmingsDead == sizeColony;
    }

    @Override
    public LevelService getLevel() {
        return level;
    }

    @Override
    public void init(int sizeC, int spawnS) { // <----------------------------------------- TO CHANGE (Not all variables are init)
        turn = 0;
        sizeColony = sizeC;
        spawnSpeed = spawnS;
        
        lemmings = new ArrayList<>();
        nbLemmingsSaved = 0;
    }

    @Override
    public void newLemming(int num, int h, int w) {
        LemmingService lemming = new Lemming();
        lemming.init(num, h, w);
        lemmings.add(lemming);
    }

    @Override
    public void callStepLemmings() {
        for (final LemmingService lemming : lemmings) {
            lemming.step();
        }
        
        ++turn;
    }

    @Override
    public void checkSaved() {
        // <----------------------------------------------- TO COMPLETE
        
        nbLemmingsSaved = lemmings.size() - nbLemmingsActive + nbLemmingsDead;
    }

    @Override
    public void checkDead() {
        // <----------------------------------------------- TO COMPLETE
        
        nbLemmingsDead = lemmings.size() - nbLemmingsActive + nbLemmingsSaved;     
    }

    @Override
    public void checkWin() {
        if (isGameOver()) {
            score = nbLemmingsSaved / turn * 100;
        }
    }

}
