package edu.poly.springshopbe.controller;

import edu.poly.springshopbe.domain.Category;
import edu.poly.springshopbe.dto.CategoryDto;
import edu.poly.springshopbe.service.CategoryService;
import edu.poly.springshopbe.service.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto dto,
                                            BindingResult result){
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFeilds(result);
        if(responseEntity != null){
            return responseEntity;
        }
        Category entity = new Category();
        BeanUtils.copyProperties(dto,entity);

        entity = categoryService.save(entity);

        dto.setId(entity.getId());

         return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory( @PathVariable("id") Long id,
                                             @RequestBody CategoryDto dto){
        Category entity = new Category();
        BeanUtils.copyProperties(dto,entity);

        entity = categoryService.update(id, entity);

        dto.setId(entity.getId());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<?> getAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllCategory(@PageableDefault(size = 5, sort="name", direction = Sort.Direction.ASC) Pageable pageable){

        return new ResponseEntity<>(categoryService.findAllWithPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getAllCategory(@PathVariable("id")  Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> DeleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("category with id "+id+" has been deleted", HttpStatus.OK);
    }
}
