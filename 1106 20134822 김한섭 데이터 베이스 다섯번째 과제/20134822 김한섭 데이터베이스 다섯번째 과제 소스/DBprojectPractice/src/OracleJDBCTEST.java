
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JLabel;

public class OracleJDBCTEST {
	Connection conn = null;
	private JLabel lb = new JLabel("이것은 탭 팬입니다.");
	String url = null;
	// 처음 실행시 쓰는 아이디 비밀번호. 
	String id = "system";      // 아이디
	String pw = "aster0657";   // 비밀번호
	
	public OracleJDBCTEST() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver load success");
			
			try {
				url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url,id,pw);
				System.out.println("db connet success");
			}catch(SQLException e){
				System.out.println(e);
			}
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OracleJDBCTEST();
	}

}
