package com.infofarm;

import com.infofarm.Users.Models.Permission;
import com.infofarm.Users.Models.RoleEnum;
import com.infofarm.Users.Models.Roles;
import com.infofarm.Users.Repository.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class InfofarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfofarmApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RolesRepository repository) {
		return  args -> {
			//Crear permisos
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();

			Permission readPermission = Permission.builder()
					.name("READ")
					.build();

			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();

			Permission deleteePermission = Permission.builder()
					.name("DELETE")
					.build();

			Roles roleAdmin = Roles.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission,deleteePermission))
					.build();

			Roles roleUser = Roles.builder()
					.roleEnum(RoleEnum.EMPLOYEE)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			repository.saveAll(List.of(roleAdmin, roleUser));
		};
	}
}
