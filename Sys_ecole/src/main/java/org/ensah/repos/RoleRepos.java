package org.ensah.repos;

import org.ensah.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepos extends JpaRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
