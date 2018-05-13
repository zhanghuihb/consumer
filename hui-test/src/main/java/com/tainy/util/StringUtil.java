package com.tainy.util;

import javax.servlet.ServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * 字符串辅助类，处理常用的字符串操作
 * 
 * @author 郑王力 zwangli@sina.com 创建日期： 2003.8.28
 */

public class StringUtil {

	public static final String CODING = "UTF-8";

	/**
	 * 缺省的字符串分割符
	 */
	public static String DEFAULT_DELIM = "$*";

	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private StringUtil() {
	}

	/**
	 * 将字符串数组使用缺省的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * @return 合并后的字符串
	 */
	public static String join(String[] array) {
		return join(array, DEFAULT_DELIM);
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * @param delim
	 *            分隔符，为null的时候使用缺省分割符（逗号）
	 * @return 合并后的字符串
	 */
	public static String join(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = DEFAULT_DELIM;
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	/**
	 * 将字符串使用缺省分割符（逗号）划分的单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 */
	public static String[] split(String source) {
		return split(source, DEFAULT_DELIM);
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。 注意：分隔字符串中每一个 <b>(ANY) </b>的字符都作为独立的分割符。 <br>
	 * 举个例子： <br>
	 * "mofit.com.cn"用"com"分割后的结果是三个字符串"fit."、"."和"n"，而不是"mofit."和".cn"。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 *         如果delim为null则使用逗号作为分隔字符串。
	 */
	public static String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = DEFAULT_DELIM;
		}
		StringTokenizer st = new StringTokenizer(source, delim);

		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。 注意：准确的说应该是匹配，而不是包含。 <br>
	 * 举个例子：字符串数组"mofit.com.cn","neusoft.com"里 <b>不包含 </b>"com"， <br>
	 * 但是 <b>包含 </b>"mofti.com.cn"。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @param caseSensitive
	 *            是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 */
	public static boolean contains(String[] strings, String string, boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			} else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。大小写敏感。 <br>
	 * 注意：准确的说应该是匹配，而不是包含。 <br>
	 * 举个例子：字符串数组"mofit.com.cn","neusoft.com"里 <b>不包含 </b>"com"， <br>
	 * 但是 <b>包含 </b>"mofti.com.cn"。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 */
	public static boolean contains(String[] strings, String string) {
		return contains(strings, string, true);
	}

	/**
	 * 去除左边多余的空格。
	 * 
	 * @param value
	 *            待去左边空格的字符串
	 * @return 去掉左边空格后的字符串
	 */
	public static String trimLeft(String value) {
		String result = value;
		if (result == null) {
			return result;
		}
		char ch[] = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 * 去除右边多余的空格。
	 * 
	 * @param value
	 *            待去右边空格的字符串
	 * @return 去掉右边空格后的字符串
	 */
	public static String trimRight(String value) {
		String result = value;
		if (result == null) {
			return result;
		}
		char ch[] = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 得到字符串的字节长度。汉字占两个字节，字母占一个字节
	 * 
	 * @param source
	 *            字符串
	 * @return 字符串的字节长度
	 */
	public static int getLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 使用给定的字串替换源字符串中指定的字串。
	 * 
	 * @param mainString
	 *            源字符串
	 * @param oldString
	 *            被替换的字串
	 * @param newString
	 *            替换字串
	 * @return 替换后的字符串
	 */
	public final static String replace(String mainString, String oldString, String newString) {
		if (mainString == null) {
			return null;
		}
		int i = mainString.lastIndexOf(oldString);
		if (i < 0) {
			return mainString;
		}
		StringBuffer mainSb = new StringBuffer(mainString);
		while (i >= 0) {
			mainSb.replace(i, i + oldString.length(), newString);
			i = mainString.lastIndexOf(oldString, i - 1);
		}
		return mainSb.toString();
	}

	/**
	 * 将给定的字符串转换为中文GBK编码的字符串。
	 * 
	 * @param str
	 *            输入字符串
	 * @return 经GBK编码后的字符串，如果有异常，则返回原编码字符串
	 */
	public final static String toChinese(final String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		String retVal = str;
		try {
			retVal = new String(str.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return retVal;
	}

	/**
	 * 将给定的中文GBK编码转换为UNICODE编码的字符串。
	 * 
	 * @param str
	 *            输入字符串
	 * @return 经GBK编码后的字符串，如果有异常，则返回原编码字符串
	 */
	public final static String toUNICODE(final String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		String retVal = str;
		try {
			retVal = new String(str.getBytes("ZHS16GBK"), "GBK");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return retVal;
	}

	/**
	 * 用于字符串显示。将html敏感的尖括号、引号、连接号等用转义符替代。 <br>
	 * 建议用法：在接收到客户端传来的字符串时，不进行转换，直接存入数据库； <br>
	 * 在从数据库中取出，传给客户端做html显示时，才转换。
	 * 
	 * @param input
	 *            需要检查的字符串
	 * @return 转化后的字串
	 */
	public final static String convertToHTML(String input) {
		if (null == input || "".equals(input)) {
			return input;
		}

		StringBuffer buf = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else if (ch == '&') {
				buf.append("&amp;");
			} else if (ch == '"') {
				buf.append("&quot;");
			} else if (ch == '\n') {
				buf.append("<BR/>");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * 定义字符串加密时需要用到的标记字符串
	 */
	private static String ENCRYPT_IN = "YN8K1JOZVURB3MDETS5GPL27AXW`IHQ94C6F0~qwert!@yuiop#$asdfghj%kl^&*zxc vbn(m)_+|{}:\"<>?-=\\[];,./'";

	/**
	 * 定义字符串加密时需要用到的转义字符串
	 */
	private static String ENCRYPT_OUT = "qazwsxcderfvbgtyhnmjuiklop~!@#$%^&*()_+|{ }:\"<>?-=\\[];,./'ABCDE`FGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 对给定字符串进行加密操作
	 * 
	 * @param inPass
	 *            待加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encrypt(String inPass) {
		String stringIn = ENCRYPT_IN;
		String stringOut = ENCRYPT_OUT;
		int time1 = Calendar.getInstance().get(Calendar.MINUTE);
		int time2 = Calendar.getInstance().get(Calendar.SECOND);
		int offset = (time1 + time2) % 95;
		String outPass = stringIn.substring(offset, offset + 1);
		stringIn = stringIn + stringIn;
		stringIn = stringIn.substring(offset, offset + 95);
		String temp = "";
		for (int i = 0; i <= inPass.length() - 1; i++) {
			temp = temp + stringOut.charAt(stringIn.indexOf(inPass.charAt(i)));

		}
		outPass = outPass + temp;
		return outPass;
	}

	/**
	 * 对给定字符串进行解密操作
	 * 
	 * @param outPass
	 *            待解密的字符串
	 * @return 解密还原后的字符串
	 */
	public static String decrypt(String outPass) {
		String stringIn = ENCRYPT_IN;
		String stringOut = ENCRYPT_OUT;
		int offset = stringIn.indexOf(outPass.charAt(0));
		stringIn = stringIn + stringIn;
		stringIn = stringIn.substring(offset, offset + 95);
		outPass = outPass.substring(1);
		String inPass = "";
		for (int i = 0; i <= outPass.length() - 1; i++) {
			inPass = inPass + stringIn.charAt(stringOut.indexOf(outPass.charAt(i)));

		}
		return inPass;
	}

	// 指定的字符串累加
	public static String strAdd(String chr, int len) {
		if (len > 0) {
			StringBuffer ret = new StringBuffer(len);
			for (int i = 0; i < len; i++) {
				ret.append(chr);
			}
			return (ret.toString());
		} else {
			return "";
		}
	}

	// 给字符串补足到指定的长度，从左边补足chr指定的字符
	public static String lPad(String source, String chr, int len) {
		int lenleft = len - source.length();
		if (lenleft < 0) {
			lenleft = 0;
		}
		return (strAdd(chr, lenleft) + source);
	}

	// 给字符串补足到指定的长度，从右边补足chr指定的字符
	public static String rPad(String source, String chr, int len) {
		int lenleft = len - source.length();
		if (lenleft < 0) {
			lenleft = 0;
		}
		return (source + strAdd(chr, lenleft));
	}

	/**
	 * 根据给定前缀，给名称加上限定前缀
	 * 
	 * @param prefix
	 * @param name
	 * @return
	 */
	public static String qualify(String prefix, String name) {
		if (name == null || prefix == null) {
			throw new NullPointerException();
		}
		return new StringBuffer(prefix.length() + name.length() + 1).append(prefix).append('.').append(name).toString();
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 * @author alex (90167)
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 * @author alex (90167)
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 * @author alex (90167)
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 * @author alex (90167)
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 * @author alex (90167)
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 * @author alex (90167)
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author alex (90167)
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 获取字符串的MD5码，以16进制字符串返回，长度为32
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(CODING));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("get MD5 string error!");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("get MD5 string error!");
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String s = Integer.toHexString(0xFF & byteArray[i]);
			if (s.length() == 1) {
				md5StrBuff.append("0");
			}
			md5StrBuff.append(s);
		}
		return md5StrBuff.toString();
	}

	/**
	 * 获得32位UUID
	 * 
	 * @Description
	 * @param
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 生成支付交易流水号
	 * @return
	 */
	public static String generateOutTradeNo() {

		int hashCodeV = UUID.randomUUID().toString().hashCode();
		
		if(hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		
		return org.apache.commons.httpclient.util.DateUtil.formatDate(new Date(), "yyyyMMddHHmmssSSS") + String.format("%011d",hashCodeV);
	}
	
	/**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getRequestBody(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

	public static void main(String[] args) {

		String[] test = { "北京mofit.com.cn", " neusoft.com ", "<out\"OfTax>" };
		String all = join(test, "||");

		System.out.println("字符串[" + test[0] + "],[" + test[1] + "],[" + test[2] + "]合并结果：" + all);
		System.out.println("字符串[" + test[0] + "],[" + test[1] + "],[" + test[2] + "]是否包含［<out\"oftax>］(区分大小写)："
				+ contains(test, "<out\"oftax>"));
		System.out.println("字符串[" + test[0] + "],[" + test[1] + "],[" + test[2] + "]是否包含［<out\"oftax>］(不区分大小写)："
				+ contains(test, "<out\"oftax>", false));
		System.out.println("字符串[" + all + "]转换为html：" + convertToHTML(all));

		String en = encrypt(test[2]);

		System.out.println("字符串[" + test[2] + "]加密后为：" + en);
		System.out.println("字符串[" + en + "]解密后为：" + decrypt(en));
		System.out.println("字符串[" + test[0] + "]的长度为：" + getLength(test[0]));
		System.out.println("字符串[" + all + "]用[Company]替换[.com]后为：[" + replace(all, ".com", "Company") + "]");

		String[] sp = split(test[0], "com");
		System.out.println("字符串[" + test[0] + "]用[com]分隔为" + sp.length + "个字符串。分别为：[" + sp[0] + "],[" + sp[1] + "],[" + sp[2]
				+ "]...");

		System.out.println("字符串[" + test[1] + "]去掉左空格为：[" + trimLeft(test[1]) + "]");
		System.out.println("字符串[" + test[1] + "]去掉右空格为：[" + trimRight(test[1]) + "]");
	}
}