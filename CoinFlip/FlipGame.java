import java.io.*;
import java.util.*;

/*
	Main class
	Creates the coinGame and starts it.
	Plays in loop based on user input
*/
public class FlipGame {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CoinGame theCoinGame = new CoinGame("Tom", "Mark");
		String usersAnswer = "";

		do {
			theCoinGame.start();

			System.out.println("Play Again?");

			usersAnswer = scan.next();
		} while((usersAnswer.startsWith("y") || usersAnswer.startsWith("Y")));
	}
}