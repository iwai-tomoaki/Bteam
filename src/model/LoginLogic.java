package model;
import dao.Division_DAO;

public class LoginLogic extends Division_DAO{
	public String singi;
	public LoginLogic(User user,int a){
		super(user,a);
		for(int b = 0;)
		if(numbar.equals("000001") && user.getPass().equals("R9DB3")){singi = "true";}
		else {
		singi ="false";
		}
	}
}
