package io.polyhx.lhgames.game.bot;

import io.polyhx.lhgames.game.*;
import io.polyhx.lhgames.game.action.AbstractPointAction;
import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.action.MoveAction;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;

import java.util.ArrayList;
import java.util.List;


public class Bot extends BaseBot {

//    public  static ArrayList<Tile> path = new ArrayList<>();

    public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
//        GoTo.mapPrint(map);


//        if (!path.isEmpty()) {
//            System.out.println("Path isnt enpty!" + " : " + path);
//            IAction action = new  MoveAction(GoTo.getNextMovePathFinding(player, map, path));
//            IAction action = new MoveAction(path.get(0));
//            path.remove(0);
//            return  action;
//        }
//        else {
            IAction action = GoTo.decision(map, player, others, info, new ArrayList<>());

            System.out.println(action.getActionType() + " : " + action.getJSONContent());
            return action;
//        }
    }

    public static boolean IsFull(Player player) {
        if (player.getCarriedResource() >= player.getResourceCapacity())
            return true;
        else
            return false;
    }


}
