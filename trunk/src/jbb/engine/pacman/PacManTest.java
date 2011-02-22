package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Position;

import junit.framework.TestCase;



public class PacManTest extends TestCase {
	
	private static final int HP = 77;
	private static final int LIVES = 2;
	
	private PacWorld board;
	private PacMan pacman;
	
	protected void setUp() {
		board = new PacWorld(5,5);
		pacman = new PacMan(new ImageIcon(), HP, LIVES, board, new Position(0,0));
	}
	
	public void testInit() {
		assertFalse(pacman.getInvulnerable());
		assertEquals(HP, pacman.getHitPoints());
		assertEquals(LIVES, pacman.getLives());
	}
}
