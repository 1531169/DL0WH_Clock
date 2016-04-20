package de.dl0wh.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.time.LocalTime;

public enum WatchHand {
	HOUR		(15,  30, 0.5F,  Color.GREEN),	// 360 / 24 = 15
	MINUTE		( 6,  10, 0.7F,  Color.BLUE),	// 360 / 60 = 6
	SECOND		( 6, -10, 0.9F,  Color.RED);	// 360 / 60 = 6
	
	private int   degOfThis;
	private int   padding;
	private Color color;
	private float factor;
	static final BasicStroke BASICSTROKE = new BasicStroke(1);
	static final int DOTSIZE	 = 10;

	private WatchHand(int degOfThis, int padding, float factor, Color color) {
		this.degOfThis = degOfThis;
		this.padding   = padding;
		this.factor    = factor;
		this.color 	   = color;
	}
	
	public Point getPoint(int xyPos, int radius) {
		int curPos = getTime() * getDegOfThis();
		return CirclePointCalculations
				.getPointByCirclePosition(xyPos, radius, curPos, getFactor());
	}
	
	public Point getDotPoint(int xyPos, int radius) {
		//xyPos -= (DOTSIZE / 2);
		Point p = CirclePointCalculations
				.getPoint(xyPos, radius, getTime() * getDegOfThis());
		p.x -= (DOTSIZE / 2);
		p.y -= (DOTSIZE / 2);
		return p;
	}

	private int getTime() {
		switch (this) {
			case HOUR:
				return LocalTime.now().getHour();
			case MINUTE:
				return LocalTime.now().getMinute();
			case SECOND:
				return LocalTime.now().getSecond();
			default:
				return 0;
		}
	}

	/**
	 * @return the degOfThis
	 */
	public int getDegOfThis() {
		return degOfThis;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	/**
	 * @return the factor
	 */
	public int getPadding() {
		return padding;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the factor
	 */
	public float getFactor() {
		return factor;
	}
}