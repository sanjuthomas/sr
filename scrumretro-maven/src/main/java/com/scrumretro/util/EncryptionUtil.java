package com.scrumretro.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

	public static String encryptPassword(String password) {
		byte[] by = password.getBytes();
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
        md.reset();
        md.update(by);
        byte messageDigest[] = md.digest();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
                buffer.append(Integer.toHexString(0xFF & messageDigest[i]));
        }
        return buffer.toString();
	}
	
}
