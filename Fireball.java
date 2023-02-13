//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Fireball extends Sprite{
//"variables" but its just the image
  BufferedImage fire;
//const
 public Fireball(int x, int y)
 {
   this.x= x;
   this.y= y;
   this.w =47;
   this.h =47;
   fire = View.loadimage("fireball.png");
 }
   public void draw(Graphics g, int scrollPos){
      g.drawImage(fire, x - scrollPos, y, null);
  }
//gravity and bounce behavior
 void update(){
  vertVel+=1.2;
    y+=vertVel;
    if (y>545){

      vertVel=-15;
    }
    x+=20;
 }
 public String toString(){
   return "Fireball (x,y) = (" +x+", "+y+"), width = " +w+", height = "+h;
 }
//indentifiers
 boolean IsPipe(){
  return false;
}

boolean IsMario(){
  return false;
}

boolean IsGoomba(){
  return false;
}
@Override
boolean IsFireball(){
  return true;
} 
}