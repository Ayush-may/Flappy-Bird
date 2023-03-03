import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener , ActionListener{

	private static final int SCREEN_WIDTH = 360;
	private static final int SCREEN_HEIGHT = 600;
	private static final int DELAY = 10;
	private int frameCount;
	private int score;
	private boolean running;
	private ArrayList<Pipe> pipe;
	private Bird bird;
	private Timer timer;
	
	GamePanel(){
		running = true;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLUE);
		this.setFocusable(true);
		this.addKeyListener(this);
		pipe = new ArrayList<Pipe>();
		pipe.add(new Pipe(SCREEN_WIDTH, SCREEN_HEIGHT));
		bird = new Bird(SCREEN_WIDTH, SCREEN_HEIGHT);
		timer = new Timer (DELAY, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(running) {
			frameCount++;
			if(frameCount == 200) {
				pipe.add(new Pipe(SCREEN_WIDTH, SCREEN_HEIGHT));
				frameCount = 0;
			}
			for(int i=0; i<pipe.size(); i++) {
				g.setColor(Color.RED);
				g.fillRect(  pipe.get(i).getX(), 0, pipe.get(i).getWidth(), (int)pipe.get(i).getTop()  );
				g.fillRect(  pipe.get(i).getX(), SCREEN_HEIGHT - (int)pipe.get(i).getBottom(), pipe.get(i).getWidth(),SCREEN_HEIGHT - (int)pipe.get(i).getTop()  );
				pipe.get(i).moveLeft();
				checkScore( (int)(bird.getX() + bird.getSize()) , (int)pipe.get(i).getX() );
				checkCollision(pipe.get(i));
			}	
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.BOLD, 50)); 
			g.drawString(score + "",SCREEN_WIDTH/2-10, 50);
			g.setColor(Color.WHITE);
			g.fillRect((int)bird.getX(), (int)bird.getY(), bird.getSize(), bird.getSize());			
		}
		else {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 50)); 
			g.drawString("Game Over!!", SCREEN_WIDTH/8-10, SCREEN_HEIGHT/2);
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.BOLD, 50)); 
			g.drawString(score + "",SCREEN_WIDTH/2-10, 50);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Press Space to play again", SCREEN_WIDTH/6, SCREEN_HEIGHT/2 + 30);
		}
	}
	
	public void checkCollision(Pipe p) {
		if(bird.getX() + bird.getSize() == p.getX() && p.getTop() > bird.getY() ) {
			bird.setX(p.getX() - bird.getSize());
			running = false;
			timer.stop();
			repaint();
		}
		if(p.getX() < bird.getX() + bird.getSize()/2 && p.getX() + p.getWidth() > bird.getX() + bird.getSize()/2 && bird.getY() < p.getY() + p.getTop()) {
			running = false;
			timer.stop();
			repaint();
		}
		if(bird.getX() + bird.getSize() == p.getX() && bird.getY() + bird.getSize() > SCREEN_HEIGHT - p.getBottom() ) {
			running = false;
			timer.stop();
			repaint();
		}
		if(bird.getX() + bird.getSize()/2 > p.getX() && bird.getX() + bird.getSize()/2 < p.getX() + p.getWidth() && bird.getY() + bird.getSize() > SCREEN_HEIGHT - p.getBottom()) {
			running = false;
			timer.stop();
			repaint();
		}
	}
	public void checkScore(int birdX , int pipeX) {
		if(birdX == pipeX) {
			score++;
		}
	}
	public void replay() {
		score = 0;
		running = true;
		bird.setBirdPos();
		pipe = new ArrayList<Pipe>();
		pipe.add(new Pipe(SCREEN_WIDTH, SCREEN_HEIGHT));
		frameCount = 0;
		timer.restart();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		bird.moveDown();
		bird.checkCollision();
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(running) {
				bird.moveUp();
			}
			else{
				replay();
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
}
