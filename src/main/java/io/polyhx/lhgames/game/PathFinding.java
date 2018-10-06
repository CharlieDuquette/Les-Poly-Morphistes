package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class PathFinding
{
	private Point           person;
	private ArrayList<Point> openSet;

	//public PathFinding(Case[][] map, ArrayList<Tile> coffres, Tile person, Tile sortie) {
	//	this.person = person;
	//	this.coffres = new ArrayList<>(coffres);
	//	final Tile sortie1 = sortie;
	//	this.map = map;
	//}

	//public ArrayList<Tile> bestPath() {
	//	Tile start = person;

		/*ArrayList<Tile>
				coffresOrdonnes = new ArrayList<>(),
				inaccessible = new ArrayList<>();

		for (final Tile coffre : coffres) {
			coffre.setSize(aStar(coffre, start).size());
			if (coffre.getSize() > 0)
				coffresOrdonnes.add(coffre);
			else
				inaccessible.add(coffre);
		}

    // A*
    private int gScore, fScore;
    private Point parent;

		coffresOrdonnes.sort((tile, t1) -> Integer.compareUnsigned(tile.getSize(), t1.getSize()));

		ArrayList<Tile> pathComplet = aStar(start, coffresOrdonnes.get(0));

		int n;
		for (n = 1; n < coffresOrdonnes.size(); n++)
			pathComplet.addAll(aStar(coffresOrdonnes.get(n - 1), coffresOrdonnes.get(n)));

		pathComplet.addAll(aStar(coffresOrdonnes.get(n - 1), sortie));*/
		//ArrayList<Tile> pathComplet = aStar(start, coffres.get(0));

		//pathComplet.addAll(aStar(coffres.get(0), coffres.get(1)));
		//pathComplet.addAll(aStar(coffres.get(1), coffres.get(2)));

		//return pathComplet;
	//}

	private ArrayList<Point> aStar(Point begin, Point end, Map map) {
		int tentativeGScore;
/*
		final ArrayList<Point> closedSet = new ArrayList<>();
		openSet = new ArrayList<>();
		final Point start = map[begin.getY()][begin.getX()];
		openSet.add(start);
		// the cost of going ....
		start.setGScore(0);

		// for the first node
		// TODO Remove `start.` and case.calculerDistance OU Retirer le premier paramètre
		// Calculer la distance entre le nœud de départ et le nœud d’arrivée
		start.setFScore(start.calculerDistance(end));

		//Case current;
		while (!openSet.isEmpty()) {

			Point current = new Point(0, 0);
			int minScore = Integer.MAX_VALUE;
			for (final Point c : openSet)
				if (c.getFScore() < minScore)
					minScore = (current = c).getFScore();

			//if (current.getX() == end.getX() && current.getY() == end.getY()) {
			if (current.equals(end))
				return reconstructPath(current, start);

			openSet.remove(current);
			closedSet.add(current);

			for (final Point neighbour : current.neighbours) {
				if (closedSet.contains(neighbour))
					continue;

				//if (openSet.indexOf(n) < 0) {
				//	openSet.add(n);
				tentativeGScore = current.getGScore() + 1;
				//}

				// TODO Reconsider
				if (!openSet.contains(neighbour))
					openSet.add(neighbour);
				else if (tentativeGScore >= neighbour.getGScore())
					continue;

				//if (tentativeGScore >= n.getGScore()) {
				//	continue;
				//}

				// this path is the best record it

				neighbour.setParent(current);
				neighbour.setGScore(tentativeGScore);
				neighbour.setFScore(tentativeGScore + neighbour.calculerDistance(end));
			}
		}*/
		//Runner.reset();
		return new ArrayList<>();
	}

	private ArrayList<Point> reconstructPath(Point current, Point start) {

		/*ArrayList<Tile> moveInverse = new ArrayList<>();
		Tile            moveTemp;

		// current.position.getX() == start.position.getX()
		//	&& current.position.getY() == start.position.getY()
		while (current.calculerDistance(start.position) != 0 && current.parent != null) {

			moveTemp = new Tile(current.position.getX() - current.parent.position.getX(),
					current.position.getY() - current.parent.position.getY());

			if (!(current.typePrincip == ' ' && current.typeSecond == 'x' && moveTemp.getY() == 1)) {
				moveInverse.add(moveTemp);
			} else {
				moveInverse.add(moveTemp);
			}

			current = current.parent;

			Collections.reverse(moveInverse);

			moveInverse.forEach(tile -> System.out.println(tile.getX() + " " + tile.getY()));

			// inverse la array
			//ArrayList<Tile> temp = new ArrayList<>();
			//for (int i = moveInverse.size() - 1; i >= 0; i--) {
			//	temp.add(moveInverse.get(i));
			//	System.out.println(moveInverse.get(i).getX() + "   " + moveInverse.get(i).getY());
			//}
		}
		//Runner.reset();
		return moveInverse;
		*/
		return new ArrayList<>();
	}
}
