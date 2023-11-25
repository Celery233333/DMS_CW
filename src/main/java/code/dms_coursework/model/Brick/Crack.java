package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * Crack create a Polyline object that represents the crack according to the depth of step in a crack, crack steps in the certain bounds
 * @author Cheng Qin-modified
 */
public class Crack{

    private final int CRACK_SECTIONS = 3;
    private final double JUMP_PROBABILITY = 0.7;

    private final int LEFT = 10;
    private final int RIGHT = 20;
    private final int UP = 30;
    private final int DOWN = 40;

    private final int VERTICAL = 100;
    private final int HORIZONTAL = 200;

    // polyline uses to create polygon
    private Polyline crack;
    // depth of step in a crack(will use rand to handle later)
    private int crackDepth;
    private int steps;
    private Rectangle bounds;
    private Random rnd;

    /**
     * Create a new instance of Crack according to the depth of step in a crack, crack steps in the certain bounds
     * @param crackDepth An int that represents the depth of certain step in a crack
     * @param steps An int that represents the steps of the crack
     * @param bounds A Rectangle object that represents the bounds of the crack
     */
    public Crack(int crackDepth, int steps, Rectangle bounds){
        crack = new Polyline();
        this.crackDepth = crackDepth;
        this.steps = steps;
        this.bounds = bounds;
        rnd = new Random();
    }

    /**
     * Get the shape of the crack
     * @return A Polyline that represents the shape of the crack
     */
    public Polyline draw(){
        return crack;
    }

    /**
     * Reset the crack to null
     */
    public void reset(){
        crack = new Polyline();
    }

    /**
     * Choose the direction and position of the crack
     * @param point A Point2D that represents the position of the crack occurrence point
     * @param direction An int that represents the direction of the crack
     */
    protected void checkCrack(Point2D point, int direction){
        // the position that ball impact with brick
        Point2D impact = new Point2D((int)point.getX(),(int)point.getY());
        Point2D start;
        Point2D end;

        // 4 kinds of cracks
        switch(direction){
            // crack start from impact point and end at the top right point (will use rand to adjust)
            case LEFT:
                start = new Point2D(bounds.getX() + bounds.getWidth(), bounds.getY());
                end = new Point2D(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds.getHeight());
                Point2D tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);
                break;
            // crack start from impact point and end at the top left point (will use rand to adjust)
            case RIGHT:
                start = new Point2D(bounds.getX(),bounds.getY());
                end = new Point2D(bounds.getX(), bounds.getY() + bounds.getHeight());
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);
                break;
            // crack start from impact point and end at the down right point (will use rand to adjust)
            case UP:
                start = new Point2D(bounds.getX(), bounds.getY() + bounds.getHeight());
                end = new Point2D(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds.getWidth());
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            // crack start from impact point and end at the top right point (will use rand to adjust)
            case DOWN:
                start = new Point2D(bounds.getX(),bounds.getY());
                end = new Point2D(bounds.getX() + bounds.getWidth(), bounds.getY());
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
        }
    }

    /**
     * Create the crack from the start position to the end position
     * @param start A Point2D that represents the start point
     * @param end A Point2D that represents the end point
     */
    protected void makeCrack(Point2D start, Point2D end){

        Polyline path = new Polyline(start.getX(),start.getY());

        // Evenly divide the total distance
        double w = (end.getX() - start.getX()) / (double)steps;
        double h = (end.getY() - start.getY()) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;
        for(int i = 1; i < steps;i++){

            x = (i * w) + start.getX();
            y = (i * h) + start.getY() + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.getPoints().addAll(x,y);

        }

        path.getPoints().addAll(end.getX(),end.getY());
        path.setStroke(Color.rgb(255, 255, 255));
        crack = path;
    }

    /**
     * Generate the crack depth randomly
     * @param bound An int that represents the bound value
     * @return An int that represents the distance of certain step of crack
     */
    public int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    /**
     * Check if it is out of the bound
     * @param i An int that represents the current step of crack
     * @param steps An int that represents the crack sections
     * @param divisions An int that represents the total amount of the steps of crack
     * @return
     */
    public boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    /**
     * Decide the distance of a step
     * @param bound An int that represents the bound value
     * @param probability A double that represents the possibility of success
     * @return Return the distance if it succeeds <br>Return 0 if it not
     */
    public int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;
    }

    /**
     * Generate a random point between two specified points and the direction
     * @param from A Point2D object that represents the start point
     * @param to A Point2D object that represents the end point
     * @param direction An int that represents the direction
     * @return A Point2D that represents the destination position of this step of crack
     */
    public Point2D makeRandomPoint(Point2D from,Point2D to, int direction){
        Point2D out = new Point2D(0,0);
        int pos;

        switch(direction){
            case HORIZONTAL:
                // The offset length is between the start and end point
                pos = rnd.nextInt((int) to.getX() - (int) from.getX()) + (int) from.getX();
                out = new Point2D(pos,to.getY());
                break;
            case VERTICAL:
                pos = rnd.nextInt((int) to.getY() - (int) from.getY()) + (int) from.getY();
                out = new Point2D(to.getX(),pos);
                break;
        }
        return out;
    }
}
