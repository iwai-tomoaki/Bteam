package model;
import java.io.Serializable;

	public class User implements Serializable
	{
		private int emp_id; //社員ID EMP_ID
		private String name;  //ユーザー名 EMP_NAME
		private int num;  //社員番号 EMP_NUM
 		private String pass;  //パスワード PASS
 		private int status; //在席状態 PRES_STATUS
 		private int divi_id; //部署ID DIVI_ID
 		private int place_id; //勤務地ID WORKPLACE_ID
 		private int auth_id; //権限ID AUTH_ID

 		public User() {}

		public User(int emp_id, String name, int num, String pass, int status, int divi_id, int place_id, int auth_id) {
			super();
			this.emp_id = emp_id;
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

		public User(String pass, String newpass) {
			this.pass = pass;
		}

		public int getEmp_id() {
			return emp_id;
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
