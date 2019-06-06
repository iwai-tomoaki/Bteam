package model;
import java.io.Serializable;

	public class User implements Serializable
	{
		private int id; //社員ID
		private String name;  //ユーザー名
		private int num;  //社員番号
 		private String pass;  //パスワード
 		private int status; //在席状態
 		private int divi_id; //部署ID
 		private int place_id; //勤務地ID
 		private int auth_id; //権限ID

 		public User() {}

		public User(int id, String name, int num, String pass, int status, int divi_id, int place_id, int auth_id) {
			super();
			this.id = id;
			this.name = name;
			this.num = num;
			this.pass = pass;
			this.status = status;
			this.divi_id = divi_id;
			this.place_id = place_id;
			this.auth_id = auth_id;
		}

		public User(int num, String pass) {
			this.num = num;
			this.pass = pass;
		}

		public int getId() {
			return id;
		}

		public int getNum() {
 			return num;
 		}
 		public String getName() {
 			return name;
 		}
 		public String getPass() {
 			return pass;
 		}
		public int getStatus() {
			return status;
		}
		public int getDivi_id() {
			return divi_id;
		}
		public int getPlace_id() {
			return place_id;
		}
		public int getAuth_id() {
			return auth_id;
		}

	}
//完了
