package MainGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDB {
	public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;

	// 참조  테이블을 검색하기 위해서 사용됩니다.  
	public static Statement stmt2;
	public static ResultSet rs2;

	public static int CustomerNum = 0;
	public static int purchase_num = 0;
	// 구매테이블과 고객테이블의 총 수를 계산하기 위한 값. 
	
	String url = null;
	
	// 처음 실행시 쓰는 아이디 비밀번호.
	String id = "system"; // 아이디
	String pw = "aster0657"; // 비밀번호

	public MainDB() {
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
			System.out.println(sql);
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

	public void select2(int num, String sql) {
		// TODO Auto-generated method stub
		try {
			// 쿼리문 작성을 위한 statement 얻어오기
			stmt2 = conn.createStatement();
			// System.out.println("select");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(sql);
		try {
			stmt2.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs2 = stmt2.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (num == 31) { // 참조테이블 검색 sql 명령어 
			try {
				while (rs2.next()) {
					InventoryGUI.PurchaseList.purchase_data[purchase_num][1] = rs2.getString(2); // 제품명
					InventoryGUI.PurchaseList.purchase_data[purchase_num][2] = rs2.getString(3); // 규격
					InventoryGUI.PurchaseList.purchase_data[purchase_num][4] = rs2.getString(4); // 단가
					InventoryGUI.PurchaseList.purchase_data[purchase_num][5] = rs2.getString(5); // 생산처
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (num == 32) {  // 참조테이블 검색 sql 명령어
			try {
				while (rs2.next()) {
					InventoryGUI.PurchaseList.purchase_data[purchase_num][6] = rs2.getString(3); // 납품업체명
					InventoryGUI.PurchaseList.purchase_data[purchase_num][7] = rs2.getString(5); // 번호
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void select(int num, String sql) {
		// TODO Auto-generated method stub
		try {
			// 쿼리문 작성을 위한 statement 얻어오기
			stmt = conn.createStatement();
			// System.out.println("select");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(sql);  // 사용되는 sql 문 출력
			stmt.executeUpdate(sql);
			rs = stmt.executeQuery(sql);
			
			// num 값으로 질의문을 구분하여 각각의 질의에 대한 이벤트를 구현. 
			if (num == 0) { // product
				int data_num = 0;
				int reset = 0;
				for (int a = 0; a < 2880; a++) {
					for (int i = 0; i < 4; i++) {
						InventoryGUI.ProductList.data[reset][i] = " ";
					}
					reset++;
				}
				while (rs.next()) {
					for (int i = 0; i < 4; i++) {
						InventoryGUI.ProductList.data[data_num][i] = rs.getString(i + 2);
					}
					data_num++;
				}
			} else if (num == 1) { // customer
				int data_num = 0;
				int reset = 0;
				for (int a = 0; a < 100; a++) {
					for (int i = 0; i < 5; i++) {
						customGUI.CustomerList.customer_data[reset][i] = " ";
					}
					reset++;
				}
				while (rs.next()) {
					for (int i = 0; i < 5; i++) {
						customGUI.CustomerList.customer_data[data_num][i] = rs.getString(i + 2);
					}
					data_num++;
				}
			} else if (num == 2) { // stock init
				int data_num = 0;
				int reset = 0;
				for (int a = 0; a < 700; a++) {
					for (int i = 0; i < 6; i++) {
						InventoryGUI.StockList.Stock_data[reset][i] = " ";
					}
					reset++;
				}
				while (rs.next()) {
					for (int i = 0; i < 6; i++) {
						InventoryGUI.StockList.Stock_data[data_num][i] = rs.getString(i + 4);
					}
					data_num++;
				}
			} else if (num == 11) { // size search
				int data_num = 0;
				for (int a = 0; a < 100; a++) {
					InventoryGUI.ProductList.type[a] = " ";
				}
				while (rs.next()) {
					InventoryGUI.ProductList.type[data_num] = rs.getString(3);
					data_num++;
				}
			} else if (num == 12) { // size search
				int data_num = 0;
				for (int a = 0; a < 100; a++) {
					InventoryGUI.ProductList.type[a] = " ";
				}
				while (rs.next()) {
					InventoryGUI.ProductList.type[data_num] = rs.getString(5);
					data_num++;
				}
			} else if (num == 21) { // purchase input data
				while (rs.next()) {
					InventoryGUI.ProductList.Purchase_product_code = rs.getString(2);
					InventoryGUI.ProductList.Purchase_deliver_code = rs.getString(3);
				}
			} else if (num == 3) { // purchase init
				purchase_num = 0;
				for (int a = 0; a < 100; a++) {
					for (int i = 0; i < 8; i++) {
						InventoryGUI.PurchaseList.purchase_data[a][i] = " ";
					}
				}
				while (rs.next()) {
					// 제품코드를 통해서 제품명 / 제품규격 / 제품가격 / 생산처를 구해온다.
					String product_select_sql = "select * from GANSUNG.product where PRODUCT_CODE = '";
					product_select_sql += rs.getString(3) + "'";
					String deliver_select_sql = "select * from GANSUNG.deliver where deliver_product_code = '";
                    deliver_select_sql += rs.getString(4) + "'"; // 납품업체 코드 
                    
					InventoryGUI.PurchaseList.purchase_data[purchase_num][0] = rs.getString(5); // date
					InventoryGUI.PurchaseList.purchase_data[purchase_num][3] = rs.getString(4); // num
					select2(31, product_select_sql);
					select2(32, deliver_select_sql);
					purchase_num++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() { // db 자원 반납
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
