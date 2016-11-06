package com.jbd.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Crypter {

	final String strPassPhrase = "1234567890123456789012345"; // min 24 chars

	public String encrypt(String str) throws Exception {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		SecretKey key = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
		Cipher cipher = Cipher.getInstance("DESede");

		cipher.init(Cipher.ENCRYPT_MODE, key);
		return DatatypeConverter.printBase64Binary(cipher.doFinal(str.getBytes()));
	}

	public String decrypt(String str) throws Exception {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		SecretKey key = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
		Cipher cipher = Cipher.getInstance("DESede");

		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(str)));
	}
}
