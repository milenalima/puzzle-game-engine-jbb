package jbb.engine.mouseland.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jbb.engine.Position;
import jbb.engine.mouseland.Mouse;
import jbb.engine.mouseland.MouseHero;
import jbb.engine.mouseland.MouseLand;
import jbb.engine.mouseland.MouseTrap;

import org.junit.Before;
import org.junit.Test;

public class MouseTrapTest {
	
	private MouseLand board;
	private MouseHero mh;
	private Mouse m;
	private MouseTrap mt;
		
	@Before
	public void setUp() throws Exception {
		board = new MouseLand();
		mh = (MouseHero) board.getHero();
		mt = new MouseTrap(new Position(1,13),board);
	}


	@Test
	public void testPickedUp() {
		//If Mouse is on same Tile as MouseTrap
		assertFalse(mt.pickedUp(m));
		//If MouseHero is on same Tile as MouseTrap
		assertTrue(mt.pickedUp(mh));
	}

}
