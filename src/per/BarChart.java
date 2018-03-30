package per;


import java.awt.Font;
 
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * ������״ͼ������
 *
 */
public class BarChart {
    ChartPanel frame1;
    static StrPro strPro=new StrPro();
    static DoFile doFile=new DoFile();
    public  BarChart(){
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                             "����������Խ��ͼ��", // ͼ�����
                            "������/��", // Ŀ¼�����ʾ��ǩ
                            "����", // ��ֵ�����ʾ��ǩ
                            dataset, // ���ݼ�
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                            false,          // �Ƿ����ɹ���
                            false           // �Ƿ�����URL����
                            );
         
        //�����￪ʼ
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
           
          //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������
           
         frame1=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame

    }
    private static CategoryDataset getDataSet() {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.addValue(100, "������", "��һ��");
//        dataset.addValue(100, "��ȷ��", "��һ��");
//        //dataset.addValue(100, "����", "��һ��");
//	   
//	   dataset.addValue(200, "������", "�ڶ���");
//	   dataset.addValue(200, "��ȷ��", "�ڶ���");
//	   //dataset.addValue(200, "����", "�ڶ���");
//	   
//	   dataset.addValue(300, "������", "������");
//	   dataset.addValue(300, "��ȷ��", "������");
//	   //dataset.addValue(300, "����", "������");
//	   
//	   dataset.addValue(400, "������", "������");
//	   dataset.addValue(400, "��ȷ��", "������");
//	   //dataset.addValue(400, "����", "������");
//	   
//	   dataset.addValue(500, "������", "������");
//	   dataset.addValue(500, "��ȷ��", "������");
//	   //dataset.addValue(500, "����", "������");
	   
	   ArrayList<String> score=new ArrayList<String>();
	   String[] data=new String[2];
	   score=doFile.readLastNLine("./static.txt",5);
	   Collections.reverse(score);
	   int count=1;
	   for(String st:score){
		   data=strPro.StrSplitScore(st);
		   dataset.addValue(Integer.parseInt(data[1]), "������", "��"+count+"��");
		   dataset.addValue(Integer.parseInt(data[0]), "��ȷ��", "��"+count+"��");
		   count++;
	   }
       return dataset;
    }
	public ChartPanel getChartPanel(){
		return frame1;
	}
}