package table_try;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

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
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}
