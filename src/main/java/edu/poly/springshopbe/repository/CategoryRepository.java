package edu.poly.springshopbe.repository;

import edu.poly.springshopbe.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByNameStartsWith(String name, Pageable pageable);

}
