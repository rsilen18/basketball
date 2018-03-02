import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
/**
 * 
 * @author rsilen18
 *	high score: 14
 */
public class WalkingFrame extends JFrame implements ActionListener
{
	private Man man;
	private Basket basket;
	private ArrayList<Ball> balls;
	boolean isJumping = false;
	private int score = 0;
	private JLabel scoreboard, instructions1, instructions2, instructions3, clock, gameOver;
	private double time = 60;
	
	public WalkingFrame()
	{
		setBounds(100, 100, 600, 400);
		setLayout(null);
		man = new Man(0, getHeight() - 80);
		add(man);
		basket = new Basket(500, getHeight() - 91);
		add(basket);
		basket.setDX(-1);
		JLabel label1 = new JLabel("Score:");
		label1.setBounds(10, 10, 50, 20);
		label1.setVisible(true);
		add(label1);
		scoreboard = new JLabel("" + score);
		scoreboard.setBounds(60, 10, 50, 20);
		scoreboard.setVisible(true);
		add(scoreboard);
		clock = new JLabel("Clock:  " + time);
		clock.setBounds(10, 30, 85, 20);
		clock.setVisible(true);
		add(clock);
		instructions1 = new JLabel("A and D to move left and right");
		instructions1.setBounds(getWidth() - 200, 10, 200, 20);
		instructions1.setVisible(true);
		add(instructions1);
		instructions2 = new JLabel("W to jump");
		instructions2.setBounds(getWidth() - 200, 30, 200, 20);
		instructions2.setVisible(true);
		add(instructions2);
		instructions3 = new JLabel("Spacebar to shoot");
		instructions3.setBounds(getWidth() - 200, 50, 200, 20);
		instructions3.setVisible(true);
		add(instructions3);
		gameOver = new JLabel("Game Over");
		gameOver.setBounds(getWidth() / 2, getHeight() / 2, 180, 40);
		gameOver.setVisible(false);
		add(gameOver);
		balls = new ArrayList<Ball>();
		Timer timer = new Timer(10, this);
		timer.start();
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_W)
				{
					if (isJumping == false)
						man.setDY(-15);
				}
				if (e.getKeyCode() == e.VK_A)
				{
					man.setDX(-2);
				}
				if (e.getKeyCode() == e.VK_D)
				{
					man.setDX(2);
				}
				if (e.getKeyCode() == e.VK_SPACE)
				{
					if (balls.size() == 0)
					{
						balls.add(new Ball(man.getX(), man.getY()));
						add(balls.get(balls.size() - 1));
						balls.get(balls.size() - 1).setDX(10);
						balls.get(balls.size() - 1).setDY(-20);
						System.out.println(balls.size());
						System.out.print(score + "_");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == e.VK_S)
				{
					man.setDY(0);
				}
				if (e.getKeyCode() == e.VK_A)
				{
					man.setDX(0);
				}
				if (e.getKeyCode() == e.VK_D)
				{
					man.setDX(0);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		});
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		new WalkingFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (man.getY() < getHeight() - 80)
		{
			man.setDY(man.getDY() + 1);
			isJumping = true;
		}
		else if (man.getY() >= getHeight() - 79)
		{
			man.setDY(0);
			man.setLocation(man.getX(), getHeight() - 80);
			isJumping = false;
		}
		if (man.getX() < 0)
		{
			man.setDX(0);
			man.setLocation(0, man.getY());
		}
		else if (man.getX() > getWidth() - 40)
		{
			man.setDX(0);
			man.setLocation(getWidth() - 40, man.getY());
		}
		man.update();
		for (int i = balls.size() - 1; i >= 0; i--)
		{
			balls.get(i).setDY(balls.get(i).getDY() + 1);
			balls.get(i).update();
			if (balls.get(i).getX() + 10 >= basket.getX() + 10 && balls.get(i).getX() + 10 <= basket.getX() + 25 && balls.get(i).getY() + 10 >= basket.getY() + 5 && balls.get(i).getY() + 10 <= basket.getY() + 21)
			{
				score++;
				scoreboard.setText("" + score);
			}
			if (balls.get(i).getX() >= getWidth() || balls.get(i).getY() >= getHeight())
			{
				balls.get(i).setVisible(false);
				balls.remove(i);
			}
		}
		if (time > 0)
		{
			time -= 0.01;
			clock.setText("Clock:  " + time);
		}
		if (time <= 0)
		{
			gameOver.setLocation(getWidth() / 2, getHeight() / 2);
			gameOver.setVisible(true);
			man.setLocation(man.getX(), man.getY());
			basket.setDX(0);
			for (int i = balls.size() - 1; i >= 0; i--)
			{
				balls.get(i).setVisible(false);
			}
		}
		instructions1.setBounds(getWidth() - 200, 10, 200, 20);
		instructions2.setBounds(getWidth() - 200, 30, 200, 20);
		instructions3.setBounds(getWidth() - 200, 50, 200, 20);
		basket.setLocation(basket.getX(), getHeight() - 91);
		if (basket.getX() <= 300)
			basket.setDX(1);
		else if (basket.getX() >= getWidth() - 40)
			basket.setDX(-1);
		basket.update();
		repaint();
	}
	
}
