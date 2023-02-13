//Author: Avery Nelson
//Date: 10.15.22
//Description: Mario jumping and gravity

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Mario extends Sprite{
  //variables
boolean left;
boolean right;
boolean top;
boolean bottom;
boolean jumping;
BufferedImage[] mario_images;
int groundtime, marionum, jumptime;
int prevx, prevy;
//constructor
public Mario(int x, int y)
 {
    this.x= x;
    this.y= y;
    this.w= 60;
    this.h= 95;
    jumptime=0;
    groundtime=0;
    marionum=0;
    jumping=false;
    mario_images = new BufferedImage[5];
    mario_images[0] = View.loadimage("mario1.png");
    mario_images[1] = View.loadimage("mario2.png");
    mario_images[2] = View.loadimage("mario3.png");
    mario_images[3] = View.loadimage("mario4.png");
    mario_images[4] = View.loadimage("mario5.png");
 }
 //marshalling
 Json marshal()
 {
    Json ob = Json.newObject();
    ob.add("x", x);
    ob.add("y", y);
    return ob;
 }
 public Mario(Json ob)
 {
   x= (int)ob.getLong("x");
   y= (int)ob.getLong("y");
   w= 55;
   h= 96;
 }
 //coordinates for pipe snap
 void coordinates(){
  prevx=x;
  prevy=y;
 }
 //draw
 public void draw(Graphics g, int scrollPos){
  g.drawImage(mario_images[marionum], x - scrollPos, y, w, h, null);
}
//update and gravity
 void update(){
  vertVel+=1.2;
    y+=vertVel;
    if (y>500){
      vertVel=0.0;
      y=500;
      groundtime=0;
    }
 }
 //string override
 public String toString(){
   return "Mario (x,y) = (" +x+", "+y+"), width = " +w+", height = "+h;
 }
//pipe collison
 public void getOut(Sprite p){
  if (((y+h)>=p.y) && ((prevy+h)<=p.y)){
    vertVel=0.0;
    y=p.y-h;
    groundtime=0;
  }
  if (((y)<=(p.y+p.h))&&((prevy)>=(p.y+p.h))){
    vertVel=0.0;
    y=p.y+p.h;
  }
  if (x+w>=p.x&&prevx+w<=p.x){
    x=p.x-w;
  }
  if (x<=p.x+p.w&&prevx>=p.x+p.w){
    x=p.x+p.w;
  }
 }
//identifiers
 boolean IsPipe(){
  return false;
}
@Override
boolean IsMario(){
  return true;
}

boolean IsGoomba(){
  return false;
}

boolean IsFireball(){
  return false;
} 
}