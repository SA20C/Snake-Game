/**
* Lead Author(s):
* @author Stevin Atkins ; student ID
* References:
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*BroCode. (2020b, July 20). Java snake game 🐍. Www.youtube.com. https://www.youtube.com/watch?v=bI6e6qjJ8JQ
*
*/
package SnakeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Purpose: The reponsibility of GamePanel is ...
 *
 * GamePanel is-a ...
 * GamePanel is ...
 */
public class GamePanel extends JPanel implements ActionListener{
	//Static final creates constant integers 
	static final int SCREEN_WIDTH = 600; //Could be changed for a larger width and more x coords 
	static final int SCREEN_HEIGHT = 600; //Could be changed for a larger height
	static final int UNIT_SIZE = 25; //Increases the size of the units in the game 
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/ UNIT_SIZE;
	static final int DELAY = 75;
	final int x [] = new int[GAME_UNITS]; //holds x coordinates of the body parts to include the head 
	final int y [] = new int[GAME_UNITS]; //holds all the y coordinates
	int bodyParts = 2; //Starting snek size 
	int applesEaten;
	int appleX; //x coord for apples, which will appear randomly each time
	int appleY; //y coords of apples 
	char direction = 'R'; //Snake will start off facing the right direction, could be changed to L:Left, U: Up, D: Down, 
	boolean running = false;
	Timer timer;
	Random random;
	
	
	
	GamePanel(){
			random = new Random();
			this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
			this.setBackground(Color.black);
			this.setFocusable(true);
			this.addKeyListener(new MyKeyAdapter());
			startGame();
		}
		public void startGame() {
			newApple();
			running = true;
			timer = new Timer(DELAY, this);
			timer.start();
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		public void draw(Graphics g) {
			
			
			
			
			//creating a matrix within our game to showcase the unit size
			if(running) {
				//This code below is for the grids - optional if you want it on or not 
//				for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE;i++) {
//					g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); 
//					g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//			}
			g.setColor(Color.yellow);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(new Color(15,80,20)); //Head color 
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(10,70,10)); //Body Color
					g.setColor(new Color (random.nextInt(255), random.nextInt(255),random.nextInt(255))); //Creates a rainbow worm, can be commented out as well.
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			g.setColor(Color.red);
			g.setFont(new Font("Impact",Font.PLAIN, 25));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " +applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " +applesEaten))/2, g.getFont().getSize());
			}
		else {
			gameOver(g);
			}
		}
		public void newApple() {
			//generate the new coordinates of an apple when this method is called, places the apple randomly within the grid  
			appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //x coords of apples 
			appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE; //y coords of apples
		}
		public void move() { //creating a for loop to cycle through the various snake body parts 
			for(int i = bodyParts;i > 0;i--) {
				x[i] = x[i - 1]; //shifting all the coordinates in the array over by 1 
				y[i] = y[i - 1];
			}
			
			switch(direction) { //creating the directions in which our snake can move
				// y coord of the head of our snake
				case 'U':
					y[0] = y[0] - UNIT_SIZE;  //Move up
					break;
				case 'D':
					y[0] = y[0] + UNIT_SIZE;  //Move down
					break;
				case 'L':
					x[0] = x[0] - UNIT_SIZE;  //Move left
					break;
				case 'R':
					x[0] = x[0] + UNIT_SIZE;  //Move Right
					break;
			}
		}
		public void checkApple() {
			if((x[0] == appleX) && (y[0] == appleY)) {
				bodyParts++;
				applesEaten++;
				newApple();
			}
			
		}
		public void checkCollisions() {
			//checks if head collides with body
			for(int i = bodyParts; i > 0; i--) {
				if((x[0] == x[i]) && (y[0] == y[i])) {
					running = false;
					
				}
			}
			//check if head touches left border
			if(x[0] < 0) {
				running = false;
			}
			//check if head touches right border
			if(x[0] > SCREEN_WIDTH) {
				running = false;
			}
			//check if head touches top border
			if(y[0] < 0) {
				running = false;
			}
			//check if head touches bottom border
			if(y[0] > SCREEN_HEIGHT) {
				running = false;
			}
			if(!running) {
				timer.stop();
			}
			
			
		}
		public void gameOver(Graphics g) {
			//Score
			g.setColor(Color.red);
			g.setFont(new Font("Impact",Font.BOLD, 25));
			FontMetrics metricsA = getFontMetrics(g.getFont());
			g.drawString("Score: " +applesEaten, (SCREEN_WIDTH - metricsA.stringWidth("Score: " +applesEaten))/2, g.getFont().getSize());
			
			//Game over text
			g.setColor(Color.red);
			g.setFont(new Font("Impact",Font.BOLD, 75));
			FontMetrics metricsB = getFontMetrics(g.getFont());
			g.drawString("Game Over", (SCREEN_WIDTH - metricsB.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2); //This will put the game over message in the center of the screen
		}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(direction != 'R') {
						direction = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(direction != 'L') {
						direction = 'R';	
					}
					break;
				case KeyEvent.VK_UP:
					if(direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction != 'U') {
						direction = 'D';
					}
					break;
			}
		}
	}
}