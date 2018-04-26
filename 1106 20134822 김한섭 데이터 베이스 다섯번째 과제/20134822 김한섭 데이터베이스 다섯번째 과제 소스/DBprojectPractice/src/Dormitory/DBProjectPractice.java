package Dormitory;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;


/**
 * @author khseob0715 
 * 20134822 김한섭 
 * DB Report GUI Main
 */
public class DBProjectPractice extends JFrame {
	private Container contentPane;

	public DBProjectPractice() {
		// TODO Auto-generated constructor stub
		init();  // GUI 초기화 
	}
		 
	JTabbedPane pane = new JTabbedPane(); // Tab 팬 생성.
	
	JTabbedPane createTabbedPane() {      // tab pane 초기화
		pane.addTab("기숙사 입사 신청", new Admission());      // 입사 탭의 레이아웃. 생성. 
		pane.addTab("기숙사 합격 조회", new JLabel("업데이트 중입니다.", JLabel.CENTER));
		pane.addTab("ACE 심리검사 신청", new ACEImage());    // ACE 심리검사 랩 레이아웃. 생성. 
		pane.addTab("상/벌점 조회", new JLabel("업데이트 중입니다.", JLabel.CENTER));
		return pane;
	}

	public void init() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(30, 30)); 
		// 배치 관리자 설정 // 수평 간격 수직 간격 각각 30
		setTitle("조선대학교 백학남학사 사생 관리 시스템");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		JTabbedPane jtabbedpane = createTabbedPane();

		contentPane.add(new Top(), BorderLayout.NORTH);    // 상단 현재 접속자 정보 와 접속 일시 표시
		contentPane.add(jtabbedpane, BorderLayout.CENTER); // 중간 tab pane 표시
		setLocationByPlatform(true);  // 윈도우 팝업이 겹치지 않도록 해주는 메소드.
		
		setSize(1550, 900); // 사이즈 설정 // 가로 세로
		setVisible(true);    // 표시
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DBProjectPractice();	
	}
}
