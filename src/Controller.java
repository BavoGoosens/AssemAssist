import UserInterface.UserInterFace;


public class Controller {
	
	private UserInterFace ui = new UserInterFace();
	
	public void run(){
		String[] login = ui.loginPrompt();
	}

}
