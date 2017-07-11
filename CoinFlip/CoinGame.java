/*
	Major class encapsulating both actors player and coin
	Fields: Players, coin.
	Methods:
		start -> starts the game by randomly picking a player to choose a flip
				 sets the opponent player the flip
				 gets the value from coin
				 checks with the player on who won
*/
public class CoinGame {
	private Player[] players = new Player[2];
	private Coin theCoin = new Coin();

	public CoinGame(String player1name, String player2name) {
		this.players[0] = new Player(player1name);
		this.players[1] = new Player(player2name);
	}

	public void start() {
		int randnum = (Math.random() < 0.5 ? 0 : 1);
		String playersPick = this.players[randnum].getRandCoinOption();
		int opponentIndex = 1 - randnum;
		this.players[opponentIndex].setCoinOption(playersPick);

		String winningFlip = this.theCoin.getCoinOption();
		this.players[0].didPlayerWin(winningFlip);
		this.players[1].didPlayerWin(winningFlip);
	}
}