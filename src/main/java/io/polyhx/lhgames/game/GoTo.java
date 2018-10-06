package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.point.VectorPoint;
import io.polyhx.lhgames.game.tile.ResourceTile;

import java.util.List;

public class GoTo {

    public static ResourceTile getClosestResource(Map map, Point playerPos){
        List<ResourceTile> resources = map.getResources();
        double distance = -1;
        ResourceTile tile = null;
        for (ResourceTile resTile : resources) {
            if ((resTile.getPosition().getDistanceTo(playerPos) < distance  | distance == -1)&& resTile.getResource() > 0) {
                tile = resTile;
            }
        }


        return tile;

    }


    public static VectorPoint goTo(Point playerPos, Point destination){
        VectorPoint vectorPoint = new VectorPoint(destination).substract(playerPos);
        return vectorPoint.toDirection();
    }
}
