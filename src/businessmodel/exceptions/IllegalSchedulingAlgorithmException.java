package businessmodel.exceptions;

public class IllegalSchedulingAlgorithmException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public IllegalSchedulingAlgorithmException(String message) {
		super(message);
	}
	
	public IllegalSchedulingAlgorithmException(){
		super("There went something wrong with the scheduling algorithm");
	}
	
}
