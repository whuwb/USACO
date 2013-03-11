/*
ID : skywind5
LANG : JAVA
TASK : dualpal 
*/


import java.io.*;
import java.util.StringTokenizer;

public class dualpal {
	
	private static final String FOLDER_PATH = "";
	
	private int numberOfDualPals = 0;
	
	private int startNumber = 0;
	
	private void run()
	{
		this.readIn(dualpal.FOLDER_PATH + "dualpal.in");
		
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(dualpal.FOLDER_PATH + "dualpal.out")));
			
			int currentNumberOfResults = 0;
			
			while (currentNumberOfResults < this.numberOfDualPals)
			{
				this.startNumber++;
				
				if (this.isDualPal(this.startNumber))
				{
					printWriter.print(Integer.toString(this.startNumber) + '\n');
					
					currentNumberOfResults ++;
				}
			}
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	private boolean isDualPal(int data)
	{
		int countOfPal = 0;
		
		//based on the description, the base will between [2..10]
		for (int base = 2; base <= 10; base++)
		{
			String tmpValue = this.convert(data, base);
			
			if (this.isPal(tmpValue))
			{
				countOfPal ++;
				
				if (countOfPal >= 2)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	private String convert(int data, int base)
	{
		String result = "";
		
		int tmp = data;
		
		while (tmp > 0)
		{
			char tmpChar = (char) (tmp % base + '0');
			
			tmp /= base;
			
			result = tmpChar + result;
		}
		
		return result;
	}
	
	private boolean isPal(String data)
	{
		char[] dataInArray = data.toCharArray();
		
		int head = 0, tail = dataInArray.length - 1;
		
		while (head < tail)
		{
			if (dataInArray[head] != dataInArray[tail])
			{
				return false;
			}
			
			head ++;
			
			tail --;
		}
		
		return true;
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			this.numberOfDualPals = Integer.parseInt( stringTokenizer.nextToken() );
			this.startNumber = Integer.parseInt(stringTokenizer.nextToken());
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
		
	}
	
	public static void main(String[] args)
	{
		dualpal data = new dualpal();
		
		data.run();
	}
}
