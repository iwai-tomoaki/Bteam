package model;
import dao.Division_DAO;

public class LoginLogic extends Division_DAO{
	public String singi;
	public boolean kai = false;
	public int a = 0;
	public LoginLogic(User user){
		super();
		int num =user.getNum();
		String pass = user.getPass();
		for(int b : user_int) {
			int inum =user_int.get(b-1);
			String spa=user_str.get(b-1);
			if(inum==num && spa.equals(pass)){
				this.kai=true;
				return;
			}
		}
	}
}
