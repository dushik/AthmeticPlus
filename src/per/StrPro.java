package per;
/**
 * �ַ�����������
 *
 */
public class StrPro {
	
	/**
	 * �ָ��ַ�����=��ǰ�������
	 * @param string
	 */
	public String StrSplitBefore(String string){
		String temp[]=string.split("\\=");
		return temp[0];
	}
	
	/**
	 * �ָ��ַ�����=�����������
	 * @param string
	 */
	public String StrSplitAfter(String string){
		String temp[]=string.split("\\=");
		return temp[1];
	}
	
	/**
	 * �ָ��ַ�����-���õ�ǰ�������
	 * @param string
	 */
	public String[] StrSplitScore(String string){
		return string.split("\\-");
	}
	
	/**
	 * �ָ��ַ����õ���-��ǰ�������
	 * @param string
	 */
	public String StrMinus(String string){
		System.out.println(string);
		System.out.println(string.substring(0, string.lastIndexOf("-")));
		return string.substring(0, string.lastIndexOf("-"));
	}
	
	/**
	 * �ָ��ַ����õ���/��ǰ�������
	 * @param string
	 */
	public String StrDivision(String string){
		System.out.println(string);
		System.out.println(string.substring(0, string.lastIndexOf("/")));
		return string.substring(0, string.lastIndexOf("/"));
	}
}
