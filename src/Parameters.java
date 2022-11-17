
public class Parameters {
	
	int initialPop;
	int foodSatiation;
	int frameWidth;
	int frameHeight;
	int initialFood;
	double fruitRate;
	int initialSatiation;
	double mateRate;
	double foodRate;
	double eatProbPower;
	double eatProbRescale;
	int finchWidth;
	int finchHeight;
	int delay;
	int hungerPerSecond;
	double mutationRate;
	double mutationRange;
	int approxSpeed;

	
	public Parameters()
	{
		initialPop = 3;
		foodSatiation = 25;
		frameWidth = 700;
		frameHeight = 500;
		initialFood = 25;
		fruitRate = 0.5;	//0 to 1, the rest is insects
		initialSatiation = 100;
		mateRate = 0.0025;
		foodRate = 0.017;
		eatProbPower=3;
		eatProbRescale = 0.1;
		finchWidth = 60;
		finchHeight = 80;
		delay = 3;	//for timer
		hungerPerSecond = 35;
		mutationRate = 0.15;
		mutationRange = 0.7;	//interval it can vary in, so if 0.5 then varies between -0.25 to +0.25
		approxSpeed = 5;
	}
	
	public int getInitialPop()
	{
		return initialPop;
	}
	
	public int getFoodSatiation()
	{
		return foodSatiation;
	}
	
	public int getFrameWidth()
	{
		return frameWidth;
	}
	
	public int getFrameHeight()
	{
		return frameHeight;
	}
	
	public int getInitialFood()
	{
		return initialFood;
	}
	
	public double getFruitRate()
	{
		return fruitRate;
	}
	
	public int getInitialSatiation()
	{
		return initialSatiation;
	}
	
	public double getMateRate()
	{
		return mateRate;
	}
	
	public double getFoodRate()
	{
		return foodRate;
	}
	
	public double getEatProbPower()
	{
		return eatProbPower;
	}
	
	public int getFinchWidth()
	{
		return finchWidth;
	}
	
	public int getFinchHeight()
	{
		return finchHeight;
	}
	
	public int getDelay()
	{
		return delay;
	}
	
	public int getHungerPerSecond()
	{
		return hungerPerSecond;
	}
	
	public double getMutationRate()
	{
		return mutationRate;
	}
	
	public double getMutationRange()
	{
		return mutationRange;
	}

	public double getEatProbRescale()
	{
		return eatProbRescale;
	}

	public int getApproxSpeed()
	{
		return approxSpeed;
	}
	
	
	
}
