package game.contracts;

import game.decorators.GameEngDecorator;
import game.services.GameEngService;

public class GameEngContract extends GameEngDecorator {
    public GameEngContract(GameEngService delegate) {
        super(delegate);
    }
    
//    private void checkInvariant() {
//        // \inv: isActive(num) = num \in getNumLemmingsActive()
//    	
//    	// inv: isGameOver() = getNbLemmingsSaved() + getNbLemmingsDead() ==
//        //                       getSizeColony() (minimisation)
//    	if (!(isGameOver() ==
//    			(getNbLemmingsSaved() + getSizeColony() == getSizeColony()))) {
//    		throw new InvariantError("!(isGameOver() == (getNbLemmingsSaved() + getSizeColony() == getSizeColony()))");
//    	}
//    	
//        // \inv: getNbLemmingsActive() = card(getNumLemmingsActive())
//        //        (minimisation)
//    	if (!(getNbLemmingsActive() == getNumLemmingsActive().size())) {
//    		throw new InvariantError("!(getNbLemmingsActive() = card(getNumLemmingsActive()))");
//    	}
//    	
//        // \inv: getNbLemmingsCreated() = getNbLemmingsDead() +
//        //           getNbLemmingsSaved() + getNbLemmingsActive() (minimisation)
//    	if (!(getNbLemmingsCreated() ==
//    		getNbLemmingsDead() + getNbLemmingsSaved() + getNbLemmingsActive())) {
//    		
//    	}
//    }
}
