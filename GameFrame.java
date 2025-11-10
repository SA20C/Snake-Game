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

import javax.swing.JFrame;

/**
 * Purpose: The reponsibility of GameFrame is ...
 *
 * GameFrame is-a ...
 * GameFrame is ...
 */
public class GameFrame extends JFrame{
	
	GameFrame(){
		
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

}
