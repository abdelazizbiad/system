package org.ensah.config.comptedetails;

import java.util.ArrayList;
import java.util.Collection;

import org.ensah.domains.Compte;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class ComptetoCompteDetails implements Converter<Compte, CompteDetails>{

	@Override
	public CompteDetails convert(Compte compte) {
		CompteDetails compteDetails = new CompteDetails();

        if (compte != null) {
        	compteDetails.setUsername(compte.getUsername());
        	compteDetails.setPassword(compte.getPassword());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
           
            authorities.add(new SimpleGrantedAuthority(compte.getRole().getRoleName()));
            compteDetails.setAuthorities(authorities);
        }

        	return compteDetails;
	}

}
