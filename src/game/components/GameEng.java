package game.components;

import game.enums.Nature;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;
import game.services.RequireLevelService;

import java.util.ArrayList;
import java.util.List;

public class GameEng implements
    /* require */
    RequireLevelService,

    /* provide */
    GameEngService {
    private int score;
    private int turn;
    
    private int sizeColony;
    private int spawnSpeed;
    
    private int nbLemmingsDead;
    private int nbLemmingsSaved;
    
    private List<LemmingService> lemmingsActive;
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
        return lemmingsActive.size();
    }

    @Override
    public int getNbLemmingsCreated() {
        return nbLemmingsDead + nbLemmingsSaved + lemmingsActive.size();
    }

    @Override
    public LemmingService getLemming(int num) {
        return lemmingsActive.get(num);
    }

    @Override
    public boolean isActive(int num) {
        return num < lemmingsActive.size();
    }

    @Override
    public List<Integer> getNumLemmingsActive() {
        final List<Integer> numLemmingsActive = new ArrayList<>
                                             (lemmingsActive.size());
        lemmingsActive.forEach(lemming ->
                numLemmingsActive.add(lemming.getNum()));

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
    public void init(int sizeC, int spawnS) {
        turn = 0;
        sizeColony = sizeC;
        spawnSpeed = spawnS;
        
        lemmingsActive = new ArrayList<>();
        nbLemmingsSaved = 0;
        nbLemmingsDead = 0;
    }

    @Override
    public void newLemming(int num) {
        LemmingService lemming = new Lemming();
        lemming.init(num, level.getHEntrance(), level.getWEntrance());
        lemmingsActive.add(lemming);
    }

    @Override
    public void callStepLemmings() {
        lemmingsActive.forEach(lemming -> lemming.step());
        ++turn;
    }

    @Override
    public void checkSaved() {
        lemmingsActive.removeIf(lemming ->
                    lemming.getHPos() == level.getHExit() &&
                    lemming.getWPos() == level.getWExit());

        nbLemmingsSaved = getNbLemmingsCreated() - lemmingsActive.size() +
                          nbLemmingsDead;
    }

    @Override
    public void checkDead() {
        lemmingsActive.removeIf(lemming -> lemming.isDead());
        
        nbLemmingsDead = getNbLemmingsCreated() - lemmingsActive.size() +
                         nbLemmingsSaved;     
    }

    @Override
    public void checkWin() {
        if (isGameOver()) {
            score = nbLemmingsSaved / turn * 100;
        }
    }

    @Override
    public void bindLevelService(LevelService service) {
        level = service;
    }
}
