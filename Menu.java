package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Menu {
	
	public int menu[];
	public int f;
	
	public Menu() {
		menu = new int[] {0,0,0};
		
	}
	
	public void draw(Graphics2D g) {
		int f=0;
		for(int i=0;i<3;i++) {
			if(menu[i]==1) {
				g.setColor(Color.cyan);}
			else
				g.setColor(Color.blue);
			g.fillRect(450, 150+f, 300, 70);
			f+=100;
		}
		g.setColor(Color.black);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString("Tryb nauki", 500, 200);
		g.drawString("Tryb gry", 520, 300);
		g.drawString("Jak grać", 520, 400);
	}

	
}
