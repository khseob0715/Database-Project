package taxGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class taxMainGUI extends JPanel{

	public taxMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);  // ��ġ������ ����. ��ǥ�� ����.
		init();           // �ʱ� ����     
	}
	JTabbedPane pane = new JTabbedPane(); // TabPane ����
	
	JTabbedPane createTabbedPane() {      // tab pane �ʱ�ȭ
		pane.addTab("������ȸ", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));
		pane.addTab("������ȸ", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));    
		pane.addTab("�հ�ǥ", new JLabel("������Ʈ ���Դϴ�.", JLabel.CENTER));
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
