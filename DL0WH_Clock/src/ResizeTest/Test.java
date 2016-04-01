package ResizeTest;

import java.awt.Color;
import de.dl0wh.clock.*;
import javax.swing.JFrame;

public class Test {
	public static void main(String[] args) {
		/*
		Frame f = new Frame();
		f.setVisible(true);
		f.setBackground(new Color(255, 0, 255));
		*/
		
		JFrame f2 = new JFrame();
		f2.add(new Clock());
		f2.setVisible(true);
		f2.getContentPane().setBackground(new Color(255, 0, 255));
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**/
	}
}
