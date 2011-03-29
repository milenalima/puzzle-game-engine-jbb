package jbb.engine.pacman.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jbb.engine.Hero;
import jbb.engine.Item;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;
import jbb.engine.pacman.PacDot;
import jbb.engine.pacman.PacMan;
import jbb.engine.pacman.PacWorld;

import org.junit.Before;
import org.junit.Test;

public class PacWorldTest {
	
	PacWorld pw = null;

	@Before
	public void setUp() throws Exception {
		pw = new PacWorld();
	}

	@Test
	public void testResetPlayingField() {
		Hero hero = pw.getHero();
		Position pos = new Position(hero.getPosition().getRow(),hero.getPosition().getCol());
		hero.setPosition(new Position(6,5));
		pw.resetPlayingField();
		assertEquals(pos,hero.getPosition());
	}

	@Test
	public void testPlayTurn() {
		pw.playTurn(new Position(6,6));
	}

	@Test
	public void testGetWidth() {
		assertEquals(PacWorld.WIDTH, pw.getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(PacWorld.HEIGHT, pw.getHeight());
	}

	@Test
	public void testGetItem() {
		// no item at this position
		Item item = pw.getItem(new Position(6,1));
		assertEquals(null, item);
		
		// item in front of PacMan has PacDot
		item = pw.getItem(new Position(6,2));
		if (!(item instanceof PacDot)) fail("Not returning right item");
		
		// this position is a Wall
		item = pw.getItem(new Position(5,2));
		assertEquals(null, item);
	}

	@Test
	public void testGetTile() {
		// should return hero at this position
		Tile tile = pw.getTile(new Position(6,1));
		assertEquals(pw.getHero(), tile);
		
		// item in front of PacMan has PacDot
		tile = pw.getTile(new Position(6,2));
		if (!(tile instanceof PacDot)) fail("Not returning pacdot");
		
		// this position is a Wall
		tile = pw.getTile(new Position(5,2));
		if (!(tile instanceof Wall)) fail("Not returning Wall");
	}

	@Test
	public void testGetHero() {
		PacMan tile = (PacMan) pw.getTile(PacWorld.DEFAULT_PACMAN_POS);
		assertEquals(tile,pw.getHero());
	}

	@Test
	public void testPlaceItem() {
		// no item at this position
		Position pos = new Position(6,1);
		PacDot pd = new PacDot(pos,pw,false);
		pw.placeItem(pd);
		assertEquals(pd, pw.getItem(pos));
		
		// a wall at this position
		pos = new Position(5,2);
		pd = new PacDot(pos,pw,false);
		pw.placeItem(pd);
		assertEquals(pd, pw.getItem(pos));
	}
}
