//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
	//variables & declarations
	int pipe_x;
	int pipe_y;
	int mario_x;
	int mario_y;
	int p, murderedgoombas;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> kills;
	Pipe pipesample;
	Mario mario;

	//constructor
	Model()
	{
		mario_x=100;
		mario_y=100;
		murderedgoombas=0;
		sprites= new ArrayList<Sprite>();
		kills= new ArrayList<Sprite>();
		mario= new Mario(mario_x, mario_y);
		sprites.add(mario);
	}
	//saving
	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpListPipes= Json.newList();
		ob.add("pipes", tmpListPipes);
		Json tmpListGoombas=Json.newList();
		ob.add("goombas", tmpListGoombas);
		//Iterator it = sprites.iterator();
		for (int i=0; i<sprites.size(); i++){
			if (sprites.get(i).IsPipe())
				tmpListPipes.add(((Pipe)sprites.get(i)).marshal());
			if (sprites.get(i).IsGoomba())
				tmpListGoombas.add(((Goomba)sprites.get(i)).marshal());
		}
		return ob;
	}
	//loading
	void unmarshal(Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		Json tmpListPipes = ob.get("pipes");
		for(int i = 0; i < tmpListPipes.size(); i++)
		    sprites.add(new Pipe(tmpListPipes.get(i)));
		Json tmpListGoombas = ob.get("goombas");
		for(int i = 0; i < tmpListGoombas.size(); i++)
		    sprites.add(new Goomba(tmpListGoombas.get(i)));
	}
	//one iterator use, other used for goomba kill stats
	void printsprites()
	{
		Iterator<Sprite> it = sprites.iterator();
		System.out.println("The sprites in list are: ");
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
	}
	}
	//add pipe
	public void addPipe(int mx, int my)
	{
		boolean foundPipe=false;
		for (int i=0; i<sprites.size();i++){
			if (sprites.get(i).IsPipe()){
			if (((Pipe)sprites.get(i)).PipeSpotFree(mx, my)==true){
				foundPipe=true;
				sprites.remove(sprites.get(i));
			}
		}}
		if (!foundPipe){
		sprites.add(new Pipe(mx, my));	
		}
	}
	//second iterator use
	public void printKills(){
		System.out.println("Goombas were killed at:");
		Iterator<Sprite> death=kills.iterator();
		while(death.hasNext()){
         Sprite element = death.next();
		 System.out.println((Goomba)element);
		 murderedgoombas++;
		}
	}
	//add goomba
	public void addGoomba(int mx, int my)
	{
		sprites.add(new Goomba(mx, my));	
	}
	//fireball launcher
	public void throwfire(){
		sprites.add(new Fireball((mario.x+mario.w), ((mario.y+mario.h/2)))); //Made changes here
	}
	//update function, collisons
	public void update()
	{
		for (int i=0; i<sprites.size(); i++){
			sprites.get(i).update();
			for (int j=1; j<sprites.size(); j++){
				if (collision(sprites.get(i), sprites.get(j))){
					if (sprites.get(i).IsMario()){
						if (sprites.get(j).IsPipe())
							mario.getOut(sprites.get(j));
					}
			 		if (sprites.get(i).IsGoomba()){
			 			if (sprites.get(j).IsPipe()){
			 				((Goomba)sprites.get(i)).getOut(sprites.get(j));
						}
						if (sprites.get(i).IsGoomba()){ 
							if (sprites.get(j).IsFireball()){ 
								((Goomba)sprites.get(i)).onFire=true;
							}
						}
						if (((Goomba)sprites.get(i)).onFire&&((Goomba)sprites.get(i)).killcounter>=15){
							kills.add(sprites.get(i));
							sprites.remove(i);
						}
					}
				}
			}
			if (sprites.get(i).IsFireball()){
				if(((Fireball)sprites.get(i)).x-mario.x>=1000){
					sprites.remove(i);
				}
			}
		}
	}
//collison detection
	public boolean collision(Sprite m, Sprite p){
		boolean grey=true;
	//left, right
		if((m.x+m.w)<(p.x)){
			grey= false;
		}
		if((m.x)>(p.x+p.w)){
			grey= false;
		}
	//bottom, top
		if(((m.y+m.h)<(p.y))){
			grey=false;
		}
		if(((m.y)>(p.y+p.h))){
			grey=false;
		}
		return grey;
	}
}