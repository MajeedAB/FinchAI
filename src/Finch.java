import java.util.Random;
import java.util.ArrayList;

public class Finch {

	Parameters param;
	Random rand = new Random();
	ArrayList<Integer> eaten = new ArrayList<Integer>();
	
	int satiation;
	double beakLength;
	int xPos;
	int yPos;
	int xVel;
	int yVel;
	int approxSpeed;
	
	public Finch(Parameters p)
	{
		param = p;
		approxSpeed = param.getApproxSpeed();
		satiation = param.getInitialSatiation();
//		beakLength = Math.random();
		beakLength = Math.random();
		xPos = rand.nextInt(param.getFrameWidth()-param.getFinchWidth());
		yPos = rand.nextInt(param.getFrameHeight()-param.getFinchHeight());
		xVel = rand.nextInt(approxSpeed*2+1) - approxSpeed;
		yVel = (int) Math.floor(Math.sqrt(approxSpeed*approxSpeed-xVel*xVel));
		if(rand.nextBoolean())
			yVel *= -1;
	}
	
	public Finch(Parameters p, double b1, double b2)
	{
		param = p;
		approxSpeed = param.getApproxSpeed();
		satiation = param.getInitialSatiation();
		beakLength = merge(b1,b2);
		if(beakLength<=0)
			beakLength = 0.001;
		if(beakLength>=1)
			beakLength = 0.999;
		xPos = rand.nextInt(param.getFrameWidth()-param.getFinchWidth());
		yPos = rand.nextInt(param.getFrameHeight()-param.getFinchHeight());
		xVel = rand.nextInt(approxSpeed*2+1) - approxSpeed;
		yVel = (int) Math.floor(Math.sqrt(approxSpeed*approxSpeed-xVel*xVel));
		if(rand.nextBoolean())
			yVel *= -1;
	}
	
	private double merge(double b1, double b2)
	{
		double mutation=0;
		if(Math.random() < param.getMutationRate())
		{
			mutation = ( Math.random() - 0.5 ) * param.getMutationRange() ;
		}
		return ( (b1+b2)/2 + mutation );
	}
	
	public boolean tryEating(boolean onF, int foodType)
	{
		if(onF)	//if finch is on food, try eating
		{
			double eatProb = Math.pow( Math.abs(foodType-beakLength) , param.getEatProbPower());
			eatProb *= param.getEatProbRescale();
			boolean eaten = ( Math.random() < eatProb ) ;
			if(eaten)
			{
				satiation+=param.getFoodSatiation();
			}
			return eaten;
		}
		else
		{
			return false;			
		}
	}
	
	public void move()
	{
		xPos+=xVel;
		yPos+=yVel;
		
		if(xPos<0 || xPos>param.getFrameWidth()-param.getFinchWidth())
			xVel *= -1;
		if(yPos<0 || yPos>param.getFrameHeight()-param.getFinchHeight())
			yVel *= -1;
		
//		if(rand.nextBoolean())
//		{
//			if(rand.nextBoolean())
//			{
//				if(xVel<5)
//				{
//					xVel++;
//					int ySign = (yVel>=0)? 1 : -1;
//					yVel = (int) Math.floor(Math.sqrt(16-xVel*xVel)) * ySign;					
//				}
//			}
//			else if(xVel>-5)
//			{
//				xVel--;
//				int ySign = (yVel>=0)? 1 : -1;
//				yVel = (int) Math.floor(Math.sqrt(16-xVel*xVel)) * ySign;
//			}
//		}		
	}
	
	public void depleteHunger()
	{
		satiation--;
	}
	
	public int getSatiation()
	{
		return satiation;
	}
	
	public double getBeakLength()
	{
		return beakLength;
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}

	
	
}
