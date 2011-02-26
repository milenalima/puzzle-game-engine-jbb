package jbb.engine.mouseland;


import jbb.engine.*;

public class MouseWorld extends Board{
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	public MouseWorld() {
		super(WIDTH, HEIGHT);
	}
	
	@Override
	protected void populatePlayingField()
	{
		// Hero is always at the end of the list;
		moveableTiles.add(new Mouse(new Position(2,2), this));
		moveableTiles.add(new MouseMan(new Position(4,2), this));
		playingField[0][0] = new MouseTrap(new Position(0,0),this, true);
		playingField[0][1] = new MouseTrap(new Position(0,1),this, false);
		playingField[0][2] = new MouseTrap(new Position(0,2),this, false);
		playingField[0][3] = new MouseTrap(new Position(0,3),this, false);
		playingField[0][4] = new MouseTrap(new Position(0,4),this, false);
		
		playingField[1][0] = new MouseTrap(new Position(1,0),this, false);
		playingField[1][1] = new Wall(new Position(1,1),this);
		playingField[1][2] = new Wall(new Position(1,2),this);
		playingField[1][3] = new Wall(new Position(1,3),this);
		playingField[1][4] = new MouseTrap(new Position(1,4),this, false);
		
		playingField[2][0] = new MouseTrap(new Position(2,0),this, false);
		playingField[2][1] = new MouseTrap(new Position(2,1),this, false);
		playingField[2][2] = moveableTiles.get(moveableTiles.size()-2);
		playingField[2][3] = new Tile(new Position(2,3),this);
		playingField[2][4] = new MouseTrap(new Position(2,4),this, false);

		playingField[3][0] = new MouseTrap(new Position(3,0),this, false);
		playingField[3][1] = new Wall(new Position(3,1),this);
		playingField[3][2] = new Wall(new Position(3,2),this);
		playingField[3][3] = new Wall(new Position(3,3),this);
		playingField[3][4] = new MouseTrap(new Position(3,4),this, false);
		
		playingField[4][0] = new MouseTrap(new Position(4,0),this, false);
		playingField[4][1] = new MouseTrap(new Position(4,1),this, false);
		playingField[4][2] = moveableTiles.get(moveableTiles.size()-1);
		playingField[4][3] = new MouseTrap(new Position(4,3),this, false);
		playingField[4][4] = new MouseTrap(new Position(4,4),this, false);
	}
	
	public static void main(String[] args) {
		MouseWorld pw = new MouseWorld();
		System.out.println(pw.toString());
		pw.playTurn(new Position(4,0));
		System.out.println(pw.toString());
		pw.playTurn(new Position(4,0));
		System.out.println(pw.toString());
		pw.playTurn(new Position(0,0));
		System.out.println(pw.toString());
		pw.playTurn(new Position(0,0));
		System.out.println(pw.toString());
		pw.playTurn(new Position(0,0));
	}
}