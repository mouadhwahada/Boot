package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.pispring.Entities.Food;

@Repository
public interface FoodRepo extends JpaRepository<Food,Long> {
}
