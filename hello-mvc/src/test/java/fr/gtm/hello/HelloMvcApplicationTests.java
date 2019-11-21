package fr.gtm.hello;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloMvcApplicationTests {
	@Autowired
	ClientDao dao;
//	@Test
//	void contextLoads() {
//		assertTrue(dao!=0);
//	}
	@Test
	void getUserByNom() {	
		assertNotNull(dao.getByNom("gaston"));
	}
	

}
