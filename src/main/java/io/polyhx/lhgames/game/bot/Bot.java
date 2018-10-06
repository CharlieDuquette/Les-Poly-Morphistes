package io.polyhx.lhgames.game.bot;

import io.polyhx.lhgames.game.GameInfo;
import io.polyhx.lhgames.game.GoTo;
import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.Player;
import io.polyhx.lhgames.game.action.AbstractPointAction;
import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.action.MoveAction;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;

import java.util.ArrayList;
import java.util.List;


public class Bot extends BaseBot {
    public static ArrayList<Point> path = new ArrayList<>();


    public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
//        GoTo.mapPrint(map);
        return new MoveAction(new Point(0, 1));
/*


        if (path.isEmpty()) {

        AbstractPointAction action = GoTo.decision(map, player, others, info, new ArrayList<>());

        System.out.println(action.getActionType() + " : "  + action.getJSONContent());
        return action;
        }
        else{
            System.out.println(path + " ###@@@");
            IAction action = new MoveAction(path.get(0));
            path.remove(0);
            return action;
        }*/
    }

    public static boolean IsFull(Player player) {
        if (player.getCarriedResource() >= player.getResourceCapacity())
            return true;
        else
            return false;
    }
}
