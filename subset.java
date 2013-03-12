/*
ID : skywind5
LANG : JAVA
TASK : subset 
*/

import java.io.*;

public class subset {

	private final String FOLDER_PATH = "";
	
	private final int MAX_SIZE = 39 ;
	private final int MAX_NUMBER = (this.MAX_SIZE + 1) * (this.MAX_SIZE) / 2;
	
	private int number = 0;
	private int answer = 0;
	
	private int targetSum = 0;
	
	private int[] min_value = new int[this.MAX_SIZE + 1];
	
	private int[][] opt = new int[this.MAX_SIZE + 1][this.MAX_NUMBER + 1];
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "subset.in");
		
		this.targetSum = this.number * (this.number + 1) / 2;
		
		if (this.targetSum % 2 == 0)
		{
			this.targetSum /= 2;
			
			this.init();
			
			this.calc();
		}
		
		this.output(this.FOLDER_PATH + "subset.out");
	}
	
	private void init()
	{
		opt[1][0] = 1;
	}
	
	private void calc()
	{
		for (int i = 1; i < this.number; i++)
		{
			for (int j = 0; j <= this.targetSum; j++)
			{
				this.opt[i + 1][j] += this.opt[ i ][j];
				
				if (j + i <= this.targetSum)
				{
					this.opt[i + 1][j + i] += this.opt[i][j];
				}
			}
		}
		
		this.answer = this.opt[this.number][this.targetSum];
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.number = Integer.parseInt(bufferedReader.readLine());
			
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
			
			printWriter.write(Integer.toString(this.answer) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		subset data = new subset();
		
		data.run();
	}
	
}
