/*
ID : skywind5
LANG : JAVA
TASK : calfflac
*/

import java.io.*;

public class calfflac {
	
	private final String FOLDER_PATH = "";
	
	private final int MAX_SIZE = 20000 + 1;
	
	private char[] originalText = new char[this.MAX_SIZE];
	
	private Node[] data = new Node[this.MAX_SIZE];
	
	private int dataSize = 0;
	
	private Answer answer = new Answer();
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "calfflac.in");
		
		for (int i = 0; i < this.dataSize; i++)
		{
			this.search(i, i);
			
			if (i > 0)
			{
				if (this.data[i - 1].getValue() == this.data[i].getValue())
				{
					this.search(i - 1, i);
				}
			}
		}
		
		this.output(this.FOLDER_PATH + "calfflac.out");
	}
	
	private void search(int startIndex, int endIndex)
	{
		while (this.data[startIndex].getValue() == this.data[endIndex].getValue())
		{
			startIndex --; endIndex ++;
			
			if (startIndex < 0)
			{
				break;
			}
			
			if (endIndex >= this.dataSize)
			{
				break;
			}
			
			if (this.data[startIndex].getValue() != this.data[endIndex].getValue())
			{
				break;
			}
		}
		
		startIndex++; endIndex --;
		
		if (endIndex - startIndex + 1 > this.answer.getValue())
		{
			//updates answer
			this.answer.setValue(endIndex - startIndex + 1);
			
			this.answer.setStartPosition(this.data[startIndex].getOriginalPosition());
			
			this.answer.setEndPosition(this.data[endIndex].getOriginalPosition());
		}
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader( new FileReader(filePath) );
			
			int tmp = 0;
			while (bufferedReader.ready())
			{
				this.originalText[tmp] = (char) bufferedReader.read();
				
				if (this.isLetter(this.originalText[tmp]))
				{
					Node tmpNode = new Node();
					
					tmpNode.setOriginalPosition(tmp);
					tmpNode.setValue(this.originalText[tmp]);
					
					this.addData(tmpNode);
				}
				
				tmp++;
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
			PrintWriter printWriter = new PrintWriter(new BufferedWriter( new FileWriter(filePath) ));
			
			printWriter.print(Integer.toString(this.answer.getValue()) + '\n');
			
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = this.answer.getStartPosition(); i <= this.answer.getEndPosition(); i++)
			{
				stringBuilder.append(this.originalText[i]);
			}
			
			printWriter.print(stringBuilder.toString() + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage());
		}
	}
	
	private void addData(Node node)
	{
		this.data[this.dataSize] = node;
		
		this.dataSize++;
	}
	
	private boolean isLetter(char data)
	{
		if (data >= 'a' && data <= 'z')
		{
			return true;
		}
		
		if (data >= 'A' && data <= 'Z')
		{
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args)
	{
		calfflac model = new calfflac();
		
		model.run();
	}
	
	public class Node
	{
		private int originalPosition = 0;
		
		private char value= ' ';
		
		public int getOriginalPosition()
		{
			return this.originalPosition;
		}
		
		public void setOriginalPosition(int value)
		{
			this.originalPosition = value;
		}
		
		public char getValue()
		{
			return this.value;
		}
		
		public void setValue(char value)
		{
			if (value >= 'A' && value <= 'Z')
			{
				this.value = (char) (value - 'A' + 'a');
			}
			else
			{
				this.value = value;
			}
		}
	}
	
	public class Answer
	{
		private int value = 0;
		
		private int startPosition = 0;
		
		private int endPostition = 0;
		
		public int getValue()
		{
			return this.value;
		}
		
		public void setValue(int value)
		{
			this.value = value;
		}
		
		public int getStartPosition()
		{
			return this.startPosition;
		}
		
		public void setStartPosition(int value)
		{
			this.startPosition = value;
		}
		
		public int getEndPosition()
		{
			return this.endPostition;
		}
		
		public void setEndPosition(int value)
		{
			this.endPostition = value;
		}
	}
	
}
