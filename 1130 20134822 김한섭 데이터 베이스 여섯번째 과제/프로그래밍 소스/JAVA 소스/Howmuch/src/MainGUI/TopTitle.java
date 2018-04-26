package MainGUI;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

public class TopTitle extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		// 상당 텍스트 양식. 
		Date date = new Date();
		SimpleDateFormat text = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // 포맷 지정

		Font f = new Font("Times", Font.BOLD, 15); // 글꼴 설정
		g.setFont(f); // 글꼴 지정
		
		g.drawString("광성공구철물상사 관리시스템", 100, 25);
		g.drawString("접속 일시 : " + text.format(date), 1200, 25);
		this.setSize(1550, 30);
	}
}