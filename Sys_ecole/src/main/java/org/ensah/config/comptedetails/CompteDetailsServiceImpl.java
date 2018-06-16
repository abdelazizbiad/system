package org.ensah.config.comptedetails;

import org.ensah.domains.Compte;
import org.ensah.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.core.convert.converter.Converter;

@Service("userDetailsService")
public class CompteDetailsServiceImpl implements UserDetailsService{
	@Autowired
    private GuestService guestService;

    @Autowired
    private Converter<Compte, CompteDetails> userUserDetailsConverter;

    @Override
    public CompteDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Compte compte = guestService.signin(username);
        if (compte.equals(null)) {
            throw new UsernameNotFoundException("No such user with username :" + username);
        }
        return userUserDetailsConverter.convert(compte);
}
}
