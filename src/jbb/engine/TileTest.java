package jbb.engine;

import junit.framework.TestCase;

public class TileTest extends TestCase {
	
	private Board board;
	
	public void setUp() {
		board = new Board(10,10);
		// board constructor creates an array of array
		// of Tiles, so I will use these in the test cases.
	}
	
	public void testConstructor() {
		Tile tile1 = new Tile(new Position(0,0), board);
		assertTrue(tile1.getAccessible());
		assertEquals(new Position(0,0), tile1.getPosition());
		
		Tile tile2 = new Tile(new Position(3,3), board);
		assertTrue(tile2.getAccessible());
		assertEquals(new Position(0,0), tile2.getPosition());
		
		try {
			new Tile(new Position(10,0), board);
			assertTrue(false); // should not get here
		} catch (IndexOutOfBoundsException exc) {
			assertEquals("Row out of bounds", exc.getMessage());
		}
		
		try {
			new Tile(new Position(0,10), board);
			assertTrue(false); // should not get here
		} catch (IndexOutOfBoundsException exc) {
			assertEquals("Col out of bounds", exc.getMessage());
		}
	}
	
	public void testSetPosition() {
		Tile tile = new Tile(new Position(0,0), board);
		
		Position pos1 = new Position(2,2);
		tile.setPosition(pos1);
		assertEquals(pos1, tile.getPosition());
		
		Position pos2 = new Position(5,3);
		tile.setPosition(pos2);
		assertEquals(pos2, tile.getPosition());
		
		Position pos3 = new Position(10,0);
		try {
			tile.setPosition(pos3);
			assertTrue(false); // should not get to this point
		} catch (IndexOutOfBoundsException exc) {
			assertEquals("Row out of bounds", exc.getMessage());
		}
		
		Position pos4 = new Position(0,10);
		try {
			tile.setPosition(pos4);
			assertTrue(false); // should not get to this point
		} catch (IndexOutOfBoundsException exc) {
			assertEquals("Col out of bounds", exc.getMessage());
		}
	}
	
	public void testSetAccessible() {
		Tile tile = new Tile(new Position(0,0), board);
		
		assertTrue(tile.getAccessible());
		
		tile.setAccessible(false);
		assertFalse(tile.getAccessible());
		
		tile.setAccessible(true);
		assertTrue(tile.getAccessible());
	}
	
	public void testGetAdjacentTiles() {
		Tile tile1 = new Tile(new Position(0,0), board);
		Tile[] array1 = {null,null,null,
				null,new Tile(new Position(0,1),board),
				null, new Tile(new Position(1,0), board), new Tile(new Position(1,1), board)};
		assertEquals(array1, tile1.getAdjacentTiles());
		
		Tile tile2 = new Tile(new Position(2,2), board);
		Tile[] array2 = {new Tile(new Position(1,1),board),new Tile(new Position(1,2),board),new Tile(new Position(1,3),board),
				new Tile(new Position(2,1),board),new Tile(new Position(2,3), board),
				new Tile(new Position(3,1), board), new Tile(new Position(3,2),board), new Tile(new Position(3,3),board)};
		assertEquals(array2, tile2.getAdjacentTiles());
		
		Tile tile3 = new Tile(new Position(9,5), board);
		Tile[] array3 = {new Tile(new Position(8,4),board),new Tile(new Position(8,5),board),new Tile(new Position(8,6),board),
				new Tile(new Position(9,4),board),new Tile(new Position(9,6),board),
				null, null, null};
		assertEquals(array3, tile3.getAdjacentTiles());
	}
}
