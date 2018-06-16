package org.ensah.repos;

import java.util.List;

import org.ensah.domains.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EtudiantRepos extends JpaRepository<Etudiant, Long>{
	Etudiant findByCne(String cne);
	@Query("Select e from Etudiant e where e.nom like :x")
	List<Etudiant> findEtudiantsWordkey(@Param("x")String wordkey);
}
