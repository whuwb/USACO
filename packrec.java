/*
ID : skywind5
LANG : JAVA
PROG : packrec
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class packrec {
	
	private final String FOLDER_PATH = "";
	
	private final int NUMBER_OF_RECTANGLES = 4;
	
	private Rectangle[] rectangles = new Rectangle[this.NUMBER_OF_RECTANGLES + 1];
	
	private Rectangle[] data = new Rectangle[this.NUMBER_OF_RECTANGLES + 1];
	
	private boolean[] usedRectangles = new boolean[this.NUMBER_OF_RECTANGLES + 1];
	
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	private int[] orders = new int[this.NUMBER_OF_RECTANGLES + 1];
	
	private int[] towards = new int[this.NUMBER_OF_RECTANGLES + 1];
	
	private int minSquare = 10000000;
	
	public void run()
	{
		this.readIn(FOLDER_PATH + "packrec.in");
		
		for (int i = 0; i < this.NUMBER_OF_RECTANGLES; i++)
		{
			this.usedRectangles[i] = false;
		}
		
		this.search(0);
		
		Collections.sort(this.answers);
		
		this.output(this.FOLDER_PATH + "packrec.out");
	}
	
	private void search(int depth)
	{
		for (int i = 0; i < this.NUMBER_OF_RECTANGLES; i++)
		{
			if (this.usedRectangles[i] == false)
			{
				this.orders[depth] = i;
				
				if (depth + 1 < this.NUMBER_OF_RECTANGLES)
				{
				
					this.usedRectangles[i] = true;
					
					this.search(depth + 1);
					
					this.usedRectangles[i] = false;
					
				}
				else
				{
					this.build(0);
				}
			}
		}
	}
	
	private void build(int depth)
	{
		for (int i = 0; i < 2; i++)
		{
			this.towards[depth] = i;
			
			if (depth + 1 < this.NUMBER_OF_RECTANGLES)
			{
				this.build(depth + 1);
			}
			else
			{
				this.check();
			}
		}
	}
	
	private void check()
	{
		this.createData();
			
		int width = 0, height = 0;
		for (int i = 0; i<this.NUMBER_OF_RECTANGLES; i++)
		{
			width += this.data[i].getWidth();
			
			height = Math.max(height, this.data[i].getHeight());
		}
		
		this.update(width, height);
		
		//2
		width = this.maxOfFour(w(0) + w(1) + w(2), w(3), 0, 0);
		
		height = this.maxOfFour(h(1), h(2), h(0), 0) + h(3);
		
		this.update(width, height);
		
		//3
		
		width = this.maxOfFour(w(0) + w(1), w(2), 0, 0) + w(3);
		height = Math.max(Math.max(h(0), h(1)) + h(2), h(3) );
		
		this.update(width, height);
		
		//4
		
		width = w(0) + w(1) + Math.max(w(2), w(3));
		height = this.maxOfFour(h(0), h(1), h(2)+h(3), 0);
		
		this.update(width, height);
		
		height = Math.max(h(0) + h(2), h(1) + h(3));
		
		if (h(2) >= h(1) + h(3))
		{
			width = this.maxOfFour(w(0), w(1) + w(2), w(2) + w(3), 0);
		}
		
		if ((h(2) > h(3)) && (h(2) < h(3) + h(1)))
		{
			width = this.maxOfFour(w(0) + w(1), w(1) + w(2), w(2) + w(3), 0);
		}
		
		if ((h(3) > h(2)) && (h(3) < h(2) + h(0)))
		{
			width = this.maxOfFour(w(0) + w(1), w(0) + w(3), w(2) + w(3), 0);
		}
		
		if (h(3) >= h(0) + h(3))
		{
			width = this.maxOfFour(w(1), w(1) + w(3), w(2) + w(3), 0);
		}		
	

		if (h(2) == h(3))
		{
			width = Math.max(w(2) + w(3), w(0) + w(1));
		}
		
		
		this.update(width, height);
	}
	
	private int w(int i)
	{
		return this.data[i].getWidth();
	}
	
	private int h(int i)
	{
		return this.data[i].getHeight();
	}
	
	private int maxOfFour(int a, int b, int c, int d)
	{
		int result = 0;
		
		result = Math.max(a, b);
		
		result = Math.max(result, c);
		
		result = Math.max(result, d);
		
		return result;
	}
	
	private void update(int width, int height)
	{
		if (width > height)
		{
			int tmp = width;
			width = height;
			height = tmp;
		}
					
		
		if (width * height > this.minSquare)
		{
			return ;
		}
		
		if (width * height < this.minSquare)
		{
			this.minSquare = width * height;
			
			this.answers.clear();
			
			this.answers.add(new Answer(width, height));
			
			return;
		}
		
		boolean isExist = false;
		for (Answer answer : this.answers)
		{
			if (answer.getRectangle().getWidth() == width && answer.getRectangle().getHeight() == height)
			{
				isExist = true;
				break;
			}
		}
		
		if (isExist == false)
		{
			this.answers.add(new Answer(width, height));
		}
	}
	
	private void createData()
	{
		for (int i=0; i<this.NUMBER_OF_RECTANGLES; i++)
		{
			this.data[i] = new Rectangle();
			
			if (this.towards[i] == 0)
			{
				this.data[i].setWidth(this.rectangles[this.orders[i]].getWidth());
				
				this.data[i].setHeight(this.rectangles[this.orders[i]].getHeight());
			}
			else
			{
				this.data[i].setHeight(this.rectangles[this.orders[i]].getWidth());
				
				this.data[i].setWidth(this.rectangles[this.orders[i]].getHeight());
			}
		}
	}
	
	private void output(String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			printWriter.print(Integer.toString(this.minSquare) + "\n");
			
			for (Answer answer : this.answers)
			{
				printWriter.print(Integer.toString(answer.getRectangle().getWidth()) + ' ' + 
									Integer.toString(answer.getRectangle().getHeight()) + '\n');
			}
			
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
			
			for (int i = 0; i < 4; i++)
			{
				StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				
				this.rectangles[i] = new Rectangle(Integer.parseInt(stringTokenizer.nextToken()), 
													Integer.parseInt(stringTokenizer.nextToken()) );
								
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
		packrec data = new packrec();
		
		data.run();
	}
	
	public class Rectangle
	{		
		private int width;
		private int height;
		
		public Rectangle()
		{
			
		}
		
		public Rectangle(int width, int height)
		{
			this.width = width;
			this.height = height;
		}
		
		public int getWidth()
		{
			return this.width;
		}
		
		public void setWidth(int value)
		{
			this.width = value;
		}
		
		public int getHeight()
		{
			return this.height;
		}
		
		public void setHeight(int value)
		{
			this.height = value;
		}
	}
	
	public class Answer implements Comparable<Answer>
	{
		private Rectangle rectangle = new Rectangle();
		
		public Answer()
		{
			
		}
		
		public Answer(int width, int height)
		{
			this.rectangle = new Rectangle(width, height);
		}
		
		public Rectangle getRectangle()
		{
			return this.rectangle;
		}
		
		public void setRectangle(Rectangle value)
		{
			this.rectangle = value;
		}
		
		public int compareTo(Answer otherAnswer)
		{
			return this.getRectangle().getWidth() - otherAnswer.getRectangle().getWidth();
		}
	}
}
