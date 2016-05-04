package game.contracts;

import game.components.Level;
import game.decorators.GameEngDecorator;
import game.services.GameEngService;
import game.services.LemmingService;

import java.util.List;

public class GameEngContract extends GameEngDecorator {
    public GameEngContract(GameEngService delegate) {
        super(delegate);
    }
    
    private void checkInvariant() {
        // \inv: \forall num \in getNumLemmingsActive(), isActive(num)
        final List<Integer> numLemmingsActive = getNumLemmingsActive();
        for (Integer num : numLemmingsActive) {
            if (!isActive(num)) {
                throw new InvariantError("!isActive(num)");
            }
        }
    	
    	// inv: isGameOver() = getNbLemmingsSaved() + getNbLemmingsDead() ==
        //                       getSizeColony() (minimisation)
    	if (!(isGameOver() ==
    			(getNbLemmingsSaved() + getNbLemmingsDead() == getSizeColony()))) {
    		throw new InvariantError("!(isGameOver() == (getNbLemmingsSaved() + getNbLemmingsDead() == getSizeColony()))");
    	}
    	
        // \inv: getNbLemmingsActive() = card(getNumLemmingsActive())
        //        (minimisation)
    	if (!(getNbLemmingsActive() == getNumLemmingsActive().size())) {
    		throw new InvariantError("!(getNbLemmingsActive() = card(getNumLemmingsActive()))");
    	}
    	
        // \inv: getNbLemmingsCreated() = getNbLemmingsDead() +
        //           getNbLemmingsSaved() + getNbLemmingsActive() (minimisation)
    	if (!(getNbLemmingsCreated() ==
    		getNbLemmingsDead() + getNbLemmingsSaved() + getNbLemmingsActive())) {
            throw new InvariantError("!(getNbLemmingsCreated() == getNbLemmingsDead() + getNbLemmingsSaved() + getNbLemmingsActive())");
    		
    	}
    	
    	// \inv: getSizeColony() ≥ getNbLemmingsCreated()
    	if (!(getSizeColony() >= getNbLemmingsCreated())) {
            throw new InvariantError("!(getSizeColony() >= getNbLemmingsCreated())");
    	}
    	
        // \inv: getNbLemmingsActive() ≥ 0
    	if (!(getNbLemmingsActive() >= 0)) {
    	    throw new InvariantError("!(getNbLemmingsActive() >= 0)");
    	}
    	
        // \inv: getNbLemmingsDead()≥ 0
    	if (!(getNbLemmingsDead() >= 0)) {
            throw new InvariantError("!(getNbLemmingsDead() >= 0)");
    	}
        
        // \inv: getNbLemmingsSaved() ≥ 0
    	if (!(getNbLemmingsSaved() >= 0)) {
            throw new InvariantError("!(getNbLemmingsSaved() >= 0)");
    	}
        
        // \inv: getNbLemmingsCreated() ≥ 0
    	if (!(getNbLemmingsCreated()>= 0)) {
            throw new InvariantError("!(getNbLemmingsCreated()>= 0)");
    	}
    }
    
    @Override
    public int getScore() {
        /* Pre-condition(s) */
        // \pre: isGameOver()
        if (!isGameOver()) {
            throw new PreconditionError("!isGameOver()");
        }
        
        return super.getScore();
    }
    
    @Override
    public LemmingService getLemming(int num) {
        /* Pre-condition(s) */
        // \pre: isActive(num)
        if (!isActive(num)) {
            throw new PreconditionError("!isActive(num)");
        }
        
        return super.getLemming(num);
    }
    
