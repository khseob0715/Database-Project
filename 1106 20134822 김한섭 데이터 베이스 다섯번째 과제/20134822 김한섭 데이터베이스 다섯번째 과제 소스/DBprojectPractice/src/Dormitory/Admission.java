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
 * 20134822 김한섭 
 * DB Report GUI 기숙사입사신청 탭 
 */
public class Admission extends JPanel {
	private JTextField[] field; // Textfield 값 입력 받는 공간
	private JComboBox<String> typeCombo; // comboBox
	private AdmissionDB ad;
	// 신청일 날짜 format
	private Date date;
	private SimpleDateFormat text;
	private String sql;

	public Admission() {
		// TODO Auto-generated constructor stub
		ad = new AdmissionDB();

		setLayout(null);

		date = new Date();
		text = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // 포맷 지정

		Admission_Button();
		FixLable_JLabel();
		ComboBox();
		TextField();
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Font f = new Font("Times", Font.BOLD, 15); // 글꼴 설정
		g.setFont(f); // 글꼴 지정
		g.drawString(":::: 기숙사 입사 신청 ::::", 100, 100);
		g.drawString(":::: 유    의   사    항 ::::", 100, 500);
		g.drawString(
				"가. 준비물 : 이불, 베개, 침대패드(누비패트 의무,  홑겹 패트 사용금지), 옷걸이, 세면도구, 휴지 및 휴지통, 청소도구, 슬리퍼 (소음이 없는 것), 기타 생활용품 등.",
				100, 540);
		g.drawString("나. 반입금지 물품 : 전열기구(전기장판 또는 전기방석, 다리미, 커피포트, 토스터기, 취사도구 등) "
				+ "인화물질, 위험물, TV, 오디오, 가스버너, 전기밥솥, 자전거 등의 전자제품 반입은 일체 금함.", 100, 580);
		g.drawString("다. 반입 가능 전기 제품 : 컴퓨터 및 주변기기, 스탠드, 소형오디오, 선풍기, 미니청소기, 충전기, 전기면도기, 이·미용기구", 100, 620);
		g.drawString("라. 동물출입 및 사육은 일체 금함(시각장애인 안내견 제외)", 100, 660);

		f = new Font("Times", Font.BOLD, 12);
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString("신청일", 1040, 310);
		this.setPreferredSize(new Dimension(1550, 30));
		// PaintComponent method에 setSize 명령어를 추가하게 되면 component가 정상적으로 작동하지 않음
	}

	private void Admission_Button() { // 입사 탭의 레이아웃의 버튼
		JButton[] Button = new JButton[4];
		Button[0] = new JButton(" 취    소 ");
		Button[1] = new JButton(" 수    정 ");
		Button[2] = new JButton(" 신    청 ");
		Button[3] = new JButton(" 종    료 ");
		for (int i = 0; i < 3; i++) {
			Button[i].setSize(100, 40);
			Button[i].setLocation(1065 + i * 120, 130);
			add(Button[i]);
		}
		Button[3].setSize(100, 40);
		Button[3].setLocation(1305, 720);
		add(Button[3]);

		Button[0].addActionListener(new ActionListener() {
			// 취소 버튼을 누르면 textfiled의 값을 초기화 시킴.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < field.length; i++) {
					field[i].setText(""); //
				}
				field[12].setText(text.format(date));
				typeCombo.setSelectedIndex(0);
				Dialog("취소");
			}
		});

		Button[1].addActionListener(new ActionListener() {
			// 수정 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog("수정");
				createDeleteSQL();
				ad.insert(sql);
			}
		});

		Button[2].addActionListener(new ActionListener() {
			// 신청 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog("신청");
				createInsertSQL();
				ad.insert(sql);
			}
		});
		Button[3].addActionListener(new ActionListener() {
			// 신청 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ad.close(); // db 자원 반납.
				Dialog("종료");
			}
		});
	}

	private void Dialog(String text) { // 다이얼로그 제목을 인자로 받음.
		JDialog dialog = new JDialog(); // 다이얼로그 생성
		dialog.setTitle(text);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		dialog.add(new JLabel(text + "되었습니다.", JLabel.CENTER));
		JButton okBtn = new JButton("확인");
		dialog.add(okBtn);
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(text == "종료") {    // 종료 버튼 일 경우.
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

	private void FixLable_JLabel() { // 고정된 Label 생성 함수.
		JLabel[] FixLabel = new JLabel[15];
		String[] FixLabelName = { "입사신청구분", "한글이름", "현재학년", "휴대폰", "본인 주소", "보호자성명", 
				"본인주소", "대학", "학적상태", "E-Mail", "관계", "학과(부)", "생년월일", "", "보호자연락처" };
		EtchedBorder eb = new EtchedBorder(Color.BLACK, Color.GRAY); // 테두리를 넣는다.

		int FixLableIndex = 0;
		for (String name : FixLabelName) {
			FixLabel[FixLableIndex] = new JLabel(name);
			FixLabel[FixLableIndex].setHorizontalAlignment(SwingConstants.CENTER); // 가운대 정렬
			FixLabel[FixLableIndex].setBorder(eb); // 테두리 추가
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

	// comboBox 생성, 배치
	private void ComboBox() {
		String[] type = { "일반입사", "입사타입1", "입사타입2", "입사타입3" };
		typeCombo = new JComboBox<String>();
		int num = type.length;
		for (int i = 0; i < num; i++) {
			typeCombo.addItem(type[i]);
		}
		typeCombo.setBounds(220, 200, 1200, 30);
		add(typeCombo);
		setVisible(true);
	}

	// TextField 생성, 배치
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
		// 신청일 날짜는 지정.

		field[12].setEditable(false); // 수정 못함.

		for (int i = 0; i < 14; i++) {
			add(field[i]);
		}
	}

	// InsertSql 문 생성
	private void createInsertSQL() {
		// 기본키는 본인 전화번호의 뒷자리 6자리를 사용.
		sql = "INSERT INTO DORMITORY.STUDENT VALUES(";
		// table 소유자 명을 써주지 않으면 table을 찾을 수 없다는 오류가 발생한다. ORA-00942
		sql += field[2].getText().substring(4) + ","; // PNumber
		sql += "'" + typeCombo.getItemAt(typeCombo.getSelectedIndex()) + "',"; // dType
		sql += "'" + field[0].getText() + "',"; // Name
		sql += field[1].getText() + ","; // Grade
		sql += field[2].getText() + ","; // Phone
		for (int i = 3; i < 13; i++) {
			sql += "'" + field[i].getText() + "',";
		}
		sql += field[13].getText() + ")";
		System.out.println(sql); // 생성된 SQL 문 생성.

	}

	private void createDeleteSQL() {
		sql = "DELETE FROM DORMITORY.STUDENT WHERE PNumber ='" + field[2].getText().substring(4) + "'";
		ad.insert(sql);
		createInsertSQL();
	}
}
