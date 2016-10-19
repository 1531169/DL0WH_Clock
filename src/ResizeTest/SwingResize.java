package ResizeTest;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SwingResize extends JPanel
{
    protected void paintComponent(Graphics g)
    {
        int w = (int)(((double)(getWidth()))/2+0.5);
        int h = (int)(((double)(getHeight()))/2+0.5);
        g.setColor(Color.WHITE);
        g.fillRect(0, h, w,h);
        g.fillRect(w, 0, w,h);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.fillRect(w, h, w,h);
    }

    public static void main(String[] args)
    {
    	Toolkit.getDefaultToolkit().setDynamicLayout(true);
    	System.setProperty("sun.awt.noerasebackground", "true");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( new SwingResize() );
        frame.setSize(100, 100);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }

}