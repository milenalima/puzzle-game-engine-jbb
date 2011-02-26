package jbb.engine.pacman;

import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.Position;

public class PacGame {
	
	private PacWorld board;
	
	public PacGame() {
		board = new PacWorld();
	}
	
	public void play() {
		Position newPos;
		Scanner s;
		System.out.println("You must play until the end");
		while (true) {
			System.out.println(board);
			System.out.print("Type next position in form [row](space)[col]: ");
			s = new Scanner(System.in);
			try {
				newPos = new Position(s.nextInt(),s.nextInt());
				board.playTurn(newPos);
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
		PacGame game = new PacGame();
		game.play();
	}
}
