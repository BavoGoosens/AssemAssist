package businessmodel;

import java.util.HashMap;

/**
 * This class keeps track of all the users of this system.
 * 
 * @author Team 10
 *
 */
public class UserManagement {

	/**
	 * A variable containing all the known Users.
	 */
	private HashMap<String,User> system_users = new HashMap<String,User>();
	
	/**
	 * A constructor to make an User manager. Two mechanics, one manager and one garageholder will be created.
	 */
	public UserManagement(){
		GarageHolder garageholder = new GarageHolder("Bouwe","Ceunen","Bouwe2014", "henk");
		Mechanic mechanic1 = new Mechanic("Sander","Geijsen","Sander2014","henk");
		Mechanic mechanic2 = new Mechanic("Michiel", "Vandendriesche","Michiel2014", "henk");
		Manager manager = new Manager("Bavo", "Goosens", "Bavo2014", "henk");
		system_users = new HashMap<String, User>();
		system_users.put("Bouwe2014", garageholder);
		system_users.put("Sander2014", mechanic1);
		system_users.put("Michiel2014", mechanic2);
		system_users.put("Bavo2014", manager);
	}
	
	/**
	 * This method constructs the UserManagement object and populates the list of users.
	 * 
	 * @param map
	 * 		  A HashMap with all the known system users.
	 */
	public UserManagement(HashMap<String,User> map){
		this.setSystemUsers(map);
	}

	/**
	 * This method returns a User object given the user's username.
	 * 
	 * @param uname the username of the user.
	 * 
	 * @return User
	 * 		   If the User is in the system return the User object.
	 * 
	 */
	public User getUser(String uname){
		return this.system_users.get(uname);
	}

	/**
	 * This method returns all the known users.
	 * 
	 * @return HashMap<String, User>
	 * 		   A HashMap with all the system users.
	 */
	private HashMap<String, User> getSystemUsers(){
		return this.system_users;
	}

	/**
	 * This method populates the HashMap with users.
	 * 
	 * @param system_users
	 * 		  A HashMap with all the system users.
	 */
	private void setSystemUsers(HashMap<String,User> system_users) {
		this.system_users = system_users;
	}

	/**
	 * This method checks if the provided username and password are correct.
	 * 
	 * @param uname The username of the User.
	 * 
	 * @param pwd   The password of the User.
	 * 
	 * @return boolean
	 * 		   true if the provided information is correct. false otherwise.
	 */
	public boolean authenticate(String uname, String pwd){
		if (this.isUserInSystem(uname)){
			return this.getUser(uname).authenticate(uname, pwd);
		}
		return false;
	}

	/**
	 * This method checks if the provided username is known to the system.
	 * 
	 * @param uname The username to be checked
	 * 
	 * @return boolean 
	 * 		   true if the user is found in the system. false otherwise.
	 */
	public boolean isUserInSystem(String uname){
		return this.getSystemUsers().containsKey(uname);
	}

	public boolean canPlaceOrder(User user){
		try{
			if (user instanceof GarageHolder) return true;
		}catch (NullPointerException e){
			return false;
		}
		return false;
	}

	public boolean canControlAssemblyLine(User user){
		try{
			if (user instanceof Manager) return true;
		}catch (NullPointerException e){
			return false;
		}
		return false;
	}

	public boolean isMechanic(User user){
		try{
			if (user instanceof Mechanic) return true;
		}catch (NullPointerException e){
			return false;
		}
		return false;
	}
}
