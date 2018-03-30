package per;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
/**
 * �ļ�����������
 *
 */
public class DoFile {
	/**
	 * ���ж����ļ�����
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
	
    /** 
     * ��ȡ�ļ����N��  
     * ���ݻ��з��жϵ�ǰ�������� 
     * ʹ��ͳ�����жϵ�ǰ��ȡ��N�� 
     * @param path ���ļ�·��
     * @param numRead ��ȡ������ 
     * @return List<String> 
     */  
    public ArrayList<String> readLastNLine(String path, long numRead)  
    {  
    	File file=new File(path);
    	ArrayList<String> result = new ArrayList<String>();  
        long count = 0;  
        if (!file.exists() || file.isDirectory() || !file.canRead()){  
            return null;  
        }  
        RandomAccessFile fileRead = null;  
        try{  
            fileRead = new RandomAccessFile(file, "r");  
            long length = fileRead.length();  
            if (length == 0L){  
                return result;  
            }  
            else{  
                long pos = length - 1;  
                while (pos > 0){  
                    pos--;  
                    fileRead.seek(pos);  
                    if (fileRead.readByte() == '\n'){    
                        String line = fileRead.readLine();
                        result.add(line);   
                        count++;  
                        if (count == numRead){  
                            break;  
                        }  
                    }  
                }  
                if (pos == 0){  
                    fileRead.seek(0);  
                    result.add(fileRead.readLine());  
                }  
            }  
        }  
        catch (IOException e){  
            e.printStackTrace();  
        }  
        finally{  
            if (fileRead != null){  
                try{  
                    fileRead.close();  
                }  
                catch (Exception e){  
                }  
            }  
        }  
        return result;  
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
            for(Integer st:content){
                writer.write(String.valueOf(st));
                writer.write("-");
            }
            writer.write(System.getProperty("line.separator"));
            writer.flush();
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
