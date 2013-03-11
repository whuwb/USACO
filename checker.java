/*
ID : skywind5
LANG : JAVA
TASK : checker
*/

import java.io.*;
import java.util.ArrayList;

public class checker {
	
	private final String FOLDER_PATH = "";
	private final int MAX_SIZE = 13 + 1;
	
	private boolean[] lineRight = new boolean[this.MAX_SIZE + this.MAX_SIZE + 2];
	
	private boolean[] lineLeft = new boolean[this.MAX_SIZE + this.MAX_SIZE + 2];
	
	private ArrayList<Status> answers = new ArrayList<Status>();
	
	private Node[] nodes = new Node[this.MAX_SIZE];
	
	private int size = 0;
	
	Status status = new Status();
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "checker.in");
		
		this.init();
		
		this.search(0);
		
		this.output(this.FOLDER_PATH + "checker.out");
	}
	
	private void search(int row)
	{
		int col = this.nodes[0].next;
		
		while (col <= this.size)
		{			
			if (this.isOk(row, col))
			{
				this.status.board[row] = col;
				
				if (row + 1 == this.size)
				{
					this.addAnswer();
				}
				else
				{
					this.removeNode(col);
					this.lineLeft[row + col] = false;
					this.lineRight[col - row + this.MAX_SIZE] = false;
				
					this.search(row + 1); 
					
					this.addNode(col);
					this.lineLeft[row + col] = true;
					this.lineRight[col - row + this.MAX_SIZE] = true;
				}
			}
			
			col = this.nodes[col].next;
		}
	}
	
	private boolean isOk(int row, int col)
	{
		return this.lineLeft[row + col] && this.lineRight[col - row + this.MAX_SIZE];
	}
	
	private void removeNode(int index)
	{
		int prevIndex = this.nodes[index].prev;
		
		int nextIndex = this.nodes[index].next;
		
		if (prevIndex >= 0)
		{
			this.nodes[prevIndex].next = nextIndex;
		}
		
		if (nextIndex <= this.size)
		{
			this.nodes[nextIndex].prev = prevIndex;
		}
	}
	
	private void addNode(int index)
	{
		int prevIndex = this.nodes[index].prev;
		
		int nextIndex = this.nodes[index].next;
		
		if (prevIndex >= 0 )
		{
			this.nodes[prevIndex].next = index;
		}
		
		if (nextIndex <= this.size)
		{
			this.nodes[nextIndex].prev = index;
		}
	}
	
	private void addAnswer()
	{
		Status answer = new Status();
		
		for (int i = 0; i < size; i++)
		{
			answer.board[i] = this.status.board[i];
		}
		
		this.answers.add(answer);
	}
	
	private void init()
	{
		for (int i = 0; i <= this.size; i++)
		{
			this.nodes[i] = new Node(i - 1, i + 1);
		}
		
		for (int row =0; row < this.size; row++)
		{
			for (int col = 0; col <= this.size; col ++)
			{
				this.lineLeft[row + col] = true;
				this.lineRight[col - row + this.MAX_SIZE] = true;
			}
		}
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			int count = 0;
		
			for (Status answer : this.answers)
			{
				for (int i = 0; i + 1< size; i++)
				{
					printWriter.print(Integer.toString(answer.board[i]) + ' ');
				}
				
				printWriter.print(Integer.toString(answer.board[this.size - 1]) + '\n');
				
				count++;
				if (count == 3)
				{
					break;
				}
			}
			
			printWriter.print(Integer.toString(this.answers.size()) + '\n');
			
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
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		checker data = new checker();
		
		data.run();
	}
	
	public class Status
	{
		private final int MAX_SIZE = 13 + 1;
		
		public int[] board = new int[this.MAX_SIZE];
	}
	
	public class Node
	{
		private int next = 0;
		
		private int prev = 0;
		
		public Node(int prev, int next)
		{
			this.setNext(next);
			this.setPrev(prev);
		}
		
		public int getNext()
		{
			return this.next;
		}
		
		public void setNext(int value)
		{
			this.next = value;
		}
		
		public int getPrev()
		{
			return this.prev;
		}
		
		public void setPrev(int value)
		{
			this.prev = value;
		}
	}
}
