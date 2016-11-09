package com.jam.javautils.encrypt.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.jam.javautils.encrypt.BASE64Decoder;

public class AesDecode {
	public static void main(String[] args) throws Exception {
		decode("JIYHmdjysdk98KJ", "YVCZvzh1ubd5fOgEoZ8/8A==");
	}

	/**
	 * 
	 * @param key
	 *            JVM参数 如:-Dcom.meizu.properties.key=JIYHmdjysdk98KJ
	 * @param plain
	 *            密文
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @author gangjian 2016年11月9日
	 */
	public static void decode(String key, String plain)
			throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec bkey = new SecretKeySpec(makeKey(key), "AES");
		cipher.init(Cipher.DECRYPT_MODE, bkey, cipher.getParameters());
		byte[] plaintxt = cipher.doFinal(BASE64Decoder.decode(plain));
		System.out.println(new String(plaintxt, "UTF-8"));
	}

	private static byte[] makeKey(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] key = new byte[16];
		byte[] key1 = str.getBytes("UTF-8");
		if (key1.length <= key.length) {
			System.arraycopy(key1, 0, key, 0, key1.length);
		} else {
			System.arraycopy(key1, 0, key, 0, key.length);
		}
		return key;
	}
}
