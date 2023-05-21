package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadingCSVFile {
  public static ArrayList<String[]> readFile(String path) throws IOException{
      String currentLine ;
      FileReader fileReader= new FileReader(path);
      BufferedReader br = new BufferedReader(fileReader);
      ArrayList<String[]> data = new ArrayList<String[]>();
      while ((currentLine = br.readLine()) != null) {
        System.out.println(currentLine);
        // Parsing the currentLine String
        String[] line = currentLine.split(",");
        data.add(line);
      }
      
      return data;
  }
}