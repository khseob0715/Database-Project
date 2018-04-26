package Dormitory;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;


/**
 * @author khseob0715 
 * 20134822 ���Ѽ� 
 * DB Report GUI Main
 */
public class DBProjectPractice extends JFrame {
	private Container contentPane;

	public DBProjectPractice() {
		// TODO Auto-generated constructor stub
		init();  // GUI �ʱ�ȭ 
	}
		 
	JTabbedPane pane = new JTabbedPane(); // Tab �� ����.
	
	JTabbedPane createTabbedPane() {      // tab pane �ʱ�ȭ
		pane.addTab("����� �Ի� ��û", new Admission());      // �Ի� ���� ���̾ƿ�. ����. 
		pane.addTab("����� �հ� ��ȸ", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));
		pane.addTab("ACE �ɸ��˻� ��û", new ACEImage());    // ACE �ɸ��˻� �� ���̾ƿ�. ����. 
		pane.addTab("��/���� ��ȸ", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));
		return pane;
	}

	public void init() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(30, 30)); 
		// ��ġ ������ ���� // ���� ���� ���� ���� ���� 30
		setTitle("�������б� ���г��л� ��� ���� �ý���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		JTabbedPane jtabbedpane = createTabbedPane();

		contentPane.add(new Top(), BorderLayout.NORTH);    // ��� ���� ������ ���� �� ���� �Ͻ� ǥ��
		contentPane.add(jtabbedpane, BorderLayout.CENTER); // �߰� tab pane ǥ��
		setLocationByPlatform(true);  // ������ �˾��� ��ġ�� �ʵ��� ���ִ� �޼ҵ�.
		
		setSize(1550, 900); // ������ ���� // ���� ����
		setVisible(true);    // ǥ��
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DBProjectPractice();	
	}
}
