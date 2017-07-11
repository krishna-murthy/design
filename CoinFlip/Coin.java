/*
	Actor: Coin
	Fields: coinOption
	Methods:
		getCoinOption() : String -> Returns the chosen coin option
*/
public class Coin {
	private String coinOption = "";
	public String[] coinValue = {"HEADS", "TAILS"};

	public Coin() {
		int randnum = (Math.random() < 0.5 ? 0 : 1);
		this.coinOption = coinValue[randnum];
	}

	public String getCoinOption() {
		return this.coinOption;
	}
}