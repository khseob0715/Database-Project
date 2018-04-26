package Dormitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author khseob0715 
 * 20134822 ���Ѽ� 
 * DB Report GUI DB ����
 */
public class AdmissionDB {
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public static String temp;
	String url = null;
	// ó�� ����� ���� ���̵� ��й�ȣ.
	String id = "system"; // ���̵�
	String pw = "aster0657"; // ��й�ȣ

	public AdmissionDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver load success");

			try {
				url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, id, pw);
				System.out.println("db connet success");
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}

	}

	public void insert(String sql) {
		try {
			// ������ �ۼ��� ���� statement ������
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void select(String sql) {
		try {
			// ������ �ۼ��� ���� statement ������
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.executeUpdate(sql);
			rs = stmt.executeQuery(sql);
			temp="";
			while (rs.next()) {
				temp = rs.getString("Name");
				System.out.println(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close() {
		// db �ڿ� �ݳ�.
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
