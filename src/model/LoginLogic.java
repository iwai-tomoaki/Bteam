package model;
import action.Changenum;

public class LoginLogic extends Changenum{
	public String singi;
	public LoginLogic(User user){
		super(user);
		if(numbar.equals("000001") && user.getPass().equals("R9DB3")){singi = "true";}
		else {
		singi ="false";
		}
	}
}
