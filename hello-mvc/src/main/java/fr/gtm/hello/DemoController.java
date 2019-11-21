package fr.gtm.hello;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
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

	// execution à la racine de l'url avec par exemple
	// http://localhost:7070/?name=simon ou http://localhost:7070/
	@GetMapping("/")
	public String hello(@RequestParam(name = "name", defaultValue = "Johnathan", required = false) String name,
			Model model) {
		String message = "Bon anniversaire " + name;
		model.addAttribute("message", message);
		return "home";
	}

//	@GetMapping("/{nom}")
//	public String bonanniv(@PathVariable(name ="nom", value = "Johnathan", required = false) String name, Model model) {
//		String message = "Bon anniversaire "+name;
//		model.addAttribute("message", message);
//		return "home";
//	}

	@GetMapping("/signin")
	public String signing(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signin";
	}
//private static final Logger LOG = Logger.getLogger("lol");

	@PostMapping("/connexion")
	public String connexion(User user, Model model) {
		try {
			User queryUser = dao.getByNom(user.getNom());
//			LOG.info(user.getPassword());
//			LOG.info(queryUser.getPassword());

			if (user.getPassword().equals(queryUser.getPassword())) {
				model.addAttribute("user", user);
				return "ok";
			} else {
				return "pw incorrect";
			}
		} catch (Exception e) {
			return "user-inexistant";
		}
	}

}
