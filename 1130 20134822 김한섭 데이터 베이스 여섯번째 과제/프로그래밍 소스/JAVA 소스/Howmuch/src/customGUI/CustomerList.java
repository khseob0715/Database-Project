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
	// static �̱� ������ ó������ �����ؾߵ�.
	private JButton jbutton[];
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private JTable jt;

	private String customerTableList[] = { "CUSTOMER_NAME", "CUSTOMER_PRE", 
			"CUSTOMER_PHONE", "CUSTOMER_LOCATION", "CUSOTMER_EMAIL" };

	public CustomerList() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		MainGUI.MainGUI.mdb.select(1, select_sql);
		insert_sql = insert_default;
		list_Table(); // table ����
		Button(); // button ����
		FixLable_JLabel();
		TextField();
	}
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // �۲� ����
		g.setFont(f); // �۲� ����
		g.drawString("���� ���/��ȸ/����", 1030, 20);
		g.drawString("�� ����� ��� ��������� �Է��Ͽ� �ֽñ� �ٶ��ϴ�. ", 1030, 220);
		g.drawString("�� ��ȸ�� ������ ��� �ϳ��� ���� �̻� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.", 1030, 250);
		
		
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method�� setSize ��ɾ �߰��ϰ� �Ǹ� 
		// component�� ���������� �۵����� ����
	}
	private void list_Table() {
		String column[] = { "����", "��ǥ��", "��ȭ��ȣ", "�ּ�", "Email" };

		jt = new JTable(customer_data, column);
		jt.setSize(1000, 750);
		jt.setLocation(0, 0);
		add(jt); // table ���� �� �߰�

		JScrollPane sp = new JScrollPane(jt);
		sp.setSize(1000, 750);
		sp.setLocation(0, 0);
		add(sp); // table�� scroll bar ���� �� �߰�
	}

	private void Button() {
		JButton[] Button = new JButton[3];
		Button[0] = new JButton(" ��  ��");
		Button[1] = new JButton(" ��  ȸ");
		Button[2] = new JButton(" ��  ��");

		for (int i = 0; i < Button.length; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1400, 40 + i * 40);
			add(Button[i]);
		}
		// ��� ��ư
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

				remove(jt); // ���� ������Ʈ �����
				MainGUI.MainGUI.mdb.select(1, select_sql);
				list_Table(); // ��ȣ��.

				for (int i = 0; i < 5; i++) {
					field[i].setText("");
				}
			}
		});
		// ��ȸ ��ư
		Button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = select_sql + " where ";
				boolean input = false;
				for (int i = 0; i < 5; i++) {
					if (!"".equals(field[i].getText())) {
						input = true; // �׸��� �ϳ��� �Է��� �Ǿ�� �����.
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
				if (input) { // ���� �־ ��ȸ�ϴ� ���
					MainGUI.MainGUI.mdb.select(1, sql);
				} else { // ��ü ��ȸ�� ���
					MainGUI.MainGUI.mdb.select(1, select_sql);
				}
				list_Table();

				for (int i = 0; i < 5; i++) {
					field[i].setText("");
				}
			}
		});
		// ���� ��ư
		Button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String delete_sql = "delete from GANSUNG.customer where ";
				boolean input = false;
				for (int i = 0; i < 5; i++) {
					if (!"".equals(field[i].getText())) {
						input = true; // �׸��� �ϳ��� �Է��� �Ǿ�� �����.
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

				if (input) { // ���� �־ �����ϴ� ���
					MainGUI.MainGUI.mdb.insert(delete_sql);
				} 
				remove(jt); // ���� ������Ʈ �����
				MainGUI.MainGUI.mdb.select(1, select_sql);
				list_Table(); // ��ȣ��.

				for (int i = 0; i < 5; i++) {
					field[i].setText("");
				}
			}
		});
	}

	private void FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[5];
		String[] FixLabelName = { "�����", "��ǥ��", "��ȭ��ȣ", "�ּ�", "Email" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int t = 0;
		for (int i = 0; i < 5; i++) {
			FixLabel[i].setLocation(1030, 40 + t++ * 30);
			add(FixLabel[i]);
		}
	}

	private void TextField() {  // TextField ����, ��ġ
		field = new JTextField[5];
		for (int i = 0; i < 5; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(1150, 40 + i * 30);
			add(field[i]);
		}
	}

}
