package imt.example.springshopbe.service;

import imt.example.springshopbe.domain.Category;
import imt.example.springshopbe.exception.CategoryException;
import imt.example.springshopbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public  Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    public  Category update(Long id,Category entity) {
        Optional<Category> category = categoryRepository.findById(id) ;
        if(category.isEmpty()){
            throw new CategoryException("Categoy id "+id+"khong ton tai!");
        }
        try {
            Category category1 = category.get() ;
            category1.setName(entity.getName());
            category1.setStatus(entity.getStatus());
            return categoryRepository.save(category1);
        }catch (Exception e){
            throw new CategoryException("Loix");
        }

    }

    public List<Category> getAll(){
        return categoryRepository.findAll() ;
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category getById(Long id){
        Optional<Category> category = categoryRepository.findById(id) ;
        if(category.isEmpty()){
            throw  new  CategoryException("Categoy id "+id+"khong ton tai!");
        }
        return category.get();
    }

    public void deleteById(Long id){
        Category category = getById(id) ;
        categoryRepository.delete(category);
    }

}
