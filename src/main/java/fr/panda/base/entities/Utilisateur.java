/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.base.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author gtu
 */

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Utilisateur {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String login;
    private String mail;
    private String mdp; 
    
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList();
    
}
