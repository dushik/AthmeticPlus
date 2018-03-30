package per;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * –¥»ÎÃ‚ø‚¿‡
 * 
 */
public class WriteToFile {

	public WriteToFile(String path,ArrayList<String> content)
	{
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            //bw.write("201571030106");  
            //bw.newLine();
            for(String con:content){
                bw.write(con);  
                bw.newLine();
            }
            bw.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	public ArrayList<String> ReadFromFile(String path)
	{
		ArrayList<String> tempList=new ArrayList<String>();
		try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
        	while((line=br.readLine())!=null){
        		tempList.add(line);
        	}
        	br.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		return tempList;
	}
}
