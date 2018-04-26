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

public class ProductList extends JPanel {

	private String select_sql = "select * from GANSUNG.product";
	public static String data[][] = new String[2880][4];
	// static �̱� ������ ó������ �����ؾߵ�.
	private JButton jbutton[];
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private JTable jt;
	private JComboBox<String> typeCombo; // comboBox

	private String ProductTableList[] = { "PRODUCT_NAME", "PRODUCT_SIZE", "PRODUCT_PRICE", "PRODUCT_HOME" };
	public static String[] type = new String[100];  // combo box�� �� �԰ݵ��� ��. 

	
	// Purchase Table Input Data
	public static String Purchase_product_code;
	public static String Purchase_deliver_code;
		
	public ProductList() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		MainGUI.MainGUI.mdb.select(0, select_sql);
		Product_list_Table(); // table ����
		Product_list_Button(); // button ����
		FixLable_JLabel();
		TextField();
		
		ComboBox();
	}
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // �۲� ����
		g.setFont(f); // �۲� ����
		g.drawString("��ǰ ��ȸ", 1030, 20);
		
		g.drawString("��ǰ �ֹ�", 1030, 200);
		g.drawString("�� ��ǰ�� �Է� �� �԰� �˻��� ���� �԰��� �����Ͻñ� �ٶ��ϴ�.", 1030, 350);
		g.drawString("�� �ֹ������ ��ǰ�ֹ����� �޴����� Ȯ���Ͻñ� �ٶ��ϴ�.", 1030, 380);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method�� setSize ��ɾ �߰��ϰ� �Ǹ� component�� ���������� �۵����� ����
	}
	private void Product_list_Table() {

		String column[] = { "��ǰ��", "��ǰ�԰�", "��ǰ�ܰ�", "��ǰ����ó" };

		jt = new JTable(data, column);
		jt.setSize(1000, 750);
		jt.setLocation(0, 0);
		add(jt); // table ���� �� �߰�

		JScrollPane sp = new JScrollPane(jt);
		sp.setSize(1000, 750);
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
			Button[i].setLocation(1400, 40 + i * 40);
			add(Button[i]);
		}
		for (int i = 2; i < 4; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1400, 220 + (i - 2) * 40);
			add(Button[i]);
		}
		// ��ȸ���
		Button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql = select_sql + " where ";
				boolean input = false;
				for (int i = 0; i < 4; i++) {
					if (!"".equals(field[i].getText())) {
						// System.out.println(field[i].getText());
						input = true; // �׸��� �ϳ��� �Է��� �Ǿ�� �����.
					}
				}

				boolean first = false;
				for (int i = 0; i < 4; i++) {
					if (!"".equals(field[i].getText())) {
						if (first) {
							sql += " AND ";
						}
						sql += ProductTableList[i];
						sql += " Like '%";
						sql += field[i].getText();
						sql += "%' ";
						first = true;
					}
				}

				if (input) {
					MainGUI.MainGUI.mdb.select(0, sql);
					Product_list_Table();
				}
			}

		});
		// ��ü��ȸ
		Button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				MainGUI.MainGUI.mdb.select(0, select_sql);
				Product_list_Table();

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
				String sql = select_sql + " where PRODUCT_NAME = '" + field[4].getText() + "'";
				boolean input = false;

				if (!"".equals(field[4].getText())) {
					// �˻��ϱ�  
					MainGUI.MainGUI.mdb.select(11, sql);
					remove(typeCombo); // ���� ������Ʈ ����� .
					ComboBox();
				}else {
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
				sql += field[4].getText() +"'";
				
				MainGUI.MainGUI.mdb.select(21, sql);
				//Product_list_Table();
			
				String insert_sql = "insert into GANSUNG.purchase values('";
				insert_sql += text.format(date) + "','"; /* purchase_code */
				insert_sql += Purchase_deliver_code + "','";
				insert_sql += Purchase_product_code + "','";
				insert_sql += field[5].getText() + "','"; 
				insert_sql += text.format(date) + "')";
				MainGUI.MainGUI.mdb.insert(insert_sql);
			}
		});

	}

	private void FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[7];
		String[] FixLabelName = { "��ǰ��", "��ǰ�԰�", "��ǰ�ܰ�", "��ǰ����ó", "��ǰ��", "��ǰ�԰�", "�ֹ�����" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		int ct = 0; // ������
		for (int i = 0; i < 4; i++) {
			FixLabel[i].setLocation(1030, 40 + ct++ * 30);
			add(FixLabel[i]);
		}
		ct = 0;
		for (int i = 4; i < 7; i++) {
			FixLabel[i].setLocation(1030, 220 + ct++ * 30);
			add(FixLabel[i]);
		}
	}

	// TextField ����, ��ġ
	private void TextField() {
		field = new JTextField[6];
		for (int i = 0; i < 4; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(1150, 40 + i * 30);
			add(field[i]);
		}
		for (int i = 4; i < 6; i++) {
			field[i] = new JTextField();
			field[i].setSize(230, 30);
			field[i].setLocation(1150, 220 + (i - 4) * 60);
			add(field[i]);
		}
	}

	private void ComboBox() {
		typeCombo = new JComboBox<String>();
		int num = type.length;
		for (int i = 0; i < num; i++) {
			typeCombo.addItem(type[i]);
		}
		typeCombo.setBounds(1150, 250, 230, 30);
		add(typeCombo);
	}

}
