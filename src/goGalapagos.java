import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class goGalapagos /*implements ActionListener */{
//	Timer tm = new Timer(5,this);
//	
//
//	public void actionPerformed(ActionEvent e)
//	{
//		System.out.println("test");
//	}

	public static void main(String[] args)
	{
//		goGalapagos go = new goGalapagos();
//		go.tm.start();
		
		double x = Math.random();
		String beak = String.valueOf(x);
		System.out.println(x);
		char[] data = new char[4];
		for(int i=0; i<data.length; i++)
		{
			data[i] = beak.charAt(i);
		}
		
		for(int i=0; i<data.length; i++)
		{
			System.out.println("char#"+i+" is "+data[i]);
		}
	}
}
