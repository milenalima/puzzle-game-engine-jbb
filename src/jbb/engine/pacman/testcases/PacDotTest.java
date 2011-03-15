package jbb.engine.pacman.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jbb.engine.GameOver;
import jbb.engine.Position;
import jbb.engine.pacman.PacMan;
import jbb.engine.pacman.PacWorld;

import org.junit.Before;
import org.junit.Test;

public class PacDotTest {

private PacWorld board;
private PacMan pm;
	
	@Before
	public void setUp() throws Exception {
		board = new PacWorld();
		pm = (PacMan) board.getHero();
	}

	@Test
	public void testPickedUp() {
		/*
		 * Two things to test:
		 * 1. PacMan picks up a pacdot
		 * 2. PacMan picks up a powerpellet
		 */
		// #1
		// a move to the right will pick up a pacdot (6,2).
		try {
			board.playTurn(new Position(6,2));
		} catch (GameOver go) {
			fail("Game over was thrown.");
		}
		// a pacdot is worth 10 points.
		assertEquals(10,pm.getPoints());
		// #2
		// move pacman near a powerpellet
		pm.setPosition(new Position(1,2));
		// pick up the powerpellet
		try {
			board.playTurn(new Position(1,1));
		} catch (GameOver go) {
			fail("Game over was thrown.");
		}
		// a powerpellet is worth 10 points.
		assertEquals(20,pm.getPoints());
		assertTrue(pm.isInvulnerable());
		try {
			// move 9 times
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			board.playTurn(new Position(1,11));
			// check if still invulnerable
			assertTrue(pm.isInvulnerable());
			// one more move should set pacman as vulnerable
			board.playTurn(new Position(1,1));
			assertFalse(pm.isInvulnerable());
		} catch (GameOver go) {
			fail("Game over was thrown.");
		}
	}

}
