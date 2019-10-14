package com.yhf.txl.utils;
/**
 * 
 * 判断字符串是否为空
 *
 */
public class StringUtil {
	public static boolean isEmpty(String string) {
		if(string==null||"".equals(string)) {
			return true;
		}
		return false;
	}
}
