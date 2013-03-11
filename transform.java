


import java.io.*;

public class transform {
	
	public final static String FOLDER_PATH = "";
	
	private int size = 0;
	
	private Board startBoard = new Board();
	private Board targetBoard = new Board();
	
	public void run()
	{
		this.readIn(transform.FOLDER_PATH + "transform.in");
		
		this.output(this.convert(), transform.FOLDER_PATH + "transform.out");
	}
	
	private int convert()
	{		
		Board tmpBoard = new Board();
		
		tmpBoard.board = this.startBoard.board;
		tmpBoard.size = this.startBoard.size;
		
	//	this.startBoard.print();
		
		for (int i = 1; i <= 3; i++)
		{
			tmpBoard.turnRight();
			
			if (this.hitTarget(tmpBoard))
			{
				return i;
			}
		}
		
		tmpBoard.board = this.startBoard.board;
		
	//	tmpBoard.print();
		
		tmpBoard.reflection();
		
		if (this.hitTarget(tmpBoard))
		{
			return 4;
		}
		
//		tmpBoard.print();
		
		for (int i = 1; i <= 3; i++)
		{
			tmpBoard.turnRight();
			
			if (this.hitTarget(tmpBoard))
			{
				return 5;
			}
		}
		
		if (this.hitTarget(this.startBoard))
		{
			return 6;
		}
		
		return 7;
	}
	
	private boolean hitTarget(Board board)
	{
		for (int i = 0; i < this.size; i ++)
		{
			for (int j = 0; j < this.size; j ++)
			{
				if (board.board[i][j] != this.targetBoard.board[i][j])
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void output(int result, String filePath)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			printWriter.write(Integer.toString(result) + '\n');
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
		
	}
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			this.size = Integer.parseInt(bufferedReader.readLine());
			
			for (int i = 0; i < this.size; i++)
			{
				this.startBoard.addRow(bufferedReader.readLine());
			}
						
			for (int i = 0; i < this.size; i++)
			{
				this.targetBoard.addRow(bufferedReader.readLine());
			}
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		transform data = new transform();
		
		data.run();
	}
	
	public class Board
	{
		private final static int MAX_SIZE = 10 + 1;
		
		private char[][] board = new char[MAX_SIZE][MAX_SIZE];
		
		private int size = 0;//there is no row at the beginning
		
		public void addRow(String data)
		{			
			this.board[this.size] = data.toCharArray();
			
			this.size++;
		}
		
		public void turnRight()
		{
			char[][] tmpBoard = new char[MAX_SIZE][MAX_SIZE];
			
			for (int i = 0; i < this.size; i++)
			{
				for (int j = 0; j < this.size; j++)
				{
					tmpBoard[i][j] = this.board[this.size - 1 - j][i];
				}
			}
			
			this.board = tmpBoard;
		}
		
		public void reflection()
		{
			char[][] tmpBoard = new char[MAX_SIZE][MAX_SIZE];
			
			for (int i = 0; i < this.size; i++)
			{
				for (int j = 0; j < this.size; j++)
				{
					tmpBoard[i][j] = this.board[i][this.size - 1 - j];
				}
			}
			
			this.board = tmpBoard;
		}
		
		public void print()
		{
			for (int i = 0; i < this.size; i ++)
			{
				for (int j = 0; j < this.size; j++)
				{
					System.out.print(this.board[i][j]);
					System.out.print(' ');
				}
				
				System.out.print('\n');
			}
		}
	}
}
