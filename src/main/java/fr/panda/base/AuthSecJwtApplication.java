package fr.panda.base;

import fr.panda.base.dao.MessageRepository;
import fr.panda.base.dao.RoleRepository;
import fr.panda.base.dao.UtilisateurRepository;
import fr.panda.base.entities.Message;
import fr.panda.base.entities.Role;
import fr.panda.base.entities.Utilisateur;
import fr.panda.base.service.CompteService;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthSecJwtApplication implements CommandLineRunner{
    
    @Autowired
    private MessageRepository messageRepository; 
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private CompteService compteService; 
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;
            
    
    @Bean // Pour etre executer et etre une bean Spring ce qui nous permet de l'injecter partout 
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

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
        Stream.of("USER" , "ADMIN" , "SUPERADMIN", "NOROLE").forEach(nom->{
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
            compteService.save(util);
        });
        //compteService..forEach(System.out::println);
        
        //initialisation de quelques messages : 
        
        System.out.println("Fin de l'initialisation");
        Stream.of("Matheo Message" , "Teenou Message" , "Puppet Master Message" , "Major Motoko Message").
                forEach((String message) -> {
                    Message mes = new Message(null, message);
                    messageRepository.save(mes);
        });
        messageRepository.findAll().forEach(System.out::println);
        Utilisateur sesam = new Utilisateur();
            sesam.setLogin("meruem");
            sesam.setMail("meruem@gmail.com");
            sesam.setMdp("meruem");
            List<Role> sesamRoles = new ArrayList();
            sesamRoles.add(roleRepository.findByIntitule("ADMIN"));
            sesam.setRoles(sesamRoles);
            compteService.save(sesam);
            utilisateurRepository.findAll().forEach(System.out::println);
        
    }

}
 