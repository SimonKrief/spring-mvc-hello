package fr.gtm.hello;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

public class MainHash {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		//users et sha256 etant les colonnes de mdp et de hash mdp
		//UPDATE users SET sha256 = sha2(password,256)
		hashToString("gastonpw");//96202ba172c88b16bcdd26ba1a2184283d5fefd12252b280b2f1257c3c0aa254
//		hashToString("batmanpw");//73906e2889f1ba11f4d3a1a702e59821c91fcebc5a130e872ee36f83e8511589
//		hashToString("adelepw");//86a99641ab7de9fea75b1321a372758c32c9b9f1c927d5ac1d7d4c897ded8bd8
//		hashToString("franckpw");//04f6e71336edf4f8ab51a40756751b6154f8442f3564598779a724a40c953db1
		hash("gastonpw");

	}

	public static String hash(String passwordToHash) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] bytes = md.digest(passwordToHash.getBytes());
		BigInteger bi = new BigInteger(1,bytes);
		
		System.out.println(bytes);

		return bi.toString();
	}
	
	public static void hashToString(String passwordToHash) {
//		String passwordToHash = "Hello, world";
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for SHA-256
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
		// pour "Hello, world" devrait être:
		// :4ae7c3b6ac0beff671efa8cf57386151c06e58ca53a78d83f36107316cec125f
		System.out.println(generatedPassword);
	}

}
