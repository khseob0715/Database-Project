package Dormitory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
/**
 * @author khseob0715 
 * 20134822 ���Ѽ� 
 * DB Report GUI ACE ���ɸ� ��û �� 
 */
public class ACEImage extends JPanel {
	private JTextField[] field; // Textfield �� �Է� �޴� ����
	private AdmissionDB ad;
	private String sql;         // sql ���ǹ�. 
	private String selectTimeString = null;
	private JTextField Snum;    // �й� �Է� �޴� �ʵ�

	public ACEImage() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // ��ġ������ ����. ��ǥ�� ����.
		ad = new AdmissionDB();   // AdmissionDB class ȣ��

		// ��� �̹��� ��ġ.
		ImageIcon imageicon = new ImageIcon("ACE.png");
		JLabel label = new JLabel(imageicon);
		label.setSize(1000, 500);
		label.setLocation(200, 0);
		add(label); // label �߰�

		FixLable_JLabel();  // ������ label
		TextField();        // textfield ����
		Radio();            // radio ����
		Button();           // ��û, ��ûȮ�� ��ư. 
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawString("�� ��û�� �Ϸ�Ǹ� ���翡�� ������ ���ԵǸ�, ��Ȯ�� ��� ������ ���ϼ���", 200, 700);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method�� setSize ��ɾ �߰��ϰ� �Ǹ� component�� ���������� �۵����� ����
	}

	private void FixLable_JLabel() { // ������ Label ���� �Լ�.
		JLabel[] FixLabel = new JLabel[5];
		String[] FixLabelName = { "�̸�", "�а�", "�й�", "��ȭ��ȣ", "���ð�" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // �׵θ��� �ִ´�.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // ����� ����
			FixLabel[FixLableIndex].setBorder(eb); // �׵θ� �߰�
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		for (int i = 0; i < 5; i++) {
			FixLabel[i].setLocation(200, 530 + i * 30);
		}
		for (int i = 0; i < 5; i++) {
			add(FixLabel[i]);
		}

	}

	private void TextField() {  // textfield ���� �� ��ġ. 
		field = new JTextField[5];
		for (int i = 0; i < 4; i++) {
			field[i] = new JTextField();
			field[i].setSize(300, 30);
			field[i].setLocation(320, 530 + i * 30);
			add(field[i]);
		}

	}

	private void Radio() {  // radio ���� �� ��ġ 
		JRadioButton[] selectTime = new JRadioButton[3];
		selectTime[0] = new JRadioButton("10:00~12:00", true);
		selectTime[1] = new JRadioButton("02:00~04:00");
		selectTime[2] = new JRadioButton("04:00~06:00");

		selectTimeString = selectTime[0].getText();

		for (int i = 0; i < 3; i++) {
			selectTime[i].setLocation(320 + i * 100, 650);
			selectTime[i].setSize(100, 30);
			selectTime[i].setBackground(Color.WHITE);
			add(selectTime[i]);
		}

		// �ϳ� Ŭ�� �ϸ� ������ �ΰ��� �����ǵ��� ������. 
		selectTime[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectTimeString = selectTime[0].getText();
				if (selectTime[1].isSelected() == true) {
					selectTime[1].setSelected(false);
				}
				if (selectTime[2].isSelected() == true) {
					selectTime[2].setSelected(false);
				}
			}
		});
		selectTime[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectTimeString = selectTime[1].getText();
				if (selectTime[0].isSelected() == true) {
					selectTime[0].setSelected(false);
				}
				if (selectTime[2].isSelected() == true) {
					selectTime[2].setSelected(false);
				}
			}
		});
		selectTime[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectTimeString = selectTime[2].getText();
				if (selectTime[1].isSelected() == true) {
					selectTime[1].setSelected(false);
				}
				if (selectTime[0].isSelected() == true) {
					selectTime[0].setSelected(false);
				}
			}
		});
	}

	private void Button() {  // ��ư �ΰ�. 
		JButton[] Button = new JButton[2];
		Button[0] = new JButton(" ��      û ");
		Button[1] = new JButton(" ��ûȮ�� ");

		for (int i = 0; i < 2; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1000 + i * 120, 700);
			add(Button[i]);
		}
		Button[0].addActionListener(new ActionListener() {
			// ��û ��ư // SQL �� ���� �� �߰�.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createInsertSQL();
				ad.insert(sql);
				Dialog("��û");
			}
		});
		Button[1].addActionListener(new ActionListener() {
			// ��ûȮ�� ��ư // SQL �� ���� �� �߰�.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog("��ûȮ��");

			}
		});
	}
	
	
    // ��û ��ư�� ��û Ȯ�� ��ư�� ���̾�α� ����� �ٸ�. 
	private void Dialog(String text) { // ���̾�α� ������ ���ڷ� ����.
		JDialog dialog = new JDialog(); // ���̾�α� ����
		dialog.setTitle(text);
		dialog.setLayout(new BorderLayout(10, 10));
		JButton okBtn = new JButton("Ȯ��");
		if (text == "��û") {  // ��û ��ư�� ��� ���̺�� Ȯ�� ��ư�� ����. 
			dialog.add(new JLabel(text + "�Ǿ����ϴ�.", JLabel.CENTER), BorderLayout.CENTER);
		} else {              // ��û Ȯ�� ��ư�� ��� �й��� �Է��ϴ� textfield�� ����. 
			Snum = new JTextField();
			dialog.add(new JLabel("�й��� �Է��ϼ���", JLabel.CENTER), BorderLayout.NORTH);
			Snum.setSize(100, 100);
			dialog.add(Snum, BorderLayout.CENTER);
		}
		dialog.add(new JLabel("            ", JLabel.CENTER), BorderLayout.EAST);
		dialog.add(new JLabel("            ", JLabel.CENTER), BorderLayout.WEST);
		dialog.add(okBtn, BorderLayout.SOUTH);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(text == "��û") {
					dialog.dispose();
				}else { // ��û Ȯ�� ��ư�� ���. select ���� ����� �˻��� �� �� Dialog2�� �����Ų��. 
					createSelectSQL();
					ad.select(sql);
					dialog.dispose();
					Dialog2("��û����");
				}
			}
		});
		dialog.setSize(300, 150);
		dialog.setLocation(750, 450);
		dialog.setVisible(true);
	}

	// insert ���� �������ִ� �޼ҵ� 
	private void createInsertSQL() {
		// �⺻Ű�� �й�
		sql = "INSERT INTO DORMITORY.ACE VALUES(";
		// table ������ ���� ������ ������ table�� ã�� �� ���ٴ� ������ �߻��Ѵ�. ORA-00942
		for (int i = 0; i < 2; i++) {
			sql += "'" + field[i].getText() + "',";
		}
		sql += field[2].getText() + ",";
		sql += field[3].getText() + ",'";
		sql += selectTimeString + "')";
		System.out.println(sql); // ������ SQL �� ����.
	}

	// select ���� �������ִ� �޼ҵ�. 
	private void createSelectSQL() {
		sql = "select * from DORMITORY.ACE where Snum =" + Snum.getText();
		System.out.println(sql); // ������ SQL �� ����.	
	}

	// ��û���� Ȯ�� ���̾�α� 
	private void Dialog2(String text) { // ���̾�α� ������ ���ڷ� ����.
		JDialog dialog = new JDialog(); // ���̾�α� ����
		dialog.setTitle(text);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		if(ad.temp == "") {
			dialog.add(new JLabel("��û������ �����ϴ�.", JLabel.CENTER));
		}else {
			dialog.add(new JLabel("��û������ �ֽ��ϴ�.", JLabel.CENTER));
		}
		JButton okBtn = new JButton("Ȯ��");
		dialog.add(okBtn);
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();
			}
		});
		dialog.setSize(300, 150);
		dialog.setLocation(750, 450);
		dialog.setVisible(true);
	}
}
