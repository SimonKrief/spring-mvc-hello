package fr.gtm.hello;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<User, Long> {

//	User getByNom(String nom);

	//Remarque
	//On n'utilise plus de requete JPA en jpql car on ne veut pas faire transiter une entité.

	// c'est pas bien:
	//(Native Query)
	String nom = "gaston";
	@Query(value = "SELECT u.sha256 FROM USERS u WHERE u.user = "+"\'"+nom+"\'", nativeQuery = true)
	String trouverUserNative();
	// c'est bien:
	//(Indexed Native Query Parameter ?1=user)
	@Query(value = "SELECT u.sha256 FROM USERS u WHERE u.user = ?1", nativeQuery = true)
	String trouverUserNativeParam(String user);
	/*********************************************/
	
	List<User> findAll();
	@Transactional
	@Modifying//necessaire pour forcer l'update en l'absence de sortie (cas de select)
	@Query(value = "INSERT INTO authentification.users (`user`, password, `role`, sha256) VALUES('roger', 'rogerpw', 'admin', 'le_sha_de_roger')", nativeQuery = true)
	void testEnregistrerUser();
	


	@Transactional
	@Modifying
//	@Query(value = "INSERT INTO authentification.users (`user`, password, `role`, sha256) VALUES('?1', '?2', '?3', '?4')", nativeQuery = true)
	@Query(value = "INSERT INTO USERS (`user`, password, `role`, sha256) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
	void enregistrerUser(String user, String password, String role, String sha);

	
	
}
