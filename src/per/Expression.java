package per;

import java.util.ArrayList;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 运算式类
 * @author dusk
 *
 */
public class Expression {
	
		StrPro strPro=new StrPro();
		Calculate calculate=new Calculate(); 
		Random random=new Random();
		ProperFraction proper=new ProperFraction();
		DoFile doFile=new DoFile();
		
		/**
		 * 生成运算结果非负，非小数的运算式
		 * @param num 题目数量
		 */
		public void generateExpressionA(int num){
			char[] operator=new char[]{'+','-','*','/'};
			ArrayList<String> expression=new ArrayList<String>();
			for(int i=0;i<num;i++){
				int n=random.nextInt(3)+3; //3-5个运算符
				int[] number=new int[n+1];
				for(int j=0;j<=n;j++){
					number[j]=random.nextInt(100)+1; //4-5个数字
				} 
				String ex=new String();
				for(int j=0;j<n;j++){
					int s=random.nextInt(4);//随机选择某个运算符
					ex+=String.valueOf(number[j])+String.valueOf(operator[s]);///5+4/
				}
				ex+=String.valueOf(number[n]);
				float result=calculate.calrp(calculate.getrp(ex));
				if(decideNegDiv(result)){
					expression.add(ex+="="+(int)result);
				}else{
					i--;
				}
			}
			doFile.WriteToFile("ArithmeticExpression.txt",expression);				
		}
		
		/**
		 * 生成带括号运算结果非负，非小数的运算式
		 * @param num
		 */
		public void generateExpressionB(int num){
			char[] operator=new char[]{'+','-','*','/'};
			ArrayList<String> expression=new ArrayList<String>();
			for(int i=0;i<num;i++){
				int n=random.nextInt(3)+1; //3-5个运算符
				int[] number=new int[n+1];
				for(int j=0;j<=n;j++){
					number[j]=random.nextInt(100)+1; //4-5个数字
				} 
				String ex=new String();
				int c=random.nextInt(3);
				for(int j=0;j<n;j++){
					switch(c){
					case 0:
						if(j==0){//(6-
							ex+='('+String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
						if(j==1){
							ex+=String.valueOf(number[j])+')'+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
						else{
							ex+=String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
					case 1:
						if(j==1){//3-(4-
							ex+='('+String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
						if(j==2){
							ex+=String.valueOf(number[j])+')'+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
						else{
							ex+=String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
					case 2:
						if(j==2){//5-6-(7- 
							ex+='('+String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
						if(j==3){//5-6-(7-5)-
							ex+=String.valueOf(number[j])+')'+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
						else{
							ex+=String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
							break;
						}
					}
					ex+=String.valueOf(number[j])+String.valueOf(operator[random.nextInt(4)]);///5+4/
				}
				ex+=String.valueOf(number[n]);
				if(ex.indexOf('(')!=-1){
					if(ex.indexOf(')')==-1){
						ex+=")";
					}
				}
				float result=calculate.calrp(calculate.getrp(ex));
				if(decideNegDiv(result)){
					expression.add(ex+="="+(int)result);
				}else{
					i--;
				}
			}
			doFile.WriteToFile("ArithmeticExpression.txt",expression);
		}
		
		/**
		 * 生成具有真分数运算式数的非负运算式
		 * @param num
		 */
		public void generateExpressionC(int num){
			char[] operator=new char[]{'+','-'};
			ArrayList<String> expression=new ArrayList<String>();
			for(int i=0;i<num;i++){
				int n=random.nextInt(3)+2; //3-5个运算符
				//ArrayList<ProperFraction> proList=new ArrayList<ProperFraction>();
				ProperFraction[] proList=new ProperFraction[n+1];
				for(int j=0;j<=n;j++){
					proList[j]=new ProperFraction();
				}
				char[] symbol=new char[n];
				String ex=new String();
				for(int j=0;j<n;j++){
					char sm=operator[random.nextInt(2)];
					ex+=proList[j].toString()+sm;
					proList[0]=proper.calProperFraction(proList[j], proList[j+1],sm);
				}
				ex+=proList[n]+"="+proList[0].toString();
				if(proList[0].getNuma()/proList[0].getNumb()<0){
					i--;
				}
				else{
					expression.add(ex);
				}
			}
			doFile.WriteToFile("ArithmeticExpression.txt",expression);			
		}
		
		private boolean decideNegDiv(float data){
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
