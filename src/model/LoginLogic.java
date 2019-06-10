package model;
import dao.EmployeeDAO;

public class LoginLogic extends EmployeeDAO{
	public boolean true_or_false = false;		//Loginサーブレットでログインの判定に使う
	public LoginLogic(User user){
		super();			//継承でEmployeeDAOの引数なしのメソッドに接続
		int input_num =user.getEmp_num();			//入力した社員番号をUserクラスから取得
		String input_pass = user.getPass();		//入力したパスワードをUserクラスから取得
		int roop = emp_num.size();			//EmployeeDAOで取得したuser_intの行数(要素数)をroop変数に代入
		for(int roop_order = 0; roop_order < roop; roop_order ++) {		//取得した要素数分ループ
			int emp_num_order = emp_num.get(roop_order);			//ArrayList<Integer>の(roop_order)番目の中身をint型に変換してuser_num_orderに代入
			String pass_order = pass.get(roop_order);	//ArrayList<String>の(roop_order)番目の中身をString型に変換してuser_pass_orderに代入
			if(emp_num_order == input_num && pass_order.equals(input_pass)){		//入力した社員番号とパスワードがテーブル内から取得した社員番号とパスワードに一致するか判定
				this.true_or_false = true;		//一致した場合true_or_falseにtrueを入れる
				return;		//社員番号とパスワードが一致するのは一組だけなので一致したものがあったらreturnでLoginサーブレットに戻る
			}
		}
	}
}
