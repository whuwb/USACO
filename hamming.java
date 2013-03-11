/*
ID : skywind5
LANG : JAVA
TASK : hamming 
*/

import java.io.*;
import java.util.StringTokenizer;

public class hamming {
	
	private final String FOLDER_PATH  = "";
	
	private final int MAX_NUMBER = 512;
	
	private final int MAX_COUNT = 64;
	
	private int[] numbers = new int[this.MAX_COUNT];
	
	private int countOfNumber = 0;
	
	private int size = 0;
	
	private int minDistance = 0;
	
	private int upperBound = 0;
	
	private int[][] distance = new int[this.MAX_NUMBER][this.MAX_NUMBER];
	
	private boolean finish = false;
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "hamming.in");
		
		this.init();
		
		this.numbers[0] = 0;
		
		this.search(1);
		
		this.output(this.FOLDER_PATH + "hamming.out");
	}
	
	private void search(int depth)
	{
		if (this.finish)
		{
			return;
		}
		
		for (int i = this.numbers[depth - 1] + 1; i <= this.upperBound; i++)
		{	
			if (this.finish)
			{
				return;
			}
			
			if (this.check(depth, i))
			{
				this.numbers[depth] = i;
				
				if (depth + 1 < this.countOfNumber)
				{
					this.search(depth + 1);
				}
				else
				{
					this.finish = true;
					return;
				}
			}			
		}
	}
	
	private boolean check(int depth, int value)
	{
		for (int i = 0; i < depth; i++)
		{
			if (this.distance[this.numbers[i]][value] < this.minDistance)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private void init()
	{
		this.upperBound = this.getUpperBound(this.size);
		
		for (int i = 0; i <= this.upperBound; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				this.distance[i][j] = this.getDistance(i, j);
				
				this.distance[j][i] = this.distance[i][j];
			}
		}
	}
	
	private int getDistance(int first, int second)
	{
		int count = 0;
		
		for (int i = 0; i < this.size; i++)
		{
			if ((first % 2) != (second % 2))
			{
				count ++;
			}
			
			first /= 2;
			second /= 2;
		}
		
		return count;
	}
	
	private int getUpperBound(int size)
	{
		int tmp = 1;
		
		for (int i = 1; i < size; i ++)
		{
			tmp = tmp * 2 + 1;
		}
		
		return tmp;
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			this.countOfNumber = Integer.parseInt(stringTokenizer.nextToken());
			
			this.size = Integer.parseInt(stringTokenizer.nextToken());
			
			this.minDistance = Integer.parseInt(stringTokenizer.nextToken());
			
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
			PrintWriter printWriter = new PrintWriter( new BufferedWriter (new FileWriter(filePath)) );
			
			for (int i = 0; i + 1< this.countOfNumber; i++)
			{
				printWriter.print(Integer.toString(this.numbers[i]));
				
				if ((i + 1) % 10 == 0)
				{
					printWriter.print("\n");
				}
				else
				{
					printWriter.print(" ");
				}
			}
			
			printWriter.print(Integer.toString(this.numbers[this.countOfNumber - 1]) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		hamming data = new hamming();
		
		data.run();		
	}
	
}
