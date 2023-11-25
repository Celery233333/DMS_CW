package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import static java.lang.System.exit;

/**
 * BrickFactory obey the factory pattern that provides the creation of the other concrete bricks
 * @author Cheng Qin
 */
public class BrickFactory {
    /**
     * Create certain brick according to the name, position, height and width of the brick
     * @param brickName A String that represents the name of the brick
     * @param point A Point2D that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     * @return A Brick object that represents the brick that factory create
     */
    public Brick makeBrick(String brickName, Point2D point, int height, int width) {
        switch (brickName) {
            case "Clay Brick":
                return new ClayBrick(point, height, width);
            case "Cement Brick":
                return new CementBrick(point, height, width);
            case "Steel Brick":
                return new SteelBrick(point, height, width);
            case "Award Brick":
                return new AwardBrick(point, height, width);
            case "Question Brick":
                return new QuestionBrick(point, height, width);
            case "TNT Brick":
                return new TNTBrick(point, height, width);
            default:
                System.out.println("Error Input!!!");
                exit(-1);
                return null;
        }
    }
}
