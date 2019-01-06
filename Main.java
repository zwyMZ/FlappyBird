
import javax.swing.*;
public class Main {
	public static void main(String [] args){
		JFrame obj = new JFrame();
		obj.setSize(800,800);
		Gameplay gameplay = new Gameplay();
		obj.setTitle("Brickout Ball");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);	
	}
}
