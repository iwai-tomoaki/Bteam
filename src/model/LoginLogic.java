package model;
import action.Changenum;

public class LoginLogic extends Changenum{
	public String singi;
	public LoginLogic(User user){
		super(user);
		if(numbar.equals("000446") && user.getPass().equals("1234")){singi = "true";}
		else {
		singi ="false";
		}
	}
}
