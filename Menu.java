package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public int menu[];
	public int f;
	public static final Color LIGHT_blue = new Color(153,217,234);
	public static final Color MEDIUM_blue = new Color(0,162,232);
	
	public Menu() {
		menu = new int[] {0,0,0};
	}
	
	public void paint(Graphics g) {
		int f=0;
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 1024);
		for(int i=0;i<3;i++) {
			if(menu[i]==1) {
				g.setColor(LIGHT_blue);}
			else
				g.setColor(MEDIUM_blue);
			g.fillRect(450, 350+f, 300, 70);
			f+=100;
		}
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString("Tryb nauki", 500, 400);
		g.drawString("Tryb gry", 520, 500);
		g.drawString("Jak grać", 520, 600);
	}
}
