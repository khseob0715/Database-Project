package customGUI;

import javax.swing.JButton;
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

public class CustomerList extends JPanel {

	private String select_sql = "select * from GANSUNG.customer";
	private String insert_sql = "";
	private String insert_default = "insert into GANSUNG.customer values('";
	public static String customer_data[][] = new String[100][5];
	// static 이기 때문에 처음부터 생성해야됨.
	private JButton jbutton[];
	private JTextField[] field; // Textfield 값 입력 받는 공간
	private JTable jt;

	private String customerTableList[] = { "CUSTOMER_NAME", "CUSTOMER_PRE", 
			"CUSTOMER_PHONE", "CUSTOMER_LOCATION", "CUSOTMER_EMAIL" };

	public CustomerList() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		MainGUI.MainGUI.mdb.select(1, select_sql);
		insert_sql = insert_default;
		list_Table(); // table 생성
		Button(); // button 생성
		FixLable_JLabel();
		TextField();
	}
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // 글꼴 설정
		g.setFont(f); // 글꼴 지정
		g.drawString("고객사 등록/조회/삭제", 1030, 20);
		g.drawString("※ 등록의 경우 모든정보를 입력하여 주시기 바랍니다. ", 1030, 220);
		g.drawString("※ 조회와 삭제의 경우 하나의 정보 이상 입력하여 주시기 바랍니다.", 1030, 250);
		
		
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method에 setSize 명령어를 추가하게 되면 
		// component가 정상적으로 작동하지 않음
	}
	private void list_Table() {
		String column[] = { "고객사", "대표자", "전화번호", "주소", "Email" };

		jt = new JTable(customer_data, column);
		jt.setSize(1000, 750);
		jt.setLocation(0, 0);
		add(jt); // table 생성 및 추가

		JScrollPane sp = new JScrollPane(jt);
		sp.setSize(1000, 750);
		sp.setLocation(0, 0);
		add(sp); // table의 scroll bar 생성 및 추가
	}

	private void Button() {
		JButton[] Button = new JButton[3];
		Button[0] = new JButton(" 등  록");
		Button[1] = new JButton(" 조  회");
		Button[2] = new JButton(" 삭  제");

		for (int i = 0; i < Button.length; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1400, 40 + i * 40);
			add(Button[i]);
		}
		// 등록 버튼
		Button[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date date = new Date();
				SimpleDateFormat text = new SimpleDateFormat("yyyyMMddhhmmss"); 
				insert_sql += "customer_" + text.format(date) + "','";
				insert_sql += field[0].getText() + "','";
				insert_sql += field[1].getText() + "','";
				insert_sql += field[2].getText() + "','";
				insert_sql += field[3].getText() + "','";
				insert_sql += field[4].getText() + "')";
				MainGUI.MainGUI.mdb.insert(insert_sql);
				insert_sql = insert_default;

				remove(jt); // 이전 컴포넌트 지우기
				MainGUI.MainGUI.mdb.select(1, select_sql);
				list_Table(); // 재호출.

				for (int i = 0; i < 5; i++) {
					field[i].setText("");
				}
			}
		});
		// 조회 버튼
		Button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = select_sql + " where ";
				boolean input = false;
				for (int i = 0; i < 5; i++) {
					if (!"".equals(field[i].getText())) {
						input = true; // 항목중 하나라도 입력이 되어야 실행됨.
					}
				}
				boolean first = false;
				for (int i = 0; i < 5; i++) {
					if (!"".equals(field[i].getText())) {
						if (first) {
							sql += " AND ";
						}
						sql += customerTableList[i];
						sql += " Like '%";
						sql += field[i].getText();
						sql += "%' ";
						first = true;
					}
				}
				if (input) { // 값이 있어서 조회하는 경우
					MainGUI.MainGUI.mdb.select(1, sql);
				} else { // 전체 조회의 경우
					MainGUI.MainGUI.mdb.select(1, select_sql);
				}
				list_Table();

				for (int i = 0; i < 5; i++) {
					field[i].setText("");
				}
			}
		});
		// 삭제 버튼
		Button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String delete_sql = "delete from GANSUNG.customer where ";
				boolean input = false;
				for (int i = 0; i < 5; i++) {
					if (!"".equals(field[i].getText())) {
						input = true; // 항목중 하나라도 입력이 되어야 실행됨.
					}
				}
				boolean first = false;
				for (int i = 0; i < 5; i++) {
					if (!"".equals(field[i].getText())) {
						if (first) {
							delete_sql += " AND ";
						}
						delete_sql += customerTableList[i];
						delete_sql += " Like '%";
						delete_sql += field[i].getText();
						delete_sql += "%' ";
						first = true;
					}
				}

				if (input) { // 값이 있어서 삭제하는 경우
					MainGUI.MainGUI.mdb.insert(delete_sql);
				} 
				remove(jt); // 이전 컴포넌트 지우기
				MainGUI.MainGUI.mdb.select(1, select_sql);
				list_Table(); // 재호출.

				for (int i = 0; i < 5; i++) {
					field[i].setText("");
				}
			}
		});
	}

	private void FixLable_JLabel() { // 고정된 Label 생성 함수.
		JLabel[] FixLabel = new JLabel[5];
		String[] FixLabelName = { "고객사명", "대표자", "전화번호", "주소", "Email" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // 테두리를 넣는다.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // 가운대 정렬
			FixLabel[FixLableIndex].setBorder(eb); // 테두리 추가
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int t = 0;
		for (int i = 0; i < 5; i++) {
			FixLabel[i].setLocation(1030, 40 + t++ * 30);
			add(FixLabel[i]);
		}
	}

	private void TextField() {  // TextField 생성, 배치
		field = new JTextField[5];
		for (int i = 0; i < 5; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(1150, 40 + i * 30);
			add(field[i]);
		}
	}

}
