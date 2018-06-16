package org.ensah.repos;

import org.ensah.domains.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRepos extends JpaRepository<Niveau, Long>{

}
