package org.ensah.repos;

import java.util.List;

import org.ensah.domains.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepos extends JpaRepository<Professeur, Long>{
	Professeur findByCin(String cin);
	@Query("Select e from Professeur e where e.nom like :x")
	List<Professeur> findProfesseursWordkey(@Param("x")String wordkey);
}
