package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.action.*;
import io.polyhx.lhgames.game.bot.Bot;
import io.polyhx.lhgames.game.point.IPoint;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;
import io.polyhx.lhgames.game.tile.TileContent;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoTo {


    private static ArrayList<Tile> runFirst(Tile start, Tile end, Map map) {
        ArrayList<Tile> path = new ArrayList<>();
        System.out.println("Start: x " + start.getX() + " y " + start.getY());
        System.out.println("End  : x " + end.getX() + " y " + end.getY());
        path = PathFinding.aStar(start, end, map);
        System.out.println("YEE");
        for (Tile t : path)
            System.out.printf("(%d, %d) : %s\n", t.getX(), t.getY(), t.getContent());
        // TODO Reconsider
        path.remove(0);
        return path;
    }

    private static Point getNextMovePathFinding(Player player, Map map, ArrayList<Tile> path) {

        if (0 < path.size()) {
            Tile tile = path.get(0);
            Point p = new Point(tile.getX() - player.getPosition().getX(), tile.getY() - player.getPosition().getY());
            System.out.println(" " + p.getX() + " " + p.getY());
            return p;
        }
        path = null;
//        i = 0;

        return new Point();

    }

    public static ResourceTile getClosestResource(Map map, Point playerPos, ArrayList<Point> positionsToIgnore) {
        List<ResourceTile> resources = map.getResources();
        double distance = -1;
        ResourceTile tile = null;
        for (ResourceTile resTile : resources) {

            if ((resTile.getPosition().getDistanceTo(playerPos) < distance | distance == -1) && resTile.getResource() > 0 ) {
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


        return tile;

    }

    public static Tile getClosestShit(Map map, Point playerPos, ArrayList<Point> positionsToIgnore, TileContent shit) {
        //Get list of shits

        ArrayList<Tile> shits = new ArrayList();
        for (List<Tile> tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                if (tile.getContent().equals(shit)) {
                    shits.add(tile);
                }

            }
        }

        Tile patate = null;
        //getting closest
        double distance = -1;
        for (Tile tile : shits) {
            if (tile.getPosition().getDistanceTo(playerPos) < distance | distance == -1) {
                boolean shouldIgnore = false;
                for (Point point : positionsToIgnore) {
                    if (VectorPoint.equals(point, tile.getPosition())) {
                        shouldIgnore = true;
                        break;
                    }
                }
                if (!shouldIgnore) {
                    patate = tile;
                }
            }
        }
        return patate;

    }


    public static VectorPoint goTo(Point playerPos, Point destination) {
        VectorPoint vectorPoint = new VectorPoint(destination).substract(playerPos);
        return vectorPoint.toDirection();
    }


    public static VectorPoint goArround(Map map, Player player, VectorPoint move) {

        System.out.println("@@ Go around");

        Random rand = new Random();


        Tile futureTile = map.getTile(VectorPoint.add(player.getPosition(), move));
        while (futureTile.isResource()) {
            int  n = rand.nextInt(3) + 0;

            switch (n) {
                case 0:
                    move = new VectorPoint(-1, 0);
                    break;
                case 1:
                    move = new VectorPoint(1, 0);
                    break;
                case 2:
                    move = new VectorPoint(0, 1);
                    break;
                case 3:
                    move = new VectorPoint(0, -1);
                    break;
            }
            futureTile = map.getTile(VectorPoint.add(player.getPosition(), move));

        }



        return move;
    }

    public static IAction decisionMove(Map map, Player player, List<Player> others, GameInfo info, ArrayList<Point> pointsToIgnore, Point destination) {

        VectorPoint move = GoTo.goTo(player.getPosition(), destination);


        Tile futureTile = map.getTile(VectorPoint.add(player.getPosition(), move));

        if (futureTile.isWall()) {
            System.out.println("@@@ATTACKING WALL");
            return new MeleeAttackAction(move);
//                return decision(map, player, others, info, pointsToIgnore);
        }

        else if (futureTile.isResource()) {
            System.out.println("@@ UNSTuCK ");

            ArrayList<Tile> path = runFirst(map.getTile(player.getPosition()), map.getTile(destination), map);

            return new MoveAction(getNextMovePathFinding(player, map, path));


            //return new MoveAction(goArround(map, player, move));
        }
        else if (futureTile.isPlayer()) {
            return new MeleeAttackAction(move);
        }
        else if (futureTile.isLava()) {
            pointsToIgnore.add(futureTile.getPosition());
            return decision(map, player, others, info, pointsToIgnore);
        }
        else {
            System.out.println("@@@Going to:" + destination);
            return new MoveAction(move);

        }
    }


    public static IAction decision(Map map, Player player, List<Player> others, GameInfo info, ArrayList<Point> pointsToIgnore) {
        System.out.println(pointsToIgnore);
        System.out.println("PlayerPos: " + VectorPoint.toString(player.getPosition()));
        System.out.println("Player Backpack: " + player.getCarriedResource() + " Cap: " + player.getResourceCapacity());

        Tile tile = null;
        System.out.println(getInfoOnPlayer(player));

        TileContent destinationContent = null;
        if (player.getTotalResource() > 30000 && getNextItemToBuy(player) != null && !Bot.IsFull(player)) {

            System.out.println("@@@GOINGTOSHOP");

            tile = getClosestShit(map, player.getPosition(), pointsToIgnore, TileContent.SHOP);
            destinationContent = TileContent.SHOP;
            if (tile == null) {
                System.out.println("@@@Looking for shop");
                return decisionMove(map, player, others, info, pointsToIgnore, new Point(0, 0));

            }

        }
        else {
            tile = GoTo.getClosestResource(map, player.getPosition(), pointsToIgnore);
            destinationContent = TileContent.RESOURCE;
        }


        System.out.println("Destination: " + destinationContent);

        if (Bot.IsFull(player)) {
            System.out.println("@@@GOING TO HOME BECAUSE FULL");

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
            else if (destinationContent == TileContent.SHOP && futureTile.isShop()) {
                Item itemToBuy = getNextItemToBuy(player);
                System.out.println("@@@BUYING : " + itemToBuy);

                    return new PurchaseAction(itemToBuy);

            }
            else {
                System.out.println("@@@GettingRessource");
                return decisionMove(map, player, others, info, pointsToIgnore, tile.getPosition());
            }
        }
        else {

            System.out.println("@@@Going HOME because tile null");
            return decisionMove(map, player, others, info, pointsToIgnore, player.getHousePosition());
        }

    }


    public static Item getNextItemToBuy(Player player) {


        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.PICKAXE);
        items.add(Item.BACKPACK);
        items.add(Item.SWORD);
        items.add(Item.SHIELD);
        items.add(Item.HEALTH_POTION);

        for (Item item : items) {
            if (!player.getItems().contains(item)) {
                return item;
            }
        }
        return null;

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

    public static String getInfoOnPlayer(Player player){
        return "Nom du joueur : " + player.getName() + "\n" +
                "position : ( " + player.getX() + "," + player.getX() + ") \n" +
                "Place dans le sac : " + player.getResourceCapacity() + "\n" +
                "Ressource dans le sac : " + player.getCarriedResource() + "\n" +
                "Total rour" + player.getTotalResource() + "\n" +
                "Vitesse de collecte : " + player.getCollectingSpeed()+ "\n" +
                "Capacite level : " + player.getCapacityLevel() + "\n" +
                "Sante level : " + player.getHealthLevel() + "\n" +
                "Sante actuelle : " + player.getCurrentHealth() + "\n" ;
    }
}
