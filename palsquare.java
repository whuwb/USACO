/*
ID: skywind5
LANG: JAVA
TASK: palsquare 
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class palsquare {
	
	private int baseNumber = 0;
	private final static String fileFolderPath = "";
	private final static int MaxNum = 300 * 300 + 1;
	
	private ArrayList<String> results = new ArrayList<String>();
	
	private void readIn(String filePath)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			String line = bufferedReader.readLine();
			this.baseNumber = Integer.parseInt( line);
			
			bufferedReader.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	private boolean isSquare(int data)
	{
		int tmp = (int) Math.sqrt(data);
		
		return (tmp * tmp == data);
	}
	
	private int convertToTenBase(String data)
	{
		int alpha = 1;
		
		int result = 0;
		
		char[] dataInArray = data.toCharArray();
		
		for (int i = dataInArray.length - 1; i >=0; i--)
		{
			result += this.convertCharToTenBasedNumber(dataInArray[i]) * alpha;
			
			alpha *= this.baseNumber;
		}
		
		return result;
	}
	
	private int convertCharToTenBasedNumber(char ch)
	{
		if (ch >= '0' && ch <= '9')
		{
			return ch - '0';
		}
		
		return ch - 'A' + 10;
	}
	
	private char convertNumberIntoChar(int number)
	{
		if (number < 10)
		{
			return (char)(number + '0');
		}
		
		return (char)(number - 10 + 'A');
	}
	
	
	private void generatePal(String pal)
	{
		
		for (int i = 0; i<this.baseNumber; i++)
		{
			char tmpChar = this.convertNumberIntoChar(i);
			String tmpValue = tmpChar + pal + tmpChar;
			
			int num = this.convertToTenBase(tmpValue);
			
			if (num < 0 || num > palsquare.MaxNum)
			{
				break;
			}
			
			if (num == 0)
			{
				if (this.convertToTenBase('1' + tmpValue) > palsquare.MaxNum + palsquare.MaxNum)
				{
					break;
				}
			}
			
			if (!tmpValue.startsWith("0") && this.isSquare(num))
			{
				this.results.add(tmpValue);
			}
			
			if (num < palsquare.MaxNum)
			{
				this.generatePal(tmpValue);
			}
		}
	}
	
	private String convertNumberIntoString(int num)
	{
		String result = "";
		
		while (num > 0)
		{
			int tmp = num % this.baseNumber;
			
			num /= this.baseNumber;
			
			result = this.convertNumberIntoChar(tmp) + result;
		}
		
		return result;
	}
	
	private void output(String filePath)
	{
		
		Collections.sort(this.results, new SortByString());
		
		try
		{
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			
			for (String result : this.results)
			{
				int num = this.convertToTenBase(result);
				
				num = (int) Math.sqrt(num);
				
				printWriter.print(this.convertNumberIntoString(num) + ' ' +  result + '\n');
			}
			
			printWriter.close();
		}
		catch (IOException ex)
		{
			System.out.print(ex.getMessage() + '\n');
		}
	}
	
	public void run()
	{
		this.readIn(palsquare.fileFolderPath + "palsquare.in");
		
		this.generatePal("");
		
		for (int i = 0; i<this.baseNumber; i++)
		{
			if (i > 0)
			{
				if (this.isSquare(i))
				{
					String tmp = "" + this.convertNumberIntoChar(i);
					
					this.results.add( tmp);
				}
			}
			
			String tmpString = "";
			tmpString += this.convertNumberIntoChar(i);
			
			this.generatePal(tmpString);
		}
		
		this.output(palsquare.fileFolderPath + "palsquare.out");
	}
	
	public static void main(String[] args)
	{
		palsquare pal = new palsquare();
		
		pal.run();
	}
	
	public class SortByString implements Comparator
	{
		public int compare(Object obj1, Object obj2)
		{
			char[] str1 = ((String) obj1).toCharArray();
			char[] str2 = ((String) obj2).toCharArray();
			
			if (str1.length > str2.length)
			{
				return 1;
			}
			else if (str1.length < str2.length)
			{
				return 0;
			}
			else
			{
				for (int i = 0; i < str1.length; i++)
				{
					if (str1[i] > str2[i])
					{
						return 1;
					}
					else if (str1[i] < str2[i])
					{
						return 0;
					}
				}
			}
			
			return 0;
		}
	}
	
}


