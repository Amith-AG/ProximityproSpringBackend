package com.amithag.backendproximity.respositry;

import com.amithag.backendproximity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role,Long> {
 Role findByName(String name);

 @Query(value = "SELECT ro.name FROM specialist_role sr JOIN role ro ON sr.role_id = ro.id WHERE sr.specialist_id= :id",nativeQuery = true)
 List<String>findRoleBySid(long id);
}
