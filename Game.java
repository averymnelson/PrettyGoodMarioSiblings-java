//Author: Avery Nelson
//Date: 10.31
//Description: mario, pipes, goombas, & fireballs

import javax.swing.JFrame;
import java.awt.Toolkit;


public class Game extends JFrame
{
	//variables and declarations
	Model model;
	Controller controller;  
	View view;
	int size;

	//constructor
	public Game()
	{
		model=new Model();
		controller= new Controller(model);
		view = new View(controller, model);
		this.setTitle("A5 - Adding Elements");
		size=1000;
		this.setSize(size, size);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		Json j = Json.load("pipemap.json");
		model.unmarshal(j);
		System.out.println("File is loaded.");
		System.out.println("Entering play mode.");
		System.out.println("Your choices in functions are: \n'l' for loading a map \n's' for saving pipes and goombas \n'q' or the escape key for quitting \n'e' or 'p' for pipe editing mode \n'g' for goomba editing mode \n'k' for giving a record of goombas killed.");
	}

	//main
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	//run function
	public void run(){
	while(true)
	{
		controller.update();
		model.update();
		view.repaint();
		Toolkit.getDefaultToolkit().sync();
		try
		{
			Thread.sleep(40);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
}
