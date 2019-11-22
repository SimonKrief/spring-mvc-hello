package fr.gtm.hello;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DemoController {
	@Autowired
	ClientDao dao;
	private static final Logger LOG = Logger.getLogger("lol");


	// execution à la racine de l'url avec par exemple
	// "http://localhost:7070/?name=simon" ou "http://localhost:7070/"
	@GetMapping("/")
	public String hello(@RequestParam(name = "name", defaultValue = "Johnathan", required = false) String name,
			Model model) {
		String message = "Bon anniversaire " + name;
		model.addAttribute("message", message);
		return "home";
	}

	@GetMapping("/signin")
	public String signing(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signin";
	}
	
	//http://localhost:7070/signin
//	@PostMapping("/connexion")
//	public String connexion(User user, Model model) {
//		try {
//			User queryUser = dao.getByNom(user.getNom());
//			LOG.info(user.getPassword());
//			LOG.info(queryUser.getPassword());
//
//			if (user.getPassword().equals(queryUser.getPassword())) {
//				model.addAttribute("user", user);
//				return "ok";
//			} else {
//				return "pw incorrect";
//			}
//		} catch (Exception e) {
//			return "user-inexistant";
//		}
//	}
	
	//http://localhost:7070/signin
	//en utilisant une requete native sur le hash du mdp en base
	@PostMapping("/connexion")
	public String connexionHash(@RequestParam(name ="password") String pw,  User user, Model model) {
		//hash du pw entré
		String hashedUserPwStr = hashToString(pw);
		try {
			//recupération du hash en base correspondant au nom 
			String hashedQueryUserPwStr = dao.trouverUserNativeParam(user.getNom());
			if (hashedUserPwStr.equals(hashedQueryUserPwStr)) {
				//ajout au model pour dire bonjour à user.
				model.addAttribute("user", user);
				return "ok";
			} else {
				return "pw incorrect";
			}
		} catch (Exception e) {
			//il n'y a pas de hash correspondant au nom 
			return "user-inexistant";
		}
	}
	
	
	public static String hashToString(String passwordToHash) {
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
		System.out.println(generatedPassword);
		return generatedPassword;
	}
	

	

}
