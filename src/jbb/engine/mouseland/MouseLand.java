package jbb.engine.mouseland;


import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;

public class MouseLand extends Board{
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	public MouseLand() {
		super(WIDTH, HEIGHT);
		movableTiles.add(new MouseHero(new Position(4,2), this));
		movableTiles.add(new Mouse(new Position(2,2), this));
		syncItemMapAndField(movableTiles);
	}
	
	@Override
	protected void populateItemMap() {
		itemMap[0][0] = new Tile(new Position(0,0),this);
		itemMap[0][1] = new Tile(new Position(0,1),this);
		itemMap[0][2] = new Tile(new Position(0,2),this);
		itemMap[0][3] = new Tile(new Position(0,3),this);
		itemMap[0][4] = new Tile(new Position(0,4),this);
		
		itemMap[1][0] = new Tile(new Position(1,0),this);
		itemMap[1][1] = new Wall(new Position(1,1),this);
		itemMap[1][2] = new Wall(new Position(1,2),this);
		itemMap[1][3] = new Wall(new Position(1,3),this);
		itemMap[1][4] = new Tile(new Position(1,4),this);
		
		itemMap[2][0] = new Tile(new Position(2,0),this);
		itemMap[2][1] = new Tile(new Position(2,1),this);
		itemMap[2][2] = new Tile(new Position(2,2),this);
		itemMap[2][3] = new Tile(new Position(2,3),this);
		itemMap[2][4] = new Tile(new Position(2,4),this);

		itemMap[3][0] = new Tile(new Position(3,0),this);
		itemMap[3][1] = new Wall(new Position(3,1),this);
		itemMap[3][2] = new Wall(new Position(3,2),this);
		itemMap[3][3] = new Wall(new Position(3,3),this);
		itemMap[3][4] = new Tile(new Position(3,4),this);
		
		itemMap[4][0] = new Tile(new Position(4,0),this);
		itemMap[4][1] = new Tile(new Position(4,1),this);
		itemMap[4][2] = new Tile(new Position(4,2),this);
		itemMap[4][3] = new Tile(new Position(4,3),this);
		itemMap[4][4] = new Tile(new Position(4,4),this);
	}

	@Override
	public void resetPlayingField() {
		// TODO Auto-generated method stub
	}
}
