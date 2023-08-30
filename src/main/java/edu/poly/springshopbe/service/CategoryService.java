package edu.poly.springshopbe.service;

import edu.poly.springshopbe.Exception.CategoryException;
import edu.poly.springshopbe.domain.Category;
import edu.poly.springshopbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository  caRepo;

    public Category  save(Category entity){
        return caRepo.save(entity);
    }

    public Category  update(long id, Category entity){
        Optional<Category> existed = caRepo.findById(id);

        if(existed.isEmpty()){
            throw new CategoryException("Catagory "+id+ " does not exist");
        }

        try{
            Category existedCategory = existed.get();
            existedCategory.setName(entity.getName());
            existedCategory.setStatus(entity.getStatus());

            return caRepo.save(existedCategory);
        }catch (Exception ex){
            throw  new CategoryException("Category is update fail");
        }
    }

    public List<Category> findAll(){
        return caRepo.findAll();
    }

    public Page<Category> findAllWithPage(Pageable pageable){
        return caRepo.findAll(pageable);
    }

    public Optional<Category> findById(Long id){
        Optional<Category> found = caRepo.findById(id);
        if(found.isEmpty()) {
            throw new CategoryException("Category with id "+id+" does not exist");
        }
        return found;
    }

    public void deleteCategoryById(Long id){
        Optional<Category> found = caRepo.findById(id);
        if(found.isEmpty()) {
            throw new CategoryException("Category with id "+id+" does not exist");
        }
        else {
            caRepo.deleteById(id);
        }
    }

}
