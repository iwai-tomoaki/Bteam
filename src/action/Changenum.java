package action;

import model.User;

public class Changenum {
	public String numbar =null;
	public Changenum(User user){
		int numda = user.getNum();
		String numbar = String.valueOf(numda);
		int numm = numbar.length();
		StringBuilder nu = new StringBuilder(numbar);
		for(int a=numm;a<6;a++) {
			nu.insert(0,"0");
		}
		this.numbar = nu.toString();
	}
}
