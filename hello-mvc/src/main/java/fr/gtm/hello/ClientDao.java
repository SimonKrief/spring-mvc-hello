package fr.gtm.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientDao extends JpaRepository<User, Long>{

	User getByNom(String nom);

}
