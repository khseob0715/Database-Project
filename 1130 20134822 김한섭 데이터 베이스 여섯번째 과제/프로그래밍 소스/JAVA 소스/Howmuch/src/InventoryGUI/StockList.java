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

public class StockList extends JPanel {

	private String select_sql = "select * from GANSUNG.stock";
	public static String Stock_data[][] = new String[700][6];
	// static �̱� ������ ó������ �����ؾߵ�.
	private JButton jbutton[];
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private JTable jt;

	private JComboBox<String> typeCombo; // comboBox

	private String stockTableList[] = { "STOCK_NAME", "STOCK_HOME" };

	public StockList() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		MainGUI.MainGUI.mdb.select(2, select_sql);
		Product_list_Table(); // table ����
		Product_list_Button(); // button ����
		Stock_list_Button();
		FixLable_JLabel();
		Stock_FixLable_JLabel();
		TextField();
		ComboBox();
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // �۲� ����
		g.setFont(f); // �۲� ����
		g.drawString("��� ��ȸ", 1030, 20);
		g.drawString("�� ���� �����ϰ� �ִ� ��ǰ�� ������ ��ȸ �����մϴ�.", 1030, 160);
		g.drawString("�� ��ȸ���� �ʴ� ��ǰ�� ���Ű���ǰ����ȸ �޴����� Ȯ�� �ٶ��ϴ�.", 1030, 190);
		g.drawString("��� ����", 1030, 280);
		g.drawString("�� ��ǰ�� �Է� �� �԰� �˻��� ���� �԰��� �����Ͻñ� �ٶ��ϴ�.", 1030, 450);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method�� setSize ��ɾ �߰��ϰ� �Ǹ� component�� ���������� �۵����� ����
	}

	private void Product_list_Table() {
		String column[] = { "��ǰ��", "��ǰ�԰�", "������", "�ǸŰ���", "��ǰ����", "��ǰ����ó" };

		jt = new JTable(Stock_data, column);
		jt.setSize(1000, 750);
		jt.setLocation(0, 0);
		add(jt); // table ���� �� �߰�

		JScrollPane sp = new JScrollPane(jt);
		sp.setSize(1000, 750);
		sp.setLocation(0, 0);
		add(sp); // table�� scroll bar ���� �� �߰�

	}

	private void Product_list_Button() {
		JButton[] Button = new JButton[2];
		Button[0] = new JButton(" ��      ȸ");
		Button[1] = new JButton(" ��ü��ȸ");

		for (int i = 0; i < Button.length; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1400, 40 + i * 40);
			add(Button[i]);
		}
		// ��ȸ
		Button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = select_sql + " where ";
				boolean input = false;
				for (int i = 0; i < 2; i++) {
					if (!"".equals(field[i].getText())) {
						// System.out.println(field[i].getText());
						input = true; // �׸��� �ϳ��� �Է��� �Ǿ�� �����.
					}
				}

				boolean first = false;
				for (int i = 0; i < 2; i++) {
					if (!"".equals(field[i].getText())) {
						if (first) {
							sql += " AND ";
						}
						sql += stockTableList[i];
						sql += " Like '%";
						sql += field[i].getText();
						sql += "%' ";
						first = true;
					}
				}

				if (input) {
					MainGUI.MainGUI.mdb.select(2, sql);
					Product_list_Table();
				}

				for (int i = 0; i < 2; i++) {
					field[i].setText("");
				}
			}

		});
		// ��ü��ȸ
		Button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				MainGUI.MainGUI.mdb.select(2, select_sql);
				Product_list_Table();
				for (int i = 0; i < 2; i++) {
					field[i].setText("");
				}
			}
		});
	}

	private void Stock_list_Button() {
		JButton[] Button = new JButton[3];
		Button[0] = new JButton(" �԰ݰ˻�");
		Button[1] = new JButton(" ��      ��");
		Button[2] = new JButton(" ��ü��ȸ");

		for (int i = 0; i < Button.length; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1400, 300 + i * 40);
			add(Button[i]);
		}
		// �԰ݰ˻�
		Button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = select_sql + " where Stock_NAME = '" + field[2].getText() + "'";
				boolean input = false;

				if (!"".equals(field[2].getText())) {
					// �˻��ϱ�
					MainGUI.MainGUI.mdb.select(12, sql);
					remove(typeCombo); // ���� ������Ʈ ����� .
					ComboBox();
				}
			}
		});
		// ����
		Button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String update_sql = "update GANSUNG.Stock set ";
				boolean first = false;
				boolean second = false;
				if (!"".equals(field[3].getText())) {
					update_sql += "Stock_num = '" + field[3].getText() +"'"; 
					first = true;
				}
				if (!"".equals(field[4].getText())) {
					if(first) {
						update_sql += ", ";
					}
					update_sql += "Stock_Price = '" + field[4].getText() +"'"; 
					second = true;
				}
				
				if(first && second) {
					update_sql += " where Stock_Name = '" + field[2].getText() + "' AND ";
					update_sql += " Stock_size = '" + typeCombo.getItemAt(typeCombo.getSelectedIndex()) + "'";
				}else if(first) {
					update_sql += " where Stock_Name = '" + field[2].getText() + "'";
				}else if(second) {
					update_sql += " where Stock_size = '" + typeCombo.getItemAt(typeCombo.getSelectedIndex()) + "'";
				}
				MainGUI.MainGUI.mdb.insert(update_sql);
				
				MainGUI.MainGUI.mdb.select(2, select_sql);
				Product_list_Table();
			}
		});
		// ��ü ����
		Button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainGUI.MainGUI.mdb.select(2, select_sql);
				Product_list_Table();

				for (int i = 0; i < 2; i++) {
					field[i].setText("");
				}

			}
		});

	}

	private void FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[2];
		String[] FixLabelName = { "��ǰ��", "��ǰ����ó" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int t = 0;
		for (int i = 0; i < 2; i++) {
			FixLabel[i].setLocation(1030, 40 + t++ * 30);
			add(FixLabel[i]);
		}
	}

	// TextField ����, ��ġ
	private void TextField() {
		field = new JTextField[5];
		for (int i = 0; i < 2; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(1150, 40 + i * 30);
			add(field[i]);
		}
		field[2] = new JTextField();
		field[2].setSize(230, 30);
		field[2].setLocation(1150, 300);
		add(field[2]);

		for (int i = 3; i < 5; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(1150, 270 + i * 30);
			add(field[i]);
		}
	}

	private void Stock_FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[4];
		String[] FixLabelName = { "��ǰ��", "�԰�", "������", "�ǸŰ���" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int t = 0;
		for (int i = 0; i < 4; i++) {
			FixLabel[i].setLocation(1030, 300 + t++ * 30);
			add(FixLabel[i]);
		}
	}

	private void ComboBox() {
		typeCombo = new JComboBox<String>();
		int num = ProductList.type.length;
		for (int i = 0; i < num; i++) {
			typeCombo.addItem(ProductList.type[i]);
		}
		typeCombo.setBounds(1150, 330, 230, 30);
		add(typeCombo);
	}

}
