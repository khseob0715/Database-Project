package InventoryGUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class InventoryMainGUI extends JPanel{

	public InventoryMainGUI() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // 배치관리자 없음. 좌표로 설정.
		init();
	}
	JTabbedPane pane = new JTabbedPane(); // TabPane 생성
	
	JTabbedPane createTabbedPane() {      // tab pane 초기화
		pane.addTab("재고품목조회", new StockList());   
		pane.addTab("구매가능품목조회", new ProductList());    
		pane.addTab("물품주문내역", new PurchaseList());
		
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
