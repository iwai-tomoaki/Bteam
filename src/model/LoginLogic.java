package model;
import dao.Division_DAO;

public class LoginLogic extends Division_DAO{
	public boolean kai = false;
	public int a = 0;
	public LoginLogic(User user){
		super();
		int num =user.getNum();
		String pass = user.getPass();
		int roop = user_int.size();
		for(int b= 0;b<roop;b++) {
			int inum =user_int.get(b);
			String spa=user_str.get(b);
			if(inum==num && spa.equals(pass)){
				this.kai=true;
				return;
			}
		}
	}
}
