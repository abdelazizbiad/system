package org.ensah.domains;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String roleName;
	@OneToMany(mappedBy="role")
	private List<Compte> lesComptes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Compte> getLesComptes() {
		return lesComptes;
	}
	public void setLesComptes(List<Compte> lesComptes) {
		this.lesComptes = lesComptes;
	}
	
}
