package io.polyhx.lhgames.game.bot;

import io.polyhx.lhgames.game.*;
import io.polyhx.lhgames.game.action.AbstractPointAction;
import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;

import java.util.ArrayList;
import java.util.List;


public class Bot extends BaseBot {


    public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
//        GoTo.mapPrint(map);


        IAction action = GoTo.decision(map, player, others, info, new ArrayList<>());

        System.out.println(action.getActionType() + " : "  + action.getJSONContent());
        return action;
    }

    public static boolean IsFull(Player player) {
        if (player.getCarriedResource() >= player.getResourceCapacity())
            return true;
        else
            return false;
    }



    private int i = 0;

    private static Point NULL = new Point();

    private static ArrayList<Tile> path;

    private void runFirst(Tile start, Tile end, Map map) {
       i = 0;
       path = null;
        System.out.println("Start: x " + start.getX() + " y " + start.getY());
        System.out.println("End  : x " + end.getX() + " y " + end.getY());
        path = PathFinding.aStar(start, end, map);
        System.out.println("YEE");
        for (Tile t : path)
            System.out.printf("(%d, %d) : %s\n", t.getX(), t.getY(), t.getContent());
        // TODO Reconsider
        path.remove(0);
    }

  /*  public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
//        map.print();
        // getRelativePoint
        System.out.println(GoTo.getInfoOnPlayer(player));
        System.out.println("Here");
        if (path == null) {
            Point p = player.getPosition();
            runFirst(map.getTile(player.getPosition()), map.getTile(p.getX() - 6, p.getY() + 3), map);
            System.out.println(" - " + path.size());
        }
        System.out.println("Done");



        //for (Tile t : path)
        //  System.out.println(" + " + t.getY());
        //Tile t = path.get(i++);
        //System.out.printf("(%d, %d) : %s\n", t.getX(), t.getY(), t.getContent());
        //MoveAction ma = new MoveAction();

        return createMoveAction(NULL);
    }*/
}
