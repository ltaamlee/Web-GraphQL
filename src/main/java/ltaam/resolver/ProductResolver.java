package ltaam.resolver;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ltaam.entity.Category;
import ltaam.entity.Product;
import ltaam.entity.User;
import ltaam.repository.CategoryRepository;
import ltaam.repository.ProductRepository;
import ltaam.repository.UserRepository;

@Controller
public class ProductResolver {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public ProductResolver(ProductRepository productRepo, UserRepository userRepo, CategoryRepository categoryRepo){
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @QueryMapping
    public List<Product> allProductsSorted(){
        return productRepo.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId){
    	return productRepo.findByCategory_Id(categoryId);
    }

    @MutationMapping
    public Product createProduct(@Argument String title,
                                 @Argument Integer quantity,
                                 @Argument String description,
                                 @Argument Float price,
                                 @Argument Long userId,
                                 @Argument Long categoryId){
        Optional<User> u = userRepo.findById(userId);
        Optional<Category> c = categoryRepo.findById(categoryId);
        if(u.isPresent() && c.isPresent()){
            Product p = new Product();
            p.setTitle(title); p.setQuantity(quantity); p.setDescription(description);
            p.setPrice(BigDecimal.valueOf(price)); p.setUser(u.get()); p.setCategory(c.get());
            return productRepo.save(p);
        }
        return null;
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id,
                                 @Argument String title,
                                 @Argument Integer quantity,
                                 @Argument String description,
                                 @Argument Float price){
        Optional<Product> p = productRepo.findById(id);
        if(p.isPresent()){
            Product prod = p.get();
            prod.setTitle(title); prod.setQuantity(quantity); prod.setDescription(description);
            prod.setPrice(BigDecimal.valueOf(price));
            return productRepo.save(prod);
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id){
        if(productRepo.existsById(id)){ productRepo.deleteById(id); return true; }
        return false;
    }
}