package de.dl0wh.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.time.LocalTime;

public enum WatchHand {
	HOUR		(15,  30, 0.5F,  Color.GREEN),	// 360 / 24 = 15
	MINUTE		( 6,  10, 0.7F,  Color.BLUE),		// 360 / 60 = 6
	SECOND		( 6, -10, 0.9F,  Color.RED),		// 360 / 60 = 6
	HOURSTROKE	(30,   1, 0.9F,  Color.DARK_GRAY), // 360 / 12 = 30
	MINUTESTROKE( 6,   1, 0.95F, Color.GRAY);		// 360 / 60 = 6

	private int   degOfThis;
	private int   padding;
	private Color color;
	private float factor;
	static final int DEG_START	 = 450;
	static final int DEG_STOP	 = 60;
	static final int DOTSIZE	 = 10;
	static final BasicStroke BASICSTROKE = new BasicStroke(1);
	static final float FULL_SIZE_FACTOR = 1.0F;

	private WatchHand(int degOfThis, int padding, float factor, Color color) {
		this.degOfThis = degOfThis;
		this.padding   = padding;
		this.factor    = factor;
		this.color 	   = color;
	}
	
	public Point getPoint(int xyPos, int radius) {
		int x = xyPos + getX(radius);
		int y = xyPos - getY(radius);
		return new Point(x, y);
	}
	
	public Point getPointByCirclePositionFullSize(int xyPos, int radius, int circlePos) {
		return getPointByCirclePosition(xyPos, radius, circlePos, FULL_SIZE_FACTOR);
	}
	
	public Point getPointByCirclePosition(int xyPos, int radius, int circlePos) {
		return getPointByCirclePosition(xyPos, radius, circlePos, getFactor());
	}
	
	public Point getPointByCirclePosition(int xyPos, int radius, int circlePos, float factor) {
		int x = xyPos + elementarGetX(radius, circlePos, getDegOfThis(), factor);
		int y = xyPos - elementarGetY(radius, circlePos, getDegOfThis(), factor);
		return new Point(x, y);
	}
	
	public Point getDotPoint(int xyPos, int radius) {
		xyPos -= (DOTSIZE / 2);
		int x = xyPos + getX(radius, FULL_SIZE_FACTOR);
		int y = xyPos - getY(radius, FULL_SIZE_FACTOR);
		return new Point(x, y);
	}

	public int getX(int radius) {
		return getX(radius, getFactor());
	}

	public int getY(int radius) {
		return getY(radius, getFactor());
	}

	public int getDotX(int radius) {
		return getX(radius, FULL_SIZE_FACTOR);
	}

	public int getDotY(int radius) {
		return getY(radius, FULL_SIZE_FACTOR);
	}

	private int getX(int radius, float factor) {
		// degree = 450(= upper point in circle) - (HH/MM/SS * degrees of
		// HH/MM/SS)
		return elementarGetX(radius, getTime(), getDegOfThis(), factor);
	}
	
	private int elementarGetX(int radius, int i, int degPerElm, float factor) {
		double deg = DEG_START - (i * degPerElm);
		return (int) (Math.cos(Math.toRadians(deg)) * radius * factor);
	}

	private int getY(int radius, float factor) {
		// degree = 450(= upper point in circle) - (HH/MM/SS * degrees of
		// HH/MM/SS)
		return elementarGetY(radius, getTime(), getDegOfThis(), factor);
	}
	
	private int elementarGetY(int radius, int i, int degPerElm, float factor) {
		double deg = DEG_START - (i * degPerElm);
		return (int) (Math.sin(Math.toRadians(deg)) * radius * factor);
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

	/**
	 * @param factor
	 *            the factor to set
	 */
	public void setFactor(float factor) {
		this.factor = factor;
	}
}
