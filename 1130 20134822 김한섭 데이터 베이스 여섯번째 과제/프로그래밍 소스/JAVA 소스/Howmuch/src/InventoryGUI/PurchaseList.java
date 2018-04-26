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
	// static �̱� ������ ó������ �����ؾߵ�.
	private JButton jbutton[];
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private JTable purchase_jt;
	private JComboBox<String> typeCombo; // comboBox

	private String PurchaseTableList[] = { "purchase_code" };
	private String productTableList[] = {"PRODUCT_NAME", "Product_home"};
	public PurchaseList() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		MainGUI.MainGUI.mdb.select(3, select_sql);
		Purchase_list_Table(); // table ����
		Product_list_Button(); // button ����
		FixLable_JLabel();
		TextField();
		orderArea();
		ComboBox();
	}
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // �۲� ����
		g.setFont(f); // �۲� ����
		g.drawString("�ֹ����� ��ȸ", 230, 600);
		
		g.drawString("��ǰ �ֹ�", 800, 600);
		g.drawString("�� ��ǰ�� �Է� �� �԰� �˻��� ���� �԰��� �����Ͻñ� �ٶ��ϴ�.", 800, 740);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method�� setSize ��ɾ �߰��ϰ� �Ǹ� component�� ���������� �۵����� ����
	}
	private void orderArea() {
		JLabel TextLabel = new JLabel("��ǰ�ֹ��ϱ�");
		TextLabel.setSize(100, 100);
		TextLabel.setLocation(1030, 150);
		add(TextLabel);
	}

	public void Purchase_list_Table() {

		String column[] = { "���ų�¥", "��ǰ��", "��ǰ�԰�", "��ǰ����", "��ǰ�ܰ�", "��ǰ����ó", "��ǰ��ü", "��ǰ��ü��ȣ" };

		purchase_jt = new JTable(purchase_data, column);
		purchase_jt.setSize(1530, 550);
		purchase_jt.setLocation(0, 0);
		add(purchase_jt); // table ���� �� �߰�

		JScrollPane sp = new JScrollPane(purchase_jt);
		sp.setSize(1530, 550);
		sp.setLocation(0, 0);
		add(sp); // table�� scroll bar ���� �� �߰�

	}

	private void Product_list_Button() {
		JButton[] Button = new JButton[4];
		Button[0] = new JButton(" ��      ȸ ");
		Button[1] = new JButton(" ��ü��ȸ ");
		Button[2] = new JButton(" �԰ݰ˻� ");
		Button[3] = new JButton(" �ֹ��ϱ� ");
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
				remove(purchase_jt); // ���� ����
				Purchase_list_Table(); // ��ȣ��

				for (int i = 0; i < 4; i++) {
					field[i].setText("");
				}
			}
		});

		// �԰ݰ˻�
		Button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = "select * from GANSUNG.product where PRODUCT_NAME = '" + field[2].getText() + "'";
				boolean input = false;

				if (!"".equals(field[2].getText())) {
					// �˻��ϱ�
					MainGUI.MainGUI.mdb.select(11, sql);
					remove(typeCombo); // ���� ������Ʈ ����� .
					ComboBox();
				} else {
					// Dialog�� ����.
				}

			}
		});

		// �ֹ��ϱ�
		Button[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date date = new Date();
				SimpleDateFormat text = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // ���� ����

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
				
				// �ٽ� ȣ���ϱ� 
				MainGUI.MainGUI.mdb.select(3, select_sql);
				remove(purchase_jt); // ���� ����
				Purchase_list_Table(); // ��ȣ��
			}
		});

	}

	private void FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[5];
		String[] FixLabelName = { "��ǰ��", "��ǰ����ó", "��ǰ��", "��ǰ�԰�", "�ֹ�����" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int ct = 0; // ������
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

	// TextField ����, ��ġ
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
