package io.polyhx.lhgames.game.bot;

import io.polyhx.lhgames.game.GameInfo;
import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.Player;
import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.action.MoveAction;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.ResourceTile;

import java.util.List;

public class Bot extends BaseBot {

    int x=4;

    public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
        MoveAction mouvement = null;

        while(x!=0){
            mouvement = new MoveAction(Point.LEFT);
            x = x - 1;
        }

        return mouvement;
    }

    public boolean IsFull(Player player) {
        if (player.getCarriedResource() >= player.getResourceCapacity())
            return true;
        else
            return false;
    }



}
