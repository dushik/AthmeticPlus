package per;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartPanel;


/**
 * 
 * @author dusk
 *
 */
public class Main{
	private static ArrayList<String> arithExpress =new ArrayList<String>();
	private static HashMap<Integer, String> resHm=new HashMap<Integer, String>();
	private static HashMap<Integer, String> ansHm=new HashMap<Integer, String>();
	private static int index=0;
	private static boolean doFlag=false;
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				CalFrame frame=new CalFrame();
				DoFile doFile=new DoFile();
				StrPro strPro=new StrPro();
				Expression expression=new Expression();
				arithExpress=doFile.ReadFromFile("./ArithmeticExpression.txt");//默认从当前目录读取题库
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setTitle("小学四则运算测试器");
				
				JMenuBar menuBar = new JMenuBar();
				
		        JMenu fileMenu = new JMenu("系统");
		        JMenu viewMenu = new JMenu("题型选择");
		        JMenu editMenu = new JMenu("编辑");
		        JMenu aboutMenu = new JMenu("关于");
		        
		        menuBar.add(fileMenu);
		        menuBar.add(viewMenu);
		        menuBar.add(editMenu);
		        menuBar.add(aboutMenu);
		        
		        JPanel panel = new JPanel(new BorderLayout());
		        
		        JPanel centerPanel = new JPanel(new BorderLayout());
		        panel.add(centerPanel,BorderLayout.CENTER);
		        
		        // 创建文本区域组件
		        JTextArea textArea = new JTextArea();
		        textArea.setLineWrap(true);                         
		        textArea.setFont(new Font(null, Font.PLAIN, 18));   
		        JScrollPane scrollPane = new JScrollPane(
		                textArea,
		                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		        );
		        
		        JPanel rightPanel = new JPanel();
		        rightPanel.setLayout(null);
		        
		        JLabel arithLabel=new JLabel("点击开始答题按钮,开始答题");
		        arithLabel.setBounds(30,80,250,50);
		        arithLabel.setFont(new Font(null, Font.PLAIN, 20));
		        JLabel helloLabel=new JLabel("请仔细查看算式后得出计算结果!");
		        helloLabel.setBounds(20,10,500,50);
		        helloLabel.setFont(new Font(null, Font.PLAIN, 20));
		     
		        JTextField textField = new JTextField(8);
		        textField.setBounds(330, 80, 200, 40);
		        textField.setFont(new Font(null, Font.PLAIN, 20));
		        JButton btn = new JButton("确定");
		        btn.setFont(new Font(null, Font.PLAIN, 20));
		        btn.setBounds(340, 160, 180, 40);
		        
		        rightPanel.add(helloLabel);
		        rightPanel.add(arithLabel);
		        rightPanel.add(textField);
		        rightPanel.add(btn);
		        
		        JSplitPane splitPane = new JSplitPane();
		        splitPane.setLeftComponent(scrollPane);
		        splitPane.setRightComponent(rightPanel);
		        splitPane.setOneTouchExpandable(true);
		        splitPane.setContinuousLayout(true);
		        splitPane.setDividerLocation(300);
		        
		        centerPanel.add(splitPane,BorderLayout.CENTER);
		        
		        // 创建文本区域, 用于显示相关信息
		        final JTextArea msgTextArea = new JTextArea(10, 30);
		        msgTextArea.setLineWrap(true);
		        panel.add(msgTextArea);
		        
		        //MenuBar组件部分
		        JMenuItem newMenuItem = new JMenuItem("最近测试成绩分析");
		        JMenuItem openMenuItem = new JMenuItem("打开题库");
		        JMenuItem generateMenuItem = new JMenuItem("生成题库");
		        JMenuItem exitMenuItem = new JMenuItem("退出");
		        
		        fileMenu.add(openMenuItem);
		        fileMenu.add(generateMenuItem);
		        fileMenu.addSeparator();       // 添加一条分割线
		        fileMenu.add(newMenuItem);
		        fileMenu.add(exitMenuItem);
		        
		        JMenuItem copyMenuItem = new JMenuItem("复制");
		        JMenuItem pasteMenuItem = new JMenuItem("粘贴");
		        // 子菜单添加到一级菜单
		        editMenu.add(copyMenuItem);
		        editMenu.add(pasteMenuItem);
		        
		        JMenuItem aboutMenuItem = new JMenuItem("技术支持");
		        aboutMenu.add(aboutMenuItem);

		        /*
		         * 创建 "视图" 一级菜单的子菜单
		         */
		        final JRadioButtonMenuItem radioButtonMenuItem01 = new JRadioButtonMenuItem("百以内整数算式");
		        final JRadioButtonMenuItem radioButtonMenuItem02 = new JRadioButtonMenuItem("带括号百以内整数算式");
		        final JRadioButtonMenuItem radioButtonMenuItem03 = new JRadioButtonMenuItem("真分数百以内整数算式");
		        // 子菜单添加到一级菜单
		        //viewMenu.add(openQuestion);
		        viewMenu.addSeparator();                // 添加一个分割线
		        viewMenu.add(radioButtonMenuItem01);
		        viewMenu.add(radioButtonMenuItem02);
		        viewMenu.add(radioButtonMenuItem03);
		        // 其中两个 单选按钮子菜单，要实现单选按钮的效果，需要将它们放到一个按钮组中
		        ButtonGroup btnGroup = new ButtonGroup();
		        btnGroup.add(radioButtonMenuItem01);
		        btnGroup.add(radioButtonMenuItem02);
		        btnGroup.add(radioButtonMenuItem03);
		        // 默认第一个单选按钮子菜单选中
		        radioButtonMenuItem01.setSelected(true);
		        
