package HomGUI;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class HomeMainGUI extends JPanel{
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private String sql;         // sql ���ǹ�. 
	private String selectTimeString = null;
	private JTextField Snum;    // �й� �Է� �޴� �ʵ�
	
	public HomeMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // ��ġ������ ����. ��ǥ�� ����.
		ImageIcon imageicon = new ImageIcon("homeimg.png");
		JLabel label = new JLabel(imageicon);
		label.setSize(2000, 811);
		label.setLocation(0, 0);
		add(label); // label �߰�
	}

}
