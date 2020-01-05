package table_try;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		JPanel pan = new JPanel();
		Gameplay gameplay = new Gameplay();
		
		obj.setBounds(0, 0, 1280, 730);
		obj.add(pan);
		pan.setBackground(Color.black);
		obj.setTitle("Table");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obj.add(gameplay);
	}
}
