package de.dl0wh.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;

public enum WatchStripe {
	HOURSTROKE	(30,   1, 0.9F,  Color.DARK_GRAY), // 360 / 12 = 30
	MINUTESTROKE( 6,   1, 0.95F, Color.GRAY);	   // 360 / 60 = 6
	
	private int   degOfThis;
	private int   padding;
	private Color color;
	private float factor;
	static final BasicStroke BASICSTROKE = new BasicStroke(1);
	
	private WatchStripe(int degOfThis, int padding, float factor, Color color) {
		this.degOfThis = degOfThis;
		this.padding   = padding;
		this.factor    = factor;
		this.color 	   = color;
	}
	
	public Point getStartPoint(int xyPos, int radius, 
			int circlePos) {
		return CirclePointCalculations
				.getPointByCirclePosition(xyPos, radius, 
						circlePos * getDegOfThis(), getFactor());
	}
	
	public Point getEndPoint(int xyPos, int radius, 
			int circlePos) {
		return CirclePointCalculations
				.getPointByCirclePositionFullSize(xyPos, radius, 
						circlePos  * getDegOfThis());
	}
	
	/**
	 * -------------------------------------------------- 
	 * Start of getter-methods of final properties.
	 * --------------------------------------------------
	 */

	/**
	 * @return the degOfThis
	 */
	public int getDegOfThis() {
		return degOfThis;
	}

	/**
	 * @param degOfThis the degOfThis to set
	 */
	public void setDegOfThis(int degOfThis) {
		this.degOfThis = degOfThis;
	}

	/**
	 * @return the padding
	 */
	public int getPadding() {
		return padding;
	}

	/**
	 * @param padding the padding to set
	 */
	public void setPadding(int padding) {
		this.padding = padding;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the factor
	 */
	public float getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(float factor) {
		this.factor = factor;
	}
}
