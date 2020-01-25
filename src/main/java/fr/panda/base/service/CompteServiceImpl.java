/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.base.service;

import fr.panda.base.dao.RoleRepository;
import fr.panda.base.dao.UtilisateurRepository;
import fr.panda.base.entities.Role;
import fr.panda.base.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gtu
 */

@Service
@Transactional
public class CompteServiceImpl implements CompteService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public Utilisateur save(Utilisateur util) {
        String mdpEncode = bCryptPasswordEncoder.encode(util.getMdp());
        util.setMdp(mdpEncode);
        return utilisateurRepository.save(util);
        
    }

    @Override
    public Role save(Role role) {
       return roleRepository.save(role);
    }

    @Override
    public void AddRoleToUtilisateur(String login, String intitule) {
        Role role = roleRepository.findByIntitule(intitule);
        Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
        utilisateur.getRoles().add(role);
        //Comme la classe est transactionelle, il commit automatiquement a chaque ajout et rajoute à la base de donnée
        
    }

    @Override
    public Utilisateur findUtilisateurByLogin(String login) {
        return utilisateurRepository.findByLogin(login);
    }
    
}
