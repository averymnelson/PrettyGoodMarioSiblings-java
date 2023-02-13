//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Sprite{
    //variables
    int x, y, w, h;
    double vertVel, xvel;
    BufferedImage image;
    //empty constructor
    public Sprite(){

    }
    //functions
    abstract void update();

    abstract void draw(Graphics g, int scrollPos);
    //identifiers
    boolean IsPipe(){
        return false;
    }

    boolean IsMario(){
        return false;
    }

    boolean IsGoomba(){
        return false;
    }

    boolean IsFireball(){
        return false;
    }

    Json marshal()
 {
    Json ob = Json.newObject();
    ob.add("x", x);
    ob.add("y", y);
    return ob;
 }

}
