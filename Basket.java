import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Basket extends JComponent
{
	private int dx = 0, dy = 0;
	
	public Basket(int x, int y)
	{
		setLocation(x, y);
		setSize(40, 53);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		Ellipse2D.Double basket = new Ellipse2D.Double(0, 8, 15, 10);
		g2.fill(basket);
		Rectangle backboard = new Rectangle(15, 0, 4, 20);
		g2.fill(backboard);
		Rectangle support = new Rectangle(18, 16, 8, 4);
		g2.fill(support);
		Rectangle stem = new Rectangle(23, 18, 4, 35);
		g2.fill(stem);
	}

	public void setDY(int y)
	{
		dy = y;
	}
	
	public void setDX(int x)
	{
		dx = x;
	}
	
	public void update()
	{
		setLocation(getX() + dx, getY() + dy);
	}
	
}
