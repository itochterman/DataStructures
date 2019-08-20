
public class Space<E>{
	private int value; 
	private int position; 
	private String type; 
	private int PlayerOnBoard;
	private Player player;
	
	public <E>Space() {}
	public <E> Space(int value, int position, String type, int PlayerOnBoard, Player player) {
		this.value = value; 
		this.position = position; 
		this.type = type; 
		this.PlayerOnBoard = PlayerOnBoard;
		this.player = player;
		
	
		
	}
	public Player getPlayer() {return player;}
	public void setPlayer(Player player) {this.player = player;}
	public void setPlayeronBoard(int PlayerOnBoard) {this.PlayerOnBoard = PlayerOnBoard;}
	public void setPosition(int position) {this.position = position;}
	public void setType(String type) {this.type = type;}
	public void setValue(int value) {this.value = value;}
	public String toString() {
		return(getValue() + (PlayerOnBoard > 0 ? (", P: "+ PlayerOnBoard) : "") );
	}
	public int getPosition() {return position;}
	public int getValue() {return value;} 
	public String getType() {return type;} 
	public int getPlayerOnBoard() {return PlayerOnBoard;}
	
}


