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
	 * 分割字符串“-”得到前面的内容
	 * @param string
	 */
	public String[] StrSplitScore(String string){
		return string.split("\\-");
	}
	
	/**
	 * 分割字符串得到“-”前面的内容
	 * @param string
	 */
	public String StrMinus(String string){
		System.out.println(string);
		System.out.println(string.substring(0, string.lastIndexOf("-")));
		return string.substring(0, string.lastIndexOf("-"));
	}
	
	/**
	 * 分割字符串得到“/”前面的内容
	 * @param string
	 */
	public String StrDivision(String string){
		System.out.println(string);
		System.out.println(string.substring(0, string.lastIndexOf("/")));
		return string.substring(0, string.lastIndexOf("/"));
	}
}
