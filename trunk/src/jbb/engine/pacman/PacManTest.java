package jbb.engine.pacman;

import jbb.engine.Position;
import junit.framework.TestCase;



public class PacManTest extends TestCase {
	
	private PacWorld board;
	private PacMan pacman;
	
	protected void setUp() {
		board = new PacWorld();
		pacman = new PacMan(new Position(0,0), board);
	}
	
	public void testInit() {
		assertEquals(new Position(0,0), pacman.getPosition());
		assertEquals(0, pacman.getPoints());
		assertFalse(pacman.getInvulnerable());
		assertEquals(PacMan.LIVES, pacman.getLives());
	}
	
	public void testAddLife() {
		assertEquals(PacMan.LIVES, pacman.getLives());
		pacman.addLife();
		assertEquals(PacMan.LIVES + 1, pacman.getLives());
		pacman.addLife();
		pacman.addLife();
		pacman.addLife();
		assertEquals(PacMan.LIVES + 4, pacman.getLives());
	}
	
	public void testRemoveLife() {
		for (int i = 0; i < PacMan.LIVES - 1; i++)
			assertFalse(pacman.removeLife());
		assertTrue(pacman.removeLife()); // take away last life
		assertTrue(pacman.removeLife()); // keep taking
	}
	
	public void testAddPoints() {
		pacman.addPoints(20);
		assertEquals(20,pacman.getPoints());
		pacman.addPoints(-30);
		assertEquals(-10,pacman.getPoints());
		// negative points are allowed, it just means you're really bad
	}
	
	public void testSubtractPoints() {
		pacman.subtractPoints(20);
		assertEquals(-20,pacman.getPoints());
		// negative points are allowed, it just means you're really bad
		pacman.subtractPoints(-30);
		assertEquals(10,pacman.getPoints());
	}
	
	public void testSetInvulnerable() {
		/*
		// assuming PacMan.INVULN_LEN = 10;
		assertFalse(pacman.getInvulnerable());
		pacman.setInvulnerable(true);
		assertTrue(pacman.getInvulnerable());
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 1)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 2)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 3)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 4)
		assertTrue(pacman.getInvulnerable());
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 5)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 6)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 7)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 8)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 9)
		assertTrue(pacman.getInvulnerable());
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 10)
		assertFalse(pacman.getInvulnerable());
		
		// assuming PacMan.INVULN_LEN = 10;
		pacman.setInvulnerable(true);
		assertTrue(pacman.getInvulnerable());
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 1)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 2)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 3)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 4)
		assertTrue(pacman.getInvulnerable());
		pacman.setInvulnerable(true); // should reset timer
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 1)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 2)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 3)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 4)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 5)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 6)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 7)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 8)
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()+1)); // move right (turn 9)
		assertTrue(pacman.getInvulnerable());
		pacman.moveTo(new Position(pacman.getPosition().getRow(),pacman.getPosition().getCol()-1)); // move left (turn 10)
		assertFalse(pacman.getInvulnerable());
		
		// testing setInvulnearable(false);
		pacman.setInvulnerable(true);
		assertTrue(pacman.getInvulnerable());
		pacman.setInvulnerable(false);
		assertFalse(pacman.getInvulnerable());
		*/
	}
	
	public void testMoveTo() {
		
	}
	
	public void testToString() {
		
	}
}
