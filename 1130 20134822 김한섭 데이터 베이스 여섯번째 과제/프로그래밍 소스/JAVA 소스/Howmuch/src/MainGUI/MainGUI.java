package MainGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainGUI extends JFrame{
	// 중심 GUI이며 탭팬에 탭팬을 겹쳐서 구현하였습니다. 
	private Container contentPane;
	public static MainDB mdb;
	public MainGUI() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	JTabbedPane pane = new JTabbedPane(); // TabPane 생성
	JTabbedPane createTabbedPane() {      // tab pane 초기화
		pane.addTab("Home", new HomGUI.HomeMainGUI());   
		pane.addTab("재고관리", new InventoryGUI.InventoryMainGUI());
		pane.addTab("고객관리", new customGUI.customMainGUI());
		pane.addTab("거래내역", new taxGUI.taxMainGUI());
		return pane;
	}
	
	public void init() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(30, 30));
		// 배치관리자 설정 // 수평 간격 30 수직 간겨 30 
		setTitle("광성공구철물상사 관리시스템");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane jtabbedpane = createTabbedPane();
		contentPane.add(new TopTitle(),  BorderLayout.NORTH); // 상당 레이아웃
		contentPane.add(jtabbedpane,  BorderLayout.CENTER);   // 내용
		
		setSize(1550, 900);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mdb = new MainDB(); // 메인 DB 호출 // static선언
		new MainGUI();      // 메인 GUI 호출
	}

}