    @Override
    public void init(int sizeC, int spawnS) {
        /* Initialisation */
        super.init(sizeC, spawnS);
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getTurn() == 0
        if (!(getTurn() == 0)) {
            throw new PostconditionError("!(getTurn() == 0)");
        }
        
        // \post: getSizeColony() == sizeC
        if (!(getSizeColony() == sizeC)) {
            throw new PostconditionError("!(getSizeColony() == sizeC)");
        }
        
        // \post: getNbLemmingsSaved() == 0
        if (!(getNbLemmingsSaved() == 0)) {
            throw new PostconditionError("!(getNbLemmingsSaved() == 0)");
        }
        
        // \post: getNbLemmingsDead() == 0
        if (!(getNbLemmingsDead() == 0)) {
            throw new PostconditionError("!(getNbLemmingsDead() == 0)");
        }
        
        // \post: card(getNumLemmingsActive()) == 0
        if (!(getNumLemmingsActive().size() == 0)) {
            throw new PostconditionError("!(card(getNumLemmingsActive()) == 0)");
        }
        
        // \post: getSpawnSpeed() == spawnS
        if (!(getSpawnSpeed() == spawnS)) {
            throw new PostconditionError("!(getSpawnSpeed() == spawnS)");
        }
        
        // \post: getLevelInit() == getLevel() (copy)
        if (!(Level.areEqual(getLevelInit(), getLevel()))) {
            throw new PostconditionError("!(getLevelInit() == getLevel())");
        }
    }
    
    @Override
    public void executeTurn() {
        /* Pre-invariant */
        checkInvariant();
        
        /* Pre-condition(s) */
        // \pre: !isGameOver()
        if (isGameOver()) {
            throw new PreconditionError("isGameOver()");
        }
        
        /* Capture(s) */
        final int turn_atPre = getTurn();
//      final List<Integer> numLemmingsCreated_atPre = getNumLemmingsActive();
        final int nbLemmingsCreate_atPre = getNbLemmingsCreated();
        final int spawnSpeed = getSpawnSpeed();

        /* Processing */
        super.executeTurn();
        
        /* Post-invariant */
        checkInvariant();
        
        /* Post-condition(s) */
        // \post: getTurn() == getTurn()@pre + 1
        if (!(getTurn() == turn_atPre + 1)) {
            throw new PostconditionError("!(getTurn() == getTurn()@pre + 1)");
        }
        
        // \post: \forall num \in getNumLemmingsActive()@pre,
        //              if getLevel().getNature(getLemming(num).getHPos(), getLemming(num).getWPos()) == Nature::EXIT then:
        //                  num \not \in getNumLemmingsActive() ^ getNbLemmingsSaved()++
//      for (Integer num : numLemmingsCreated_atPre) {
//            if (getLevel().getNature(getLemming(num).getHPos(), getLemming(num).getWPos()) == Nature.EXIT) {
//                if (!(!getNumLemmingsActive().contains(num))) {
//                    throw new PostconditionError("!(num \\not \\in getNumLemmingsActive() ^ getNbLemmingsSaved()++)");
//                }
//            }
//      }
          
        // \post: \forall num \in getNumLemmingsActive()@pre,
        //              if getLemming(num).isDead() then:
        //                  num \not \in getNumLemmingsActive() ^ getNbLemmingsDead()++
//      for (Integer num : numLemmingsCreated_atPre) {
//            if (getLemming(num).isDead()) {
//                if (!(!getNumLemmingsActive().contains(num))) {
//                    throw new PostconditionError("!(num \\not \\in getNumLemmingsActive() ^ getNbLemmingsDead()++)");
//                }
//             }
//      }
        
        // \post: if getNbLemmingsCreated()@pre * getSpawnSpeed()@pre == getTurn()@pre ^ getNbLemmingsCreated()@pre < getSizeColony() then:
        //             getLemming(getNbLemmingsCreated()@pre) == Lemming::init(getNbLemmingsCreated(), getLevel().getHEntrance(), getLevel().getWEntrance())
        if (nbLemmingsCreate_atPre * spawnSpeed == turn_atPre && nbLemmingsCreate_atPre < getSizeColony()) {
            if (!(getLemming(nbLemmingsCreate_atPre) != null)) {
                throw new PostconditionError("!(getLemming(getNbLemmingsCreated()@pre) == Lemming::init(getNbLemmingsCreated(), getLevel().getHEntrance(), getLevel().getWEntrance())");
            }
        }
        
        // \post: if isGameOver() then:
        //          getScore() == (getNbLemmingsSaved() / getTurn()@pre) * 100
        if (isGameOver()) {
            if (!(getScore() == (int) ((double) getNbLemmingsSaved() / turn_atPre * 100))) {
                throw new PostconditionError("!(getScore() == (getNbLemmingsSaved() / getTurn()@pre) * 100)");
            }
        }
    }
}
