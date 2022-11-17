import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Animation extends JPanel implements ActionListener
{
	ArrayList<Finch> finchList = new ArrayList<Finch>();
	ArrayList<Food> foodList = new ArrayList<Food>();
	JLabel longBeaks = new JLabel();
	JLabel maxLength = new JLabel();
	JLabel shortBeaks = new JLabel();
	JLabel minLength = new JLabel();
	JLabel averageLength = new JLabel();
	JLabel insect = new JLabel();
	JLabel fruit = new JLabel();
	JLabel foodQuantity = new JLabel();
	JLabel finchPopulation = new JLabel();
	
	Parameters param;
	
	public Animation(Parameters p)
	{
		this.setLayout(null);
		
		param = p;
		
		JLabel lb = new JLabel("Long beaks %");
		lb.setSize(120, 30);
		lb.setLocation(20, param.getFrameHeight()+40);
		longBeaks.setSize(40,30);
		longBeaks.setLocation(140, param.getFrameHeight()+40);
		this.add(lb);
		this.add(longBeaks);
		
		JLabel mx = new JLabel("Max beak length");
		mx.setSize(120, 30);
		mx.setLocation(220, param.getFrameHeight()+40);
		maxLength.setSize(40,30);
		maxLength.setLocation(330, param.getFrameHeight()+40);
		this.add(mx);
		this.add(maxLength);
		
		JLabel sb = new JLabel("Short beaks %");
		sb.setSize(120, 30);
		sb.setLocation(20, param.getFrameHeight()+70);
		shortBeaks.setSize(40,30);
		shortBeaks.setLocation(140, param.getFrameHeight()+70);
		this.add(sb);
		this.add(shortBeaks);
		
		JLabel mn = new JLabel("Min beak length");
		mn.setSize(120, 30);
		mn.setLocation(220, param.getFrameHeight()+70);
		minLength.setSize(40,30);
		minLength.setLocation(330, param.getFrameHeight()+70);
		this.add(mn);
		this.add(minLength);
		
		JLabel av = new JLabel("Average beak length");
		av.setSize(120, 30);
		av.setLocation(420, param.getFrameHeight()+55);
		averageLength.setSize(40,30);
		averageLength.setLocation(550, param.getFrameHeight()+55);
		this.add(av);
		this.add(averageLength);
		
		JLabel ins = new JLabel("Insects (for long) %");
		ins.setSize(120, 30);
		ins.setLocation(20, param.getFrameHeight()+120);
		insect.setSize(40,30);
		insect.setLocation(140, param.getFrameHeight()+120);
		this.add(ins);
		this.add(insect);
		
		JLabel fru = new JLabel("Fruits (for short) %");
		fru.setSize(120, 30);
		fru.setLocation(20, param.getFrameHeight()+150);
		fruit.setSize(40,30);
		fruit.setLocation(140, param.getFrameHeight()+150);
		this.add(fru);
		this.add(fruit);
		
		JLabel tot = new JLabel("Total food");
		tot.setSize(120, 30);
		tot.setLocation(240, param.getFrameHeight()+135);
		foodQuantity.setSize(40,30);
		foodQuantity.setLocation(310, param.getFrameHeight()+135);
		this.add(tot);
		this.add(foodQuantity);
		
		JLabel pop = new JLabel("Finch population");
		pop.setSize(120, 30);
		pop.setLocation(390, param.getFrameHeight()+135);
		finchPopulation.setSize(40,30);
		finchPopulation.setLocation(500, param.getFrameHeight()+135);
		this.add(pop);
		this.add(finchPopulation);
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(int f=0; f<finchList.size(); f++)
		{
			String beak = String.valueOf(finchList.get(f).getBeakLength());			
			char[] data = new char[5];
			
			for(int i=0; i<data.length; i++)
			{
				data[i] = (char) beak.charAt(i);
			}
			
			int x = finchList.get(f).getXPos();
			int y = finchList.get(f).getYPos();
			double length = finchList.get(f).getBeakLength();
			
			drawBird(g,x,y,length);
			g.drawChars(data, 0, data.length, x+20, y+30);
		}
		
		for(int food=0; food<foodList.size(); food++)
		{
			int x = foodList.get(food).getX();
			int y = foodList.get(food).getY();
			if(foodList.get(food).getType()==0)	//if insect
			{
				g.setColor(Color.BLACK);
				g.fillRect(x, y, 12, 3);
			}
			else	//if fruit
			{
				g.setColor(Color.RED);
				g.fillOval(x, y, 10, 10);
			}
		}
		
	}
	
	private void drawBird(Graphics g, int x, int y, double length)
	{
		int width = param.getFinchWidth();
		int height = param.getFinchHeight();
		
		float b =  (float) (1 - length*0.8) ;
		g.setColor( Color.getHSBColor(0f, 1f, b) );	
		//colour has to be depending on length, hsb (0,1,b) b goes from 0.2 to 1 as length goes from 1 to 0
		int beakL = (int) (8 + length*27);	//between 8 and 35 ?		-> 8 + (0 to 27) yet beak is 0 to 1, so *27
		int beakW = (int) (8 + (1-length)*12);	//between 8 and 20?		-> 8 + (0 to 12)	same, 			so *12
		int xPoints[] = {x+width*4/5,x+width*4/5,x+width*4/5+(beakL)};	//here there is scale depending on length
		int yPoints[] = {y+height*1/5,y+height*1/6+beakW,y+height*1/6+beakW/2};
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(Color.GRAY);
		g.fillArc(x, y, width*2/3, height*2/3, 180, 180);
		g.fillOval(x+width*1/2, y+height*1/8, width/3, height/3);
	}

	private void drawFakeBird(Graphics g, int x, int y)
	{
		g.setColor(Color.RED);
		g.drawRect(x, y, 30, 40);
		g.fillRect(x, y, 20, 10);
		g.setColor(Color.YELLOW);
		g.drawOval(x+5, y, 50, 50);
		g.fillOval(x+10,y,50,50);
		g.setColor(Color.BLACK);
		g.drawLine(x,y,x,y+70);
	}

	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	public void setFinches(ArrayList<Finch> finchList)
	{
		this.finchList = finchList;
	}
	
	public void setFoodList(ArrayList<Food> foodList)
	{
		this.foodList = foodList;
	}
	
	public void setProportions()
	{
		double longs=0;
		double shorts=0;
		double max=0;
		double min=1;
		double av=0;
		int lSize = finchList.size();
		for(int i=0; i<lSize; i++)
		{
			double Tbeak = finchList.get(i).getBeakLength();
			if(Tbeak<0.5)
			{
				shorts++;
			}
			else
			{
				longs++;
			}
			if(Tbeak>max)
				max=Tbeak;
			if(Tbeak<min)
				min=Tbeak;
			av += Tbeak;
		}
		longs *= (double) 100 / lSize ;
		shorts *= (double) 100 / lSize ;
		av /= lSize;
		longBeaks.setText(String.valueOf(longs));
		shortBeaks.setText(String.valueOf(shorts));
		maxLength.setText(String.valueOf(max));
		minLength.setText(String.valueOf(min));
		averageLength.setText(String.valueOf(av));
		finchPopulation.setText(String.valueOf(finchList.size()));
		
		double insectCount=0;
		double fruitCount=0;
		for(int i=0; i<foodList.size(); i++)
		{
			if(foodList.get(i).getType()==0)
			{
				insectCount++;
			}
			else
			{
				fruitCount++;
			}
		}
		insectCount *= (double) 100 / foodList.size() ;
		fruitCount *= (double) 100 / foodList.size() ;
		insect.setText(String.valueOf(insectCount));
		fruit.setText(String.valueOf(fruitCount));
		foodQuantity.setText(String.valueOf(foodList.size()));
	}
	
//	Timer tm = new Timer(5,this);
//	public static void main(String[] args)
//	{
//	Animation t = new Animation();
//	t.tm.start();
//	JFrame jf = new JFrame();
//	jf.setTitle("tut");
//	jf.setSize(600,400);
//	jf.setVisible(true);
//	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	jf.add(t);
//
//	}
}



