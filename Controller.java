//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import java.awt.event.MouseListener;
//import javax.lang.model.util.ElementScanner14;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	//variables & declarations
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean space;
	boolean control;
	int hold=0;
	boolean edit=false;
	boolean goombacontrol=false;
	//constructors
	Controller()
	{
	}
	Controller(Model m){
		model =m;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	//set environment
	void setView (View v) {
		view=v;
	}

	//click actions
	public void mousePressed(MouseEvent e)
	{
		if (edit){
			model.addPipe(e.getX()+View.scrollPos, e.getY());
		}
		if (goombacontrol){
			model.addGoomba(e.getX()+View.scrollPos, e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
	
	//key actions
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: model.mario.jumping=true; while(!model.mario.jumping || model.mario.jumptime<=15){
				model.mario.jumptime++;
			} break;
			
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_ESCAPE: System.out.println("Goodbye. Exiting now..."); System.exit(0); break;
			case KeyEvent.VK_SPACE: space=false; break;
			case KeyEvent.VK_CONTROL: model.throwfire(); break;
		}
		char c = Character.toLowerCase(e.getKeyChar());
		if (c=='q')
		{
			//quit
			System.out.println("Goodbye. Exiting now...");
			System.exit(0);
		}
		if (c=='s')
		{
			//save
			Json saveObject=model.marshal();
			saveObject.save("pipemap.json");
			System.out.println("File saved.");
		}
		if (c=='l')
		{
			//load file
			Json j = Json.load("pipemap.json");
			model.unmarshal(j);
			System.out.println("File is loaded.");
		}
		if ((c=='e')||(c=='p')){
			//pipe edit
			if (edit){
				edit=false;
				System.out.println("Pipe Edit OFF");
			}
			else{
				edit=true;
				goombacontrol=false;
				System.out.println("Pipe Edit ON");
			}
		}
		if (c=='g'){
			//goomba edit
			if (goombacontrol){
				goombacontrol=false;
				System.out.println("Goomba Edit OFF");
			}
			else{
				goombacontrol=true;
				edit=false;
				System.out.println("Goomba Edit ON");
			}
		}
		if (c=='k'){
			//goomba kill counts
			model.printKills();
			System.out.println("For a total of "+model.murderedgoombas+" goombas.");
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void jump(boolean jumping){
		if(jumping){
			if((model.mario.vertVel==0)&&(model.mario.jumptime<15)){
				model.mario.vertVel=-15.0; 
			}else if ((model.mario.vertVel==0)&&(model.mario.jumptime>15)){
				model.mario.vertVel=-25.0;
			} 
			model.mario.jumping=false; 
			model.mario.jumptime=0;
		}
		else{
			model.mario.jumping=false;
			model.mario.jumptime=0;
		}
	   }
	void update()
	{
		if(!space){
			jump(model.mario.jumping);
		}
		if(!keyLeft){
			model.mario.coordinates();
			model.mario.x+=10;
			if (model.mario.marionum<4){
				model.mario.marionum++;
			}else{
				model.mario.marionum=0; 
			}
		}
		if(!keyRight){
			model.mario.coordinates();	
			model.mario.x-=10;
			if (model.mario.marionum>0){
				model.mario.marionum--;
			}else{
				model.mario.marionum=4; 
			}			
		}
	}
}
