
public class UnmatchedParenthesesException extends Exception{
	String message;
	
	public UnmatchedParenthesesException(String message) {
		this.message = message;
	}
	public String getMessage() {return message;}
}
