package de.dl0wh.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.time.LocalTime;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Clock extends JComponent {
	private static final int START_POINT	= 23,
					  		 PADDING		= 23,
					  		 TWO			= 2,
					  		 DEG_CIRCLE		= 360;

	private RenderingHints hints = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	private boolean run		= true;
	// Settings for showing watch hands
	private boolean isWatchHandHourEnabled		= true;
	private boolean isWatchHandMinuteEnabled	= true;
	private boolean isWatchHandSecondEnabled 	= true;
	private boolean isWatchHandPointsEnabled	= true;

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
		drawStripesInFiveMinuteSteps(g);
		drawStripesInHourSteps(g);
		drawWatchHands(g);
		// make transparent
		//AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.5f);
		//g.setComposite(alpha);
		//drawTime(g);
		
		g.dispose();
	}
	
	void drawStripesInHourSteps(Graphics2D g) {
		drawStripesByWatchHandType(g, WatchHand.HOURSTROKE);
	}
	
	void drawStripesInFiveMinuteSteps(Graphics2D g) {
		drawStripesByWatchHandType(g, WatchHand.MINUTESTROKE);
	}
	
	private void drawStripesByWatchHandType(Graphics2D g, WatchHand wH) {
		g.setStroke(WatchHand.BASICSTROKE);
		g.setColor(wH.getColor());
		for(int i = 1; i <= DEG_CIRCLE / wH.getDegOfThis(); i++) {
			Point pStart = wH.getPointByCirclePosition(getMiddleXY(), getRadius(), i);
			Point pStop  = wH.getPointByCirclePositionFullSize(getMiddleXY(), getRadius(), i);
			g.drawLine(pStart.x, pStart.y, pStop.x, pStop.y);
		}
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
		Point p = wH.getPoint(getMiddleXY(), getRadius());
		g.setColor(wH.getColor());
		g.drawLine(getMiddleXY(), getMiddleXY(), p.x, p.y);
		if(isWatchHandPointsEnabled()) {
			Point dP = wH.getDotPoint(getMiddleXY(), getRadius());
			// abstand zur linie abziehen
			g.fillOval(dP.x, dP.y, wH.DOTSIZE, wH.DOTSIZE);
		}
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
	
	int getRadius() {
		return getDiameter() / TWO;
	}
	
	int getDiameter() {
		return getMin() - (TWO * getPadding());
	}
	
	int getMiddleXY() {
		return getMin() / TWO;
	}
	
	Point getCenterPoint() {
		int xy = getMin() / TWO;
		return new Point(xy, xy);
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
	 * @return the startPoint
	 */
	public int getStartPoint() {
		return START_POINT;
	}
	
	/**
	 * @return the padding
	 */
	public int getPadding() {
		return PADDING;
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
	
	/**
	 * @return the isWatchHandPointsEnabled
	 */
	public boolean isWatchHandPointsEnabled() {
		return isWatchHandPointsEnabled;
	}

	/**
	 * @param isWatchHandPointsEnabled the isWatchHandPointsEnabled to set
	 */
	public void setWatchHandPointsEnabled(boolean isWatchHandPointsEnabled) {
		this.isWatchHandPointsEnabled = isWatchHandPointsEnabled;
	}
}
