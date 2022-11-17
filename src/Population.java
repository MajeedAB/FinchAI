import java.util.ArrayList;
import java.util.Random;
public class Population {
	
	Parameters param;
	Random rand = new Random();
	ArrayList<Finch> finchList = new ArrayList<Finch>();
	ArrayList<Integer> eaten = new ArrayList<Integer>();
	int totalTime=0;
	
	public Population(Parameters p)
	{
		param = p;
		init();
	}
	
	public void init()
	{
		for(int i=0; i<param.getInitialPop(); i++)
		{
			finchList.add(new Finch(param));
		}
	}
	
	public void mate()
	{
		double beak1 = finchList.get(rand.nextInt(finchList.size())).getBeakLength();
		double beak2 = finchList.get(rand.nextInt(finchList.size())).getBeakLength();
		
		Finch child = new Finch(param, beak1, beak2);
		finchList.add(child);
	}
	
	public void moveFinches()
	{
		for(int f=0; f<finchList.size(); f++)
		{
			finchList.get(f).move();
		}
	}
	
	public void updateHunger(ArrayList<Food> foodList)
	{
		double hps = param.getHungerPerSecond();
		int Dt = param.getDelay();
		int k = (int) Math.floor(totalTime * hps / 1000);
		boolean hungerDown =  (totalTime - Dt < k * 1000 / hps) ;
		
		for(int f=finchList.size()-1; f>=0; f--)	//for all finches in downwards order (in case of removal)
		{
			//first deplete hunger if needed
			if(hungerDown)
			{
				finchList.get(f).depleteHunger();
			}
			for(int food=0; food<foodList.size(); food++)	//for all foods, see if can be eaten
			{
				int fx = finchList.get(f).getXPos();
				int fy = finchList.get(f).getYPos();
				int foodx = foodList.get(food).getX();
				int foody = foodList.get(food).getY();
				
				if( (foodx>=fx && foody>=fy) && (foodx<fx+param.getFinchWidth() && foody<fy+param.getFinchHeight()) )
				{
					int foodType = foodList.get(food).getType();
					if(finchList.get(f).tryEating(true, foodType))
					{
						eaten.add(Integer.valueOf(food));
					}
				}
			}
			if(finchList.get(f).getSatiation()<=0)	//remove if 0 satiation or less
			{
				finchList.remove(f);
				System.out.println("REMOVING A FINCH");
			}
		}
		totalTime += Dt;
	}
	
	public void resetEatenFood()
	{
//		eaten.clear();
		eaten.removeAll(eaten);
	}
	
	public int[] getEatenFoodDescending()
	{
		int eatenFood[] = new int[eaten.size()];
		
		for(int i=0; i<eaten.size(); i++)
		{
			eatenFood[i] = eaten.get(i);
		}
		
		return sortDescending(eaten);
	}
	
	public int[] sortDescending(ArrayList<Integer> list)
	{
		int sorted[] = new int[list.size()];
		for(int i=0; i<sorted.length; i++)
		{
			sorted[i] = list.get(i);
		}
		boolean done = false;
		
		while(!done)
		{
			done=true;
			for(int i=0;i<sorted.length-1;i++)
			{
				if(sorted[i]<sorted[i+1])	//if sorted[i] is smaller than [i+1], they're switched
				{
					int temp = sorted[i];
					sorted[i] = sorted[i+1];
					sorted[i+1] = temp;
					//sorted [i] and [i+1] are switched
					done=false;	//there was a switch, so they were'nt well placed
				}
			}
		}
		return sorted;
	}
	
	public ArrayList<Finch> getFinchList()
	{
		return finchList;
	}
}
