package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.Tile;

import java.util.ArrayList;

public class ExtendedPoint
{

	private int gScore = Integer.MAX_VALUE, fScore;
	private final Tile tile;
	private ExtendedPoint parent;
	private ArrayList<ExtendedPoint> neighbours;

	ExtendedPoint(final Tile tile) {
		this.tile = tile;
	}

	public boolean is(final Tile tile) {
		return this.tile.getX() == tile.getX() && this.tile.getY() == tile.getY();
	}

	public Tile getTile() {
		return tile;
	}

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

	public ExtendedPoint getParent() {
		return parent;
	}

	public void setParent(final ExtendedPoint parent) {
		this.parent = parent;
	}

	public ArrayList<ExtendedPoint> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(final Map map) {
		// optimise no arraylist if no neighb
		neighbours = new ArrayList<>();
		Tile tile;

		// (tile = dd).
		// Top
		tile = map.getTileAboveOf(this.tile.getPosition());
		if (isWalkable(tile))
			neighbours.add(new ExtendedPoint(tile));

		// Bottom
		tile = map.getTileBelowOf(this.tile.getPosition());
		if (isWalkable(tile))
			neighbours.add(new ExtendedPoint(tile));

		// Right
		tile = map.getTileRightOf(this.tile.getPosition());
		//System.out.println("" + tile.getX() + " " + tile.getY());
		if (isWalkable(tile))
			neighbours.add(new ExtendedPoint(tile));

		// Left
		tile = map.getTileLeftOf(this.tile.getPosition());
		if (isWalkable(tile))
			neighbours.add(new ExtendedPoint(tile));
	}

	private boolean isWalkable(final Tile tile) {
		return tile != null && (tile.isEmpty() || tile.isWall());
	}

	public int hypot(final Point point) {
		//return (int)Math.hypot(this.tile.getX() - point.getX(), this.tile.getY() - point.getY());
		return Math.abs(point.getX() - tile.getX()) + Math.abs(point.getY() - tile.getY());
	}
}
