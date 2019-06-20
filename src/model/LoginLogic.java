package model;
import java.util.List;

import dao.EmployeeDAO;

public class LoginLogic{
	public boolean true_or_false = false;		//Loginサーブレットでログインの判定に使う
	public String user_name = null;
	public int auth = 0;
	public LoginLogic(User user){
		EmployeeDAO dao = new EmployeeDAO();
		List<User> login = dao.UserDAO(user);
		if(login.size() == 1) {
			this.true_or_false = true;		//一致した場合true_or_falseにtrueを入れる
			String user_name_order = login.get(0).getEmp_name();		//ArrayList<String>の(roop_order)番目の中身をString型に変換してuser_name_orderに代入
			this.auth = login.get(0).getAuth_id();			//その社員の権限番号をauthに代入
			this.user_name = user_name_order;		//取得したuser_name_orderをuser_nameに代入
			return;		//社員番号とパスワードが一致するのは一組だけなので一致したものがあったらreturnでLoginサーブレットに戻る
		}
	}
}
