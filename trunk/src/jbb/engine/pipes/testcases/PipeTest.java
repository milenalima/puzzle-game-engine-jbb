package jbb.engine.pipes.testcases;

import static org.junit.Assert.assertEquals;
import jbb.engine.Position;
import jbb.engine.pipes.Pipe;
import jbb.engine.pipes.PipeMap;
import jbb.engine.pipes.Plumber;

import org.junit.Before;
import org.junit.Test;

public class PipeTest {

	Pipe p = null;
	PipeMap pM = null;
	
	@Before
	public void setUp(){
		pM = new PipeMap();
		p = new Pipe(new Position(4,4), pM, 'I');
	}
	
	@Test
	public void testToString() {
		assertEquals("I", p.toString());
	}

	@Test
	public void testPickedUp() {
		Plumber pl = (Plumber) pM.getHero();
		assertEquals(false, p.pickedUp(pl));
	}

	@Test
	public void testRotate() {
		p.rotate();
		assertEquals(false, p.isOpenLeft());
		assertEquals(false, p.isOpenRight());
		assertEquals(true, p.isOpenTop());
		assertEquals(true, p.isOpenBottom());
	}

	@Test
	public void testGetNumOpenings() {
		assertEquals(2,p.getNumOpenings());
	}
}
