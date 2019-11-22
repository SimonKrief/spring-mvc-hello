package fr.gtm.hello;

import org.springframework.data.jpa.repository.JpaRepository;
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
	@Query(value = "SELECT u.sha256 FROM USERS u WHERE u.user = "+"\'"+nom+"\'", nativeQuery = true) String trouverUserNative();
	// c'est bien:
	//(Indexed Native Query Parameter ?1=user)
	@Query(value = "SELECT u.sha256 FROM USERS u WHERE u.user = ?1", nativeQuery = true) String trouverUserNativeParam(String user);

}
