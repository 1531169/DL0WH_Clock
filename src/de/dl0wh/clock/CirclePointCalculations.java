package de.dl0wh.clock;

import java.awt.Point;

public class CirclePointCalculations {
	static final int DEG_START	 = 450;
	static final int DEG_STOP	 = 60;
	static final int DOTSIZE	 = 10;
	static final float FULL_SIZE_FACTOR = 1.0F;
	
	public static Point getPoint(int xyPos, int radius, int circlePos) {
		int x = xyPos + getX(radius, circlePos, FULL_SIZE_FACTOR);
		int y = xyPos - getY(radius, circlePos, FULL_SIZE_FACTOR);
		return new Point(x, y);
	}
	
	public static Point getPointByCirclePositionFullSize(int xyPos, int radius, int circlePos) {
		return getPointByCirclePosition(xyPos, radius, circlePos, FULL_SIZE_FACTOR);
	}
	
	public static Point getPointByCirclePosition(int xyPos, int radius, int circlePos, float factor) {
		int x = xyPos + getElementarX(radius, circlePos, factor);
		int y = xyPos - getElementarY(radius, circlePos, factor);
		return new Point(x, y);
	}

	public static int getX(int radius, int circlePos, float factor) {
		// degree = 450(= upper point in circle) - (HH/MM/SS * degrees of
		// HH/MM/SS)
		return getElementarX(radius, circlePos, factor);
	}
	
	public static int getElementarX(int radius, int circlePos, float factor) {
		double deg = DEG_START - circlePos;
		return (int) (Math.cos(Math.toRadians(deg)) * radius * factor);
	}

	public static int getY(int radius, int circlePos, float factor) {
		// degree = 450(= upper point in circle) - (HH/MM/SS * degrees of
		// HH/MM/SS)
		return getElementarY(radius, circlePos, factor);
	}
	
	public static int getElementarY(int radius, int circlePos, float factor) {
		double deg = DEG_START - circlePos;
		return (int) (Math.sin(Math.toRadians(deg)) * radius * factor);
	}
}
