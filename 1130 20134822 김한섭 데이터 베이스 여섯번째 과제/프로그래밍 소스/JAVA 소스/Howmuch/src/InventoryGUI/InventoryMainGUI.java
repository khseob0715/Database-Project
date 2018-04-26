package InventoryGUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class InventoryMainGUI extends JPanel{

	public InventoryMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // ��ġ������ ����. ��ǥ�� ����.
		init();
	}
	JTabbedPane pane = new JTabbedPane(); // TabPane ����
	
	JTabbedPane createTabbedPane() {      // tab pane �ʱ�ȭ
		pane.addTab("���ǰ����ȸ", new StockList());   
		pane.addTab("���Ű���ǰ����ȸ", new ProductList());    
		pane.addTab("��ǰ�ֹ�����", new PurchaseList());
		
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