		        frame.setJMenuBar(menuBar);

		        JToolBar toolBar = new JToolBar("工具栏");

		        JButton beginBtn = new JButton("开始答题");
		        JButton previousBtn = new JButton("上一题");
		        JButton pauseBtn = new JButton("下一题");
		        JButton upBtn = new JButton("提交结果");
		        
		        toolBar.add(beginBtn);
		        toolBar.add(upBtn);
		        toolBar.add(previousBtn);
		        toolBar.add(pauseBtn);
		        // 添加 工具栏 到 内容面板 的 顶部
		        panel.add(toolBar, BorderLayout.PAGE_START);
		        panel.add(centerPanel,BorderLayout.CENTER);
		        frame.setContentPane(panel);

		        openMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	textArea.append("打开题库\n");
		            	showFileOpenDialog(frame);
		            }
		        });
		        generateMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	char flag='a';//默认普通算式
		                if(radioButtonMenuItem01.isSelected()){flag='a';}
		                if(radioButtonMenuItem02.isSelected()){flag='b';}
		                if(radioButtonMenuItem03.isSelected()){flag='c';}
	                    String inputContent = JOptionPane.showInputDialog(
	                            frame,
	                            "输入题目的数量:"
	                    );
	                    if(inputContent==null||inputContent.equals("")){
	                    	textArea.append("取消生成\n");
	                    }
	                    else{
	                    	if(judge(Float.parseFloat(inputContent))){
		                    	switch(flag){
		                    	case 'a':
		                    		expression.generateExpressionA(Integer.parseInt(inputContent));
		                    		break;
		                    	case 'b':
		                    		expression.generateExpressionB(Integer.parseInt(inputContent));
		                    		break;
		                    	case 'c':
		                    		expression.generateExpressionC(Integer.parseInt(inputContent));
		                    		break;
		                    	}
				                arithExpress=doFile.ReadFromFile("./ArithmeticExpression.txt");
			                    JOptionPane.showMessageDialog(frame,"新的题库已生成,可以开始答题","消息标题",JOptionPane.INFORMATION_MESSAGE);
	                    	}else{
	                    		JOptionPane.showMessageDialog(frame,"输入非法参数","消息标题",JOptionPane.WARNING_MESSAGE);
	                    	}
	                    }
	                }
		        });
		        newMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                textArea.append("上一次测试成绩分析  被点击\n");
		                StaticChart();
		            }
		        });
		        exitMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                int result = JOptionPane.showConfirmDialog(
		                        frame,
		                        "确认退出？",
		                        "提示",
		                        JOptionPane.YES_NO_CANCEL_OPTION
		                );
		                if(result==0){
		                	System.exit(0); 
		                }
		            }
		        });
		        aboutMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                JOptionPane.showMessageDialog(
		                        frame,
		                        "Copyright ©  杜世康，李玉莹",
		                        "消息标题",
		                        JOptionPane.INFORMATION_MESSAGE
		                );
		            }
		        });

		        radioButtonMenuItem01.addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent e) {
		                //System.out.println("单选按钮01 是否被选中: " + radioButtonMenuItem01.isSelected());
		            }
		        });
		        
		        beginBtn.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	textArea.append("开始答题\n");
		            	resHm.clear();ansHm.clear();
		            	doFlag=true;
		            	if(arithExpress.isEmpty()){
		                    JOptionPane.showMessageDialog(
		                            frame,
		                            "没有读取到默认题库，请先生成或者打开本地题库",
		                            "消息标题",
		                            JOptionPane.WARNING_MESSAGE
		                    );
		            	}
		            	else{
		            		index=0;
		            		arithLabel.setText(strPro.StrSplitBefore(arithExpress.get(index)));   
		            	}
		            }
		        });
		        
		        previousBtn.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                if(!doFlag){
		                    JOptionPane.showMessageDialog(frame,"请点击开始答题按钮，进行测试！","消息标题",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
			            	textArea.append("上一题\n");
			                textArea.append("算式："+strPro.StrSplitBefore(arithExpress.get(index))+"="+textField.getText()+"\n");
			                if(!textField.getText().equals("")||textField.getText()!=null){
				                ansHm.put(index, strPro.StrSplitAfter(arithExpress.get(index)));
				                resHm.put(index, textField.getText());
			                }
			                index-=1;
			                if(index<0){
			                    JOptionPane.showMessageDialog(
			                            frame,
			                            "已经是第一题了！",
			                            "消息标题",
			                            JOptionPane.WARNING_MESSAGE
			                    );
			                    index+=1;
			                }else{
			            		textField.setText("");
				            	arithLabel.setText(strPro.StrSplitBefore(arithExpress.get(index)));
			                }
		                }
		            }
		        });
		        
		        pauseBtn.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                if(!doFlag){
		                    JOptionPane.showMessageDialog(frame,"请点击开始答题按钮，进行测试！","消息标题",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
			                textArea.append("下一题\n");
			                textArea.append("算式："+strPro.StrSplitBefore(arithExpress.get(index))+"="+textField.getText()+"\n");
			                if(!textField.getText().equals("")||textField.getText()!=null){
				                ansHm.put(index, strPro.StrSplitAfter(arithExpress.get(index)));
				                resHm.put(index, textField.getText());
			                }
		            		index+=1;
			                if(index<arithExpress.size()){
			            		textField.setText("");
				            	arithLabel.setText(strPro.StrSplitBefore(arithExpress.get(index)));
			                }else{
			                	index-=1;
			                    JOptionPane.showMessageDialog(
			                            frame,
			                            "已经是最后一题了，回答完成请提交！",
			                            "消息标题",
			                            JOptionPane.WARNING_MESSAGE
			                    );
			                }
		                }
		            }
		        });
		        
		        //确定，下一题
		        btn.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                if(!doFlag){
		                    JOptionPane.showMessageDialog(frame,"请点击开始答题按钮，进行测试！","消息标题",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
			                textArea.append("算式："+strPro.StrSplitBefore(arithExpress.get(index))+"="+textField.getText()+"\n");
			                if(!textField.getText().equals("")||textField.getText()!=null){
				                ansHm.put(index, strPro.StrSplitAfter(arithExpress.get(index)));
				                resHm.put(index, textField.getText());
			                }
			                index+=1;
			            	if(index<arithExpress.size()){
			            		textField.setText("");
				            	arithLabel.setText(strPro.StrSplitBefore(arithExpress.get(index)));
			            	}
			            	else{		                	
			            		index-=1;
			                    JOptionPane.showMessageDialog(
			                            frame,
			                            "已经是最后一题了，回答完成请提交！",
			                            "消息标题",
			                            JOptionPane.WARNING_MESSAGE
			                    );
			            	}
		                }
		            }
		        });
		        
		        upBtn.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	if(!resHm.isEmpty()||!ansHm.isEmpty()){
			                textArea.append("已提交答案\n");
			                textField.setText("");
			                arithLabel.setText("点击开始答题按钮,开始答题");
		            		doFlag=false;
			                index=0;
			                ArrayList<Integer> temp=new ArrayList<Integer>();
			                temp.add(critical(resHm,ansHm));
			                temp.add(arithExpress.size());
			                doFile.appendFile("./static.txt",temp);
			                resHm.clear();ansHm.clear();
			                //cframe.dispose();
		            	}
		            	else{
		                    JOptionPane.showMessageDialog(frame,"你还没有答题，回答完成后请提交！","消息标题",JOptionPane.WARNING_MESSAGE);
		            	}
		            }
		        });
		        textArea.append("你好，小朋友!\n点击开始答题进行测试\n");
