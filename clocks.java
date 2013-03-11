/*
ID : skywind5
LANG : JAVA
TASK : clocks
*/

import java.io.*;
import java.util.StringTokenizer;

public class clocks {

	private final String FOLDER_PATH = "";
	
	private final int MAX_SIZE_OF_STATUSES = (int) Math.pow(4, 9);
	
	private Status[] statuses = new Status[this.MAX_SIZE_OF_STATUSES + 1];
	
	private boolean[] existStatus = new boolean[this.MAX_SIZE_OF_STATUSES + 1];
	
	private String[] operations = new String[]{"ABDE", "ABC", "BCEF", "ADG", "BDEFH",
											"CFI", "DEGH", "GHI", "EFHI"};
	
	private int statusesSize = 0;
	
	private boolean finished = false;
	
	private Status finalStatus = new Status();
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "clocks.in");
		
		this.search();
		
		this.output(this.FOLDER_PATH + "clocks.out");
	}
	
	private void search()
	{
		int head = 0;
		
		while (head < this.statusesSize)
		{			
			if (finished)
			{
				break;
			}
			
			this.extractNode(this.statuses[head]);
			
			head ++;
		}
	}
	
	private void extractNode(Status status)
	{
		for (int operationID = status.getStart(); operationID < this.operations.length; operationID++)
		{
			for (int count = 0; count < 4; count++)
			{
				
				if (operationID == 3)
				{
					if ((status.clocks[0] + count) % 4 != 0)
					{
						continue;
					}
				}
					
				Status tmpStatus = new Status();
				
				for (int i = 0; i < 9; i ++)
				{
					tmpStatus.clocks[i] = status.clocks[i];
				}
				
				for (int i=0; i < 9; i++)
				{
					tmpStatus.operations[i] = status.operations[i];
				}
				
				tmpStatus.update(this.operations[operationID].toCharArray(), operationID, count);
				
				if (this.isExistStatus(tmpStatus) == false)
				{
					if (tmpStatus.isFinished())
					{
						this.finalStatus = tmpStatus;
						
						this.finished = true;
						
						return;
					}
					
					this.addStatus(tmpStatus);
				}
			}
		}
	}
	
	private boolean isExistStatus(Status status)
	{	
		return this.existStatus[status.getHashValue()];
	}
	
	private void addStatus(Status status)
	{
		this.statuses[this.statusesSize] = status;
		
		this.statusesSize ++;
		
		this.existStatus[status.getHashValue()] = true;
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader ( new FileReader (filePath) );
			
			Status status = new Status();
			
			for (int row = 0; row < 3; row ++)
			{
				StringTokenizer stringTokenizer = new StringTokenizer( bufferedReader.readLine() );
				
				for (int col = 0; col < 3; col ++)
				{
					status.clocks[ row * 3 + col ] = (Integer.parseInt(stringTokenizer.nextToken() ) / 3) % 4;
				}
			}
			
			this.addStatus(status);
			
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
			PrintWriter printWriter = new PrintWriter( new BufferedWriter(new FileWriter(filePath) ) );
			
			boolean first = true;
			
			for (int operationID = 0; operationID < this.finalStatus.NUMBER_OF_OPERATION; operationID++ )
			{
				for (int i = 0; i < this.finalStatus.operations[operationID]; i++)
				{
				
					if (first)
					{
						first = false;
					}
					else
					{
						printWriter.print(" ");
					}
					
					printWriter.print(Integer.toString(operationID + 1));
				
				}
				
			}
			
			printWriter.print("\n");
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	public static void main(String[] args)
	{
		clocks data = new clocks();
		
		data.run();
	}
	
	public class Status implements Cloneable
	{
		public final int NUMBER_OF_CLOCK = 9;
		
		public final int NUMBER_OF_OPERATION = 9;
		
		public int[] clocks = new int[this.NUMBER_OF_CLOCK];
		
		public int[] operations = new int[this.NUMBER_OF_OPERATION];
		
		private int hashValue = -1;
		
		public int getStart()
		{
			int end = 8;
			
			while (end >=0 && this.operations[end] == 0)
			{
				end --;
			}
			
			return Math.max(0, end);
		}
		
		public void update(char[] operation, int operationID, int count)
		{
			this.operations[operationID] += count;
			
			for (char ch : operation)
			{
				this.clocks[ch - 'A'] = ( this.clocks[ch - 'A'] + count ) % 4; 
			}
		}
		
		public int getHashValue()
		{
			if (this.hashValue != -1)
			{
				return this.hashValue;
			}
			
			int result = 0;
			
			for (int clock : this.clocks)
			{
				result = result * 4 + clock;
			}
			
			this.hashValue = result;
			
			return result;
		}
		
		
		public boolean isFinished()
		{			
			return this.getHashValue() == 0;
		}
		
		
		@Override
		public Object clone() throws CloneNotSupportedException
		{
			Status status = null;
			
			try
			{
				status = (Status) super.clone();
			}
			catch (CloneNotSupportedException ex)
			{
				ex.printStackTrace();
			}
			
			return status;
		}
		
	}
}
