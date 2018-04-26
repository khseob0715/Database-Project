package Dormitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author khseob0715 
 * 20134822 김한섭 
 * DB Report GUI DB 연동
 */
public class AdmissionDB {
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public static String temp;
	String url = null;
	// 처음 실행시 쓰는 아이디 비밀번호.
	String id = "system"; // 아이디
	String pw = "aster0657"; // 비밀번호

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
			// 쿼리문 작성을 위한 statement 얻어오기
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
			// 쿼리문 작성을 위한 statement 얻어오기
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
		// db 자원 반납.
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
