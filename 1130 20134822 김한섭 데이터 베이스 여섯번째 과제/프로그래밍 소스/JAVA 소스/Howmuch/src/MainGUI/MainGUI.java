package MainGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainGUI extends JFrame{
	// �߽� GUI�̸� ���ҿ� ������ ���ļ� �����Ͽ����ϴ�. 
	private Container contentPane;
	public static MainDB mdb;
	public MainGUI() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	JTabbedPane pane = new JTabbedPane(); // TabPane ����
	JTabbedPane createTabbedPane() {      // tab pane �ʱ�ȭ
		pane.addTab("Home", new HomGUI.HomeMainGUI());   
		pane.addTab("������", new InventoryGUI.InventoryMainGUI());
		pane.addTab("������", new customGUI.customMainGUI());
		pane.addTab("�ŷ�����", new taxGUI.taxMainGUI());
		return pane;
	}
	
	public void init() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(30, 30));
		// ��ġ������ ���� // ���� ���� 30 ���� ���� 30 
		setTitle("��������ö����� �����ý���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane jtabbedpane = createTabbedPane();
		contentPane.add(new TopTitle(),  BorderLayout.NORTH); // ��� ���̾ƿ�
		contentPane.add(jtabbedpane,  BorderLayout.CENTER);   // ����
		
		setSize(1550, 900);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mdb = new MainDB(); // ���� DB ȣ�� // static����
		new MainGUI();      // ���� GUI ȣ��
	}

}