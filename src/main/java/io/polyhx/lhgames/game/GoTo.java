package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.action.*;
import io.polyhx.lhgames.game.bot.Bot;
import io.polyhx.lhgames.game.point.IPoint;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoTo {

    public static ResourceTile getClosestResource(Map map, Point playerPos, ArrayList<Point> positionsToIgnore) {
        List<ResourceTile> resources = map.getResources();
        double distance = -1;
        ResourceTile tile = null;
        System.out.println("[ ");
        for (ResourceTile resTile : resources) {
            System.out.println(VectorPoint.toString(resTile.getPosition()));

            if ((resTile.getPosition().getDistanceTo(playerPos) < distance | distance == -1) && resTile.getResource() > 0) {
                boolean shouldIgnore = false;
                for (Point point : positionsToIgnore) {
                    if (VectorPoint.equals(point, resTile.getPosition())) {
                        shouldIgnore = true;
                        break;
                    }
                }
                if (!shouldIgnore) {
                    tile = resTile;
                }
            }
        }

        System.out.println("]\n");

        return tile;

    }


    public static VectorPoint goTo(Point playerPos, Point destination) {
        VectorPoint vectorPoint = new VectorPoint(destination).substract(playerPos);
        return vectorPoint.toDirection();
    }

    public static void goAround(Point move) {
        System.out.println("###UNSTUCKING@@@");
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {

            int n = rand.nextInt(3) + 0;
            switch (n) {
                case 0:
                    Bot.path.add(new Point(1, 0));

                    break;
                case 1:
                    Bot.path.add(new Point(-1, 0));
                    break;
                case 2:
                    Bot.path.add(new Point(0, -1));
                    break;
                case 3:
                    Bot.path.add(new Point(0, 1));
                    break;
            }
        }
    }

    public static AbstractPointAction decisionMove(Map map, Player player, List<Player> others, GameInfo info, ArrayList<Point> pointsToIgnore, Point destination) {
        VectorPoint move = GoTo.goTo(player.getPosition(), destination);


        Tile futureTile = map.getTile(VectorPoint.add(player.getPosition(), move));

        if (futureTile.isWall()) {
            System.err.println("ATTACKING WALL");
            return new MeleeAttackAction(move);
//                return decision(map, player, others, info, pointsToIgnore);
        }
        else if (futureTile.isShop()) {
            goAround(move);
        }
        else if (futureTile.isHouse() && !VectorPoint.equals(futureTile.getPosition(), player.getHousePosition())) {
            goAround(move);
        }

        else if (futureTile.isResource()) {

            goAround(move);
        }
        else if (futureTile.isPlayer()) {
            return new MeleeAttackAction(move);
        }
        else if (futureTile.isLava()) {
            goAround(move);
            pointsToIgnore.add(futureTile.getPosition());
            return decision(map, player, others, info, pointsToIgnore);
        }
        else {
            System.err.println("Going to:" + destination);
            return new MoveAction(move);

        }
        System.out.println("EMPTYUMOVE@@");
        return new MoveAction(new Point());
    }


    public static AbstractPointAction decision(Map map, Player player, List<Player> others, GameInfo info, ArrayList<Point> pointsToIgnore) {

        System.out.println(pointsToIgnore);
        System.out.println("PlayerPos: " + VectorPoint.toString(player.getPosition()));
        System.out.println("Player Backpack: " + player.getCarriedResource() + " Cap: " + player.getResourceCapacity());

        ResourceTile tile = GoTo.getClosestResource(map, player.getPosition(), pointsToIgnore);
        if (Bot.IsFull(player)) {
            System.err.println("GOING TO HOME");

            return decisionMove(map, player, others, info, pointsToIgnore, player.getHousePosition());
        }
        if (tile != null) {
            VectorPoint move = GoTo.goTo(player.getPosition(), tile.getPosition());


            Tile futureTile = map.getTile(VectorPoint.add(player.getPosition(), move));

            if (futureTile.isResource()) {
                ResourceTile tile12 = null;
                for (ResourceTile resourceTile : map.getResources()) {
                    if (VectorPoint.equals(resourceTile.getPosition(), futureTile.getPosition())) {
                        tile12 = resourceTile;
                    }
                }
                if (tile12 != null) {
                    System.out.println(tile12.getResource());

                }
                return new CollectAction(move);
            }
            else {
                return decisionMove(map, player, others, info, pointsToIgnore, tile.getPosition());
            }
        }
        else {
            System.err.println("Going HOME");
            return decisionMove(map, player, others, info, pointsToIgnore, player.getHousePosition());
        }

    }


    public static void mapPrint(Map map) {

        int sizeX = 50, sizeY = 70;
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
