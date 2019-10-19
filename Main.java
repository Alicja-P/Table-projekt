package table_try;


import java.awt.Color;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay();
		obj.setBounds(0, 0, 1280, 1024);
		obj.setBackground(Color.DARK_GRAY);
		obj.setTitle("Table");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obj.add(gameplay);
	}
}
