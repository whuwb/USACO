/*
ID : skywind5
LANG : JAVA
TASK : milk 
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class milk {
	
	private final String FOLDER_PATH = "" ;
	
	private int numberOfFarmer = 0;
	
	private int sumOfMilk = 0;
	
	private int sumOfPrice = 0;
	
	private ArrayList<Farmer> farmers = new ArrayList<Farmer>();
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "milk.in");
		
		//sort the list by the price
		Collections.sort(this.farmers);
		
		for (Farmer farmer : this.farmers)
		{
			int currentMilk = Math.min(this.sumOfMilk, farmer.milk);
			
			this.sumOfPrice += currentMilk * farmer.price;
			
			this.sumOfMilk -= currentMilk;
			
			if (this.sumOfMilk <= 0)
			{
				break;
			}
		}
		
		this.output(this.FOLDER_PATH + "milk.out");
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			printWriter.print(Integer.toString(this.sumOfPrice) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			this.sumOfMilk = Integer.parseInt(stringTokenizer.nextToken());
			
			this.numberOfFarmer = Integer.parseInt(stringTokenizer.nextToken());
			
			for (int i = 0; i < this.numberOfFarmer; i++)
			{
				stringTokenizer = new StringTokenizer( bufferedReader.readLine() );
				
				this.farmers.add( new Farmer(
						Integer.parseInt(stringTokenizer.nextToken()), 
						Integer.parseInt(stringTokenizer.nextToken()) )
				);
								
			}
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
		
	}
	
	public static void main(String[] args)
	{
		milk data = new milk();
		
		data.run();
	}
	
	public class Farmer implements Comparable<Farmer>
	{
		private int milk = 0;
		
		private int price = 0;
		
		public Farmer(int price, int milk)
		{
			this.setPrice(price);
			this.setMilk(milk);
		}
		
		public int getMilk()
		{
			return this.milk;
		}
		
		public void setMilk(int value)
		{
			this.milk = value;
		}
		
		public int getPrice()
		{
			return this.price; 
		}
		
		public void setPrice(int value)
		{
			this.price = value;
		}
		
		public int compareTo(Farmer otherFarmer)
		{
			return this.price - otherFarmer.price;
		}
	}
	
}
