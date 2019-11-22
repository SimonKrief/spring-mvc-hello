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
	@Test
	void contextLoads() {
	assertNotNull(dao);
	}
	@Test
	void getUserByNom() {	
		assertNotNull(dao.getByNom("gaston"));
	}
	@Test
	void testNative() {	
		String str = dao.trouverUserNative();
		assertTrue(str.length()>0);
		System.out.println(str);
	}
	@Test
	void trouverUserNativeParam() {
		String user = "gaston";
		String str  = dao.trouverUserNativeParam(user);
		assertTrue(str.length()>0);

		System.out.println(str);

	}
	

}
