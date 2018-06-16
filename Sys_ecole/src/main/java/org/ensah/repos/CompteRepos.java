package org.ensah.repos;

import java.util.List;

import org.ensah.domains.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteRepos extends JpaRepository<Compte, Long>{
	Compte findByUsername(String username);
	@Query("from Compte where idUser=?1")
	List<Compte> findByIdUser(Long id);
}
