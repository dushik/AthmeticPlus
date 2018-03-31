package per;

import java.util.Random;
/**
 * 分数计算类
 * @author zczpeng https://blog.csdn.net/wfzczangpeng/article/details/7983731
 *
 */
public class ProperFraction {
    private int numa;
    private int numb;
    private static Random random = new Random();
    public ProperFraction(){
        this.numa = random.nextInt(3-1+1)+1;
        this.numb = random.nextInt(30-10+10)+10;
        int gcd = gcd(numa,numb);
         this.numa = numa / gcd;
        this.numb = numb / gcd;
    }

    @Override
    public String toString() {
        return this.numa+"/"+this.numb;
    }

    public int getNuma() {
        return numa;
    }

    public void setNuma(int numa) {
        this.numa = numa;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public static ProperFraction calProperFraction(ProperFraction a,
                                                   ProperFraction b, char symbol){
        ProperFraction properFraction = null;
        //计算分数加减乘除
        switch (symbol){
            case '+':properFraction = fracAdd(a.numa,a.numb,b.numa,b.numb);break;
            case '-':properFraction = fracSub(a.numa,a.numb,b.numa,b.numb);break;
            case '*':properFraction = fracMul(a.numa,a.numb,b.numa,b.numb);break;
            case '/':properFraction = fractDiv(a.numa,a.numb,b.numa,b.numb);break;
        }

        return properFraction;
    }
    
    static ProperFraction fracAdd(int first_numerator,int first_denominator,int second_numrator,int second_denominator){

        int denominator;
        int numerator;

        if(first_denominator==second_denominator)  //分母相同时加分子
        {
            denominator=first_denominator;
            numerator=first_numerator+second_numrator;
        }
        else  //否则同分比较分子
        {
            denominator=first_denominator*second_denominator;
            numerator=first_numerator*second_denominator+first_denominator*second_numrator;
        }
        int gcd = gcd(numerator,denominator);
        denominator = denominator / gcd;
        numerator = numerator / gcd;
        ProperFraction properFraction = new ProperFraction();
        properFraction.numa = numerator;
        properFraction.numb = denominator;
        return properFraction;
    }
    static ProperFraction fracSub(int first_numerator,int first_denominator,int second_numrator,int second_denominator){
        int denominator;
        int numerator;
        if(first_denominator==second_denominator)  //分母相同时加分子
        {
            denominator=first_denominator;
            numerator=first_numerator-second_numrator;
        }
        else  //否则同分比较分子
        {
            denominator=first_denominator*second_denominator;
            numerator=first_numerator*second_denominator-first_denominator*second_numrator;
        }
        int gcd = gcd(numerator,denominator);
        denominator = denominator / gcd;
        numerator = numerator / gcd;
        ProperFraction properFraction = new ProperFraction();
        properFraction.numa = numerator;
        properFraction.numb = denominator;
        return properFraction;
    }
    static ProperFraction fracMul(int first_numerator,int first_denominator,int second_numerator,int second_denominator){
        int denominator;
        int numerator;
        denominator=first_denominator*second_denominator;
        numerator=first_numerator*second_numerator;
        int gcd = gcd(numerator,denominator);
        denominator = denominator / gcd;
        numerator = numerator / gcd;
        ProperFraction properFraction = new ProperFraction();
        properFraction.numa = numerator;
        properFraction.numb = denominator;
        return properFraction;

    }
    static ProperFraction fractDiv(int first_numerator,int first_denominator,int second_numerator,int second_denominator){
        int denominator;
        int numerator;
        numerator = first_numerator*second_denominator;
        denominator = first_denominator*second_numerator;
        int gcd = gcd(numerator,denominator);
        denominator = denominator / gcd;
        numerator = numerator / gcd;
        ProperFraction properFraction = new ProperFraction();
        properFraction.numa = numerator;
        properFraction.numb = denominator;
        return properFraction;

    }
    static int gcd(int x,int y){
        int r;
        while( y!= 0)
        {
            r = x%y;
            x = y;
            y = r;
        }
        return x;

    }
}
