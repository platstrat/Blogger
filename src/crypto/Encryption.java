package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption{
	
	public static String digest(String plainText){
		return digest(plainText, EncryptionType.SHA256);
	}
	
	public static String digest(String plainText, EncryptionType type){
		String encrypted = null;
		try{
			MessageDigest messageDigest = MessageDigest.getInstance(type.algorithm);
			byte[] digested = messageDigest.digest(plainText.getBytes());
			encrypted = Encryption.toHexString(digested);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return encrypted;
	}
	
	public static String toHexString(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes){
			String hex = Integer.toHexString(255 & b);
			if (hex.length() < 2) sb.append('0');
			sb.append(hex);
		}
		String encoded = sb.toString();
		return encoded;
	}
}
