package ltaam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ltaam.entity.Category;

public interface CategoryRepository extends JpaRepository <Category, Long> {
	
}