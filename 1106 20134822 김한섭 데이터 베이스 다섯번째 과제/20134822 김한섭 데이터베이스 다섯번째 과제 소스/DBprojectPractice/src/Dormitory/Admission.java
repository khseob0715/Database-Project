package Dormitory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
/**
 * @author khseob0715 
 * 20134822 ���Ѽ� 
 * DB Report GUI ������Ի��û �� 
 */
public class Admission extends JPanel {
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private JComboBox<String> typeCombo; // comboBox
	private AdmissionDB ad;
	// ��û�� ��¥ format
	private Date date;
	private SimpleDateFormat text;
	private String sql;

	public Admission() {
		// TODO Auto-generated constructor stub
		ad = new AdmissionDB();

		setLayout(null);

		date = new Date();
		text = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // ���� ����

		Admission_Button();
		FixLable_JLabel();
		ComboBox();
		TextField();
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // �۲� ����
		g.setFont(f); // �۲� ����
		g.drawString(":::: ����� �Ի� ��û ::::", 100, 100);
		g.drawString(":::: ��    ��   ��    �� ::::", 100, 500);
		g.drawString(
				"��. �غ� : �̺�, ����, ħ���е�(������Ʈ �ǹ�,  Ȭ�� ��Ʈ ������), �ʰ���, ���鵵��, ���� �� ������, û�ҵ���, ������ (������ ���� ��), ��Ÿ ��Ȱ��ǰ ��.",
				100, 540);
		g.drawString("��. ���Ա��� ��ǰ : �����ⱸ(�������� �Ǵ� ����漮, �ٸ���, Ŀ����Ʈ, �佺�ͱ�, ��絵�� ��) "
				+ "��ȭ����, ���蹰, TV, �����, ��������, ������, ������ ���� ������ǰ ������ ��ü ����.", 100, 580);
		g.drawString("��. ���� ���� ���� ��ǰ : ��ǻ�� �� �ֺ����, ���ĵ�, ���������, ��ǳ��, �̴�û�ұ�, ������, ����鵵��, �̡��̿�ⱸ", 100, 620);
		g.drawString("��. �������� �� ������ ��ü ����(�ð������ �ȳ��� ����)", 100, 660);

		f = new Font("Times", Font.BOLD, 12);
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString("��û��", 1040, 310);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method�� setSize ��ɾ �߰��ϰ� �Ǹ� component�� ���������� �۵����� ����
	}

	private void Admission_Button() { // �Ի� ���� ���̾ƿ��� ��ư
		JButton[] Button = new JButton[4];
		Button[0] = new JButton(" ��    �� ");
		Button[1] = new JButton(" ��    �� ");
		Button[2] = new JButton(" ��    û ");
		Button[3] = new JButton(" ��    �� ");
		for (int i = 0; i < 3; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1065 + i * 120, 130);
			add(Button[i]);
		}
		Button[3].setSize(100, 40);
		Button[3].setLocation(1305, 720);
		add(Button[3]);

