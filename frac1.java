/*
ID : skywind5
LANG : JAVA
TASK : frac1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class frac1 {

	private final String FOLDER_PATH = "";
	
	private int upperBound = 0;//n in the problem
	
	private ArrayList<Node> nodes = new ArrayList<Node>();
	
	public void run()
	{
		this.readIn(this.FOLDER_PATH + "frac1.in");
		
		this.calc();
		
		Collections.sort(this.nodes);
		
		this.output(this.FOLDER_PATH + "frac1.out");
	}
	
	private void calc()
	{
		for (int denominator = 1; denominator <= this.upperBound; denominator ++)
		{
			for (int numerator = 1; numerator < denominator; numerator ++)
			{
				Node node = new Node(numerator, denominator);
				
				this.check(node);
			}
		}
	}
	
	private void check(Node node)
	{
		int tmp = this.getMaxFactor(node.numerator, node.denominator);
		
		if (tmp == 1)
		{
			this.nodes.add(node);
		}
	}
	
	private int getMaxFactor(int first, int second)
	{
		int tmp = 0;
		
		while (second != 0)
		{
			tmp = first % second;
			
			first = second;
			
			second = tmp;
		}
		
		return first;
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.upperBound = Integer.parseInt(bufferedReader.readLine());
			
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
			
			printWriter.print("0/1\n");
			
			for (Node node : nodes)
			{
				printWriter.print(node.toString() + '\n');
			}
			
			printWriter.print("1/1\n");
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		frac1 data = new frac1();
		
		data.run();
	}
	
	public class Node implements Comparable<Node>
	{
		private int numerator = 0;
		
		private int denominator = 0;
		
		private double value = -1;
		
		public Node(int numerator, int denominator)
		{
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		public int getNumerator()
		{
			return this.numerator;
		}
		
		public void setNumerator(int value)
		{
			this.numerator = value;
		}
		
		public int getDenominator()
		{
			return this.denominator;
		}
		
		public void setDenominator(int value)
		{
			this.denominator = value;
		}
		
		public double getValue()
		{
			if (this.value == -1)
			{
				this.setValue();
			}
			
			return this.value;
		}
		
		public void setValue()
		{
			this.value = (this.getNumerator() + 0.0) / (this.getDenominator());
		}
		
		public int compareTo(Node otherNode)
		{
			if (this.getValue() < otherNode.getValue())
			{
				return -1;
			}
			
			if (this.getValue() > otherNode.getValue())
			{
				return 1;
			}
			
			return 0;
		}
		
		@Override
		public String toString()
		{
			return Integer.toString(this.getNumerator()) + '/' 
					+ Integer.toString(this.getDenominator()) ;
		}
	}
	
}
