package customGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class customMainGUI extends JPanel{

	public customMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // ��ġ������ ����. ��ǥ�� ����.
		init();
	}
	JTabbedPane pane = new JTabbedPane(); // TabPane ����
	
	JTabbedPane createTabbedPane() {      // tab pane �ʱ�ȭ
		pane.addTab("���� ��ȸ �� ���", new CustomerList());   
		pane.addTab("�ŷ���������", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));
		pane.addTab("�Ⱓ���ŷ�����", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));    
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
