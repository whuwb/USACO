/*
ID : skywind5
LANG : JAVA
TASK : pprime 
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class pprime {
	
	private final String FOLDER_PATH = "";
	
	private int start = 0;
	private int end = 0;
	
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	
	private int max_length = 0;
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "pprime.in");
		
		this.calc();
		
		Collections.sort(this.answers);
		
		this.output(this.FOLDER_PATH + "pprime.out");
	}
	
	private void calc()
	{
		this.create("");
		
		for (int i = 0; i < 10; i++)
		{
			this.create(Integer.toString(i));
		}
	}
	
	private boolean isPrime(int data)
	{
		if (data == 2)
		{
			return true;
		}
		
		if (data % 2 == 0)
		{
			return false;
		}
		
		int bound = (int)Math.sqrt(data) + 1;
		
		for (int i = 3; i <= bound; i += 2)
		{
			if (data % i == 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private boolean check(String data)
	{
		if (data == "")
		{
			return true;
		}
		
		int tmp = this.getValueFromString(data.toCharArray());
		
		if (tmp > this.end || data.length() > this.max_length)
		{
			return false;
		}
		
		if (tmp >= this.start)
		{
			if (data.charAt(0) != '0')
			{			
				if (this.isPrime(tmp))
				{
					this.answers.add(tmp);
				}
			}
		}
			
		return true;
	}
	
	private int getValueFromString(char[] data)
	{
		int result = 0;
		
		for (int i = 0; i < data.length; i++)
		{
			result = result * 10 + data[i] - '0';
		}
		
		return result;
	}
	
	private void create(String data)
	{
		if (this.check(data) == true)
		{
			for (int i = 0; i < 10; i++)
			{
				this.create(Integer.toString(i) + data + Integer.toString(i));
			}
		}
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			this.start = Integer.parseInt(stringTokenizer.nextToken());
			this.end = Integer.parseInt(stringTokenizer.nextToken());
			
			int tmp = this.end;
			
			while (tmp > 0)
			{
				this.max_length ++;
				
				tmp /= 10;
			}
			
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
		pprime data = new pprime();
		
		data.run();
	}

}
