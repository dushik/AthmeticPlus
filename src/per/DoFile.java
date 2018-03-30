package per;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * �ļ�����������
 *
 */
public class DoFile {
	/**
	 * ���ļ�����
	 * @param path
	 * @return
	 */
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
//	/**
//	 * 
//	 * @param path
//	 * @param content
//	 */
//	public void WriteToFile(String path,ArrayList<Integer> content)
//	{
//		try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
//            for(Integer con:content){
//                bw.write(con);  
//                bw.newLine();
//            }
//            bw.close();
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//	}
	
    /** 
     * ׷���ļ���ʹ��FileWriter 
     */  
    public void appendFile(String path, ArrayList<Integer> content) {  
        try {  
            FileWriter writer = new FileWriter(path, true);
            //writer.write("/r/n");  
            for(Integer st:content){
                writer.write(st);
                writer.write("-");
            }
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
