package tn.spring.pispring.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.Entities.Role;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
    List<Address> findByUser_Role(Role role);

    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    Address findByUserId(@Param("userId") Long userId);
}
