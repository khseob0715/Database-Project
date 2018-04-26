package InventoryGUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseList extends JPanel {

	private String select_sql = "select * from GANSUNG.purchase";
	public static String purchase_data[][] = new String[700][8];
	// static 이기 때문에 처음부터 생성해야됨.
	private JButton jbutton[];
	private JTextField[] field; // Textfield 값 입력 받는 공간
	private JTable purchase_jt;
	private JComboBox<String> typeCombo; // comboBox

	private String PurchaseTableList[] = { "purchase_code" };
	private String productTableList[] = {"PRODUCT_NAME", "Product_home"};
	public PurchaseList() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		MainGUI.MainGUI.mdb.select(3, select_sql);
		Purchase_list_Table(); // table 생성
		Product_list_Button(); // button 생성
		FixLable_JLabel();
		TextField();
		orderArea();
		ComboBox();
	}
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // 글꼴 설정
		g.setFont(f); // 글꼴 지정
		g.drawString("주문내역 조회", 230, 600);
		
		g.drawString("물품 주문", 800, 600);
		g.drawString("※ 제품명 입력 후 규격 검색을 통해 규격을 선택하시기 바랍니다.", 800, 740);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method에 setSize 명령어를 추가하게 되면 component가 정상적으로 작동하지 않음
	}
	private void orderArea() {
		JLabel TextLabel = new JLabel("물품주문하기");
		TextLabel.setSize(100, 100);
		TextLabel.setLocation(1030, 150);
		add(TextLabel);
	}

	public void Purchase_list_Table() {

		String column[] = { "구매날짜", "제품명", "제품규격", "제품수량", "제품단가", "제품생산처", "납품업체", "납품업체번호" };

		purchase_jt = new JTable(purchase_data, column);
		purchase_jt.setSize(1530, 550);
		purchase_jt.setLocation(0, 0);
		add(purchase_jt); // table 생성 및 추가

		JScrollPane sp = new JScrollPane(purchase_jt);
		sp.setSize(1530, 550);
		sp.setLocation(0, 0);
		add(sp); // table의 scroll bar 생성 및 추가

	}

	private void Product_list_Button() {
		JButton[] Button = new JButton[4];
		Button[0] = new JButton(" 조      회 ");
		Button[1] = new JButton(" 전체조회 ");
		Button[2] = new JButton(" 규격검색 ");
		Button[3] = new JButton(" 주문하기 ");
		for (int i = 0; i < 2; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(600, 620 + i * 40);
			add(Button[i]);
		}
		for (int i = 2; i < 4; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1180, 620 + (i - 2) * 40);
			add(Button[i]);
		}

		Button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}

		});

		Button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				MainGUI.MainGUI.mdb.select(3, select_sql);
				remove(purchase_jt); // 기존 삭제
				Purchase_list_Table(); // 재호출

				for (int i = 0; i < 4; i++) {
					field[i].setText("");
				}
			}
		});

		// 규격검색
		Button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = "select * from GANSUNG.product where PRODUCT_NAME = '" + field[2].getText() + "'";
				boolean input = false;

				if (!"".equals(field[2].getText())) {
					// 검색하기
					MainGUI.MainGUI.mdb.select(11, sql);
					remove(typeCombo); // 이전 컴포넌트 지우기 .
					ComboBox();
				} else {
					// Dialog를 띄우기.
				}

			}
		});

		// 주문하기
		Button[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date date = new Date();
				SimpleDateFormat text = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // 포맷 지정

				String sql = "select * from GANSUNG.stock where Stock_name = '";
				sql += field[2].getText() + "'";

				MainGUI.MainGUI.mdb.select(21, sql);

				String insert_sql = "insert into GANSUNG.purchase values('";
				insert_sql += text.format(date) + "','"; /* purchase_code */
				insert_sql += ProductList.Purchase_deliver_code + "','";
				insert_sql += ProductList.Purchase_product_code + "','";
				insert_sql += field[3].getText() + "','";
				insert_sql += text.format(date) + "')";
				MainGUI.MainGUI.mdb.insert(insert_sql);
				
				// 다시 호출하기 
				MainGUI.MainGUI.mdb.select(3, select_sql);
				remove(purchase_jt); // 기존 삭제
				Purchase_list_Table(); // 재호출
			}
		});

	}

	private void FixLable_JLabel() { // 고정된 Label 생성 함수.
		JLabel[] FixLabel = new JLabel[5];
		String[] FixLabelName = { "제품명", "제품생산처", "제품명", "제품규격", "주문수량" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // 테두리를 넣는다.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // 가운대 정렬
			FixLabel[FixLableIndex].setBorder(eb); // 테두리 추가
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int ct = 0; // 보정값
		for (int i = 0; i < 2; i++) {
			FixLabel[i].setLocation(230, 620 + ct++ * 30);
			add(FixLabel[i]);
		}
		ct = 0;
		for (int i = 2; i < 5; i++) {
			FixLabel[i].setLocation(800, 620 + ct++ * 30);
			add(FixLabel[i]);
		}
	}

	// TextField 생성, 배치
	private void TextField() {
		field = new JTextField[4];
		for (int i = 0; i < 2; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(350, 620 + i * 30);
			add(field[i]);
		}
		for (int i = 2; i < 4; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(920, 620 + (i - 2) * 60);
			add(field[i]);
		}
	}

	private void ComboBox() {
		typeCombo = new JComboBox<String>();
		int num = ProductList.type.length;
		for (int i = 0; i < num; i++) {
			typeCombo.addItem(ProductList.type[i]);
		}
		typeCombo.setBounds(920, 650, 230, 30);
		add(typeCombo);
	}

}
