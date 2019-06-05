package model;
import java.io.Serializable;

	public class User implements Serializable
	{
		private int num;  //社員番号
		private String name;  //ユーザー名
 		private String pass;  //パスワード
 		
 		public User() {}
 		public User(String name)
 		{
 			this.name = name;
 		}
 		public User(int num, String pass)
 		{
 			this.num = num;
 			this.pass = pass;
 		}
 		public int getNum() {return num;}
 		public String getName() {return name;}
 		public String getPass() {return pass;}
	}
//完了
