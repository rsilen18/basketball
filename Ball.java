import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Ball extends JComponent
{
	private int dx = 0, dy = 0;
	
	public Ball(int x, int y)
	{
		setLocation(x, y);
		setSize(10, 10);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double ball = new Ellipse2D.Double(0, 0, 10, 10);
		g2.setColor(Color.ORANGE);
		g2.fill(ball);
	}
	
	public void setDX(int x)
	{
		dx = x;
	}
	
	public void setDY(int y)
	{
		dy = y;
	}
	
	public int getDY()
	{
		return dy;
	}
	
	public void update()
	{
		setLocation(getX() + dx, getY() + dy);
	}
}
