/*
ID : skywind5
LANG : JAVA
TASK : crypt1
*/

import java.io.*;
import java.util.StringTokenizer;

public class crypt1 {
	
	private final String FOLDER_PATH = "";
	
	private final int NUMBER_oF_DATAs = 5;
	
	private final int NUMBER_OF_NUMBERS = 10;
	
	private boolean[] useableNumbers = new boolean [this.NUMBER_OF_NUMBERS];
	
	private int[] data = new int[this.NUMBER_oF_DATAs];
	
	private int numberOfUseableNumbers = 0;
	
	private int result = 0;
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "crypt1.in");
		
		this.search(0);
		
		this.output(this.FOLDER_PATH + "crypt1.out");
	}
	
	private void search(int step)
	{
		for (int i = this.getStartPosition(step); i < this.NUMBER_OF_NUMBERS; i++)
		{
			if (this.useableNumbers[i])
			{
				this.data[step] = i;
				
				if (step == this.NUMBER_oF_DATAs - 1)
				{
					this.check();
				}
				else
				{
					this.search(step + 1);
				}
			}
		}
	}
	
	private void check()
	{
		int firstNumber = this.createNumber(0, 2);
		
		int firstValue = firstNumber * this.data[3];
		int secondValue = firstNumber * this.data[4];
		
		int total = firstValue + secondValue * 10;
		
		if (this.isOK(firstValue, 3) && this.isOK(secondValue, 3) && this.isOK(total, 4))
		{
			this.result ++;
		}
	}
	
	private boolean isOK(int number, int length)
	{
		int tmpLength = 0;
		
		while (number > 0)
		{
			tmpLength ++;
			
			if (this.useableNumbers[number % 10] == false)
			{
				return false;
			}
			
			number /= 10;
		}
		
		if (tmpLength != length)
		{
			return false;
		}
		
		return true;
	}
	
	private int createNumber(int startIndex, int endIndex)
	{
		int result = 0;
		
		for (int i = startIndex; i<=endIndex; i++)
		{
			result = result * 10 + this.data[i];
		}
		
		return result;
	}
	
	private int getStartPosition(int step)
	{
		if (step == 0 || step == 3)
		{
			return 1;
		}
		
		return 0;
	}
	
	private void readIn(String filePath)
	{
		for (int i = 0; i < this.NUMBER_OF_NUMBERS; i ++)
		{
			this.useableNumbers[i] = false;
		}
		
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.numberOfUseableNumbers = Integer.parseInt( bufferedReader.readLine() );
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			for (int i = 0; i < this.numberOfUseableNumbers; i++)
			{
				int tmp = Integer.parseInt(stringTokenizer.nextToken());
				
				this.useableNumbers[tmp] = true;
			}
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			printWriter.print(Integer.toString(this.result) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	public static void main(String[] args)
	{
		crypt1 data = new crypt1();
		
		data.run();
	}
	
}
