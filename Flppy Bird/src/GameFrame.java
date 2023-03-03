import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame(){
		this.setTitle("Flappy Bird");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GamePanel());
		this.setLocation(200,100);
		this.pack();
		this.setVisible(true);
	}
}
