package jbb.engine.pipes;

import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.GameOver;
import jbb.engine.Position;

/**
 * @author Jonathan Gravel, Boris Ionine
 */
public class PipeGame {
	
	private PipeMap board;
	
	public PipeGame(){
		board = new PipeMap();
	}
	
	/**
	 * Start game. This game only ends when a GameOver exception is thrown.
	 */
	public void play() {
		Position newPos;
		Scanner s1;
		System.out.println("You must play until the end");
		while (true) {
			System.out.println(board);
			System.out.print("Type next position in form [row](space)[col]: ");
			s1 = new Scanner(System.in); 
			try {
				newPos = new Position(s1.nextInt(),s1.nextInt());
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
		PipeGame game = new PipeGame();
	
		game.play();
	}
}