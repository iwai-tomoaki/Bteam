package action;

import java.sql.Connection;
import java.sql.SQLException;

import dao.Division_DAO;

public class Sample{
	public static void main(String[] args) throws SQLException{
		Connection con = Division_DAO.getConnection();
		System.out.println("con=" + con);
		con.close();
	}
}