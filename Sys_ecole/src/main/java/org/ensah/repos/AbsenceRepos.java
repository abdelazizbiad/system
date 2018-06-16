package org.ensah.repos;

import java.util.List;

import org.ensah.domains.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AbsenceRepos extends JpaRepository<Absence, Long>{
	@Query("FROM Absence WHERE id_etudiant IN (SELECT e.id FROM Etudiant e WHERE e.nom like :x)")
	List<Absence> findAbsencesWordkey(@Param("x")String wordkey);
}
