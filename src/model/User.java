package model;
import java.io.Serializable;

	public class User implements Serializable
	{
		private int emp_id; //社員ID EMP_ID
		private String emp_name;  //ユーザー名 EMP_NAME
		private int emp_num;  //社員番号 EMP_NUM
 		private String pass;  //パスワード PASS
 		private int pres_status; //在席状態 PRES_STATUS
 		private int divi_id; //部署ID DIVI_ID
 		private int place_id; //勤務地ID WORKPLACE_ID
 		private int auth_id; //権限ID AUTH_ID

 		public User() {}

		public User(int emp_id, String emp_name, int emp_num, String pass, int pres_status, int divi_id, int place_id,int auth_id) {
			this.emp_id = emp_id;
			this.emp_name = emp_name;
			this.emp_num = emp_num;
			this.pass = pass;
			this.pres_status = pres_status;
			this.divi_id = divi_id;
			this.place_id = place_id;
			this.auth_id = auth_id;
		}

		public User(String emp_name, int emp_num,int pres_status, int divi_id, int place_id) {
			this.emp_name = emp_name;
			this.emp_num = emp_num;
			this.pres_status = pres_status;
			this.divi_id = divi_id;
			this.place_id = place_id;
		}

		public User(int emp_num, String pass) {
			this.emp_num = emp_num;
			this.pass = pass;
		}

		public User(String pass, String newpass) {
			this.pass = pass;
		}

		public User(int place_id) {
			this.place_id = place_id;
		}

		public User(String emp_name) {
			this.emp_name = emp_name;
		}

		public int getEmp_id() {
			return emp_id;
		}

		public String getEmp_name() {
			return emp_name;
		}

		public int getEmp_num() {
			return emp_num;
		}

		public String getPass() {
			return pass;
		}

		public int getPres_status() {
			return pres_status;
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


		@Override
		public String toString() {
			return this.getEmp_name() +" "+ this.getEmp_num() +" "+ this.getPres_status() +" "+ this.getDivi_id() +" "+ this.getPlace_id();

		}
	}
//完了
