/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.base.dao;

import fr.panda.base.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gtu
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
