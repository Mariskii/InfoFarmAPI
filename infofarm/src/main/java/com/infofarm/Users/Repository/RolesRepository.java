package com.infofarm.Users.Repository;

import com.infofarm.Users.Models.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface RolesRepository extends CrudRepository<Roles, Integer> {
    List<Roles> findRolesByRoleEnumIn(List<String> roles);
}
