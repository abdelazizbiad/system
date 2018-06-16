package org.ensah.repos;

import org.ensah.domains.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface InscriptionRepos extends JpaRepository<Inscription, Long>{

}
