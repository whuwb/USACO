/*
ID : skywind5
LANG : JAVA
TASK : numtri 
*/

import java.io.*;
import java.util.StringTokenizer;

public class numtri {
	
	private final String FOLDER_PATH = "";
	
	private final int MAX_SIZE = 1000 + 1;
	
	private int[][] data = new int[this.MAX_SIZE][this.MAX_SIZE];
	
	private int[][] opt = new int[this.MAX_SIZE][this.MAX_SIZE];
	
	private int size = 0;
	
	int answer = 0;

	public void run()
	{
		this.readIn(this.FOLDER_PATH + "numtri.in");
		
		calc();
		
		this.output(this.FOLDER_PATH + "numtri.out");
	}
	
	private void calc()
	{
		this.opt[0][0] = this.data[0][0];
		
		for (int row = 1; row < this.size; row ++)
		{
			this.opt[row][0] = this.data[row][0] + this.opt[row - 1][0];
			
			this.opt[row][row] = this.data[row][row] + this.opt[row -1][row -1];
			
			for (int col = 1; col < row; col++)
			{				
				this.opt[row][col] = this.data[row][col] + Math.max(this.opt[row - 1][col], this.opt[row -1 ][col -1]); 
			}
		}
		
		for (int col = 0; col < this.size; col ++)
		{
			this.answer = Math.max(this.answer, this.opt[this.size -1][col]);
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
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.size = Integer.parseInt(bufferedReader.readLine());
			
			for (int row = 0; row < this.size; row ++)
			{
				StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				
				for (int col = 0; col <= row; col ++)
				{
					this.data[row][col] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		

	}
		
	public static void main(String[] args)
	{
		numtri data = new numtri();
		
		data.run();
	}
	
	
}
