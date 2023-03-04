package imt.example.springshopbe.repository;

import imt.example.springshopbe.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameStartsWith(String name, Pageable pageable);


}