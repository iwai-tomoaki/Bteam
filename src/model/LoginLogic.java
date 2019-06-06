package model;

public class LoginLogic
{
	public boolean execute(User user)
	{
		int numda = user.getNum();
		String numbar = String.valueOf(numda);
		int numm = numbar.length();
		StringBuilder nu = new StringBuilder(numbar);
		for(int a=numm;a<6;a++) {
			nu.insert(0,"0");
		}
		numbar = nu.toString();
		if(numbar.equals("000446") && user.getPass().equals("1234")){return true;}
		return false;
	}
}
