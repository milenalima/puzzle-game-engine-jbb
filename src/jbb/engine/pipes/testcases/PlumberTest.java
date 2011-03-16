package jbb.engine.pipes.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jbb.engine.GameOver;
import jbb.engine.Position;
import jbb.engine.pipes.Pipe;
import jbb.engine.pipes.PipeMap;
import jbb.engine.pipes.Plumber;

import org.junit.Before;
import org.junit.Test;

public class PlumberTest {
	
	private PipeMap board;
	private Plumber pl;

	@Before
	public void setUp() throws Exception {
		board = new PipeMap();
		pl = (Plumber) board.getHero();
	}

	@Test
	public void testHasGoodie() {
		// plumber has a pipe
		assertTrue(pl.hasGoodie(new Position(2,1)));
		// plumber does no have an empty tile
		assertFalse(pl.hasGoodie(new Position(4,4)));
	}

	@Test
	public void testGetNextPosition() {
		// plumber moves directly to space played
		try {
			board.playTurn(new Position(4,4));
		} catch (GameOver e) {
			fail("game over thrown");
		}
		assertEquals(new Position(4,4),pl.getPosition());
		// plumber does not move into a wall
		try {
			board.playTurn(new Position(0,0));
			fail("exception not thrown");
		} catch (IllegalArgumentException e) {
			// good
		} catch (GameOver e) {
			fail("game over thrown");
		}
		assertEquals(new Position(4,4),pl.getPosition());
	}
	
	@Test
	public void testPlacePipe() {
		pl.acquireNextPipeType();
		char type = pl.getNextPipeType();
		pl.placePipe();
		Pipe pipe = (Pipe) board.getItem(pl.getPosition());
		assertEquals(type, pipe.getPipeType());
	}

}
