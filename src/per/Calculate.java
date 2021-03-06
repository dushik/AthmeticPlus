package per;

import java.util.Stack;
/**
 * 后缀表达式计算类
 * @author dusk
 *
 */
public class Calculate {
	  
     Stack<Character> op = new Stack<>();  
  
    public Float getv(char op, Float f1, Float f2){  
        if(op == '+') return f2 + f1;  
        else if(op == '-') return f2 - f1;  
        else if(op  == '*') return f2 * f1;  
        else if(op == '/') return f2 / f1;  
        else return Float.valueOf(-0);  
    }  
  
    public float calrp(String rp){  
        Stack<Float> v = new Stack<>();  
        char[] arr = rp.toCharArray();  
        int len = arr.length;  
        for(int i = 0; i < len; i++){  
            Character ch = arr[i];  
            if(ch >= '0' && ch <= '9') v.push(Float.valueOf(ch - '0'));  
            else v.push(getv(ch, v.pop(), v.pop()));  
        }  
        return v.pop();  
    }  
  
    /**
     * 
     * @param s
     * @return
     */
    public String getrp(String s){  
         char[] arr = s.toCharArray();  
         int len = arr.length;  
         String out = "";  
         for(int i = 0; i < len; i++){  
             char ch = arr[i];  
             if(ch == ' ') continue;  
             if(ch >= '0' && ch <= '9') {  
                 out+=ch;  
                 continue;  
             }  
             if(ch == '(') op.push(ch);  
             if(ch == '+' || ch == '-'){  
                 while(!op.empty() && (op.peek() != '('))   
                     out+=op.pop();  
                 op.push(ch);  
                 continue;  
             }  
             if(ch == '*' || ch == '/'){  
                 while(!op.empty() && (op.peek() == '*' || op.peek() == '/'))   
                     out+=op.pop();  
                 op.push(ch);  
                 continue;  
             }  
             if(ch == ')'){  
                 while(!op.empty() && op.peek() != '(')   
                     out += op.pop();  
                 op.pop();  
                 continue;  
             }  
         }  
         while(!op.empty()) out += op.pop();  
         return out;  
    }  
}
