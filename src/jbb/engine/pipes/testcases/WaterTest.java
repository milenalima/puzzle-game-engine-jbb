package jbb.engine.pipes.testcases;

import static org.junit.Assert.*;
import jbb.engine.Position;
import jbb.engine.pipes.Pipe;
import jbb.engine.pipes.PipeMap;
import jbb.engine.pipes.Water;

import org.junit.Before;
import org.junit.Test;

public class WaterTest {

	Pipe p = null;
	PipeMap pM = null;
	Water w = null;
	
	@Before
	public void setUp(){
		pM = new PipeMap();
		p = new Pipe(new Position(4,4), pM, 'I');
		pM.placeItem(p);
		w = new Water(new Position(4,4),pM);
	}
	
	@Test
	public void testHasGoodie() {
		assertEquals(true, w.hasGoodie(new Position(4,4)));
		assertEquals(false, w.hasGoodie(new Position(5,5)));
	}

	@Test
	public void testGetNextPositions() {
		Position[] nextPositions = w.getNextPositions(new Position(4,4));
		assertEquals(new Position(4,3),nextPositions[0]);
		assertEquals(null, nextPositions[1]);
		assertEquals(new Position(4,5), nextPositions[2]);
		assertEquals(null, nextPositions[3]);
	}

}
