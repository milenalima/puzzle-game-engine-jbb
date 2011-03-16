package jbb.engine.pipes.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jbb.engine.GameOver;
import jbb.engine.Position;
import jbb.engine.pipes.PipeMap;

import org.junit.Before;
import org.junit.Test;

public class PipeMapTest {

	PipeMap pM = null;
	
	@Before
	public void setUp(){
		pM = new PipeMap();
	}

	@Test
	public void testPlayTurn() {
		try {
			pM.playTurn(new Position(6,6));
		} catch (GameOver e) {
			fail("No exception should be raised here");
		} catch (IllegalArgumentException i){
			assertEquals("Not a possible move", i.getMessage());
		}
		
		try {
			pM.playTurn(new Position(5,5));
		} catch (GameOver e) {
			fail("No exception should be raised here");
		} catch (IllegalArgumentException i){
			fail("No exception should have been raised here");
		}
		
	}

	@Test
	public void testRestartGame() {
		pM.restartGame();
		assertEquals(new Position(1,1), pM.getHero().getPosition());
	}
	
	@Test
	public void testRunWaterPressed() {
		try {
			pM.runWaterPressed();
			fail("Exception should have been thrown.");
		}
		catch(GameOver g)
		{
			assertEquals("YOU LOSE (YOU HAVE A LEAK)!", g.getMessage());
		}
	}

}
