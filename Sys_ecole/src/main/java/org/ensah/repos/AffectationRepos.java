package org.ensah.repos;


import org.ensah.domains.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AffectationRepos extends JpaRepository<Affectation, Long>{
//	@Query("from Element natural join Affectation where id_prof=?1")
//	 List<Element> findElementbyIdprof(Long idProf);
}
