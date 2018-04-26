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
 * 20134822 김한섭 
 * DB Report GUI ACE 상담심리 신청 탭 
 */
public class ACEImage extends JPanel {
	private JTextField[] field; // Textfield 값 입력 받는 공간
	private AdmissionDB ad;
	private String sql;         // sql 질의문. 
	private String selectTimeString = null;
	private JTextField Snum;    // 학번 입력 받는 필드

	public ACEImage() {
		// TODO Auto-generated constructor stub
		setLayout(null);          // 배치관리자 없음. 좌표로 설정.
		ad = new AdmissionDB();   // AdmissionDB class 호출

		// 상단 이미지 배치.
		ImageIcon imageicon = new ImageIcon("ACE.png");
		JLabel label = new JLabel(imageicon);
		label.setSize(1000, 500);
		label.setLocation(200, 0);
		add(label); // label 추가

		FixLable_JLabel();  // 고정된 label
		TextField();        // textfield 설정
		Radio();            // radio 설정
		Button();           // 신청, 신청확인 버튼. 
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawString("※ 신청이 완료되면 상담사에게 연락이 오게되며, 정확한 상담 일정을 정하세요", 200, 700);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method에 setSize 명령어를 추가하게 되면 component가 정상적으로 작동하지 않음
	}

	private void FixLable_JLabel() { // 고정된 Label 생성 함수.
		JLabel[] FixLabel = new JLabel[5];
		String[] FixLabelName = { "이름", "학과", "학번", "전화번호", "상담시간" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // 테두리를 넣는다.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // 가운대 정렬
			FixLabel[FixLableIndex].setBorder(eb); // 테두리 추가
			FixLabel[FixLableIndex++].setSize(120, 30);
		}
		for (int i = 0; i < 5; i++) {
			FixLabel[i].setLocation(200, 530 + i * 30);
		}
		for (int i = 0; i < 5; i++) {
			add(FixLabel[i]);
		}

	}

	private void TextField() {  // textfield 생성 및 배치. 
		field = new JTextField[5];
		for (int i = 0; i < 4; i++) {
			field[i] = new JTextField();
			field[i].setSize(300, 30);
			field[i].setLocation(320, 530 + i * 30);
			add(field[i]);
		}

	}

	private void Radio() {  // radio 생성 및 배치 
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

		// 하나 클릭 하면 나머지 두개는 해제되도록 구현함. 
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

	private void Button() {  // 버튼 두개. 
		JButton[] Button = new JButton[2];
		Button[0] = new JButton(" 신      청 ");
		Button[1] = new JButton(" 신청확인 ");

		for (int i = 0; i < 2; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1000 + i * 120, 700);
			add(Button[i]);
		}
		Button[0].addActionListener(new ActionListener() {
			// 신청 버튼 // SQL 문 생성 후 추가.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createInsertSQL();
				ad.insert(sql);
				Dialog("신청");
			}
		});
		Button[1].addActionListener(new ActionListener() {
			// 신청확인 버튼 // SQL 문 생성 후 추가.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog("신청확인");

			}
		});
	}
	
	
    // 신청 버튼과 신청 확인 버튼의 다이얼로그 모양이 다름. 
	private void Dialog(String text) { // 다이얼로그 제목을 인자로 받음.
		JDialog dialog = new JDialog(); // 다이얼로그 생성
		dialog.setTitle(text);
		dialog.setLayout(new BorderLayout(10, 10));
		JButton okBtn = new JButton("확인");
		if (text == "신청") {  // 신청 버튼의 경우 레이블과 확인 버튼만 있음. 
			dialog.add(new JLabel(text + "되었습니다.", JLabel.CENTER), BorderLayout.CENTER);
		} else {              // 신청 확인 버튼의 경우 학번을 입력하는 textfield가 있음. 
			Snum = new JTextField();
			dialog.add(new JLabel("학번을 입력하세요", JLabel.CENTER), BorderLayout.NORTH);
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
				if(text == "신청") {
					dialog.dispose();
				}else { // 신청 확인 버튼의 경우. select 문을 만들고 검색을 한 뒤 Dialog2를 실행시킨다. 
					createSelectSQL();
					ad.select(sql);
					dialog.dispose();
					Dialog2("신청내역");
				}
			}
		});
		dialog.setSize(300, 150);
		dialog.setLocation(750, 450);
		dialog.setVisible(true);
	}

	// insert 문을 생성해주는 메소드 
	private void createInsertSQL() {
		// 기본키는 학번
		sql = "INSERT INTO DORMITORY.ACE VALUES(";
		// table 소유자 명을 써주지 않으면 table을 찾을 수 없다는 오류가 발생한다. ORA-00942
		for (int i = 0; i < 2; i++) {
			sql += "'" + field[i].getText() + "',";
		}
		sql += field[2].getText() + ",";
		sql += field[3].getText() + ",'";
		sql += selectTimeString + "')";
		System.out.println(sql); // 생성된 SQL 문 생성.
	}

	// select 문을 생성해주는 메소드. 
	private void createSelectSQL() {
		sql = "select * from DORMITORY.ACE where Snum =" + Snum.getText();
		System.out.println(sql); // 생성된 SQL 문 생성.	
	}

	// 신청내역 확인 다이얼로그 
	private void Dialog2(String text) { // 다이얼로그 제목을 인자로 받음.
		JDialog dialog = new JDialog(); // 다이얼로그 생성
		dialog.setTitle(text);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		if(ad.temp == "") {
			dialog.add(new JLabel("신청내역이 없습니다.", JLabel.CENTER));
		}else {
			dialog.add(new JLabel("신청내역이 있습니다.", JLabel.CENTER));
		}
		JButton okBtn = new JButton("확인");
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
