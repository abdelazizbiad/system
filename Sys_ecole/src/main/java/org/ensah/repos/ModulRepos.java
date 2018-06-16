package org.ensah.repos;

import org.ensah.domains.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModulRepos extends JpaRepository<Module, Long>{
	Module findByNom(String nom);
}