		Button[0].addActionListener(new ActionListener() {
			// ��� ��ư�� ������ textfiled�� ���� �ʱ�ȭ ��Ŵ.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < field.length; i++) {
					field[i].setText(""); //
				}
				field[12].setText(text.format(date));
				typeCombo.setSelectedIndex(0);
				Dialog("���");
			}
		});

		Button[1].addActionListener(new ActionListener() {
			// ���� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog("����");
				createDeleteSQL();
				ad.insert(sql);
			}
		});

		Button[2].addActionListener(new ActionListener() {
			// ��û ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog("��û");
				createInsertSQL();
				ad.insert(sql);
			}
		});
		Button[3].addActionListener(new ActionListener() {
			// ��û ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ad.close(); // db �ڿ� �ݳ�.
				Dialog("����");
			}
		});
	}

	private void Dialog(String text) { // ���̾�α� ������ ���ڷ� ����.
		JDialog dialog = new JDialog(); // ���̾�α� ����
		dialog.setTitle(text);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		dialog.add(new JLabel(text + "�Ǿ����ϴ�.", JLabel.CENTER));
		JButton okBtn = new JButton("Ȯ��");
		dialog.add(okBtn);
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(text == "����") {    // ���� ��ư �� ���.
					System.exit(0);
				}
				else { 
					dialog.dispose();
				}
			}
		});
		dialog.setSize(150, 120);
		dialog.setLocation(750, 450);
		dialog.setVisible(true);
	}

	private void FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[15];
		String[] FixLabelName = { "�Ի��û����", "�ѱ��̸�", "�����г�", "�޴���", "���� �ּ�", "��ȣ�ڼ���", 
				"�����ּ�", "����", "��������", "E-Mail", "����", "�а�(��)", "�������", "", "��ȣ�ڿ���ó" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		for (int i = 0; i < 7; i++) {
			FixLabel[i].setLocation(100, 200 + i * 30);
		}
		int t = 0;
		for (int i = 7; i < 10; i++) {
			FixLabel[i].setLocation(550, 230 + t++ * 30);
		}
		FixLabel[10].setLocation(550, 350);
		t = 0;
		for (int i = 11; i < 14; i++) {
			FixLabel[i].setLocation(1000, 230 + t++ * 30);
		}
		FixLabel[14].setLocation(1000, 350);

		for (int i = 0; i < 15; i++) {
			add(FixLabel[i]);
		}
	}

	// comboBox ����, ��ġ
	private void ComboBox() {
		String[] type = { "�Ϲ��Ի�", "�Ի�Ÿ��1", "�Ի�Ÿ��2", "�Ի�Ÿ��3" };
		typeCombo = new JComboBox<String>();
		int num = type.length;
		for (int i = 0; i < num; i++) {
			typeCombo.addItem(type[i]);
		}
		typeCombo.setBounds(220, 200, 1200, 30);
		add(typeCombo);
		setVisible(true);
	}

	// TextField ����, ��ġ
	private void TextField() {
		field = new JTextField[14];
		for (int i = 0; i < 14; i++) {
			field[i] = new JTextField();
			field[i].setSize(330, 30);
		}
		for (int i = 0; i < 6; i++) {
			field[i].setLocation(220, 230 + i * 30);
		}
		field[3].setSize(1200, 30);
		field[5].setSize(1200, 30);
		int t = 0;
		for (int i = 6; i < 9; i++) {
			field[i].setLocation(670, 230 + t++ * 30);
		}
		field[9].setLocation(670, 350);
		t = 0;

		for (int i = 10; i < 13; i++) {
			field[i].setLocation(1120, 230 + t++ * 30);
		}
		field[13].setLocation(1120, 350);
		for (int i = 10; i < 14; i++) {
			field[i].setSize(300, 30);
		}
		field[12].setText(text.format(date));
		// ��û�� ��¥�� ����.

		field[12].setEditable(false); // ���� ����.

		for (int i = 0; i < 14; i++) {
			add(field[i]);
		}
	}

	// InsertSql �� ����
	private void createInsertSQL() {
		// �⺻Ű�� ���� ��ȭ��ȣ�� ���ڸ� 6�ڸ��� ���.
		sql = "INSERT INTO DORMITORY.STUDENT VALUES(";
		// table ������ ���� ������ ������ table�� ã�� �� ���ٴ� ������ �߻��Ѵ�. ORA-00942
		sql += field[2].getText().substring(4) + ","; // PNumber
		sql += "'" + typeCombo.getItemAt(typeCombo.getSelectedIndex()) + "',"; // dType
		sql += "'" + field[0].getText() + "',"; // Name
		sql += field[1].getText() + ","; // Grade
		sql += field[2].getText() + ","; // Phone
		for (int i = 3; i < 13; i++) {
			sql += "'" + field[i].getText() + "',";
		}
		sql += field[13].getText() + ")";
		System.out.println(sql); // ������ SQL �� ����.

	}

	private void createDeleteSQL() {
		sql = "DELETE FROM DORMITORY.STUDENT WHERE PNumber ='" + field[2].getText().substring(4) + "'";
		ad.insert(sql);
		createInsertSQL();
	}
}
