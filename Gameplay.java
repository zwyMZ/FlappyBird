
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
public class Gameplay extends JPanel implements MouseListener, ActionListener{
	private int score;
	private Timer timer;
	private int delay =20;
	private Rectangle bird;
	private boolean Gameover,started;
	private int Width=800;
	private int Height=800;
	private ArrayList<Rectangle> columns;
	private Random random;
	private int ticks,ymotion;
	public Gameplay(){
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(20,this);
		bird = new Rectangle(Width/2-10,Height/2-10,20,20);
		columns = new ArrayList<Rectangle>();
		addcolumn(true);
		addcolumn(true);
		addcolumn(true);
		addcolumn(true);
		timer.start();
	}
	public void paint(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 0, Width, Height);
		//bird
		g.setColor(Color.red);
		g.fillOval(bird.x, bird.y, bird.width, bird.height);
		//set ground
		g.setColor(Color.orange);
		g.fillRect(0, Width-120, Width, 120);
		//set grass
		g.setColor(Color.green);
		g.fillRect(0, Width-120, Width, 20);
		for (Rectangle column :columns ){
			drawColumn(g,column);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial",1,100));
		if(Gameover){
			g.drawString("Game Over!",75,Height/2-50);
		}
		if(!started){
			g.drawString("Clic to start!",75,Height/2-50);
		}
		if (!Gameover && started)
		{
			g.drawString(String.valueOf(score), Width / 2 - 25, 100);
		}
	}
	public void addcolumn(boolean begin){
		int space =300;
		int width =100;
		random=new Random();
		int height = 50+random.nextInt(300);
		if(begin){
			columns.add(new Rectangle(Width+width+columns.size()*300,Height-height-120,width,height));
			columns.add(new Rectangle(Width+width+(columns.size()-1)*300,0,width,Height-height-space));	
		}else{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+600,Height-height-120,width,height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x,0,width,Height-height-space));
		}
	}
	public void drawColumn(Graphics g,Rectangle column){
		g.setColor(Color.green);
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	public void fly(){
		if(Gameover){
			bird = new Rectangle(Width/2-10,Height/2-10,20,20);
			columns.clear();
			ymotion=0;
			score=0;
			addcolumn(true);
			addcolumn(true);
			addcolumn(true);
			addcolumn(true);
			Gameover=false;
		}
		if(!started){
			started=true;
		}else if(!Gameover){
			if(ymotion>0){
				ymotion=0;
			}
			ymotion-=10;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ticks++;
		int speed =5;
		if(started){
			for(int i=0; i<columns.size();i++){
				Rectangle column = columns.get(i);
				column.x-=speed;
			}
			if(ticks%2==0 && ymotion<15){
				ymotion+=2;
			}
			for(int i=0; i<columns.size();i++){
				Rectangle column = columns.get(i);
				if (column.x+column.width<0){
					columns.remove(column);
					if(column.y==0){
						addcolumn(false);
					}
				}
			}
		}
		bird.y+=ymotion;
		
		for(Rectangle column : columns){
			if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 5 && bird.x + bird.width / 2 < column.x + column.width / 2 + 5)
			{
				score++;
			}
			if (column.intersects(bird)){
				Gameover=true;
				bird.x=column.x-bird.width;
			}
			if (bird.y>Height-120||bird.y<0){
				Gameover=true;
			}
			if(bird.y+ymotion>=Height-120){
				bird.y=Height-120-bird.height;
			}
		}

		repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		fly();	
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
		// TODO Auto-generated method stub
		
	}

}
