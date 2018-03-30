package per;

import java.util.ArrayList;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Generate {
	public void generate(){
		int num=5;
		//�жϲ����Ϸ��ԡ�����
		if(judge(num)){
			char[] operator=new char[]{'+','-','*','/'};
			Random random=new Random();
			ArrayList<String> expression=new ArrayList<String>();
			for(int i=0;i<num;i++){
				int n=random.nextInt(3)+3; //3-5�������
				int[] number=new int[n+1]; 
				String ex=new String();
				for(int j=0;j<=n;j++){
					number[j]=random.nextInt(100)+1; //4-5������
				}
				for(int j=0;j<n;j++){
					int s=random.nextInt(4);//���ѡ��ĳ�������
					ex+=String.valueOf(number[j])+String.valueOf(operator[s]);///5+4+6+9
					if(s==3){number[j+1]=decide(number[j],number[j+1]);}
				}
				ex+=String.valueOf(number[n]);
				expression.add(ex);
			}
			WriteToFile write=new WriteToFile(
					"ArithmeticExpression.txt",calculate(expression));
						
		}
		else{
			System.out.println("�Ƿ����룡");
		}
	}
	/**
	 * �漴ȡx,yΪ1-100֮�䣬x��������y��yֵ
	 * @param x
	 * @param y
	 * @return
	 */
	private static int decide(int x,int y){
		Random random=new Random();
		if(x%y!=0){
			y=random.nextInt(100)+1;
			return decide(x,y);
		}
		else{
			return y; 
		}
	}
	
	/**
	 * ����ÿ����ʽ�Ľ��������������ʽ
	 * @param arrayList 
	 * @return arrayList
	 */
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");  
	private static ArrayList<String> calculate(ArrayList<String> arrayList){
		ArrayList<String> ArithExpress=new ArrayList<String>();
		for(String ax:arrayList){
			try {
				ax=ax+"="+jse.eval(ax);
				System.out.println(ax);
				ArithExpress.add(ax);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ArithExpress;
	}
	
	private static boolean judge(int data){
		if(data>=0){
			int temp=(int)data;
			if(temp==data)
				return true;
			else
				return false;
		}
		else{
			return false;
		}
	}
}
