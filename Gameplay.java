package table_try;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Gameplay extends JPanel{
	
	private TableGenerator map;
	private int a = 5;
	
	public Gameplay() {
		map = new TableGenerator(a,a);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
	}
	
	public void paint(Graphics g) {
		map.draw((Graphics2D)g);
		g.dispose();
	}
}
