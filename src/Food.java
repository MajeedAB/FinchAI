import java.util.ArrayList;
import java.util.Random;

public class Food {
	
	Parameters param;
	Random rand = new Random();
	int type;
	int x;
	int y;
	
	public Food(Parameters p)
	{
		param = p;
		type=0;
		if(Math.random()<param.getFruitRate())
			type=1;
		x = rand.nextInt(param.getFrameWidth()-param.getFinchWidth()) + param.getFinchWidth() ;
		y = rand.nextInt(param.getFrameHeight()-param.getFinchHeight()) + param.getFinchHeight();
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
