package jbb.engine.mouseland.testcases;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.mouseland.Mouse;
import jbb.engine.mouseland.MouseHero;
import jbb.engine.mouseland.MouseLand;
import jbb.engine.mouseland.MouseTrap;

import org.junit.Before;
import org.junit.Test;

public class MouseTest {
		private Mouse mouse;
		private MouseLand board;

	@Before
	public void setUp() throws Exception {
		board = new MouseLand();
		mouse = (Mouse) board.getTile(new Position(1,3));
	}


	@Test
	public void testHasGoodie() {
		MouseTrap mt = new MouseTrap(new Position(1,2), board); 
		board.placeItem(mt);
		//walked to a blank tile
		assertFalse(mouse.hasGoodie(new Position(1,4)));
		//walked into a MouseTrap
		assertFalse(mouse.hasGoodie(new Position(1,2)));
		//walked into a wall
		assertFalse(mouse.hasGoodie(new Position(0,3)));
	}

	@Test
	public void testGetNextPositionPosition() {
		//walked to a blank tile
		assertFalse(mouse.hasGoodie(new Position(1,4)));
		//walked into a blank tile
		assertFalse(mouse.hasGoodie(new Position(1,2)));
		//walked into a wall
		assertFalse(mouse.hasGoodie(new Position(0,3)));
		//walked into a wall
		assertFalse(mouse.hasGoodie(new Position(2,3)));
	}

	@Test
	public void testCollidesWith() {
		MouseHero mh = (MouseHero) board.getHero();
		mh.setPosition(new Position(1,2));
		//walked to a blank tile
		assertFalse(mouse.hasGoodie(new Position(1,4)));
		//walked into a MouseHero
		assertFalse(mouse.hasGoodie(new Position(1,2)));
		//walked into a wall
		assertFalse(mouse.hasGoodie(new Position(0,3)));
	}


}
