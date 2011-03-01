package jbb.engine.mouseland;
import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.GameOver;
import jbb.engine.Position;
import jbb.engine.pacman.PacMan;

public class MouseGame{
	
	private MouseLand board;
	
	public MouseGame() {
		board = new MouseLand();
	}
	
	public void play() {
		MouseHero mh = (MouseHero) board.getHero();
		Position newPos;
		Scanner s1;
		Scanner s2;
		System.out.println("You must play until the end");
		while (true) {
			System.out.println(board);
			System.out.println("Lives: " + mh.getLives());
			System.out.println("Traps: " + mh.getNumMouseTraps());
			System.out.print("Press 'y' to drop mousetrap or anything else not to: "); 
			s1 = new Scanner(System.in);
			if(s1.hasNext("y")){
				mh.setTrap();
			}
			System.out.print("Type next position in form [row](space)[col]: "); 
			s2 = new Scanner(System.in);
			try {
				newPos = new Position(s2.nextInt(),s2.nextInt());
				try {
					board.playTurn(newPos);
				} catch (GameOver go) {
					System.out.println(go.getMessage());
					// quit
					break;
				}
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println(ioobe.getMessage());
			} catch (InputMismatchException ime) {
				System.out.println("Invalid Input)");
			}
		}
	
	}
	  
	  public static void main(String[] args) {
		MouseGame game = new MouseGame();
	
		game.play();
	}
}

