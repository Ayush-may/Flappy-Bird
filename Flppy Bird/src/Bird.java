import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Bird {
	private static final int BIRD_SIZE = 20;
	private double gravity;
	private double birdX, birdY;
	private double velocityY;
	private int lift;
	private int SCREEN_WIDTH , SCREEN_HEIGHT;
	Boolean touch;
	
	
	Bird(int SCREEN_WIDTH, int SCREEN_HEIGHT ){
		this.SCREEN_WIDTH = SCREEN_WIDTH;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
		gravity = 0.8;
		lift = -13;
		touch = true;
		setBirdPos();
	}
	
	public void setBirdPos() {
		birdX = SCREEN_WIDTH/3;
		birdY = SCREEN_HEIGHT/2;
	}
	
	public void moveUp() {
		velocityY += lift;
		birdY += velocityY;
	}
	
	public void moveDown() {
		if(touch) {
			birdY += velocityY;
			velocityY *= 0.9;
			velocityY += gravity;
		}
	}
	
	public void checkCollision() {
		if(birdY < 0) {
			velocityY = 0;
			birdY = 0;
		}
		
		if(birdY + BIRD_SIZE > SCREEN_HEIGHT) {
			birdY = SCREEN_HEIGHT - BIRD_SIZE; 
		}
	}
	
	public int getSize() {
		return BIRD_SIZE;
	}
	public double getX() {
		return birdX;
	}
	public double getY() {
		return birdY;
	}
	public void setX(int x) {
		birdX = x;
	}
}
