import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageEx extends JFrame{
	public ImageEx() {
		// TODO Auto-generated constructor stub
		setTitle("이미지 삽입순서");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane(); 

		ImageIcon imageicon = new ImageIcon("testimg.png");
		JLabel label = new JLabel(imageicon);
		contentPane.add(label); // label 추가 
		
		setSize(400,400);  // 사이즈 설정 
		setVisible(true);   // 표시 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImageEx(); // ImageEX 호출
	}

}
