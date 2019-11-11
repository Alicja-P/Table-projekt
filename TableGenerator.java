package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TableGenerator {
	
	public int map[][];
	public int width = 60;
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
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]>0) {
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
					if(map[0][z]==0)
						{g.setColor(Color.blue);
						g.drawString("  "+(z+1), 90+d, 40);}
					else
						{g.setColor(Color.white);
						g.drawString("  "+(z+1), 90+d, 40);}
					d = d+59;
				}
			
				
					if(map[i][0]==0)
						{g.setColor(Color.blue);
						g.drawString((i+1)+"  ", 45, i * height + 85);}
					else
						{g.setColor(Color.white);
						g.drawString((i+1)+"  ", 45, i * height + 85);}
			}
		}
	}
	
}
