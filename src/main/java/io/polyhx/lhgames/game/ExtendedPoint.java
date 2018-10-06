package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.Point;

public class ExtendedPoint extends Point
{
	// A*
	private int gScore, fScore;
	private Point parent;

	public int getFScore() {
		return fScore;
	}

	public int getGScore() {
		return gScore;
	}

	public void setFScore(final int fScore) {
		this.fScore = fScore;
	}

	public void setGScore(final int gScore) {
		this.gScore = gScore;
	}

	public Point getParent() {
		return parent;
	}

	public void setParent(final Point parent) {
		this.parent = parent;
	}

	public double hypot(final Point point) {
		return 5; //Math.hypot();
	}
}
