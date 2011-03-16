package jbb.engine.mouseland.testcases;

import static org.junit.Assert.*;
import jbb.engine.Avatar;
import jbb.engine.Position;
import jbb.engine.mouseland.Mouse;
import jbb.engine.mouseland.MouseHero;
import jbb.engine.mouseland.MouseLand;
import jbb.engine.mouseland.MouseTrap;
import jbb.engine.pacman.PacMan;
import jbb.engine.pacman.PacWorld;

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
	}


	@Test
	public void testPickedUp() {
		mh.setPosition(new Position(1,13));
		mh.setTrap();
		assertFalse(mt.pickedUp(m));
		assertFalse(mt.pickedUp(mh));
	}

}
