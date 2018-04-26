package Dormitory;

import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
/**
 * @author khseob0715 
 * 20134822 ���Ѽ� 
 * DB Report GUI ��� �۾� 
 */
class Top extends JPanel{
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Date date = new Date();
		SimpleDateFormat text = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // ���� ����

		Font f = new Font("Times", Font.BOLD, 15); // �۲� ����
		g.setFont(f); // �۲� ����
		
		g.drawString("���� ������ ���� : " , 100, 25);
		g.drawString("���� �Ͻ� : " + text.format(date), 1200, 25);
		this.setSize(1550, 30);
	}

}