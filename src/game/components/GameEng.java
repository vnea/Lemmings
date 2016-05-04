package game.components;

import game.contracts.LemmingContract;
import game.enums.Nature;
import game.services.GameEngService;
import game.services.LemmingService;
import game.services.LevelService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameEng implements
    /* require */
    /*RequireLevelService,*/

    /* provide */
    GameEngService {
    private int score;
    private int turn;
    
    private int sizeColony;
    private int spawnSpeed;
    
    private int nbLemmingsCreated;
    private int nbLemmingsDead;
    private int nbLemmingsSaved;
    
    private List<LemmingService> lemmingsActive;
    private LevelService level;
    private LevelService levelInit;
    
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getTurn() {
        return turn;
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
        return nbLemmingsCreated;
    }

    @Override
    public LemmingService getLemming(int num) {
        return (lemmingsActive.stream().filter(lemming -> 
                                lemming.getNum() == num))
               .collect(Collectors.toList()).get(0);
    }

    @Override
    public boolean isActive(int num) {
        return lemmingsActive.stream().anyMatch(lemming ->
                                                lemming.getNum() == num);
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
    public LevelService getLevelInit() {
    	return levelInit;
    }

    @Override
    public void init(int sizeC, int spawnS) {
        turn = 0;
        sizeColony = sizeC;
        spawnSpeed = spawnS;
        
        lemmingsActive = new ArrayList<>();
        nbLemmingsCreated = 0;
        nbLemmingsSaved = 0;
        nbLemmingsDead = 0;
        
        levelInit = new Level(level);        
    }

    public void executeTurn() {
        // Call step for each lemming
        lemmingsActive.forEach(lemming -> lemming.step());

        // Checkers
        checkSaved();
        checkDead();
        checkWin();
        
        // Create new lemming if needed
        if (nbLemmingsCreated * spawnSpeed == turn &&
            nbLemmingsCreated < sizeColony) {
            newLemming();
        }
        
        // Next turn
        ++turn;
    }
    
    @Override
    public void bindLevelService(LevelService service) {
        level = service;
    }
    
    private void newLemming() {
        LemmingContract lemming = new LemmingContract(new Lemming());
        lemming.bindLevelService(level);
        lemming.init(nbLemmingsCreated, level.getHEntrance(), level.getWEntrance());
        
        lemmingsActive.add(lemming);
        ++nbLemmingsCreated;
    }

    private void checkSaved() {
        lemmingsActive.removeIf(lemming -> {
                if (level.getNature(lemming.getHPos(), lemming.getWPos()) == Nature.EXIT) {
                    ++nbLemmingsSaved;
                    return true;
                }
                
                return false;
        });
    }

    private void checkDead() {
        lemmingsActive.removeIf(lemming -> {
            if (lemming.isDead()) {
                ++nbLemmingsDead;
                return true;
            }
            
            return false;
        });
    }

    private void checkWin() {
        if (isGameOver()) {
            score = (int) ((double) nbLemmingsSaved / turn * 100);
        }
    }
    
    public static boolean areEqual(GameEngService gameEngine, GameEngService other) {
        if (gameEngine.getTurn() != other.getTurn()) {
            return false;
        }
        
        if (gameEngine.getSizeColony() != other.getSizeColony()) {
            return false;
        }
        
        if (gameEngine.getSpawnSpeed() != other.getSpawnSpeed()) {
            return false;
        }
        
        if (gameEngine.getNbLemmingsActive() != other.getNbLemmingsActive()) {
            return false;
        }
        
        if (gameEngine.getNbLemmingsSaved() != other.getNbLemmingsSaved()) {
            return false;
        }
        
        if (gameEngine.getNbLemmingsDead() != other.getNbLemmingsDead()) {
            return false;
        }
        
        gameEngine.getNumLemmingsActive().equals(other.getNumLemmingsActive());
        
        if (!Level.areEqual(gameEngine.getLevel(), other.getLevel())) {
            return false;
        }
        
        if (!Level.areEqual(gameEngine.getLevelInit(), other.getLevelInit())) {
            return false;
        }
        
        return true;
    }
}
