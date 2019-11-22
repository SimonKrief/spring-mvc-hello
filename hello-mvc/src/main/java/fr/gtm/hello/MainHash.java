package fr.gtm.hello;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

public class MainHash {

	public static void main(String[] args) throws NoSuchAlgorithmException {

//		hashToString("gastonpw");
//		hashToString("batmanpw");
//		hashToString("adelepw");
//		hashToString("franckpw");
//		hash();

	}

	public static void hash() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String passwordToHash = "Hello, world";
		byte[] bytes = md.digest(passwordToHash.getBytes());
		System.out.println(bytes);
	}
	
	public static void hashToString(String passwordToHash) {
//		String passwordToHash = "Hello, world";
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// devrait être
		// :4ae7c3b6ac0beff671efa8cf57386151c06e58ca53a78d83f36107316cec125f
		System.out.println(generatedPassword);
	}

}
