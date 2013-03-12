/*
ID : skywind5
LANG : JAVA
TASK : lamps 
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class lamps {
	
	private final String FOLDER_PATH = "";
	
	private final int MAX_NUMBER_OF_LAMPS = 100;
	
	//true == on, false == light;
	private boolean[] lamps = new boolean[this.MAX_NUMBER_OF_LAMPS];
	
	private boolean[] mustOnLamps = new boolean[this.MAX_NUMBER_OF_LAMPS];
	
	private boolean[] mustOffLamps = new boolean[this.MAX_NUMBER_OF_LAMPS];
	
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	private int numberOfLamps = 0;
	
	private int numberOfChange = 0;
	
	public void run()
	{
		this.init();
		
		this.readIn(this.FOLDER_PATH + "lamps.in");
		
		this.calc();
		
		Collections.sort(this.answers);
		
		this.output(this.FOLDER_PATH + "lamps.out");
	}
	
	private void calc()
	{
		for (int first = 0; first <= this.numberOfChange; first ++)
		{
			for (int second = 0; second <= this.numberOfChange - first; second ++)
			{
				for (int third = 0; third <= this.numberOfChange - first - second; third++)
				{
					this.check(first, second, third, this.numberOfChange - first - second - third);					
				}
			}
		}
	}
	
	private void check(int first, int second, int third, int fourth)
	{
		first = first % 2;
		second = second % 2;
		third = third % 2;
		fourth = fourth % 2;
		
		for (int i = 0; i < this.numberOfLamps; i++)
		{
			this.lamps[i] = true;
		}
		
		for (int i = 0; i < first; i++)
		{
			for (int j = 0; j < this.numberOfLamps; j++)
			{
				this.lamps[j] = !this.lamps[j];
			}
		}
		
		for (int i = 0; i < second; i++)
		{
			for (int j = 0; j < this.numberOfLamps; j+=2)
			{
				this.lamps[j] = !this.lamps[j];
			}
		}
		
		for (int i = 0; i < third; i++)
		{
			for (int j = 1; j < this.numberOfLamps; j+=2)
			{
				this.lamps[j] = !this.lamps[j];
			}
		}
		
		for (int i = 0; i < fourth; i++)
		{
			for (int j = 0; j < this.numberOfLamps; j+=3)
			{
				this.lamps[j] = !this.lamps[j];
			}
		}
		
		for (int i = 0; i < this.numberOfLamps; i++)
		{
			if ( (this.mustOnLamps[i] == true) && (this.lamps[i] == false) )
			{
				return;
			}
			
			if ( (this.mustOffLamps[i] == true) && (this.lamps[i] == true) )
			{
				return;
			}
		}
		
		Answer answer = new Answer();
		
		for (int i = 0; i < this.numberOfLamps; i++)
		{
			answer.lamps[i] = this.lamps[i];
		}
		
		this.addAnswer(answer);
	}
	
	private void addAnswer(Answer answer)
	{
		if (this.isExist(answer) == false)
		{
			this.answers.add(answer);
		}
	}
	
	private boolean isExist(Answer answer)
	{
		for (Answer otherAnswer : this.answers)
		{
			boolean isSame = true;
			
			for (int i = 0; i < this.numberOfLamps; i++)
			{
				if (otherAnswer.lamps[i] != answer.lamps[i])
				{
					isSame = false;
					break;
				}
			}
			
			if (isSame == true)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private void init()
	{
		for (int i = 0; i < this.MAX_NUMBER_OF_LAMPS; i++)
		{
			this.lamps[i] = false;
			this.mustOnLamps[i] = false;
			
			this.mustOffLamps[i] = false;
		}
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.numberOfLamps = Integer.parseInt(bufferedReader.readLine());
			
			this.numberOfChange = Integer.parseInt(bufferedReader.readLine());
			
			if (this.numberOfChange > 10)
			{
				this.numberOfChange = 10;
			}
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			//must on
			while (true)
			{
				int tmp = Integer.parseInt(stringTokenizer.nextToken());
				
				if (tmp == -1)
				{
					break;
				}
				
				this.mustOnLamps[tmp - 1] = true;
			}
			
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			//must off
			while (true)
			{
				int tmp = Integer.parseInt(stringTokenizer.nextToken());
				
				if (tmp == -1)
				{
					break;
				}
				
				this.mustOffLamps[tmp - 1] = true;
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
			
			for (Answer answer : this.answers)
			{
				StringBuilder stringBuilder = new StringBuilder();
				
				for (int i = 0; i < this.numberOfLamps; i++)
				{
					if (answer.lamps[i] == true)
					{
						stringBuilder.append('1');
					}
					else
					{
						stringBuilder.append('0');
					}
				}
				
				printWriter.print(stringBuilder.toString() + '\n');
			}
			
			if (this.answers.size() == 0)
			{
				printWriter.print("IMPOSSIBLE\n");
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
		lamps data = new lamps();
		
		data.run();
	}
	
	public class Answer implements Comparable<Answer>
	{
		private final int MAX_NUMBER_OF_LAMPS = 100;
		
		public boolean[] lamps = new boolean[this.MAX_NUMBER_OF_LAMPS];
		
		public int compareTo(Answer otherAnswer)
		{
			for (int i = 0; i < this.MAX_NUMBER_OF_LAMPS; i++)
			{
				if (this.lamps[i] != otherAnswer.lamps[i])
				{
					if (this.lamps[i])
					{
						return 1;
					}
					else
					{
						return -1;
					}
				}
			}
			
			return 0;
		}
	}
	
}
