/*
ID : skywind5
LANG : JAVA
TASK : sprime 
*/

import java.io.*;
import java.util.ArrayList;

public class sprime {
	
	private final String FOLDER_PATH = "";
	
	private final int[] startNumbers = new int[]{2, 3, 5, 7};
	
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	
	int size = 0;
	
	private void run()
	{
		this.readIn(this.FOLDER_PATH + "sprime.in");
		
		this.calc();
		
		this.output(this.FOLDER_PATH + "sprime.out");
	}
	
	private void calc()
	{
		for (int i = 0; i < this.startNumbers.length; i++)
		{
			this.search(1, this.startNumbers[i]);
		}
	}
	
	private void search(int depth, int data)
	{		
		if (depth == this.size)
		{
			this.check(data);
		}
		else if (depth < this.size)
		{
			if (this.isPrime(data))
			{				
				for (int i = 1; i < 10; i += 2)
				{
					this.search(depth + 1, data * 10 + i);
				}
			}
		}
	}
	
	private void check(int data)
	{
		if (this.isPrime(data))
		{
			this.answers.add(data);
		}
	}
	
	private boolean isPrime(int data)
	{
		int bound = (int) Math.sqrt(data) + 1;
		
		for (int i = 3; i <= bound; i ++)
		{
			if (data % i == 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.size = Integer.parseInt(bufferedReader.readLine());
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			for (int answer : this.answers)
			{
				printWriter.print(Integer.toString(answer) + '\n');
			}
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		sprime sprime = new sprime();
		
		sprime.run();
	}
	
}
