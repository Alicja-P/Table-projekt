package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TableGenerator {
	
	public int map[][];
	public int width = 100;
	public int height = 60;

	public TableGenerator(int row, int col) {
		map = new int[row][col];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.setColor(Color.white);
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				//jeśli kafelek ma wartość 1 ma kolor biały, jeśli 0 - kolor niebieski
				if(map[i][j]==1) {
					g.setColor(Color.white);
				}
				else
				{
					g.setColor(Color.blue);
				}
				
				g.fillRect(j * width + 80, i * height + 50, width-4, height-4);
				
				int d=0;
				for(int z=0; z<map[0].length; z++)
				{	
					g.drawString("  "+(z+1), 105+d, 40);
					d = d+100;
				}
			
				g.drawString((i+1)+"  ", 45, i * height + 85);
			}
		}
	}
	
}
