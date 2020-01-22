package fr.panda.base;

import fr.panda.base.dao.RoleRepository;
import fr.panda.base.dao.UtilisateurRepository;
import fr.panda.base.entities.Role;
import fr.panda.base.entities.Utilisateur;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthSecJwtApplication implements CommandLineRunner{
    
    @Autowired
    private UtilisateurRepository utilisateurRepository; 
    
    @Autowired
    private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthSecJwtApplication.class, args);
                System.out.println("Lancement de Auth security JWT Application ");
        }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Lancement de l'initialisation ...");
        //On va initialiser notre base de données H2 avec un premier jeu de données :
        //On entre les différents roles
        Stream.of("USER" , "ADMIN" , "SUPERADMIN").forEach(nom->{
            Role role = new Role(null, nom);
            roleRepository.save(role);
        });
        roleRepository.findAll().forEach(System.out::println);
        
        Stream.of("Matheo1234" , "Teenou" , "Puppet Master" , "Major Motoko").forEach(nomUti->{
            Utilisateur util = new Utilisateur();
            util.setLogin(nomUti);
            util.setMail(nomUti+"@gmail.com");
            util.setMdp(nomUti+"1234");
            
            long n = 1+(long)(Math.random() * roleRepository.count());
            List<Role> roles = new ArrayList();
            roles.add( roleRepository.getOne(n));
            System.out.println(roleRepository.getOne(n).getIntitule());
            util.setRoles(roles);
            utilisateurRepository.save(util);
        });
        utilisateurRepository.findAll().forEach(System.out::println);
        System.out.println("Fin de l'initialisation");
        
    }

}
 