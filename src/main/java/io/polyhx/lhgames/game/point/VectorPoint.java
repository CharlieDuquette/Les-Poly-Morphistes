package io.polyhx.lhgames.game.point;

public class VectorPoint extends Point {

    public VectorPoint() {
    }

    public VectorPoint(int x, int y) {
        super(x, y);
    }

    public VectorPoint(IPoint point) {
        super(point);
    }

    public VectorPoint substract(IPoint point){
        VectorPoint vector = new VectorPoint(this.getX() - point.getX(), this.getY() - point.getY());
        return vector;
    }
    public VectorPoint add(IPoint point){
        VectorPoint vector = new VectorPoint(this.getX() + point.getX(), this.getY() + point.getY());
        return vector;
    }


    public VectorPoint scale(int scalar){
        VectorPoint vectorPoint = new VectorPoint(getX() * scalar, getY() * scalar);
        return vectorPoint;
    }

    public VectorPoint toDirection(){
        int x = 0, y = 0;

        if (getX() != 0) {
            x = Math.abs(getX()) / getX();
        }
        if (getY() != 0) {
            y = Math.abs(getY()) / getY();
        }
        if (Math.abs(getX()) > Math.abs(getY())) {
            y = 0;
        }
        else {
            x = 0;
        }
        return new VectorPoint(x, y);
    }

    public VectorPoint normalize(){
        VectorPoint vectorPoint = new VectorPoint(getX(), getY());

        vectorPoint.scale((int) (1.0 / getSize()));
        return vectorPoint;
    }


    public double getSize(){
        return Math.sqrt(getX() * getX() + getY() * getY());
    }


    public static String toString(Point point) {

        return "X: " + point.getX() + " | Y: " + point.getY()
                + '\n';
    }
    @Override
    public String toString() {
        return toString(this);
    }
}
