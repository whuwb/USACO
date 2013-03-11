/*
ID : skywind5
LANG : JAVA
TASK : holstein 
*/

import java.io.*;
import java.util.StringTokenizer;

public class holstein {
	
	private final String FOLDER_PATH = "";
	
	private final int MAX_NUMBER_OF_NODES = 15;
	
	private Node[] nodes = new Node[this.MAX_NUMBER_OF_NODES];
	
	private Node targetNode = new Node();
	private int numberOfVitamins = 0;
	private int numberOfNodes = 0;
	
	private int[] answer = new int[this.MAX_NUMBER_OF_NODES];
	
	private int[] used = new int[this.MAX_NUMBER_OF_NODES];
	
	private int answerSize = this.MAX_NUMBER_OF_NODES + 1;
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "holstein.in");
		
		this.search(0);
		
		this.output(this.FOLDER_PATH + "holstein.out");
	}
	
	private void search(int index)
	{
		if (index >= this.numberOfNodes)
		{
			return;
		}
		
		//prune
		int tmp = 0;
		
		for (int i = 0; i < this.numberOfNodes; i++)
		{
			if (this.used[i] == 1)
			{
				tmp ++;
			}
		}
		
		if (tmp >= this.answerSize)
		{
			return;
		}
		
		//use
		this.used[index] = 1;
		
		this.search(index + 1);
		
		this.check();
		
		//not use
		this.used[index] = 0;
		
		this.search(index + 1);
	}
	
	private void check()
	{
		Node tmpNode = new Node();
		
		int tmp = 0;
		
		for (int i = 0; i < this.numberOfNodes; i++)
		{
			if (this.used[i] == 1)
			{
				tmp ++;
				
				for (int j = 0; j < this.numberOfVitamins; j++)
				{
					tmpNode.vitamins[j] += this.nodes[i].vitamins[j];
				}
					
			}
		}
		
		for (int i = 0; i < this.numberOfVitamins; i ++)
		{
			if (tmpNode.vitamins[i] < this.targetNode.vitamins[i])
			{
				return;
			}
		}
		
		if ( tmp < this.answerSize )
		{
			this.answerSize = tmp;
			
			for (int i = 0; i < this.numberOfNodes; i++)
			{
				this.answer[i] = this.used[i];
			}
		}
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter (new FileWriter(filePath) ));
			
			printWriter.print( Integer.toString( this.answerSize ) );
			
			for (int i = 0; i < this.numberOfNodes; i++)
			{
				if (this.answer[i] == 1)
				{
					printWriter.print(' ' + Integer.toString(i + 1));
				}
			}
			
			printWriter.print("\n");
			
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
			BufferedReader bufferedReader = new BufferedReader(new FileReader (filePath));
			
			this.numberOfVitamins = Integer.parseInt(bufferedReader.readLine());
			
			StringTokenizer stringTokenizer = new StringTokenizer( bufferedReader.readLine() );
			
			for (int i = 0; i < this.numberOfVitamins; i++)
			{
				this.targetNode.vitamins[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			
			this.numberOfNodes = Integer.parseInt(bufferedReader.readLine());
			
			for (int i = 0; i < this.numberOfNodes; i++)
			{
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				this.nodes[i] = new Node();
				
				for (int j = 0; j < this.numberOfVitamins; j++)
				{					
					this.nodes[i].vitamins[j] = Integer.parseInt(stringTokenizer.nextToken());
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
		holstein data = new holstein();
		
		data.run();
	}
	
	public class Node
	{
		private final int MAX_SIZE = 25;
		
		public int[] vitamins = new int[this.MAX_SIZE + 1];
	}
}
