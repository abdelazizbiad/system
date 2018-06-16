package org.ensah.repos;

import java.util.List;

import org.ensah.domains.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ElementRepos extends JpaRepository<Element, Long>{
	@Query("FROM Element WHERE id IN (SELECT element FROM Affectation WHERE id_prof=?1)")
	List<Element> findElementByidProf(Long id);
}
