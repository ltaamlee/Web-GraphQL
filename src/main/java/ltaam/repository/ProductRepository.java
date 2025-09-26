package ltaam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltaam.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {

	List<Product> findAllByOrderByPriceAsc();
    List<Product> findByCategories_Id(Long categoryId);
}
