
public class Player<E> {
	private int PlayerNumber;
	private E position; 
	private int Score; 
	private int numMoves; 
	private int numWins;
	private int avgMoves;
	private double movesToWin;
	
	public Player() {}
	public Player(int PlayerNumber,E position, int Score, int numMoves, int numWins) {
		this.PlayerNumber = PlayerNumber; 
		this.position = position; 
		this.Score = Score;
		this.numWins = numWins; 
		this.numMoves = numMoves;
	
	}
	
	public int getNumWins() {return numWins;}
	public void setNumWins(int numWins) {this.numWins = numWins;}
	public int getPlayerNumber() {return PlayerNumber;}
	public int getScore() {return Score;}
	public int getNumMoves() {return numMoves;}
	public E getPosition() {return position;} 
	public void setMovesToWin(double movesToWin) {this.movesToWin = movesToWin;}
	public double getMovesToWin() {return movesToWin;}
	
	
	
	public void setPlayerNumber(int PlayerNumber) {this.PlayerNumber = PlayerNumber;}
	public void setPosition(E position) {
		if (this.position != null) {
			Space oldPos = (Space)this.position;
			oldPos.setPlayer(null);
			oldPos.setPlayeronBoard(0);
		}
		
		this.position = position;
		
		Space newPos = (Space)position;
		newPos.setPlayer(this);
		newPos.setPlayeronBoard(PlayerNumber);
	} 
	public void setScore(int score) {this.Score = score;}
	public void setNumMoves(int numMoves) {this.numMoves = numMoves;}
	
	public String toString() {return (" Player: " + getPlayerNumber() + ", Space: "+ position.toString() + ", Score: " + getScore()+ ", Number of Moves: "+getNumMoves());}
	
	
	
	

}
