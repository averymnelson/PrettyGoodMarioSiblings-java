//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Goomba extends Sprite{
BufferedImage goomba, goombafire;
boolean onFire, direction;
int killcounter;
int prevx,prevy;

 public Goomba(int x, int y)
 {
   this.x= x;
   this.y= y;
   this.w =37;
   this.h =45;
   onFire=false;
   killcounter=0;
   xvel=4;
 }
   public void draw(Graphics g, int scrollPos){
    if(goomba == null)
    goomba = View.loadimage("goomba.png");
   if(goombafire==null)
    goombafire = View.loadimage("goomba_fire.png");
    if(!onFire)  
      g.drawImage(goomba, x - scrollPos, y, w, h, null);
    else
      g.drawImage(goombafire, x - scrollPos, y, w, h, null);
  }
  void changedirection(){
    xvel=xvel*(-1);
   }
//update, controls waiting after goomba catches fire also
 void update(){
  coordinates();
  vertVel+=1.2;
    y+=vertVel;
    if (y>545){
      vertVel=0.0;
      y=545;
    }
    fire();
    x+=xvel;
  }

  void fire(){
    if(onFire){
      killcounter++;
      xvel=0;
    }
  }
 public String toString(){
   return "Goomba (x,y) = (" +x+", "+y+"), width = " +w+", height = "+h;
 }
 public Goomba(Json ob)
 {
   x= (int)ob.getLong("x");
   y= (int)ob.getLong("y");
   w= 37;
   h= 45;
 }
 void coordinates(){
  prevx=x;
  prevy=y;
 }
 //pipe collison
 public void getOut(Sprite p){
 if (x+w>=p.x&&prevx+w<=p.x){
    x=p.x-w;
    changedirection();
  }
  if (x<=p.x+p.w&&prevx>=p.x+p.w){
    x=p.x+p.w;
    changedirection();
  }
 }
 //identifiers
 boolean IsPipe(){
  return false;
}

boolean IsMario(){
  return false;
}
@Override
boolean IsGoomba(){
  return true;
}

boolean IsFireball(){
  return false;
} 
}