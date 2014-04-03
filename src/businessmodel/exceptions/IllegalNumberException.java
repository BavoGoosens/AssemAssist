package businessmodel.exceptions;

public class IllegalNumberException extends RuntimeException {
	
	private static final long serialVersionUID = 4944557335300879869L;
	
	private int number;

	public IllegalNumberException() {}

	public IllegalNumberException(String message) {
		super(message);
	}

	public IllegalNumberException(int number) {
		this.number = number;
	}
	
	public IllegalNumberException(int number, String message) {
		super(message);
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}

}
