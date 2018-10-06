package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;

import java.util.List;

public class GoTo {

    public static ResourceTile getClosestResource(Map map, Point playerPos) {
        List<ResourceTile> resources = map.getResources();
        double distance = -1;
        ResourceTile tile = null;
        System.out.println("[ ");
        for (ResourceTile resTile : resources) {
            System.out.println(VectorPoint.toString(resTile.getPosition()));

            if ((resTile.getPosition().getDistanceTo(playerPos) < distance | distance == -1) && resTile.getResource() > 0) {
                tile = resTile;
            }
        }

        System.out.println("]\n");

        return tile;

    }


    public static VectorPoint goTo(Point playerPos, Point destination) {
        VectorPoint vectorPoint = new VectorPoint(destination).substract(playerPos);
        return vectorPoint.toDirection();
    }


    public static VectorPoint decision(Map map, Player player, List<Player> others, GameInfo info) {

        ResourceTile tile = GoTo.getClosestResource(map, player.getPosition());
        VectorPoint move = GoTo.goTo(player.getPosition(), tile.getPosition());
//        System.out.println("Tile: " + VectorPoint.toString(tile.getPosition()) + " player: " + VectorPoint.toString(player.getPosition()) + " : " + VectorPoint.toString(move));


        return move;
    }

    public static void mapPrint(Map map) {

        int sizeX = 25, sizeY = 70;
        char[][] mapo = new char[sizeX][sizeY];

        for (List<Tile> tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                mapo[tile.getX()][tile.getY()] = tile.getContent().getSymbol();
            }
        }
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                System.out.print(mapo[x][y] + " ");
            }
            System.out.println(" ");
        }
    }
}
