package kuzmich.graduation_project;

import kuzmich.graduation_project.model.Role;
import kuzmich.graduation_project.model.User;
import kuzmich.graduation_project.repository.RoleRepository;
import kuzmich.graduation_project.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Set;

@SpringBootApplication
public class GraduationProjectApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GraduationProjectApplication.class, args);

		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		Role adminRole = new Role("admin");

		UserRepository userRepository = ctx.getBean(UserRepository.class);
		User admin = new User();
		admin.setUserName("admin");
		admin.setPassword("$2a$12$ShivOAYWDNuUqL3ax6HzE.w8DOUBzMp2hrNF39oJge6h/TYq0ijKq");
		Set<Role> adminRoles = admin.getRoles();
		adminRoles.add(adminRole);
		admin.setRoles(adminRoles);
	}

}
