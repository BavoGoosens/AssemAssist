package businessmodel.exceptions;

public class IllegalNumberException extends RuntimeException {
	
	private static final long serialVersionUID = 4944557335300879869L;
	
	private int number;

	public IllegalNumberException() {}

	public IllegalNumberException(String message) {
		super(message);
	}

	public IllegalNumberException(int number) {
		this.setNumber(number);
	}
	
	public IllegalNumberException(int number, String message) {
		super(message);
		this.setNumber(number);
	}
	
	public int getNumber() {
		return this.number;
	}

	private void setNumber(int number) {
		this.number = number;
	}

}
