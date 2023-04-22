package controller;
import java.io.IOException;
import java.util.ArrayList;
import engine.ReadingCSVFile;
public  class Start {

	public static void main(String [] args) throws IOException
	{
		ArrayList<String[]> arr = ReadingCSVFile.readFile("Distances.csv");
		for (String[] it : arr)
		{
			for (String x : it)
			{
				System.out.print(x+' ');
			}
			System.out.println();
		}
	}
}
