package customGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class customMainGUI extends JPanel{

	public customMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // 배치관리자 없음. 좌표로 설정.
		init();
	}
	JTabbedPane pane = new JTabbedPane(); // TabPane 생성
	
	JTabbedPane createTabbedPane() {      // tab pane 초기화
		pane.addTab("고객사 조회 및 등록", new CustomerList());   
		pane.addTab("거래내역관리", new JLabel("업데이트 중입니다.", JLabel.CENTER));
		pane.addTab("기간별거래집계", new JLabel("업데이트 중입니다.", JLabel.CENTER));    
		return pane;
	}
	
	public void init() {
		
		JTabbedPane jtabbedpane = createTabbedPane();
		jtabbedpane.setSize(1550, 900);
		jtabbedpane.setLocation(0,0);
		add(jtabbedpane);
		setVisible(true);
		
	}
}
