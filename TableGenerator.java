package table_try;

import java.awt.Color;
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
		
		for(int i=0; i<map.length; i++) {
			
			for(int j=0; j<map[0].length; j++) {
					g.setColor(Color.white);
				
				g.fillRect(j * width + 80, i * height + 50, width-4, height-4);
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}
