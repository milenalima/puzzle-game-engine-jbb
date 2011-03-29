package jbb.engine.pacman.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import jbb.engine.Position;
import jbb.engine.pacman.Cam;
import jbb.engine.pacman.Danny;
import jbb.engine.pacman.PacWorld;
import jbb.engine.pacman.Sam;

import org.junit.Before;
import org.junit.Test;

public class GhostTest {
	
	private Sam sam;
	private Cam cam;
	private Danny danny;
	private PacWorld board;
	
	@Before
	public void setUp() throws Exception {
		board = new PacWorld();
		// sam ghost is situated at pos: 5,7
		sam = (Sam) board.getTile(new Position(5,7));
		//cam ghost is situated at pos: 9,3
		cam = (Cam) board.getTile(new Position(9,3));
		// danny ghost is situated at pos: 3,6
		danny = (Danny) board.getTile(new Position(3,6));
	}

	@Test
	public void testHasGoodie() {
		// pacdot at current position: not a goodie for ghost
		assertFalse(sam.hasGoodie(sam.getPosition()));
		// wall at 2,2: not a goodie for ghost
		assertFalse(sam.hasGoodie(new Position(2,2)));
		// pacman at 6,1: not a goodie for ghost
		assertFalse(sam.hasGoodie(new Position(6,1)));
		// pacdot at current position
		assertFalse(cam.hasGoodie(cam.getPosition()));
		// wall at 2,2: not a goodie for ghost
		assertFalse(cam.hasGoodie(new Position(2,2)));
		// pacman at 6,1: not a goodie for ghost
		assertFalse(sam.hasGoodie(new Position(6,1)));
		// pacdot at current position
		assertFalse(danny.hasGoodie(danny.getPosition()));
		// wall at 2,2: not a goodie for ghost
		assertFalse(danny.hasGoodie(new Position(2,2)));
		// pacman at 6,1: not a goodie for ghost
		assertFalse(sam.hasGoodie(new Position(6,1)));
	}

	@Test
	public void testCollidesWith() {
		/*
		 * two things to test:
		 * 1. a ghost collides with a non invulnerable pacman
		 * 2. a ghost collides with an invulnerable pacman
		 */
		int init_lives = board.getHero().getLives();
		// move right beside pacman
		sam.setPosition(new Position(5,1));
		// make pacman collide with ghost
		board.playTurn(new Position(5,1));
		assertEquals(init_lives-1, board.getHero().getLives());
		// move pacman beside a powerpellet (at 1,1)
		board.getHero().setPosition(new Position(1,2));
		board.playTurn(new Position(1,1));
		// move a ghost right beside pacman
		sam.setPosition(new Position(1,2));
		board.playTurn(new Position(1,2));
		assertEquals(0,sam.getLives());
	}

}
