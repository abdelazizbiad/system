package org.ensah.repos;

import org.ensah.domains.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FiliereRepos extends JpaRepository<Filiere, Long>{

}
