package jbb.engine.pacman;

import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.GameOver;
import jbb.engine.Position;

public class PacGame {
	
	private PacWorld board;
	
	public PacGame() {
		board = new PacWorld();
	}
	
	public void play() {
		PacMan hero = (PacMan) board.getHero();
		Position newPos;
		Scanner s;
		System.out.println("You must play until the end");
		while (true) {
			System.out.println("Lives: " + hero.getLives());
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
			} catch (GameOver e) {
				System.out.println(e.getMessage());
				// quit
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		PacGame game = new PacGame();
		game.play();
	}
}
