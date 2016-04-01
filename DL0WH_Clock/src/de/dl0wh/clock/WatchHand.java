package de.dl0wh.clock;

import java.awt.Color;
import java.time.LocalTime;

public enum WatchHand {
	HOUR( 15,  30, Color.GREEN),// 360 / 24 = 15 
	MINUTE(6,  10, Color.BLUE), // 360 / 60 = 6
	SECOND(6, -10, Color.RED);

	private int degOfThis;
	private int padding;
	private Color color;
	
	private WatchHand(int degOfThis, int padding, Color color) {
		this.degOfThis = degOfThis;
		this.padding   = padding;
		this.color	   = color;
	}
	
	public int getX(int factor) {
		// TODO: remove magic value
		double deg = 450 - (getTime() * getDegOfThis());
		factor -= getPadding();
		return (int)(Math.cos(Math.toRadians(deg)) * factor);
	}
	
	public int getY(int factor) {
		// TODO: remove magic value
		double deg = 450 - (getTime() * getDegOfThis());
		factor -= getPadding();
		return (int)(Math.sin(Math.toRadians(deg)) * factor);
	}
	
	private int getTime() {
		if(this == HOUR) {
			return LocalTime.now().getHour();
		} else if (this == MINUTE) {
			return LocalTime.now().getMinute();
		} else {
			return LocalTime.now().getSecond();
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
}
