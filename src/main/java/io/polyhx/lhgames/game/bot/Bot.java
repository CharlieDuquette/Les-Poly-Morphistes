package io.polyhx.lhgames.game.bot;
import io.polyhx.lhgames.game.GameInfo;
import io.polyhx.lhgames.game.GoTo;
import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.Player;
import io.polyhx.lhgames.game.action.AbstractPointAction;
import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;

import java.util.ArrayList;
import java.util.List;


public class Bot extends BaseBot {


    public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
//        GoTo.mapPrint(map);


        IAction action = GoTo.decision(map, player, others, info, new ArrayList<>());
        drinkPotion(player);


        //System.out.println(action.getActionType() + " : "  + action.getJSONContent());

        return action;
    }

    public static boolean IsFull(Player player) {
        return player.getCarriedResource() >= player.getResourceCapacity();
    }

    public IAction drinkPotion(Player player) {
        int maxHealth = player.getMaximumHealth();
        int currentHealth = player.getCurrentHealth();
        int damageTaken = 0;

        if(currentHealth < maxHealth)
            damageTaken = maxHealth - currentHealth;

        if(damageTaken >= currentHealth) {
            System.out.println("\nDrink potion");
            return createHealAction();
        }

        else {
            System.out.println("\nDon't drink potion" );
            return null;
        }
    }
}