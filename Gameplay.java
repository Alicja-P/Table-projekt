package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Random;

public class Gameplay extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	private ImageIcon plus;
	private ImageIcon multiply;
	private ImageIcon multiply_three;
	private ImageIcon multiply_five;
	private ImageIcon logo;
	private ImageIcon rules;
	public static final Color MEDIUM_blue = new Color(0,162,232);
	
	private TableGenerator map;
	private Menu menu;
	Number number;
	public int round = 0;
	public int picked = 0;		//dobre zaznaczenie liczby picked=1
	public int picked2 = 0;		//zmienna potrzebna w trybie gry do jednorazowej zmiany pola po zaznaczeniu i utrzymaniu napisu "Brawo!"
	public int score = 0;
	public int column,row;
	public int check = 0;
	
	private int x, y, x1, y1, x2, y2;						// współrzędne kursora przy przeciąganiu, przesuwaniu i klikaniu
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Point> points1 = new ArrayList<Point>();
	ArrayList<Point> points2 = new ArrayList<Point>();
	
	private Timer timer;
	public int counter;
	
	public int b=0,c=0;	// zmienne do wyświetlania wyniku
	public int add = 0;
	public int multi = 1;
	public int ready = 0;
	public int game = 0;	// menu(0)/tryb nauki(1)/tryb gry(2)/jak grać(3)/przegrana(4)
	
	Random rand = new Random();
	
	public Gameplay() {
		map = new TableGenerator(10);
		menu = new Menu();
		number = new Number(7);
		number.pick(round);	//Wybieranie liczby do zaznaczenia
		check = number.number;
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(15,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("serif", Font.BOLD, 40));
		switch(game) {
		case 0: 
			menu.paint((Graphics)g);		
			logo = new ImageIcon("logo.png");
			logo.paintIcon(this, g, 495, 70);
			break;
		case 3:
			rules = new ImageIcon("jak.png");
			rules.paintIcon(this, g, 0, 0);
			g.setColor(Color.gray);
			g.fillRect(100,570, 210, 50);
			g.fillRect(350,570, 200, 50);
			g.setColor(Color.white);
			g.drawString("Tryb nauki", 105, 605);
			g.drawString("Tryb gry", 355, 605);
			break;
		case 4:
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 730);
			g.setColor(Color.gray);
			g.fillRect(200, 350, 125, 50);
			g.fillRect(340, 350, 300, 50);
			g.setColor(Color.white);
			g.drawString("Koniec gry",200,260);
			g.drawString("Chcesz grać dalej?",200,300);
			g.drawString("Gram",210,388);
			g.drawString("Powrót do menu",350,388);
			break;
		default: 
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 730);
			g.setColor(Color.white);
			g.drawString("Wynik: "+(score),800,260);
			g.drawString("Poziom: "+(round+1)+"/9",800,360);
			g.setColor(Color.gray);
			if(game==1)
			g.fillRect(900, 420, 330, 60);
			g.fillRect(900, 520, 330, 60);
			g.setColor(Color.white);
			if(game==1)
			g.drawString("Kolejny poziom", 920, 460);
			g.drawString("Powrót do menu", 920, 560);
			
			number.draw((Graphics2D)g);
			
			if(picked2 == 1) // dobre zaznaczenie
			{
				g.setColor(MEDIUM_blue);
				g.drawString("Brawo!", 800, 150);
			}
			if(game==1) {
				g.drawString(b+" * "+c+" = "+(b*c), 800, 190);	//pisanie wyniku w trybie nauki
				map.draw((Graphics2D)g,1);
			}
			if(game==2) {
				if(multi==1) {
					g.drawString(b+" * "+c, 800, 190);		//pisanie wyniku w trybie gry
					if(add>0)
					{
						g.drawString(" + "+add, 890, 190);
					}
				}
				else
				{
					if(add>0)
					g.drawString("( "+b+" * "+c+" + "+add+" ) * "+multi, 800, 190);
					else
					g.drawString("( "+b+" * "+c+" ) * "+multi, 800, 190);
				}
				map.draw((Graphics2D)g,0);
				plus = new ImageIcon("plus2.png");
				multiply = new ImageIcon("razy2.png");
				multiply_three = new ImageIcon("razy.png");
				multiply_five = new ImageIcon("razy5.png");
				plus.paintIcon(this, g, 80, 530);
				multiply.paintIcon(this, g, 210, 530);
				multiply_three.paintIcon(this, g, 340, 530);
				multiply_five.paintIcon(this, g, 470, 530);
				g.setColor(Color.gray);
				g.fillRect(80,650, 105, 40);
				g.fillRect(210,650, 106, 40);
				g.fillRect(340,650, 105, 40);
				g.fillRect(470,650, 105, 40);
				g.fillRect(600,650, 150, 40);
				g.setColor(Color.black);
				g.fillRect(800,30, 150, 20);
				g.drawString("Cofnij", 80, 680);
				g.drawString("Cofnij", 210, 680);
				g.drawString("Cofnij", 340, 680);
				g.drawString("Cofnij", 470, 680);
				g.drawString("Sprawdź", 600, 680);
				g.setColor(Color.white);
				g.drawString("Czas: "+counter/60, 800, 40);
			}	
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(game==2)
		{
			counter--;
			if(counter==0) 		//upłynięcie czasu w trybie gry
			{
				number.pick(round);
				check = number.number;
				map.blacktable();
				counter = 660;
				reset();
				score--;
				if(round<4)
					counter = 660;
				else
					counter = 1080;
			}
		}
	
		for(int i = 0; i<map.map.length; i++) {
			for(int j = 0; j<map.map[0].length; j++) {
				if(map.map[i][j]!=3) {
					if(game==1) {
						if( x > (j*60+80) && y > (i*60+50) && picked == 0 && x<676 && y<646)		//wartość elementu (pokolorowany lub nie) w zależności od pozycji kursora
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
					if(game==2) {
						if( x > (j*60+80) && y > (i*60+50) && picked == 0 && x<(map.row*60+78) && y<(map.column*60+46))		//wartość elementu (pokolorowany lub nie) w zależności od pozycji kursora
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
		
		if(game==2 && picked==1) {
			map.blacktable();
			picked=0;
			x2 = 0;
			y2 = 0;
			x = 0;
			y = 0;
		}
		
		if(score<0)
			game = 4;
		
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
		
		if(game==1 && x2>900 && x2<1200 && y2>420 && y2<480 )		//wybranie opcji "kolejny poziom"
		{
			if(round<8) {
				round+=1;
			number.pick(round);
			while(number.number==check)	//sprawdzanie by losowane liczby się nie powtarzały
				number.pick(round);
			check = number.number;
			}
		}
		if(x2>900 && x2<1200 && y2>520 && y2<580)		//wybranie opcji "powrót do menu"
		{
			game = 0;
			reset();
			score = 0;
			round = 0;
			map.blanktable();
		}
		if(game==0)
		{
			if(x2>450 && x2<750 && y2>(350) && y2<(420))		//wybranie opcji "tryb nauki" po kliknięciu myszką w przycisk menu
			{
				game = 1;
			}
			if(x2>450 && x2<750 && y2>(450) && y2<(520))		//wybranie opcji "tryb gry" po kliknięciu myszką w przycisk menu
			{
				game = 2;
				menu.menu[0] = 1;
				map.blacktable();
				counter = 660;
				number.pick(round);
				check = number.number;
			}
			if(x2>450 && x2<750 && y2>(550) && y2<(620))		//wybranie opcji "jak grać" po kliknięciu myszką w przycisk menu
			{
				game = 3;
			}
		}
		if(game==2)
		{
			if(x2>80 && x2<185 && y2>550 && y2<650)		//kliknięcie w przycisk "+1"
			{
				++add;
			}
			if(x2>210 && x2<310 && y2>550 && y2<650)	//kliknięcie w przycisk "x2"
			{
				multi = multi * 2;
			}
			if(x2>340 && x2<440 && y2>550 && y2<650)	//kliknięcie w przycisk "x3"
			{
				multi = multi * 3;
			}
			if(x2>470 && x2<570 && y2>550 && y2<650)	//kliknięcie w przycisk "x5"
			{
				multi = multi * 5;
			}
			if(x2>80 && x2<185 && y2>650 && y2<690)		//kliknięcie w przyciski "cofnij"
			{
				--add;
			}
			if(x2>210 && x2<316 && y2>650 && y2<690)
			{
				multi = multi/2;
			}
			if(x2>340 && x2<440 && y2>650 && y2<690)
			{
				multi = multi/3;
			}
			if(x2>470 && x2<570 && y2>650 && y2<690)
			{
				multi = multi/5;
			}
			if(x2>600 && x2<750 && y2>650 && y2<690)	// kliknięcie w przycisk "sprawdz"
			{
				ready = 1;
				if((add+(b*c))*multi != number.number)
				{
					score--;
					ready = 0;
				}
			}
			if((add+(b*c))*multi == number.number && ready==1)	//w trybie gry liczba jest zaznaczona dobrze i kliknięty jest przycisk "sprawdz"
			{
				score+=1;
				if(score%6 == 0 && round<7)
					round+=1;
				number.pick(round);
				while(number.number==check)	//sprawdzanie by losowane liczby się nie powtarzały
					number.pick(round);
				check = number.number;
				picked = 1;
				picked2 = 1;
				ready = 0;
				if(round<4)
					counter = 660;
				else
					counter = 1080;
			}
		}
		if(game==4)
		{
			if(x2>200 && x2<325 && y2>350 && y2<400)	//wybranie "gram" po przegranej
			{
				game=2;
				reset();
				score=0;
				picked = 0;
				picked2 = 0;
				round = 0;
				add = 0;
				multi = 1;
				counter=660;
			}
			if(x2>340 && x2<640 && y2>350 && y2<400)	//wybranie "powrót do menu" po przegranej
			{
				game = 0;
				reset();
				score = 0;
				round = 0;
				
				map.blanktable();
			}
		}
		
		if(game==3 && x2>100 && x2<310 && y2>570 && y2<630)	//wybranie tryby nauki w "jak grać"
		{
			game = 1;
		}
		if(game==3 && x2>350 && x2<550 && y2>570 && y2<620)	//wybranie tryby gry w "jak grać"
		{
			game = 2;
			reset();
			score = 0;
			round = 0;
			counter = 660;
			map.blacktable();
		}
		x2 = 0;
		y2 = 0;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(((b*c)) == number.number)		//dobre zaznaczenie liczby
		{
			score+=1;
			if(score%6 == 0 && round<7)
				round+=1;
			number.pick(round);
			while(number.number==check)
				number.pick(round);
			check = number.number;
			picked = 1;
			picked2 = 1;
			add = 0;
			if(round<4)
				counter = 660;
			else
				counter = 1080;
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}
	
	public void reset()		//częste ustawianie zmiennych przy przechodzeniu do innych opcji czy przegraniu
	{
		x = 0;
		y = 0;
		b = 0;
		c = 0;
		add = 0;
		multi = 1;
		picked = 0;
		picked2 = 0;
	}
}
