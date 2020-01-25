/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.base.service;

import fr.panda.base.entities.Role;
import fr.panda.base.entities.Utilisateur;

/**
 *
 * @author gtu
 */
public interface CompteService {
    
    public Utilisateur save(Utilisateur util);
    
    public Role save(Role role);
    
    public void AddRoleToUtilisateur(String login, String intitule);
    
    public Utilisateur findUtilisateurByLogin( String login);
}
