/**
 * The MouseGame Class is the class that runs the game
 * 
 * @author Bruno Colantonio
 */

package jbb.engine.mouseland;
import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.GameOver;
import jbb.engine.Position;
import jbb.engine.pacman.PacMan;

public class MouseGame{
	
	private MouseLand board;
	
/**
 * Constructor for MouseGame initialises a new board
 * 
 */
	public MouseGame() {
		board = new MouseLand();
	}
/**
 * the play method runs the game, it asked for users input and with that input
 * the what happens to the next move in the game and if an error occurs and exception
 * will occur.
 * 
 */	
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
			System.out.print("Do you want to drop a mouse trap y/n: "); 
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
	
/**
 * The main method initialised a new MouseGame and calls the play function.
 * 
 */
	  public static void main(String[] args) {
		MouseGame game = new MouseGame();
	
		game.play();
	}
}

