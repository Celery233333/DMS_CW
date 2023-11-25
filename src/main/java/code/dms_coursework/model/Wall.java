package code.dms_coursework.model;

import code.dms_coursework.model.Brick.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * The Wall class will create levels for the game according to the specified draw area, brick count, line count and brick dimension ratio
 * @author Cheng Qin-modified
 */
public class Wall {
    private Brick[][] levels; // store maps
    private BrickFactory brickFactory;
    private final int LEVELS_COUNT = 6;
    private Random rnd;

    /**
     * Crate a new instance of Wall according to the specified draw area, brick count, line count and brick dimension ratio
     * @param drawArea A Rectangle object that represents the area the wall in
     * @param brickCount An int that represents the amount of bricks current wall has
     * @param lineCount An int that represents the amount of bricks in certain line
     * @param brickDimensionRatio A double that represents the dimension ratio of the brick
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        BrickFactory brickFactory = new BrickFactory();
        this.brickFactory = brickFactory;
        this.rnd = new Random();
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
    }

    /**
     * Get the levels of the game
     * @return A Brick[][] object that contains all the levels of this game
     */
    public Brick[][] getLevels() {
        return levels;
    }

    /**
     * Create curren level with certain brick (randomly replace some bricks with AwardBrick, QuestionBrick, TNTBrick)
     * @param drawArea A Rectangle object that represents the area the wall in
     * @param brickCnt An int that represents the amount of bricks current wall has
     * @param lineCnt An int that represents the amount of bricks in certain line
     * @param brickSizeRatio A double that represents the dimension ratio of the brick
     * @param name A String that represents the name of the brick
     * @return A Brick[] object that contains current level
     */
    public Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, String name) {
        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;
        // the number of bricks on each line
        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;
        Brick[] tmp  = new Brick[brickCnt];
        Point2D p;

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine; // calculate current brick belongs to which line
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen; // calculate the position in current line
            x =(line % 2 == 0) ? x : (x - (brickLen / 2)); // even lines shorten by half a brick
            double y = (line) * brickHgt;
            p = new Point2D(x,y);

            // remove the half brick the out of bounds
            if (line % 2 == 1 && x < 0) {
                p = new Point2D(0,y);
                if (rnd.nextInt(30) == 7) {
                    tmp[i] = brickFactory.makeBrick("Award Brick",p,(int) brickHgt,(int) brickLen/2);
                }
                else if (rnd.nextInt(66) == 17) {
                    tmp[i] = brickFactory.makeBrick("Question Brick", p, (int) brickHgt, (int) brickLen/2);
                }
                else {
                    tmp[i] = brickFactory.makeBrick(name, p, (int) brickHgt, (int) brickLen/2);
                }
            }
            else if (rnd.nextInt(30) == 7) {
                tmp[i] = brickFactory.makeBrick("Award Brick",p,(int) brickHgt,(int) brickLen);
            }
            else if (rnd.nextInt(66) == 17) {
                tmp[i] = brickFactory.makeBrick("Question Brick",p,(int) brickHgt,(int) brickLen);
            }
            // control to not generate at the start and end of that line
            else if (rnd.nextInt(77) == 69 && (i % brickOnLine) != brickOnLine-1 && (i % brickOnLine) != 0) {
                tmp[i] = brickFactory.makeBrick("TNT Brick", p, (int) brickHgt, (int) brickLen);
            }
            else {
                tmp[i] = brickFactory.makeBrick(name, p, (int) brickHgt, (int) brickLen);
            }
        }

        // add a brick at the end of even lines
        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p = new Point2D(x,y);

            // choose to add which types of bricks
            tmp[i] = brickFactory.makeBrick(name, p, (int) brickHgt, (int) brickLen/2);
        }
        return tmp;
    }

    /**
     * Create curren level with two kinds of bricks (randomly replace some bricks with AwardBrick, QuestionBrick, TNTBrick)
     * @param drawArea A Rectangle object that represents the area the wall in
     * @param brickCnt An int that represents the amount of bricks current wall has
     * @param lineCnt An int that represents the amount of bricks in certain line
     * @param brickSizeRatio A double that represents the dimension ratio of the brick
     * @param nameA A String that represents the name of the first kind of brick
     * @param nameB A String that represents the name of the second kind of brick
     * @return A Brick[] object that contains current level
     */
    public Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, String nameA, String nameB) {
        // if brickCount is not divisible by line count, brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;
        // the number of bricks on each line
        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;
        Brick[] tmp  = new Brick[brickCnt];
        Point2D p;

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine; // calculate current brick belongs to which line
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen; // calculate the position in current line
            x =(line % 2 == 0) ? x : (x - (brickLen / 2)); // even lines shorten by half a brick
            double y = (line) * brickHgt;
            p = new Point2D(x,y);
            // check current position belongs to type a or b
            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));

            // remove the half brick the out of bounds
            if (line % 2 == 1 && x < 0) {
                p = new Point2D(0,y);
                tmp[i] = b ?  brickFactory.makeBrick(nameA,p,(int)brickHgt,(int)brickLen) : brickFactory.makeBrick(nameB,p,(int)brickHgt,(int)brickLen/2);
                if (rnd.nextInt(30) == 6) {
                    tmp[i] = brickFactory.makeBrick("Award Brick",p,(int) brickHgt,(int) brickLen/2);
                }
                else if(rnd.nextInt(66) == 19) {
                    tmp[i] = brickFactory.makeBrick("Question Brick",p,(int) brickHgt,(int) brickLen/2);
                }
            }
            else{
                tmp[i] = b ? brickFactory.makeBrick(nameA, p, (int) brickHgt, (int) brickLen) : brickFactory.makeBrick(nameB, p, (int) brickHgt, (int) brickLen);
                if (rnd.nextInt(30) == 6) {
                    tmp[i] = brickFactory.makeBrick("Award Brick", p, (int) brickHgt, (int) brickLen);
                }
                // control TNT to not generate at the start and end of that line
                else if (rnd.nextInt(77) == 66 && (i % brickOnLine) != brickOnLine-1 && (i % brickOnLine) != 0) {
                    tmp[i] = brickFactory.makeBrick("TNT Brick", p, (int) brickHgt, (int) brickLen);
                }
                else if(rnd.nextInt(66) == 24) {
                    tmp[i] = brickFactory.makeBrick("Question Brick", p, (int) brickHgt, (int) brickLen);
                }
            }
        }

        // draw the last brick of the even line
        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p = new Point2D(x,y);
            if (rnd.nextInt(30) == 8) {
                tmp[i] = brickFactory.makeBrick("Award Brick",p,(int) brickHgt,(int) brickLen/2);
            }
            else if (rnd.nextInt(66) == 60) {
                tmp[i] = brickFactory.makeBrick("Question Brick",p,(int) brickHgt,(int) brickLen/2);
            }
            else {
                tmp[i] = brickFactory.makeBrick(nameA, p, (int) brickHgt, (int) brickLen/2);
            }
        }
        return tmp;
    }

    /**
     * Create all the levels of this game with specified kinds of bricks
     * @param drawArea A Rectangle object that represents the area the wall in
     * @param brickCount An int that represents the amount of bricks current wall has
     * @param lineCount An int that represents the amount of bricks in certain line
     * @param brickDimensionRatio A double that represents the dimension ratio of the brick
     * @return A Brick[][] object that contains all the levels of this game
     */
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,"Clay Brick");
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,"Clay Brick","Cement Brick");
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,"Clay Brick","Steel Brick");
        tmp[3] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,"Cement Brick");
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,"Steel Brick","Cement Brick");
        tmp[5] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,"Steel Brick");
        return tmp;
    }
}
