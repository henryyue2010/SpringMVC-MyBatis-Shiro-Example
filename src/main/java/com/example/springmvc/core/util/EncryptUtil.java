package com.example.springmvc.core.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * EncryptUtil for Shiro
 * 
 * @author henryyue
 *
 */
public class EncryptUtil {
	public static final String encryptMD5(String source) {
		if (source == null) {
			source = "";
		}
		Md5Hash md5 = new Md5Hash(source);
		return md5.toString();
	}
}
