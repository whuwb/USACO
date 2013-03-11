/*
ID : skywind5
LANG : JAVA
TASK : barn1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class barn1 {
	
	private final String FOLDER_PATH = "";
	
	private int minLengthOfBoards = 0;
	private int numberOfStalls = 0;
	private int numberOfCows = 0;
	private int numberOfBoards = 0;
	
	private ArrayList<Integer> cows = new ArrayList<Integer> ();
	
	//the process controller
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "barn1.in");
	
		Collections.sort(this.cows);
			
		this.minLengthOfBoards = this.cows.get(this.cows.size() - 1) 
									- this.cows.get(0) + 1 - this.getDeltaValue();
		
		this.output(this.FOLDER_PATH + "barn1.out");
	}
	
	private int getDeltaValue()
	{
		ArrayList<Integer> deltas = new ArrayList<Integer>();
		
		int lastCow = -1;
		
		for (Integer cow : this.cows)
		{
			if (lastCow > 0)
			{
				deltas.add(cow - lastCow - 1);
			}
			
			lastCow = cow;
		}
		
		Collections.sort(deltas);
		
		int size = deltas.size();
		
		int result = 0;
		
		for (int i = 1; i < this.numberOfBoards; i ++)
		{
			if (size - i < 0)
			{
				break;
			}
			
			result += deltas.get(size - i);
		}
		
		return result;
	}

	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader( new FileReader(filePath) );
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			this.numberOfBoards = Integer.parseInt(stringTokenizer.nextToken());
			this.numberOfStalls = Integer.parseInt(stringTokenizer.nextToken());
			this.numberOfCows = Integer.parseInt(stringTokenizer.nextToken());
			
			for (int i = 0; i < this.numberOfCows; i++)
			{
				this.cows.add(Integer.parseInt( bufferedReader.readLine() ));
			}
			
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage());
		}
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter (new FileWriter(filePath)) );
			
			printWriter.write(Integer.toString(this.minLengthOfBoards) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		barn1 data = new barn1();
		
		data.run();
	}
}
