package de.dl0wh.clock;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalTime;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Clock extends JComponent {
	
	/*
	 * TODO: remove magic values!!!!
	 */
	
	private final int clearPoint	= 0,
					  startPoint	= 23,
					  padding		= 23,
					  paddingHour	= 30,
					  paddingMin	= 15,
					  paddingSec	= 0;

	private RenderingHints hints = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	private boolean run		= true;
	// Settings for showing watch hands
	private boolean isWatchHandHourEnabled		= true;
	private boolean isWatchHandMinuteEnabled	= true;
	private boolean isWatchHandSecondEnabled 	= true;

	public Clock() {
	}
	
	public Clock(int width, int height) {
		setSize(width, height);
	}
	
	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D)g2.create();
		
		// render all components
		g.setRenderingHints(hints);
		
		drawOval(g);
		drawWatchHands(g);
		
		// make transparent
		//AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.5f);
		//g.setComposite(alpha);
		//drawTime(g);
		
		g.dispose();
	}
	
	void drawStripsInHourSteps() {
		// TODO: implement hour stripes
	}
	
	void drawStripesInFiveMinuteSteps() {
		// TODO: implement minutes stripes
	}
	
	void drawOval(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawOval(getStartPoint(), getStartPoint(), 
				getDiameter(), getDiameter());
	}
	
	void drawWatchHands(Graphics2D g) {
		if(isWatchHandSecondEnabled()) {
			drawWatchHand(g, WatchHand.SECOND);
		}
		if(isWatchHandMinuteEnabled()) {
			drawWatchHand(g, WatchHand.MINUTE);
		}
		if(isWatchHandHourEnabled()) {
			drawWatchHand(g, WatchHand.HOUR);
		}
	}
	
	void drawWatchHand(Graphics2D g, WatchHand wH) {
		int x = getMiddleXY() + wH.getX(getFactor());
		int y = getMiddleXY() - wH.getY(getFactor());
		g.setColor(wH.getColor());
		g.drawLine(getMiddleXY(), getMiddleXY(), x, y);
	}
	
	void drawTime(Graphics2D g) {
		// TODO: get last things from AnalogDigitalUhr.java
		// TODO: remove magic value
		Font f = new Font(Font.SERIF, Font.PLAIN, getMin() / 15);
		g.setFont(f);
		g.drawString(getLocalTimeAsString(), getMiddleXY(), getMiddleXY());
	}
	
	String getLocalTimeAsString() {
		LocalTime now = LocalTime.now();
		int hh = now.getHour();
		int mm = now.getMinute();
		int ss = now.getSecond();
		return hh + "." + mm + "." + ss;
	}
	
	int getFactor() {
		return getDiameter() - getMiddleXY();
	}
	
	int getMin() {
		return Math.min(getWidth(), getHeight());
	}
	
	int getDiameter() {
		// TODO: remove magic value
		return getMin() - (2 * getPadding());
	}
	
	int getMiddleXY() {
		// TODO: remove magic value
		return getMin() / 2;
	}
	
	/**
	 * --------------------------------------------------
	 * Control methods for the clock.
	 * --------------------------------------------------
	 */
	
	void start() {
		run = true;
		run();
	}
	
	void stop() {
		run = false;
	}
	
	private void run() {
		while(run)
		{
			repaint();
		}
	}
	
	/**
	 * --------------------------------------------------
	 * Start of getter-methods of final properties.
	 * --------------------------------------------------
	 */
	
	/**
	 * Returns starting point of clearing the component.
	 * @return the clearPoint
	 */
	public int getClearPoint() {
		return clearPoint;
	}
	
	/**
	 * Gets graphics of the component.
	 * @return the graphics
	 */
	public Graphics2D getGraphics() {
		return (Graphics2D)getGraphics();
	}

	/**
	 * @return the startPoint
	 */
	public int getStartPoint() {
		return startPoint;
	}
	
	/**
	 * @return the padding
	 */
	public int getPadding() {
		return padding;
	}
	
	/**
	 * @return the paddingHour
	 */
	public int getPaddingHour() {
		return paddingHour;
	}

	/**
	 * @return the paddingMin
	 */
	public int getPaddingMin() {
		return paddingMin;
	}

	/**
	 * @return the paddingSec
	 */
	public int getPaddingSec() {
		return paddingSec;
	}

	/**
	 * @return the run
	 */
	public boolean isRun() {
		return run;
	}
	
	/**
	 * @return the isWatchHandHourEnabled
	 */
	public boolean isWatchHandHourEnabled() {
		return isWatchHandHourEnabled;
	}

	/**
	 * @param isWatchHandHourEnabled the isWatchHandHourEnabled to set
	 */
	public void setWatchHandHourEnabled(boolean isWatchHandHourEnabled) {
		this.isWatchHandHourEnabled = isWatchHandHourEnabled;
	}

	/**
	 * @return the isWatchHandMinuteEnabled
	 */
	public boolean isWatchHandMinuteEnabled() {
		return isWatchHandMinuteEnabled;
	}

	/**
	 * @param isWatchHandMinuteEnabled the isWatchHandMinuteEnabled to set
	 */
	public void setWatchHandMinuteEnabled(boolean isWatchHandMinuteEnabled) {
		this.isWatchHandMinuteEnabled = isWatchHandMinuteEnabled;
	}

	/**
	 * @return the isWatchHandSecondEnabled
	 */
	public boolean isWatchHandSecondEnabled() {
		return isWatchHandSecondEnabled;
	}

	/**
	 * @param isWatchHandSecondEnabled the isWatchHandSecondEnabled to set
	 */
	public void setWatchHandSecondEnabled(boolean isWatchHandSecondEnabled) {
		this.isWatchHandSecondEnabled = isWatchHandSecondEnabled;
	}
}
