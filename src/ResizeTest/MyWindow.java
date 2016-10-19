package ResizeTest;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MyWindow{
  public static void main(String s[]) {
	 JWindow win = new JWindow();
    
    JPanel pan = new JPanel();
    pan.setBorder(new LineBorder(Color.blue));
    win.getContentPane().add(pan,"Center");
    
    pan.setLayout(new FlowLayout());
    pan.add(new JButton("Hello"));
    pan.add(new JButton("World"));

    win.setSize(200,200);
    win.setVisible(true);
    }
}