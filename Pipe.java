//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Pipe extends Sprite{
BufferedImage pipetemp;

 public Pipe(int x, int y)
 {
    this.x= x;
    this.y= y;
    this.w =55;
    this.h =400;
 }

 Json marshal()
 {
    Json ob = Json.newObject();
    ob.add("x", x);
    ob.add("y", y);
    return ob;
 }

 public Pipe(Json ob)
 {
   x= (int)ob.getLong("x");
   y= (int)ob.getLong("y");
   w= 55;
   h= 400;
 }
 void update(){
 }
 //draw
 public void draw(Graphics g, int scrollPos){
   if(pipetemp == null)
      pipetemp = View.loadimage("pipe.png");
  
   g.drawImage(pipetemp, x - scrollPos, y, w, h, null);
}
//pipe removal
 boolean PipeSpotFree(int mousex, int mousey)
 {
   if ((mousex>=x)&&(mousex<=(x+w))&&(mousey>=y)&&(mousey<=(y+h)))
      return true;
   else
      return false;
 }
 public String toString(){
   return "Pipe (x,y) = (" +x+", "+y+"), width = " +w+", height = "+h;
 }
 //indentifiers
@Override 
boolean IsPipe(){
   return true;
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

boolean onScreen(){
//if to check bounds of window against sprite
   return true;
}
}
