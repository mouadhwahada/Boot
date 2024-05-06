package tn.spring.pispring.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.dto.RoleName;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRolesContains(Role role);

    List<User> findByRoles_Name(RoleName roleName);

    @Query("SELECT u FROM User u WHERE u.role.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);
    User findByPhoneNumber(int phoneNumber);

    User findByPhoneNumberAndRole(int phoneNumber, Role role);

}
