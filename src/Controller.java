import java.util.HashMap;

import BusinessModel.User;
import UserInterface.UserInterFace;

/**
 * This class is the main controller instance. 
 * It handles all the communication between the user interface and the business logic layer.
 * @author BatCave
 *
 */
public class Controller {
	
	private HashMap<String,User> system_users = new HashMap<String,User>();
	
	private UserInterFace ui = new UserInterFace();
	
	public void run(){
		User current_user;
		boolean auth = false;
		while (auth == false) {
			String[] login = ui.loginPrompt();
			current_user = getUser(login[0]);
			if (current_user == null ) {
				ui.displayString("We could not find you in the System \n \n");
			} else {
				auth = current_user.authenticate(login[0],login[1]);
			}
		}
		
	}

	private User getUser(String uname) {
		return this.system_users.get(uname);
	}

	private void setSystemUsers(HashMap<String,User> system_users) {
		this.system_users = system_users;
	}

}
