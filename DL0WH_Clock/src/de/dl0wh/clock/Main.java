package de.dl0wh.clock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
class Main extends JFrame {
	private final Dimension minDim 	   = new Dimension(getMinWidth(), getMinHeight());
	private final Dimension defaultDim = new Dimension(getDefWidth(), getDefHeight());

	private final int paddingFactor = 2,
					  padding		= 20,
					  clearPoint	= 0,
					  startPoint	= 0,
					  minWidth		= 200 - 23, 
					  minHeight		= 200,
					  defWidth		= 500 - 23,
					  defHeight		= 500;

	Main() {
		init(getDefaultDim());
	}
	
	Main(Dimension dimension) {
		init(dimension);
	}
	
	Main(int width, int height) {
		init(new Dimension(width, height));
	}
	
	private void init(Dimension dim) {
		setTitle("Analog Uhr");
		setSize(dim);
		setMinimumSize(getMinDim());
		setLocationCentered();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// add clock on pane
		Clock clock = new Clock(100, 100);
		add(clock);
		setVisible(true);
		clock.start();
	}
	
	int getStartPosition() {
		return getStartPoint() + getPadding();
	}
	
	int getMaxCompSize() {
		return getMin() - (getPaddingFactor() * getPadding());
	}
	
	int getMin() {
		return Math.min(getContentPane().getWidth(), getContentPane().getHeight());
	}
	
	private void setLocationCentered() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = ((dim.width / 2) - (getWidth() / 2));
		int y = ((dim.height / 2) - (getHeight() / 2));
		setLocation(x, y);
	}

	/**
	 * Starts the whole program!
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
            		// against the flickering
            		Toolkit.getDefaultToolkit().setDynamicLayout(true);
            		JFrame.setDefaultLookAndFeelDecorated(true);
                	System.setProperty("sun.awt.noerasebackground", "true");
            	} catch(Exception e) {
            		System.err.println(e.getMessage());
            	}
           }
        });
		new Main();
	}

	/**
	 * -------------------------------------------------- 
	 * Start of getter-methods of final properties.
	 * --------------------------------------------------
	 */

	/**
	 * Gets the current factor for padding in a circle.
	 * 
	 * @return the padding
	 */
	public int getPaddingFactor() {
		return paddingFactor;
	}
	
	/**
	 * Gets the current padding the frame.
	 * 
	 * @return the padding
	 */
	public int getPadding() {
		return padding;
	}

	/**
	 * Returns starting point of clearing the frame.
	 * 
	 * @return the clearPoint
	 */
	public int getClearPoint() {
		return clearPoint;
	}
	
	/**
	 * Gets x and y position of left top corner.
	 * @return the startPoint
	 */
	public int getStartPoint() {
		return startPoint;
	}
	
	/**
	 * Gets dimension of the minimum size of the frame.
	 * 
	 * @return the minDimension
	 */
	public Dimension getMinDim() {
		return minDim;
	}
	
	/**
	 * Gets minimum width of the frame.
	 * 
	 * @return the minWidth
	 */
	public int getMinWidth() {
		return minWidth;
	}

	/**
	 * Gets minimum height of the frame.
	 * 
	 * @return the minHeight
	 */
	public int getMinHeight() {
		return minHeight;
	}
	
	/**
	 * Gets dimension of the default size of the frame.
	 * @return the defaultDim
	 */
	public Dimension getDefaultDim() {
		return defaultDim;
	}

	/**
	 * Gets the default width of the window.
	 * @return the defWidth
	 */
	public int getDefWidth() {
		return defWidth;
	}

	/**
	 * Gets the default height of the window.
	 * @return the defHeight
	 */
	public int getDefHeight() {
		return defHeight;
	}
}
