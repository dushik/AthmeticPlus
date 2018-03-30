package per;
/**
 * 字符串处理辅助类
 *
 */
public class StrPro {
	/**
	 * 分割字符串“=”前面的内容
	 * @param string
	 */
	public String StrSplitBefore(String string){
		String temp[]=string.split("\\=");
		return temp[0];
	}
	
	/**
	 * 分割字符串“=”后面的内容
	 * @param string
	 */
	public String StrSplitAfter(String string){
		String temp[]=string.split("\\=");
		return temp[1];
	}
	/**
	 * 分割字符串“-”前面的内容
	 * @param string
	 */
	public String[] StrSplitScore(String string){
		String temp[]=string.split("\\-");
		return temp;
	}
}
