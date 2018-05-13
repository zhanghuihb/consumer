package com.tainy.common.util.md5;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class MD5Util {

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
	
	/**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    @SuppressWarnings("rawtypes")
   	public static String sign(Object object, String encryptionKey, String encode) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
       	
       	Map<String, Object> maps = new HashMap<String, Object>();
           Class cls = object.getClass();
           Field[] fields = cls.getDeclaredFields();
           for (Field f : fields) {
               f.setAccessible(true);
               if (f.get(object) != null && f.get(object) != "") {
                   maps.put(f.getName(),f.get(object));
               }
           } 
           //是否有父类，获取父类的属性以及属性值
           Class superCls =  cls.getSuperclass();
           if (null != superCls) {
   			Field[] superFields = superCls.getDeclaredFields();
   			for (Field field : superFields) {
   				field.setAccessible(true);
   				if (field.get(object) != null && field.get(object) != "") {
   					maps.put(field.getName(), field.get(object));
   				}
   			}
   		}
           
           StringBuilder sb = new StringBuilder();
           //注意：map可以为空map，但不能为null，否则Java8会报NullPointerException
           //按照key值排序
           maps.entrySet()
           	.stream()
           	.sorted(Map.Entry.comparingByKey())
           	.forEachOrdered(x->sb.append(x.getValue()));
           sb.setLength(sb.length());
//           String result = URLEncoder.encode(sb.toString(), encode);
           String result = sb.toString();
           //去除所有空格
           result = result.replaceAll("\\s*", "");
           result = sign(result, encryptionKey, encode);
           return result;
       }
    
    public static String sha1(Object object, String encryptionKey) throws IllegalArgumentException, IllegalAccessException{
    	String encodeStr = getSortEncodeString(object);
    	return DigestUtils.sha1Hex(encodeStr);
    }
    
    private static String getSortEncodeString(Object object) throws IllegalArgumentException, IllegalAccessException{
    	Map<String, Object> maps = new HashMap<String, Object>();
        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(object) != null && f.get(object) != "") {
                maps.put(f.getName(),f.get(object));
            }
        } 
        //是否有父类，获取父类的属性以及属性值
        Class superCls =  cls.getSuperclass();
        if (null != superCls) {
			Field[] superFields = superCls.getDeclaredFields();
			for (Field field : superFields) {
				field.setAccessible(true);
				if (field.get(object) != null && field.get(object) != "") {
					maps.put(field.getName(), field.get(object));
				}
			}
		}
        
        StringBuilder sb = new StringBuilder();
        //注意：map可以为空map，但不能为null，否则Java8会报NullPointerException
        //按照key值排序
        maps.entrySet()
        	.stream()
        	.sorted(Map.Entry.comparingByKey())
        	.forEachOrdered(x->sb.append(x.getValue()));
        sb.setLength(sb.length());
        
        return sb.toString();
    }
    
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }  

}
