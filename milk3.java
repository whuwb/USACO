/*
ID : skywind5
LANG : JAVA
TASK : milk3 
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class milk3 {
	
	private final String FOLDER_PATH = "";
	
	private int MAX_CAPACITY = 20 + 1;
	
	private int MAX_NUMBER_OF_STATUS = this.MAX_CAPACITY * this.MAX_CAPACITY * this.MAX_CAPACITY + 1;
	
	private final int NUMBER_OF_BOTTLES = 3;
	
	private Status[] statuses = new Status[this.MAX_NUMBER_OF_STATUS];
	
	private boolean[] answers = new boolean[this.MAX_CAPACITY];
	
	private int statusesSize = 0;
	
	public void run()
	{
		this.init();
		
		this.readIn(this.FOLDER_PATH + "milk3.in");
		
		this.search();
		
		this.output(this.FOLDER_PATH + "milk3.out");
	}
	
	private void search()
	{
		int headIndex = 0;
		
		while (headIndex < this.statusesSize)
		{
			if (this.statuses[headIndex].bottles.get(0).currentCapacity == 0)
			{
				this.answers[this.statuses[headIndex].bottles.get(2).currentCapacity] = true;
			}
			
			this.extractStatus(this.statuses[headIndex]);
			
			headIndex ++;
		}
	}
	
	private void extractStatus(Status status)
	{
		for (int i = 0; i < this.NUMBER_OF_BOTTLES; i++)
		{
			for (int j = 0; j < this.NUMBER_OF_BOTTLES; j++)
			{
				if (i != j)
				{
					this.pourCapacity(status, i, j);
				}
			}
		}
	}
	
	private void pourCapacity(Status status, int first, int second)
	{
		Status tmpStatus = new Status();
		
		for (Bottle bottle : status.bottles)
		{
			Bottle tmpBottle = new Bottle();
			
			tmpBottle.setCapacity( bottle.getCapacity() );
			tmpBottle.setCurrentCapacity(bottle.getCurrentCapacity());
			
			tmpStatus.bottles.add(tmpBottle);
		}
		
		Bottle firstBottle = tmpStatus.bottles.get(first);
		
		Bottle secondBottle = tmpStatus.bottles.get(second);
		
		int deltaCapacity = Math.min( firstBottle.currentCapacity, secondBottle.capacity - secondBottle.currentCapacity );
		
		firstBottle.currentCapacity -= deltaCapacity;
		secondBottle.currentCapacity += deltaCapacity;
		
		if (this.isExistStatus(tmpStatus) == false)
		{
			this.addStatus(tmpStatus);
		}
	}
	
	private boolean isExistStatus(Status status)
	{
		for (int i = 0; i < this.statusesSize; i++)
		{
			if (status.equals(this.statuses[i]))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private void init()
	{
		for (int i = 0; i < this.MAX_CAPACITY; i++)
		{
			this.answers[i] = false;
		}
				
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter( new BufferedWriter( new FileWriter(filePath) ) );
			
			boolean first = true;
			
			for (int i = 0; i < this.MAX_CAPACITY; i++)
			{
				if (this.answers[i])
				{
					if (first == true)
					{
						first = false;
					}
					else
					{
						printWriter.print(' ');
					}
					
					printWriter.print(Integer.toString(i));
				}
			}
			
			printWriter.print("\n");
			
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
			BufferedReader bufferedReader = new BufferedReader( new FileReader(filePath) );
			
			StringTokenizer stringTokenizer= new StringTokenizer(bufferedReader.readLine());
			
			Status status = new Status();
			
			for (int i = 0; i < this.NUMBER_OF_BOTTLES; i++)
			{
				Bottle bottle = new Bottle();
				
				bottle.setCapacity(Integer.parseInt( stringTokenizer.nextToken() ));
				
				if (i < this.NUMBER_OF_BOTTLES - 1)
				{
					bottle.setCurrentCapacity(0);
				}
				else
				{
					bottle.setCurrentCapacity(bottle.capacity);
				}
				
				status.bottles.add(bottle);
			}
			
			this.addStatus(status);
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	private void addStatus(Status status)
	{
		this.statuses[ this.statusesSize ] = status;
		
		this.statusesSize ++;
	}
	
	public static void main(String[] args)
	{
		milk3 data = new milk3();
		
		data.run();
	}
	
	public class Bottle
	{
		private int capacity = 0;
		
		private int currentCapacity = 0;
		
		public int getCapacity()
		{
			return this.capacity;
		}
		
		public void setCapacity(int value)
		{
			this.capacity = value;
		}
		
		public int getCurrentCapacity()
		{
			return this.currentCapacity;
		}
		
		public void setCurrentCapacity(int value)
		{
			this.currentCapacity = value;
		}		
	}
	
	public class Status
	{
		public ArrayList<Bottle> bottles = new ArrayList<Bottle>();
		
		public boolean equals(Status otherStatus)
		{
			
			for (int i = 0; i < 3; i++)
			{
				if (this.bottles.get(i).getCurrentCapacity() != otherStatus.bottles.get(i).getCurrentCapacity() )
				{
					return false;
				}
				
			}
			
			return true;
		}
		
	}
}
