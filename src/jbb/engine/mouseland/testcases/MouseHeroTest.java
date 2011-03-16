package jbb.engine.mouseland.testcases;

import static org.junit.Assert.*;
import jbb.engine.Position;
import jbb.engine.mouseland.Mouse;
import jbb.engine.mouseland.MouseHero;
import jbb.engine.mouseland.MouseLand;
import jbb.engine.mouseland.MouseTrap;

import org.junit.Before;
import org.junit.Test;

public class MouseHeroTest {

	private MouseHero mh;
	private MouseLand board;
	
	@Before
	public void setUp() throws Exception {
		board = new MouseLand();
		mh = (MouseHero) board.getTile(new Position(13,1));
	}

	@Test
	public void testHasGoodie() {
		MouseTrap mt = new MouseTrap(new Position(12,1), board); 
		board.placeItem(mt);
		//walked to a MouseTrap
		assertFalse(mh.hasGoodie(new Position(12,1)));
		//walked into a wall
		assertFalse(mh.hasGoodie(new Position(12,2)));
		//walked into a blank tile
		assertFalse(mh.hasGoodie(new Position(13,2)));

	}

	@Test
	public void testGetNextPositionPosition() {
		//walked to a blank tile
		assertFalse(mh.hasGoodie(new Position(14,1)));
		//walked into a blank tile
		assertFalse(mh.hasGoodie(new Position(13,2)));
		//walked into a wall
		assertFalse(mh.hasGoodie(new Position(13,0)));
		//walked into a blank tile
		assertFalse(mh.hasGoodie(new Position(12,1)));
	}

	@Test
	public void testCollidesWith() {
		Mouse m = new Mouse(new Position(12,1), board);
		//walked to a blank tile
		assertFalse(mh.hasGoodie(new Position(14,1)));
		//walked into a blank tile
		assertFalse(mh.hasGoodie(new Position(13,2)));
		//walked into a wall
		assertFalse(mh.hasGoodie(new Position(13,0)));
		//walked into a Mouse
		assertFalse(mh.hasGoodie(new Position(12,1)));
	}


}
