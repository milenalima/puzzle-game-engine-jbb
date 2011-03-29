package jbb.engine.pacman.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jbb.engine.Position;
import jbb.engine.pacman.PacMan;
import jbb.engine.pacman.PacWorld;

import org.junit.Before;
import org.junit.Test;

public class PacManTest {

private PacWorld board;
private PacMan pm;
		
	@Before
	public void setUp() throws Exception {
		board = new PacWorld();
		pm = (PacMan) board.getHero();
	}

	@Test
	public void testHasGoodie() {
		// pacman can pick up pacdots
		assertTrue(pm.hasGoodie(new Position(6,2)));
		// pacman picks up pacdot beneath ghost
		assertTrue(pm.hasGoodie(new Position(9,3)));
		// pacman cannot pick up walls
		assertFalse(pm.hasGoodie(new Position(0,0)));
	}

	@Test
	public void testGetNextPosition() {
		// pacman should move towards the point on the right
		assertEquals(new Position(6,2),pm.getNextPosition(new Position(6,12)));
		// pacman should not move into a wall
		try {
			pm.getNextPosition(new Position(6,0));
			fail("did not throw exception");
		} catch (IllegalArgumentException ex) {
			//goodgood
		}
		
	}

	@Test
	public void testCollidesWith() {
		/*
		 * two things to test:
		 * 1. a non invulnerable pacman collides with a ghost
		 * 2. an invulnerable pacman collides with a pacman
		 */
		int init_lives = pm.getLives();
		// move to a space away from ghost (9,3)
		pm.setPosition(new Position(9,2));
		// make pacman collide with ghost
		board.playTurn(new Position(9,3));
		assertEquals(init_lives-1, board.getHero().getLives());
		// set pacman as invulnerable
		pm.setInvulnerable(true);
		// move to a space away from ghost (9,3)
		pm.setPosition(new Position(9,2));
		// make pacman collide with ghost
		board.playTurn(new Position(9,3));
		assertEquals(init_lives-1,pm.getLives());
	}

}
