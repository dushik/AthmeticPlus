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
	 * �ָ��ַ�����-��ǰ�������
	 * @param string
	 */
	public String[] StrSplitScore(String string){
		String temp[]=string.split("\\-");
		return temp;
	}
}
