/*
 * 2-14-2025
 * Aarav Goyal
 * Masterpiece.java
 * 
 * This program will draw three Flowers and print "Scenery" on the bottom.
 */ 

import java.awt.*;
import javax.swing.*;

public class Masterpiece
{
	public static void main(String[] args) 
	{
		Masterpiece mp = new Masterpiece();
		mp.runIt();
	}

	public void runIt()
	{
		JFrame frame = new JFrame("Scenery Masterpiece");
		frame.setSize(1200, 950);
		frame.setLocation(100, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(false);       
		MasterpiecePanel mpPanel = new MasterpiecePanel();
		frame.getContentPane().add(mpPanel);
		frame.setVisible(true);
	}
}   

class MasterpiecePanel extends JPanel
{
	public MasterpiecePanel()
	{
		setBackground(new Color(0, 166, 255));
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		int flower1X = panelWidth / 2 - 50;
		int flower1Y = panelHeight / 2 - 50;
		int flower2X = panelWidth / 2 - 300;
		int flower2Y = panelHeight / 2 + 50;
		int flower3X = panelWidth / 2 + 200;
		int flower3Y = panelHeight / 2;

		drawMountains(g);
		drawScene(g, flower1X, flower1Y); 
		drawScene(g, flower2X, flower2Y); 
		drawScene(g, flower3X, flower3Y);
		//drawGrid(g);
	}


	public void drawScene(Graphics g, int x, int y)
	{  
		drawSun(g);

		drawStems(g, x, y);

		drawSoil(g);

		drawGrass(g);

		drawLeaves(g, x, y);

		drawBirds(g);

		drawPetals(g, x, y);

		drawCenter(g, x, y);

		drawClouds(g);

		drawText(g);
	} 

	public void drawSun(Graphics g)
	{
		g.setColor(new Color(253, 184, 19));
		g.fillOval(1000, -150, 300, 300);
	}

	public void drawStems(Graphics g, int x, int y)
	{
		g.setColor(new Color(34, 139, 34)); 
		g.fillRect(x+5, y, 30, 400);
	}

	public void drawSoil(Graphics g)
	{
		g.setColor(new Color(171, 91, 48));
		g.fillRect(0, 800, 1200, 200);
	}

	public void drawMountains(Graphics g) 
	{
		g.setColor(Color.GRAY);
		int[] mount1X = {-200, 100, 400};
		int[] mount1Y = {800, 300, 800};
		g.fillPolygon(mount1X, mount1Y, 3);  

		int[] mount2X = {100, 600, 1100};
		int[] mount2Y = {800, 100, 800};
		g.fillPolygon(mount2X, mount2Y, 3); 

		int[] mount3X = {800, 1100, 1400};
		int[] mount3Y = {800, 300, 800};
		g.fillPolygon(mount3X, mount3Y, 3); 

		g.setColor(new Color(239, 228, 221));
		int[] mount4X = {27, 100, 173};
		int[] mount4Y = {420, 300, 420};
		g.fillPolygon(mount4X, mount4Y, 3);

		int[] mount5X = {443, 600, 757};
		int[] mount5Y = {320, 100, 320};
		g.fillPolygon(mount5X, mount5Y, 3);

		int[] mount6X = {1027, 1100, 1173};
		int[] mount6Y = {420, 300, 420};
		g.fillPolygon(mount6X, mount6Y, 3);


		g.setColor(Color.BLACK);
		g.drawLine(100, 300, 0, 466); 
		g.drawLine(100, 300, 250, 550);   

		g.drawLine(600, 100, 100, 800);   
		g.drawLine(600, 100, 1100, 800);  

		g.drawLine(1100, 300, 937, 571);  
		g.drawLine(1100, 300, 1200, 466); 
	}


	public void drawLeaves(Graphics g, int x, int y)
	{
		g.setColor(new Color(0, 128, 0)); 
		int[] leafX1 = {x - 15, x - 75, x+5};
		int[] leafY1 = {y + 100, y + 160, y + 140};
		g.fillPolygon(leafX1, leafY1, 3);

		int[] leafX2 = {x + 33, x + 123, x + 63};
		int[] leafY2 = {y + 140, y + 160, y + 100};
		g.fillPolygon(leafX2, leafY2, 3);
	}

	public void drawGrass(Graphics g)
	{
		g.setColor(new Color(126,200,80));
		for (int i = 0; i < 1200; i+=7)
		{
			int tall = (int)(Math.random()*15)+5;
			g.fillRect(i, 800 - tall, 3, tall);
		}

		g.setColor(new Color(86, 125, 50)); 
		g.fillRect(0, 800, 1200, 20);		
	}

	public void drawBirds(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawArc(400, 75, 100, 50, 0, 90);
		g.drawArc(500, 75, 100, 50, 90, 90);

		g.drawArc(600, 100, 100, 50, 0, 90);
		g.drawArc(700, 100, 100, 50, 90, 90);
	}

	public void drawPetals(Graphics g, int x, int y)
	{
		g.setColor(Color.RED);
		g.fillArc(x - 40, y - 140, 120, 120, 0, 180); 
		g.fillArc(x - 80, y - 100, 120, 120, 90, 180); 
		g.fillArc(x + 4, y - 100, 120, 120, 270, 180); 
		g.fillArc(x - 36, y - 60, 120, 120, 180, 180); 

		g.setColor(Color.PINK);
		g.fillOval(x - 100, y - 160, 120, 120); 
		g.fillOval(x + 20, y - 160, 120, 120); 
		g.fillOval(x - 100, y - 40, 120, 120);
		g.fillOval(x + 20, y - 40, 120, 120); 
	}

	public void drawCenter(Graphics g, int x, int y)
	{
		g.setColor(Color.YELLOW);
		g.fillOval(x - 30, y - 90, 100, 100);

		g.setColor(Color.BLACK);
		g.drawLine(x + 40, y + 6, x, y - 86);
		g.drawLine(x, y + 6, x + 40, y - 86);
		g.drawLine(x - 30, y - 40, x + 70, y - 40);
		g.fillOval(x + 10, y - 50, 20, 20); 

	}

	public void drawClouds(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(90,100,200,100);
		g.fillOval(106,10,180,180);
		g.fillOval(40,100,100,100);
		g.fillOval(250,100,100,100);

		g.fillRect(890,100,200,100);
		g.fillOval(906,10,180,180);
		g.fillOval(840,100,100,100);
		g.fillOval(1050,100,100,100);
	}

	public void drawText(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("serif", Font.PLAIN, 80));
		g.drawString("Scenery", 490, 900);
	}

	public void drawGrid(Graphics g)
	{
		int size = 10;
		int width = 1200;    
		int height = 1000;  
		g.setFont(new Font("serif", Font.PLAIN, 15));

		for(int x = 0; x <= width; x += size)
		{
			g.setColor(new Color(200, 200, 200));
			g.drawLine(x, 0, x, height);

			if(x % 100 == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(x + "", x - 12, 12);
			}
		}


		for(int y = 0; y <= height; y += size)
		{
			g.setColor(new Color(200, 200, 200));
			g.drawLine(0, y, width, y);

			if(y % 100 == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(y + "", 0, y + 15);
			}
		}
	}
}
