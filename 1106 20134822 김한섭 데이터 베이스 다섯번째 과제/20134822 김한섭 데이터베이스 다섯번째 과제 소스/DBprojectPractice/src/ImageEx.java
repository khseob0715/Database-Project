import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageEx extends JFrame{
	public ImageEx() {
		// TODO Auto-generated constructor stub
		setTitle("�̹��� ���Լ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane(); 

		ImageIcon imageicon = new ImageIcon("testimg.png");
		JLabel label = new JLabel(imageicon);
		contentPane.add(label); // label �߰� 
		
		setSize(400,400);  // ������ ���� 
		setVisible(true);   // ǥ�� 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImageEx(); // ImageEX ȣ��
	}

}
