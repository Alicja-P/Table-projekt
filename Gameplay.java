package table_try;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;



public class Gameplay extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	private ImageIcon plus;
	private ImageIcon multiply;
	private ImageIcon logo;
	private ImageIcon rules;
	
	private TableGenerator map;
	private Menu menu;
	private Number number;
	public int round = 0;
	public int picked = 0;
	public int picked2 = 0;
	public int score = 0;
	public int column,row;
	
	
	private int x, y, x1, y1, x2, y2;						// współrzędne kursora przy przeciąganiu po kafelkach, przesuwaniu w menu i klikaniu w menu
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Point> points1 = new ArrayList<Point>();
	ArrayList<Point> points2 = new ArrayList<Point>();
	
	private Timer timer;
	private int delay = 8;
	
	public int b=0,c=0;	// zmienne do wyświetlania wyniku
	public int add = 0;
	public int multi = 1;
	public int ready = 0;
	public int game = 0;	// menu/gra/tryby
	
	Random rand = new Random();
	
	
	public Gameplay() {
		map = new TableGenerator(10);
		menu = new Menu();
		number = new Number(7,10);
		number.pick(round);	//Wybieranie liczby do zaznaczenia
		while(number.number==0)
			number.pick(round);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay,this);
		timer.start();
		
		
	}
	
	public void paint(Graphics g) {
		if(game==0) {
			menu.paint((Graphics)g);		//tryb menu
			logo = new ImageIcon("logo.png");
			logo.paintIcon(this, g, 495, 70);
		}
		else {
			//pisanie polecenia
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 1024);
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 40));
			
			g.drawString("Wynik: "+(score),800,260);
			g.drawString("Poziom: "+(round+1),800,360);
			
			
			//opcja kolejny poziom
			g.setColor(Color.gray);
			g.fillRect(900, 420, 330, 60);
			g.fillRect(900, 520, 330, 60);
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Kolejny poziom", 920, 460);
			g.drawString("Powrót do menu", 920, 560);
			
			number.draw((Graphics2D)g);
			if(picked2 == 1) // dobre zaznaczenie
			{
				g.setColor(Color.blue);
				g.drawString("Brawo!", 800, 150);
			}
			if(game==1) {
				//pisanie wyniku
				g.drawString(b+" * "+c+" = "+(b*c+add), 800, 190);
				map.draw((Graphics2D)g,1);
			}
			if(game==2) {
				//pisanie wyniku
				if(multi==1) {
				g.drawString(b+" * "+c, 800, 190);
				if(add>0)
				{
					g.drawString(" + "+add, 890, 190);
				}
				}
				if(multi>1)
				{
					if(add>0)
					g.drawString("( "+b+" * "+c+" + "+add+" ) * "+multi, 800, 190);
					else
					g.drawString("( "+b+" * "+c+" ) * "+multi, 800, 190);
				}
				map.draw((Graphics2D)g,0);
				plus = new ImageIcon("plus2.png");
				multiply = new ImageIcon("razy2.png");
				plus.paintIcon(this, g, 80, 530);
				multiply.paintIcon(this, g, 210, 530);
				g.setColor(Color.gray);
				g.fillRect(80,650, 105, 40);
				g.fillRect(210,650, 106, 40);
				g.fillRect(350,650, 150, 40);
				g.setColor(Color.black);
				g.drawString("Cofnij", 80, 680);
				g.drawString("Cofnij", 210, 680);
				g.drawString("Sprawdź", 350, 680);
			}
			if(game==3)
			{
				rules = new ImageIcon("jak.png");
				rules.paintIcon(this, g, 0, 0);
				g.setColor(Color.gray);
				g.fillRect(100,570, 210, 50);
				g.fillRect(350,570, 200, 50);
				g.setColor(Color.white);
				g.drawString("Tryb nauki", 105, 605);
				g.drawString("Tryb gry", 355, 605);
			}
		}
		g.dispose();
	}

	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		for(int i = 0; i<map.map.length; i++) {
			for(int j = 0; j<map.map[0].length; j++) {
				if(map.map[i][j]!=3) {
				if( x > (j*map.width+80) && y > (i*map.height+50) && picked == 0)		//wartość elementu (pokolorowany lub nie) w zależności od pozycji kursora
				{
					map.map[i][j] = 0;
					b=i+1;
					c=j+1;
				}
				else
				{
					map.map[i][j] = 1;
					
				}}
				
			}
		}
		
		for(int i=0; i<menu.menu.length; i++)
		{
			if(x1>450 && x1<750 && y1>(350 +i*100) && y1<(420 + i*100))		//zaznaczanie przycisku menu gdy kursor nad nim się przesuwa
			{
				menu.menu[i] = 1;
			}
			else
				menu.menu[i] = 0;
		}
		
		if(x2>450 && x2<750 && y2>(350) && y2<(420))		//wybranie opcji "tryb nauki" po kliknięciu myszką w przycisk menu
		{
			game = 1;
			x2 = 0;
			y2 = 0;
		}
		if(x2>450 && x2<750 && y2>(450) && y2<(520))		//wybranie opcji "tryb gry" po kliknięciu myszką w przycisk menu
		{
			game = 2;
			menu.menu[0] = 1;
			x2 = 0;
			y2 = 0;
			map.blacktable();
		}
		if(x2>450 && x2<750 && y2>(550) && y2<(620))		//wybranie opcji "jak grać" po kliknięciu myszką w przycisk menu
		{
			game = 3;
			x2 = 0;
			y2 = 0;
		}
		
		
		if(x2>900 && x2<1200 && y2>420 && y2<480 )		//wybranie opcji "kolejny poziom"
		{
			if(round<7)
				round+=1;
			number.pick(round);
			while(number.number==0)
				number.pick(round);
			
			x2 = 0;
			y2 = 0;
			
		}
		if(x2>900 && x2<1200 && y2>520 && y2<580)		//wybranie opcji "powrót do menu"
		{
			game = 0;
			x2 = 0;
			y2 = 0;
			x = 0;
			y = 0;
			b = 0;
			c = 0;
			picked = 0;
			picked2 = 0;
			score = 0;
			round = 0;
			add = 0;
			map.blanktable();
		}
		
		if(game==2 && picked==1) {
			for(int i=0;i<1;i++)
			{
			map.blacktable();}
			picked=0;
			x2 = 0;
			y2 = 0;
			x = 0;
			y = 0;
		}
		repaint();
	}
	


	@Override
	public void mouseDragged(MouseEvent m) {
		picked = 0;
		picked2 = 0;
		add = 0;
		multi = 1;
		x=m.getX();
		y=m.getY();
		points.add(new Point(x,y));
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		x1=m.getX();
		y1=m.getY();
		points1.add(new Point(x1,y1));
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		x2=m.getX();
		y2=m.getY();
		points2.add(new Point(x2,y2));
		if(x2>80 && x2<185 && y2>550 && y2<650)
		{
			++add;
			x2 = 0;
			y2 = 0;
		}
		if(x2>210 && x2<310 && y2>550 && y2<650)
		{
			multi = multi * 2;
			x2 = 0;
			y2 = 0;
		}
		if(x2>80 && x2<185 && y2>650 && y2<690)
		{
			--add;
			x2 = 0;
			y2 = 0;
		}
		if(x2>210 && x2<316 && y2>650 && y2<690)
		{
			multi = multi/2;
			x2 = 0;
			y2 = 0;
		}
		if(x2>350 && x2<500 && y2>650 && y2<690)
		{
			ready = 1;
			x2 = 0;
			y2 = 0;
		}
		if((add+(b*c))*multi == number.number && ready==1)
		{
			score+=1;
			if(score%6 == 0 && round<7)
				round+=1;
			number.pick(round);
			while(number.number==0)
				number.pick(round);
			picked = 1;
			picked2 = 1;
			ready = 0;
		}
		
		if(game==3 && x2>100 && x2<310 && y2>570 && y2<630)
		{
			game = 1;
			x2 = 0;
			y2 = 0;
		}
		if(game==3 && x2>350 && x2<550 && y2>570 && y2<630)
		{
			game = 2;
			x2 = 0;
			y2 = 0;
			map.blacktable();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(((b*c)) == number.number)
		{
			score+=1;
			if(score%6 == 0 && round<7)
				round+=1;
			number.pick(round);
			picked = 1;
			picked2 = 1;
			add = 0;
		}
		
	}

	
}
