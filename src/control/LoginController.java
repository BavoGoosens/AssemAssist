package control;

public interface LoginController extends Controller {
	
	public void login(String uname, String password);

	public void register(String fname, String lname, String username, String type);

}
