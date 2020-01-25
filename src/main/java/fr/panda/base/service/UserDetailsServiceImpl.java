/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.base.service;

import fr.panda.base.entities.Utilisateur;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author gtu
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private CompteService compteService;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Utilisateur utilisateur = compteService.findUtilisateurByLogin(login);
        if (utilisateur == null ) throw  new UsernameNotFoundException(login);
        
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getIntitule()));
        });
        
        return new User(utilisateur.getLogin(), utilisateur.getMdp(), authorities);
    }
    
}
