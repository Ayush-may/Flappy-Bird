import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Pipe {
	
	private Random random;
	private int top;
	private int bottom;
	private int speed;
	private int pipeX;
	private int pipeY;
	private int width;
	
	Pipe(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
		random = new Random();
		this.top = random.nextInt(SCREEN_HEIGHT/2);
		this.bottom = random.nextInt(SCREEN_HEIGHT/2);
		check(SCREEN_HEIGHT);
		this.width = 50;
		speed = 1;
		pipeX = SCREEN_WIDTH;
		pipeY = 0;
	}
	
	public void moveLeft() {
		pipeX -= speed;
	}
	
	public void check(int SCREEN_HEIGHT) {
		while(top < SCREEN_HEIGHT/3 + 50) {
			top = random.nextInt(SCREEN_HEIGHT/2 );
		}
		while(bottom > SCREEN_HEIGHT/3 + 50) {
			bottom = random.nextInt(SCREEN_HEIGHT/2 );
		}
	}
	
	public int getX() {
		return pipeX;
	}
	
	public int getY() {
		return pipeY;
	}
	
	public int getTop() {
		return top;
	}
	
	public int getBottom() {
		return bottom;
	}
	
	public int getWidth() {
		return width;
	}
	
}
