//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	//variables&declarations
	Model model;
	static int scrollPos;
	//constructors
	View(Controller c, Model m)
	{
		model=m;
		scrollPos=model.mario.x;
		 c.setView(this);
		}
//image loading
		public static BufferedImage loadimage(String file) {
			BufferedImage im=null;
			try {
				im = ImageIO.read(new File(file));
				}
			 catch(Exception e){
				e.printStackTrace(System.err);
				System.exit(1);
			 }
			 return im;
		}
	//environment creation
	//Iterator<Sprites> it =sprites.iterator();
	public void paintComponent(Graphics g){
		g.setColor(new Color(128,255,255));
		scrollPos=model.mario.x-100;
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		g.setColor(Color.gray);
		g.drawLine(0, 596, 2000, 596);
		for(int i=0; i<model.sprites.size(); i++){
			model.sprites.get(i).draw(g, scrollPos);
	}
}
}