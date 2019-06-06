package model;

public class LoginLogic
{
	public boolean execute(User user)
	{
		if(user.getNum()==(446) && user.getPass().equals("1234")){return true;}
		return false;
	}
}
