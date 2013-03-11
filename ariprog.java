/*
ID : skywind5
LANG : JAVA
TASK : ariprog
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ariprog {
	
	private final String FOLDER_PATH = "";
	private final int MAX_NUMBER = 250 * 250 + 250 * 250 + 1;
	
	private int upperOfBisquare = 0;
	
	private int maxOfUseableNumber = 0;
	
	private int lengthOfBisquare = 0;
	
	private boolean[] userableNumbers = new boolean[this.MAX_NUMBER]; 
	
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.lengthOfBisquare = Integer.parseInt( bufferedReader.readLine() );
			this.upperOfBisquare = Integer.parseInt( bufferedReader.readLine() );
			
			this.maxOfUseableNumber = this.upperOfBisquare * this.upperOfBisquare * 2 + 1;
			
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
			PrintWriter printWriter = new PrintWriter( new BufferedWriter ( new FileWriter(filePath) ) );
			
			for (Answer answer : this.answers)
			{
				printWriter.print(answer.toString() + '\n');
			}
			
			if (this.answers.size() == 0)
			{
				printWriter.write("NONE\n");
			}
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	private void init()
	{
		for (int i = 0; i < this.maxOfUseableNumber; i++)
		{
			this.userableNumbers[i] = false;
		}
		
		for (int i = 0; i <= this.upperOfBisquare; i++)
		{
			for (int j = i; j <= this.upperOfBisquare; j++)
			{
				this.userableNumbers[i * i + j * j] = true;
			}
		}
	}
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "ariprog.in");
		
		this.init();
		
		for (int i = 0; i < this.maxOfUseableNumber; i++)
		{
			if (this.userableNumbers[i] == false)
			{
				continue;
			}
			
			for (int j = i + 1; j < this.maxOfUseableNumber; j++)
			{
				if (this.userableNumbers[j] == false)
				{
					continue;
				}
				
				int delta = j - i;
				
				if (i + delta * (this.lengthOfBisquare - 1) > this.maxOfUseableNumber)
				{
					break;
				}
				
				this.check(i, j - i);
			}
		}
		
		Collections.sort(this.answers);
		
		this.output(this.FOLDER_PATH + "ariprog.out");
	}
	
	private void check(int startNumber, int delta)
	{		
		for (int i = 1; i < this.lengthOfBisquare; i ++)
		{
			if (this.userableNumbers[ startNumber + i * delta] == false)
			{
				return;
			}
		}
		
		Answer answer = new Answer(startNumber, delta);
		
		this.answers.add(answer);
	}
	
	public static void main(String[] args)
	{
		ariprog data = new ariprog();
		
		data.run();
	}
	
	public class Answer implements Comparable<Answer>
	{
		public Answer(int startNumber, int delta)
		{
			this.startNumber = startNumber;
			this.delta = delta;
		}
		
		private int startNumber = 0;
		
		private int delta = 0;
		
		public int getStartNumber()
		{
			return this.startNumber;
		}
		
		public void setStartNumber(int value)
		{
			this.startNumber = value;
		}
		
		public int getDelta()
		{
			return this.delta;
		}
		
		public void setDelta(int value)
		{
			this.delta = value;
		}
		
		public int compareTo(Answer otherAnswer)
		{
			if (this.getDelta() != otherAnswer.getDelta())
			{
				return this.getDelta() - otherAnswer.getDelta();
			}
			
			return this.getStartNumber() - otherAnswer.getStartNumber();
		}
		
		@Override
		public String toString()
		{
			return Integer.toString(this.getStartNumber()) + ' ' + Integer.toString(this.getDelta());
		}
	}
}
