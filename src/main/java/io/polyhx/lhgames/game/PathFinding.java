package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.tile.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinding
{

	public static ArrayList<Tile> aStar(Tile begin, Tile end, Map map) {
		int tentativeGScore;

		final ArrayList<ExtendedPoint>
				openSet = new ArrayList<>(),
				closedSet = new ArrayList<>();

		final ExtendedPoint start = new ExtendedPoint(begin);
		openSet.add(start);
		// the cost of going ....
		start.setGScore(0);

		// for the first node
		// TODO Remove `start.` and case.calculerDistance OU Retirer le premier paramètre
		// Calculer la distance entre le nœud de départ et le nœud d’arrivée
		start.setFScore(start.hypot(end.getPosition()));

		//Case current;
		while (!openSet.isEmpty()) {

			ExtendedPoint current = null;
			int minScore = Integer.MAX_VALUE;
			for (final ExtendedPoint c : openSet)
				if (c.getFScore() < minScore)
					minScore = (current = c).getFScore();

			if (current.is(end))
				return reconstructPath(current);

			openSet.remove(current);
			closedSet.add(current);

			if (current.getNeighbours() == null)
				current.setNeighbours(map);

			for (final ExtendedPoint neighbour : current.getNeighbours()) {
				if (closedSet.contains(neighbour))
					continue;

				tentativeGScore = current.getGScore() + 1;

				// TODO Reconsider
				if (!openSet.contains(neighbour))
					openSet.add(neighbour);
				else if (tentativeGScore >= neighbour.getGScore())
					continue;

				neighbour.setParent(current);
				neighbour.setGScore(tentativeGScore);
				neighbour.setFScore(tentativeGScore + neighbour.hypot(end.getPosition()));
			}
		}

		// Aucun déplacement
		return new ArrayList<>();
	}

	private static ArrayList<Tile> reconstructPath(ExtendedPoint queue) {
		final ArrayList<Tile> path = new ArrayList<>();
		do {
			path.add(queue.getTile());
			queue = queue.getParent();
		} while( queue != null);
		// TODO Do no invert (start from end)
		Collections.reverse(path);
		return path;
	}
}
