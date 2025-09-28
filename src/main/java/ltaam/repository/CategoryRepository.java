package ltaam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltaam.entity.Category;

public interface CategoryRepository extends JpaRepository <Category, Long> {
   
	List<Category> findByNameContainingIgnoreCase(String keyword);

}