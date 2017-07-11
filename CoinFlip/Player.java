/*
	Actor: Player
	Fields: name, coinOption
	Methods:
		getRandCoinOption() : String -> Returns the chosen flip
		setCoinOption(String) : void -> Sets the opposite of oppenent's option
		didPlayerWin(String) : void -> Prints whether the current player won or lost
*/
public class Player {
	private String name = "";
	private String coinOption = "";
	public String[] coinValue = {"HEADS", "TAILS"};

	// Constructor
	public Player(String newname) {
		this.name = newname;
	}

	public String getRandCoinOption() {
		int randnum = (Math.random() < 0.5 ? 0 : 1);
		this.coinOption = coinValue[randnum];
		return coinOption;
	}

	public void setCoinOption(String opponentOption) {
		if (opponentOption.equals(coinValue[0])) {
			this.coinOption = coinValue[1];
		}
		else {
			this.coinOption = coinValue[0];
		}
	}

	public void didPlayerWin(String option) {
		if (option == this.coinOption) {
		 	System.out.println(name + " won the game");
		}
		else {
			System.out.println(name + " lost the game");
		}
	}
}
