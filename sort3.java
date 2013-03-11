/*
ID : skywidn5
LANG : JAVA
TASK : sort3 
*/

import java.io.*;

public class sort3 {
	
	private final String FOLDER_PATH = "";
	private final int MAX_SIZE = 1000 + 1;
	
	private int totalNumber = 0;
	
	private int[] data = new int[this.MAX_SIZE];
	
	private int[] numbers = new int[4];
	
	private int exchangeNumber = 0;
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "sort3.in");
		
		this.init();
		
		this.calc();
		
		this.output(this.FOLDER_PATH + "sort3.out");
	}
	
	private void init()
	{
		for (int i = 2; i < 4; i++)
		{
			this.numbers[i] += this.numbers[i - 1];
		}
	}
	
	private void calc()
	{
		for (int i = 0; i < this.totalNumber; i++)
		{
			int number = this.getNumber(i + 1);
			
			if (data[i] != number)
			{
				
				if (data[i] == 2)
				{
				
					for (int j = this.numbers[number]; j < this.totalNumber; j++)
					{
						if (data[j] == number)
						{
							int tmp = data[i];
							data[i] = data[j];
							data[j] = tmp;
							
							this.exchangeNumber ++;
							
							break;
						}
					}
				}
				else					
				{
					for (int j = this.totalNumber - 1; j >= this.numbers[number]; j--)
					{
						if (data[j] == number)
						{
							int tmp = data[i];
							data[i] = data[j];
							data[j] = tmp;
							
							this.exchangeNumber ++;
							
							break;
						}
					}
				}
			}
		}
	}
	
	private int getNumber(int index)
	{
		
		for (int i = 1; i < 4; i++)
		{
			if (index <= this.numbers[i])
			{
				return i;
			}
		}
		
		return -1;
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.totalNumber = Integer.parseInt(bufferedReader.readLine());
			
			for (int i = 0; i < this.totalNumber; i++)
			{
				this.data[i] = Integer.parseInt(bufferedReader.readLine());
				
				this.numbers[ this.data[i]] ++;
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
			PrintWriter printWriter = new PrintWriter(new BufferedWriter (new PrintWriter (filePath)));
			
			printWriter.print(Integer.toString(this.exchangeNumber) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		sort3 data = new sort3();
		
		data.run();
	}
	
}
