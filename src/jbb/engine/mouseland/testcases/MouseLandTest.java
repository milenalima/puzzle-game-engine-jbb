package jbb.engine.mouseland.testcases;

import static org.junit.Assert.assertEquals;
import jbb.engine.Hero;
import jbb.engine.Position;
import jbb.engine.mouseland.MouseLand;

import org.junit.Before;
import org.junit.Test;

public class MouseLandTest {
	
	MouseLand ml = null;

	@Before
	public void setUp() throws Exception {
		ml = new MouseLand();
	}

	@Test
	public void testResetPlayingField() {
		Hero hero = ml.getHero();
		Position pos = new Position(hero.getPosition().getRow(),hero.getPosition().getCol());
		hero.setPosition(new Position(14,1));
		ml.resetPlayingField();
		assertEquals(pos,hero.getPosition());
	}

	@Test
	public void testRestartGame() {
		Hero hero = ml.getHero();
		Position pos = new Position(hero.getPosition().getRow(),hero.getPosition().getCol());
		hero.setPosition(new Position(14,1));
		hero.removeLife();
		ml.resetPlayingField();
		assertEquals(pos,hero.getPosition());
	}

}
