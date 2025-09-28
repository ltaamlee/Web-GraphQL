package ltaam.resolver;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ltaam.entity.Category;
import ltaam.repository.CategoryRepository;

@Controller
public class CategoryResolver {

    private final CategoryRepository categoryRepo;

    public CategoryResolver(CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    @QueryMapping
    public List<Category> allCategories(){
        return categoryRepo.findAll();
    }

    @MutationMapping
    public Category createCategory(@Argument String name, @Argument String images){
        Category c = new Category();
        c.setName(name); c.setImages(images);
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument String name, @Argument String images){
        Optional<Category> c = categoryRepo.findById(id);
        if(c.isPresent()){
            Category cat = c.get();
            cat.setName(name); cat.setImages(images);
            return categoryRepo.save(cat);
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id){
        if(categoryRepo.existsById(id)){ categoryRepo.deleteById(id); return true; }
        return false;
    }
}