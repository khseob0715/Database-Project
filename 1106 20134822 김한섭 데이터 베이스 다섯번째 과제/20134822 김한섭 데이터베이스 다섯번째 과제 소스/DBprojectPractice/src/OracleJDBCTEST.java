
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JLabel;

public class OracleJDBCTEST {
	Connection conn = null;
	private JLabel lb = new JLabel("�̰��� �� ���Դϴ�.");
	String url = null;
	// ó�� ����� ���� ���̵� ��й�ȣ. 
	String id = "system";      // ���̵�
	String pw = "aster0657";   // ��й�ȣ
	
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
