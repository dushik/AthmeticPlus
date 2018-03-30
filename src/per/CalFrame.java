package per;

import javax.swing.*;
import java.awt.*;
/**
 * Ö÷½çÃæJFrame
 *
 */
class CalFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Toolkit kit=Toolkit.getDefaultToolkit();
	Dimension screenSize=kit.getScreenSize();
	int DEFAULT_WIDTH=screenSize.width;
	int DEFAULT_HEIGHT=screenSize.height;
	
	public CalFrame(){
		setSize(DEFAULT_WIDTH/2,DEFAULT_HEIGHT/2);
		setLocationByPlatform(true);
		add(new MainComponent());
	}
}
class MainComponent extends JComponent{
	
}