//		        for(String st:arithExpress){
//		        	textArea.append(st+"\n");
//		        }
                try {    
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
                } catch (Exception ex) {    
                    ex.printStackTrace();    
                }    
             
                CountDown cframe = new CountDown("计时器");    
                cframe.pack();    
                cframe.setVisible(true); 
			}
		});
	}
	private static void showFileOpenDialog(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt","docx"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt","docx"));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {// 如果点击了"确定", 则获取选择的文件路径
            File file = fileChooser.getSelectedFile();// 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
            // File[] files = fileChooser.getSelectedFiles();  file.getAbsolutePath()
            try{
            	BufferedReader br=new BufferedReader(new FileReader(file));
            	String line;
            	while((line=br.readLine())!=null){
            		arithExpress.add(line);
            	}
            	br.close();
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
    }
	
	/**
	 * 通过标准答案和测试结果，计算正确的题数
	 * @param resHm
	 * @param ansHm
	 * @return
	 */
	private static int critical(HashMap<Integer, String> resHm,HashMap<Integer, String> ansHm){
		int count=0;
		for(Map.Entry<Integer, String> resentry:resHm.entrySet()){
			String resvalue = resentry.getValue() == null?"":resentry.getValue();
			String ansvalue = ansHm.get(resentry.getKey())==null?"":ansHm.get(resentry.getKey());
			if(resvalue.equals(ansvalue)){
				count++;
			}
		}
		return count;
	}
	/**
	 * 数据可视化函数
	 */
	private static void StaticChart(){
		BarChart barChart=new BarChart();
        JFrame sframe=new JFrame();

    	sframe.setLocationRelativeTo(null);
    	sframe.setTitle("测试成绩分析");
        sframe.setLocationByPlatform(true);
        sframe.setBounds(50, 50,800,600);
        sframe.setVisible(true);
        
        ChartPanel p=barChart.getChartPanel();
        sframe.add(p);
	}

	/**
	 * 判断输入参数是否合法
	 * @param data
	 * @return
	 */
	private static boolean judge(float data){
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

