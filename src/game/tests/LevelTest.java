package game.tests;

import game.components.Level;
import game.services.LevelService;
import game.contracts.LevelContract;
import game.contracts.PostconditionError;
import game.contracts.PreconditionError;
import game.contracts.InvariantError;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LevelTest {
	private LevelService level;
	
	@Before
	public void beforeTests(){
		level = new LevelContract(new Level());
	}
	
	@Test
	// OK
	public void testNaturePre1_1() {
		
		try {
		}
		catch (PostconditionError e) {
	
		}
	}
}
