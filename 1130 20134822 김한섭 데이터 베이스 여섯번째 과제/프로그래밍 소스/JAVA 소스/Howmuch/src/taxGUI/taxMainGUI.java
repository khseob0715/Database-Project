package taxGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class taxMainGUI extends JPanel{

	public taxMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);  // 배치관리자 없음. 좌표로 설정.
		init();           // 초기 설정     
	}
	JTabbedPane pane = new JTabbedPane(); // TabPane 생성
	
	JTabbedPane createTabbedPane() {      // tab pane 초기화
		pane.addTab("매출조회", new JLabel("업데이트 중입니다.", JLabel.CENTER));
		pane.addTab("매입조회", new JLabel("업데이트 중입니다.", JLabel.CENTER));    
		pane.addTab("합계표", new JLabel("업데이트 중입니다.", JLabel.CENTER));
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
