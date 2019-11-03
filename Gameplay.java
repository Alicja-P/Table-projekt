package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, MouseMotionListener{
	
	private TableGenerator map;
	private int a = 4;
	
	private int x, y;
	ArrayList<Point> points = new ArrayList<Point>();
	
	private Timer timer;
	private int delay = 8;
	
	private int b,c;
	
	public Gameplay() {
		map = new TableGenerator(a,a);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//pisanie polecenia
		g.setColor(Color.black);
		g.fillRect(790, 160, 400, 50);
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString("Zaznacz 12 kratek!", 800, 85);
				
		g.drawString(b+" * "+c+" = "+(b*c), 800, 190);
		
		map.draw((Graphics2D)g);
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//timer.start();
		for(int i = 0; i<map.map.length; i++) {
			for(int j = 0; j<map.map[0].length; j++) {
				
				//x i y - współrzędne kursora, jeśli będą większe od wsp. początkowych kafelka to zmieniają jego wartość na 0
				if( x > (j*map.width+80) && y > (i*map.height+50) )
				{
					map.map[i][j] = 0;
					b=i+1;
					c=j+1;
				}
				else
				{
					map.map[i][j] = 1;
				}
				
			}
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		//współrzędne kursora
		x=m.getX();
		y=m.getY();
		points.add(new Point(x,y));
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mousePressed(MouseEvent m) {
		x=m.getX();
		y=m.getY();
		points.add(new Point(x,y));
		repaint();
		
	}
}
