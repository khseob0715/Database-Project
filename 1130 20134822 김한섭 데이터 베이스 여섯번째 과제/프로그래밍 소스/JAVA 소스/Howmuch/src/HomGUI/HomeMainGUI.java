package HomGUI;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class HomeMainGUI extends JPanel{
	private JTextField[] field; // Textfield 값 입력 받는 공간
	private String sql;         // sql 질의문. 
	private String selectTimeString = null;
	private JTextField Snum;    // 학번 입력 받는 필드
	
	public HomeMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // 배치관리자 없음. 좌표로 설정.
		ImageIcon imageicon = new ImageIcon("homeimg.png");
		JLabel label = new JLabel(imageicon);
		label.setSize(2000, 811);
		label.setLocation(0, 0);
		add(label); // label 추가
	}

}
