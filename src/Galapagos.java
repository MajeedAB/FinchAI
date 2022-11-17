import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.ArrayList;

public class Galapagos extends JPanel implements ActionListener
{
	
	Parameters param = new Parameters();
	Timer tm = new Timer(param.getDelay(),this);
	Population pop = new Population(param);
	ArrayList<Food> foodList = new ArrayList<Food>();
	
	public Galapagos()
	{
		init();
	}
	
	public void init()	//create initial environment (meaning food)
	//and mb after choose parameters interactively?
	{
		for(int i=0; i<param.getInitialFood(); i++)
		{
			foodList.add(new Food(param));
		}
	}	

	public void actionPerformed(ActionEvent e)	//loops each timer iter
	{
		updateFinches();	//update the finches' hunger, some get removed, some eat
		tryFood();			//mb add a food
		updateAnimation(e);	//move all objects on window
	}
	
	public void updateFinches()
	{
		updateHunger();
		tryMate();
		moveFinches();
	}
	
	public void updateHunger()	//if on food, try eating once and if works add satiation and remove food;
		//if hunger<0 , remove finch
	{
		pop.resetEatenFood();
		pop.updateHunger(foodList);
		int eaten[] = pop.getEatenFoodDescending();
		for(int f=0; f<eaten.length; f++)
		{
			if(eaten[f]<foodList.size())
			foodList.remove(eaten[f]);
		}
	}
	
	public void moveFinches()
	{
		pop.moveFinches();
	}
	
	public void tryMate()
	{
		if(Math.random()<param.getMateRate())
		{
			pop.mate();
		}
	}
		
	public void tryFood()
	{
		if(Math.random()<param.getFoodRate())
		{
			foodList.add(new Food(param));
		}
	}
	
	public void updateAnimation(ActionEvent e)
	{
		anim.setFinches(pop.getFinchList());
		anim.setFoodList(foodList);
		anim.actionPerformed(e);
		anim.setProportions();
	}
	
	static Animation anim;
	public static void main(String args[])
	{
		Galapagos gal = new Galapagos();
		anim = new Animation(gal.param);
		gal.tm.start();
		
//		t.tm.start();
		JFrame jf = new JFrame();
		jf.setTitle("tut");
		jf.setSize(gal.param.getFrameWidth()+50, gal.param.getFrameHeight()+250);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(anim);
		
	}
	
	
}
